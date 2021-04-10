package kr.or.ddit.board.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.vo.JDBCboardVO;
import kr.or.ddit.util.BuildedSqlMapClient;

public class JdbcBoardDaoImpl implements IJDBCBoardDao{

	private SqlMapClient smc;
	
	private static JdbcBoardDaoImpl dao;
	
	private JdbcBoardDaoImpl() {
		smc= BuildedSqlMapClient.getSqlMapClient();
	}
	
	
	public static JdbcBoardDaoImpl getInstance() {
		if(dao==null)dao = new JdbcBoardDaoImpl();
		return dao;
	}
	


	
	
	@Override
	public int insertBoard(JDBCboardVO boardVo) {
		int cnt= 0;
		
		try {
			
			Object obj = smc.insert("jdbc_board.insertboard",boardVo);
			
			if(obj==null)cnt=1;
			
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int cnt =0;
		try {
		
			cnt= smc.delete("jdbc_board.deleteboard",boardNo);
			
			
			
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateBoard(JDBCboardVO boardVo) {
		int cnt = 0;
		try {
			cnt= smc.update("jdbc_board.updateboard",boardVo);
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<JDBCboardVO> getAllBoardList() {
		
		List<JDBCboardVO> boardList= null;
		
		try {
		
		boardList= smc.queryForList("jdbc_board.selectboard");
			
			
		} catch (SQLException e) {
			boardList=null;
			e.printStackTrace();
		}
		
		return boardList;
	}

	@Override
	public JDBCboardVO getBoard(int boardNo) {
		JDBCboardVO boardVo=null;
		
		try {
		
			boardVo= (JDBCboardVO) smc.queryForObject("jdbc_board.getboard",boardNo);
			
		} catch (SQLException e) {
			boardVo=null;
			e.printStackTrace();
		}
		return boardVo;
	}

	@Override
	public List<JDBCboardVO> getSearchBoardList(String title) {
		List<JDBCboardVO > boardList=null;
		try {
			
			boardList= smc.queryForList("jdbc_board.searchboard",title);
			
		} catch (SQLException e) {
			boardList=null;
			e.printStackTrace();
		}
		return boardList;
	}

	@Override
	public int setCountIncrement(int boardNo) {
		int cnt= 0;
		try {
			cnt=smc.update("jdbc_board.setcount",boardNo);
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}
	

}
