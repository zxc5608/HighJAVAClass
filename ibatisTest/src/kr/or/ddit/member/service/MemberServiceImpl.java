package kr.or.ddit.member.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	
	private IMemberDao dao;
	
	private static MemberServiceImpl service;
	
	//생성자
	private MemberServiceImpl() {
		//dao= new MemberDaoImpl();
		dao = MemberDaoImpl.getInstance();
	}
	
	public static MemberServiceImpl getInstance(){
		if(service==null)service= new MemberServiceImpl();
		return service;
		
	}
	
	
	
	
	@Override
	public int insertMember(MemberVO memVo) {
		// TODO Auto-generated method stub
		return dao.insertMember(memVo);
	}

	@Override
	public int deleteMember(String memId) {
		// TODO Auto-generated method stub
		return dao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memVo) {
		// TODO Auto-generated method stub
		return dao.updateMember(memVo);
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		// TODO Auto-generated method stub
		return dao.getAllMemberList();
	}

	@Override
	public int getMemberCount(String memId) {
		// TODO Auto-generated method stub
		return dao.getMemberCount(memId);
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return dao.updateMember2(paramMap);
		
	}

}
