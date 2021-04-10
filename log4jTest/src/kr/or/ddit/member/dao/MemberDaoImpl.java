package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.DBUtil3;

public class MemberDaoImpl implements IMemberDao {
	private static final Logger logger =Logger.getLogger(MemberDaoImpl.class);
	//싱글톤패턴으로 만들어보자
	//1번
	private static MemberDaoImpl dao;
	//2번
	private MemberDaoImpl() {
		
	}
	//3번 
	public static MemberDaoImpl getInstance() {
		if(dao==null)dao= new MemberDaoImpl();
		return dao;
	}
	

	@Override
	public int insertMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int cnt =0; //반환값이 저장될 변수 (작업성공 :1, 실패 0)
		
		try {
			conn = DBUtil3.getConnection();
			
			logger.info("Connection객체 생성...");
			
			String sql = "insert into mymember (mem_id, mem_name, mem_tel, mem_addr) "
					+ " values (?, ?, ?, ?) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memVo.getMem_id());      //vo에있는거를 꺼내온다
			pstmt.setString(2, memVo.getMem_name());
			pstmt.setString(3, memVo.getMem_tel());
			pstmt.setString(4, memVo.getMem_addr());
			logger.info("PreparedStatment객체생성");
			logger.info("실행 sql문"+sql);
			logger.info("사용 데이터: ["+memVo.getMem_id()+","+memVo.getMem_name()+","+memVo.getMem_tel()+","+memVo.getMem_addr()+"]");
			
			cnt = pstmt.executeUpdate();
			logger.info("SQL문 실행 성공");
			
		} catch (SQLException e) {
			logger.error("sql문 실행 실패!"+e);
			cnt=0;
			e.printStackTrace();
		}finally {
			if(pstmt!=null) try { pstmt.close();logger.info("preparedStatement객체 반납...."); }catch(SQLException e) {}
			if(conn!=null) try { conn.close();logger.info("Connection객체 반납.."); }catch(SQLException e) {}
		
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int cnt =0;
	
		
		try {
			conn=DBUtil3.getConnection();
			
			logger.info("Connection객체 생성...");
			String sql = "delete from mymember where mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memId);
			logger.info("prepareStatement객체생성");
			logger.info("실행 sql문"+sql);
			logger.info("사용데이터"+ memId);
			
			cnt = pstmt.executeUpdate();
			logger.info("sql문 실행성공");
		} catch (SQLException e) {
			logger.error("sql문 실행 실패!");
			cnt=0;
			e.printStackTrace();
			
		}finally {
			if(pstmt!=null) try { pstmt.close();logger.info("pstmt객체반납"); }catch(SQLException e) {}
			if(conn!=null) try { conn.close();logger.info("pstmt객체반납"); }catch(SQLException e) {}
		}
		
		
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int cnt=0;
		
		try {
			conn=DBUtil3.getConnection();
			logger.info("Connection객체 생성...");

			String sql = "update mymember set mem_name = ? , mem_tel = ?, "
					+ "mem_addr = ? where mem_id = ? ";
			pstmt= conn.prepareStatement(sql);
			
			pstmt.setString(1, memVo.getMem_name());
			pstmt.setString(2, memVo.getMem_tel());
			pstmt.setString(3, memVo.getMem_addr());
			pstmt.setString(4, memVo.getMem_id());
			logger.info("preparestatement객체 생성...");
			logger.info("실행 sql문"+ sql);
			logger.info("사용된 데이터 ["+memVo.getMem_name()+","+memVo.getMem_tel()+","
									+memVo.getMem_addr()+","+memVo.getMem_id()+"]");
			cnt = pstmt.executeUpdate();
			logger.info("sql문 실행성공");
		} catch (SQLException e) {
			logger.error("sql문 실행실패");
			cnt=0;
			e.printStackTrace();
		}finally {
			if(pstmt!=null) try { pstmt.close(); logger.info("pstmt객체반납");}catch(SQLException e) {}
			if(conn!=null) try { conn.close(); logger.info("pstmt객체반납");}catch(SQLException e) {}
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		List<MemberVO> memList =null; //MemberVO 객체가 저장될 List객체 변수선언
		try {
			conn= DBUtil3.getConnection();
			logger.info("connection객체 불러오기");
			String sql = "select * from mymember";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			logger.info("statement 객체 생성");
			logger.info("sql문 실행성공"+sql);
			
			memList= new ArrayList<>(); //List객체 생성
			logger.info("list객체생성");
			
			while(rs.next()) {
				MemberVO memVo = new MemberVO(); // MemberVo객체 생성
				//ResultSet객체의 데이터를 가져와 MemberVO객체에 넣는다.
				memVo.setMem_id(rs.getString("mem_id"));
				memVo.setMem_name(rs.getString("mem_name"));
				memVo.setMem_tel(rs.getString("mem_tel"));
				memVo.setMem_addr(rs.getString("mem_addr"));
				
				logger.info("사용된 데이터 ["+memVo.getMem_name()+","+memVo.getMem_tel()+","
						+memVo.getMem_addr()+","+memVo.getMem_id()+"]");
				memList.add(memVo);
				
			}
			logger.info("memberVO객체생성");
			
		} catch (SQLException e) {
			logger.error("sql문 실행실패");
			memList= null;
			e.printStackTrace();
		}finally {
			if(rs!=null) try { rs.close(); logger.info("rs객체반납");}catch(SQLException e) {}
			if(stmt!=null) try { stmt.close(); logger.info("stmt객체반납");}catch(SQLException e) {}
			if(conn!=null) try { conn.close(); logger.info("conn객체반납");}catch(SQLException e) {}
		}
		
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		 
		int count = 0; 
		try {
		conn= DBUtil3.getConnection();
		logger.info(" conn객체생성");
		String sql = "select count(*) cnt from mymember where mem_id=?";
		
		pstmt= conn.prepareStatement(sql);
		
		pstmt.setString(1, memId);
		
		logger.info("사용한 sql"+sql);
		logger.info(" pstmt객체생성");
		rs=pstmt.executeQuery();
		logger.info(" rs객체생성");


		if(rs.next()) {
			count = rs.getInt("cnt");
			logger.info(" 실행성공");
		}
		
		} catch (Exception e) {
			count = 0;
			e.printStackTrace();
			
		}finally {
			if(pstmt!=null) try { pstmt.close(); logger.info("pstmt객체반납");}catch(SQLException e) {}
			if(conn!=null) try { conn.close(); logger.info("conn객체반납");}catch(SQLException e) {}
		}
		
		return count;
	}
	
	
	@Override
	public int updateMember2(Map<String, String> paramMap) {
		//key값 ==> 회원 Id (memId) ,변경할컬럼 (field),변경할 데이터(data)
		Connection conn= null;
		PreparedStatement pstmt= null;
		int cnt=0;
		
		try {
			conn= DBUtil3.getConnection();
			logger.info(" conn객체생성");
			String sql = "update mymember set " + paramMap.get("field") +" = ? where mem_id = ? ";
			
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, paramMap.get("data"));
			pstmt.setString(2, paramMap.get("memId"));
			logger.info("사용한 sql"+sql);
			logger.info(" pstmt객체생성");
			
			cnt= pstmt.executeUpdate();
			
			logger.info(" 실행성공");
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}finally {
			if(pstmt!=null) try { pstmt.close();  logger.info("pstmt객체반납");}catch(SQLException e) {}
			if(conn!=null) try { conn.close(); logger.info("conn객체반납"); }catch(SQLException e) {}
		}
		
		return cnt;
	}

	
}
