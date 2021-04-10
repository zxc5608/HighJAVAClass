package kr.or.ddit.board.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.board.vo.JDBCboardVO;
import kr.or.ddit.util.DBUtil3;

public class JdbcBoardDaoImpl implements IJDBCBoardDao{

	private static JdbcBoardDaoImpl dao;
	
	private JdbcBoardDaoImpl() {
		
	}
	
	
	public static JdbcBoardDaoImpl getInstance() {
		if(dao==null)dao = new JdbcBoardDaoImpl();
		return dao;
	}
	
	//DB작업에 필요한 객체 변수선언
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	//사용한 자원을 반납하는 메서드
	private void disconnect() {
		if(rs!=null)try {rs.close();}catch(SQLException e) {}
		if(stmt!=null)try {stmt.close();}catch(SQLException e) {}
		if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
		if(conn!=null)try {conn.close();}catch(SQLException e) {}
		
	}
	
	
	@Override
	public int insertBoard(JDBCboardVO boardVo) {
		int cnt= 0;
		
		try {
			conn= DBUtil3.getConnection();
			
			String sql="insert into jdbc_board(board_no, board_title, board_writer, board_date, board_cnt, board_content)"
					+" values(board_seq.nextval,?,?,sysdate,0,?)";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, boardVo.getBoard_title());
			pstmt.setString(2, boardVo.getBoard_writer());
			pstmt.setString(3, boardVo.getBoard_content());
			
			cnt= pstmt.executeUpdate();
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int cnt =0;
		try {
			conn= DBUtil3.getConnection();
			String sql = "delete from jdbc_board where board_no= ? ";
			pstmt=conn .prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			cnt =pstmt.executeUpdate();
			
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return cnt;
	}

	@Override
	public int updateBoard(JDBCboardVO boardVo) {
		int cnt = 0;
		try {
			conn= DBUtil3.getConnection();
			
			String sql = "update jdbc_board set board_title=?,board_date=sysdate, board_content=? "
					+ "where board_no= ? ";
			pstmt= conn.prepareStatement(sql);
			
			pstmt.setString(1, boardVo.getBoard_title());
			pstmt.setString(2, boardVo.getBoard_content());
			pstmt.setInt(3,boardVo.getBoard_no());
			
			cnt= pstmt.executeUpdate();
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
			conn= DBUtil3.getConnection();
			String sql =" select board_no, board_title,board_writer,"
					+ " to_char(board_date,'yyyy-mm-dd') board_date,"
					+ " board_cnt,board_content "
					+ " from jdbc_board "
					+ " order by board_no desc";
			stmt= conn.createStatement();
			rs= stmt.executeQuery(sql);
			
			boardList= new ArrayList<>();
			while(rs.next()) {
				JDBCboardVO boardVo= new JDBCboardVO();
				
				boardVo.setBoard_no(rs.getInt("board_no"));
				boardVo.setBoard_title(rs.getString("board_title"));
				boardVo.setBoard_writer(rs.getString("board_writer"));
				boardVo.setBoard_date(rs.getString("board_date"));
				boardVo.setBoard_cnt(rs.getInt("board_cnt"));
				boardVo.setBoard_content(rs.getString("board_content"));
				
				boardList.add(boardVo);
				
				
				
			}
			
		} catch (SQLException e) {
			boardList=null;
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return boardList;
	}

	@Override
	public JDBCboardVO getBoard(int boardNo) {
		JDBCboardVO boardVo=null;
		
		try {
		
			conn= DBUtil3.getConnection();
			String sql =" select board_no, board_title,board_writer,"
					+ " to_char(board_date,'yyyy-mm-dd') board_date,"
					+ " board_cnt,board_content "
					+ " from jdbc_board "
					+ " where board_no = ?";
			
			pstmt= conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				boardVo= new JDBCboardVO();
				
				boardVo.setBoard_no(rs.getInt("board_no"));
				boardVo.setBoard_title(rs.getString("board_title"));
				boardVo.setBoard_writer(rs.getString("board_writer"));
				boardVo.setBoard_date(rs.getString("board_date"));
				boardVo.setBoard_cnt(rs.getInt("board_cnt"));
				boardVo.setBoard_content(rs.getString("board_content"));
			}
			
		} catch (SQLException e) {
			boardVo=null;
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return boardVo;
	}

	@Override
	public List<JDBCboardVO> getSearchBoardList(String title) {
		List<JDBCboardVO > boardList=null;
		try {
			conn= DBUtil3.getConnection();
			String sql ="select board_no, board_title,board_writer,"
					+ "to_char(board_date,'yyyy-mm-dd') board_date,"
					+ "board_cnt,board_content "
					+ "from jdbc_board "
					+ " where board_title like '%' || ? || '%'"
					+ " order by board_no desc";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, title);
			
			rs= pstmt.executeQuery();
			boardList= new ArrayList<>();
			while(rs.next()) {
				JDBCboardVO boardVo= new JDBCboardVO();
				
				boardVo.setBoard_no(rs.getInt("board_no"));
				boardVo.setBoard_title(rs.getString("board_title"));
				boardVo.setBoard_writer(rs.getString("board_writer"));
				boardVo.setBoard_date(rs.getString("board_date"));
				boardVo.setBoard_cnt(rs.getInt("board_cnt"));
				boardVo.setBoard_content(rs.getString("board_content"));
				
				boardList.add(boardVo);
			}
		} catch (SQLException e) {
			boardList=null;
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return boardList;
	}

	@Override
	public int setCountIncrement(int boardNo) {
		int cnt= 0;
		try {
			conn= DBUtil3.getConnection();
			
			String sql =" update jdbc_board set board_cnt= board_cnt+1"
					+ " where board_no = ?";
			pstmt= conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			cnt =pstmt.executeUpdate();
			
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}
	

}
