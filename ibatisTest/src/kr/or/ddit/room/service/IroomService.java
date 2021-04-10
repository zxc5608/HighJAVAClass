package kr.or.ddit.room.service;

import java.util.List;

import kr.or.ddit.room.vo.roomVO;

public interface IroomService {
	
	public int checkin(roomVO rovo);
	
	public int checkout(int room_no);
	
	public List<roomVO>getAllList();
	
}
