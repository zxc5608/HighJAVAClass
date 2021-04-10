package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.Dao.IJDBCBoardDao;
import kr.or.ddit.board.Dao.JdbcBoardDaoImpl;
import kr.or.ddit.board.vo.JDBCboardVO;
import kr.or.ddit.test.resourceBundleTest;

public class JdbcBoardServiceImpl implements IJdbcBoardService{

	private IJDBCBoardDao dao;
	
	private static JdbcBoardServiceImpl service;
	
	private JdbcBoardServiceImpl() {
		dao =JdbcBoardDaoImpl.getInstance();
		
	}
	public static JdbcBoardServiceImpl getInstance() {
		if(service==null) service= new JdbcBoardServiceImpl();
		return service;
			
		
	}
	
	
	@Override
	public int insertBoard(JDBCboardVO boardVo) {
		// TODO Auto-generated method stub
		return dao.insertBoard(boardVo);
	}

	@Override
	public int deleteBoard(int boardNo) {
		// TODO Auto-generated method stub
		return dao.deleteBoard(boardNo);
	}

	@Override
	public int updateBoard(JDBCboardVO boardVo) {
		// TODO Auto-generated method stub
		return dao.updateBoard(boardVo);
	}

	@Override
	public List<JDBCboardVO> getAllBoardList() {
		// TODO Auto-generated method stub
		return dao.getAllBoardList();
	}

	@Override
	public JDBCboardVO getBoard(int boardNo) {
		int cnt= setCountIncrement(boardNo); //조회수를 증가시킨다
		if(cnt==0) { //조회수 증가가 실패했을때
			return null;
			
		}
		return dao.getBoard(boardNo);
	}

	@Override
	public List<JDBCboardVO> getSearchBoardList(String title) {
		// TODO Auto-generated method stub
		return dao.getSearchBoardList(title);
	}

	@Override
	public int setCountIncrement(int boardNo) {
		// TODO Auto-generated method stub
		return dao.setCountIncrement(boardNo);
	

}
}
