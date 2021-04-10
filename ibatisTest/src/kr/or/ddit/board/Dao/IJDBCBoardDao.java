package kr.or.ddit.board.Dao;

import java.util.List;

import kr.or.ddit.board.vo.JDBCboardVO;

public interface IJDBCBoardDao {
/**
 * jDBCBoardVO에 담겨진 자료를DB에 insert하는 메서드
 * 
 * @param boardVo DB에 insert할 자료가 저장될 jdbcBoardVo객체
 * @return 작업성공 1  실패 0
 */
	public int insertBoard(JDBCboardVO boardVo);
	
	/**
	 * 게시글 번호를 매개값으로 받아서 그게시글 정보를 삭제하는 메서드 
	 * @param boardNo 삭제할 게시글번호
	 * @return 작업성공 1 실패 0
	 */
	public int deleteBoard(int boardNo);
	
	
	/**
	 * 하나의 JDBCBoarVO자료를 이용하여 DB에 update하는 메서드
	 * @param boardVo update할 게시글 정보가 저장될 JDBCBoardVo객체
	 * @return 성공 1 실패0
	 */
	public int updateBoard(JDBCboardVO boardVo);
	
	/**
	 * 전체 게시글 정보를 가져와서  List에 담아서 변환하는 메서드
	 * @return JDBCBoardVO객체를 담고있는 List객체
	 * 
	 */
	public List<JDBCboardVO> getAllBoardList();
	
	
	/**
	 * 게시글 번호를 매개값으로 받아서 그 게시물정보의 내용을 가져와 반환하는 메서드
	 * @param boardNo 가져올 게시글번호
	 * @return 게시글 번호에 맞는 자료가 있으면 게시글 정보를 담고있는 JdbcBoardVO객체, 자료가 없으묜 null반환
	 */
	public JDBCboardVO getBoard(int boardNo);
	
	/**
	 * 게시글제목을 이용하여데이터를 검색하는 메서드
	 * @param title 검색할 게시글 제목
	 * @return
	 */
	public List<JDBCboardVO> getSearchBoardList(String title);
	
	/**
	 * 게시글 번호를 매개값으로 받아서 해당 자료의 조회수를 증가시키는 메서드
	 * @param boardNo 조회수를 증가할 게시글번호
	 * @return 작업성공 1 실패0
	 */
	public int setCountIncrement(int boardNo);
}
