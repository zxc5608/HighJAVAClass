package kr.or.ddit.room.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.room.vo.roomVO;
import kr.or.ddit.util.BuildedSqlMapClient;

public class roomDaoImpl implements IroomDao{

	private SqlMapClient smc;
	
	private static roomDaoImpl dao;
	
	private roomDaoImpl() {
		smc= BuildedSqlMapClient.getSqlMapClient();
		
	}
	
	public static roomDaoImpl getIntance() {
		if(dao==null)dao= new roomDaoImpl();
		return dao;
		
	}

	@Override
	public int checkin(roomVO rovo) {
		int cnt=0;
		try {
			cnt= smc.update("hotelr.checkin",rovo);
			
		}catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int checkout(int room_no) {
		int cnt=0;
		try {
			cnt= smc.delete("hotelr.checkout",room_no);
		}catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<roomVO> getAllList() {
		
		List<roomVO>roomList=null;
				
		try {
				
				roomList= smc.queryForList("hotelr,getroom");
			
		} catch (SQLException e) {
				roomList=null;
				e.printStackTrace();
				}
		return roomList;
	}
}
