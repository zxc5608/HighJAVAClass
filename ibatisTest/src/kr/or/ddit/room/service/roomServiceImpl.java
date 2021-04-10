package kr.or.ddit.room.service;

import java.util.List;

import kr.or.ddit.member.main.MemberController;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.room.dao.IroomDao;
import kr.or.ddit.room.dao.roomDaoImpl;
import kr.or.ddit.room.vo.roomVO;

public class roomServiceImpl implements IroomService{

	private IroomDao dao;
	
	private static roomServiceImpl service;
	
	private roomServiceImpl() {
		dao= roomDaoImpl.getIntance();
		
	}
	public static roomServiceImpl getInstance() {
		if(service==null)service = new roomServiceImpl();
		return service;
	}
	
	@Override
	public int checkin(roomVO rovo) {
		// TODO Auto-generated method stub
		return dao.checkin(rovo);
	}
	@Override
	public int checkout(int room_no) {
		// TODO Auto-generated method stub
		return dao.checkout(room_no);
	}
	@Override
	public List<roomVO> getAllList() {
		// TODO Auto-generated method stub
		return dao.getAllList();
	}
}
