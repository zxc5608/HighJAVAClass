package kr.or.ddit.member.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.member.vo.MemberVO;

/**
 * 
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 작성해서 Service에 전달하는 DAO의 interface
 *
 */
public interface IMemberDao {
	
	
	/**
	 * MemberVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param memVo DB에 insert할 자료가 저장된 MemberVO객체
	 * @return insert 작업성공 :1 , 실패 : 0
	 */
	public int insertMember(MemberVO memVo);
	
	
	/**
	 * 회원 Id를 매개값으로 받아서 해당 회원 정보를 삭제하는 메서드
	 * @param memId 삭제할 회원Id
	 * @return 삭제성공 1 , 삭제실패 0
	 */
	public int deleteMember(String memId);
	
	
	/**
	 * MemberVO에 담겨진 정보를 이용하여 회원정보를 수정하는 메서드
	 * @param memVo 수정할 정보가 저장된 MemberVO객체
	 * @return 수정성공:1 수정실패 :0
	 * 	 
	 */
	public int updateMember(MemberVO memVo);
	
	
	/**
	 * DB의 회원테이블의 전체 레코드를 가져와서 List에 담아서 반환하는 메서드
	 * 
	 * @return MemberVO객체를 담고있는  List객체
	 */
	public List<MemberVO> getAllMemberList();
	
	
	/**
	 * 회원id를 매개값으로 받아서 해당회원의 개수를 반환하는 메서드
	 * @param memId 검색할 회원 ID
	 * @return 검색된 회원ID 개수
	 */
	public int getMemberCount(String memId);
	
	/**
	 * Map의 정보를 이용하여 회우너정보들중 원하는 컬럼을 수정하는메서드
	 * Map의 key값 : 회원 ID(memId), 변경할 컬럼명(field), 변경할 데이터(data)
	 * 
	 * @param paramMap 회원 ID,변경할 컬럼 , 변경할데이터가 저장된 Map객체 
	 * @return 수정성공 :1, 실패 : 0
	 */
	public int updateMember2(Map<String,String> paramMap);
	
	
	
}
