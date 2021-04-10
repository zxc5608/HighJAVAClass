package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.print.DocFlavor.INPUT_STREAM;
import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil2;
import kr.or.ddit.util.DBUtil3;

/*
   	회원을 관리하는 프로그램 작성하기
   	(DB시스템의 MYMEMBER테이블 이용)
   	
   	-처리조건
   	1.아래메뉴의 기능을 모두 구현한다.(CRUD 구현하기)
   	2.'자료추가'에서는 입력한 회원ID가 중복되는지 여부를 검사해서 중복되면 다시입력 받도록 한다.
   	3.'자료삭제'는 회원 ID를 입력 받아 삭제한다.
   	4.'자료수정'은 회원 ID를 제외한 전체자료를 수정한다.
   	
   	메뉴예시)
   		-- 작업선택 --
   		1.자료 추가				-->insert (c)
   		2.자료 삭제				-->delete (d)
   		3.자료 수정				-->update (u)
   		4.전체 자료 출력			-->select (R)
   		0.작업 끝
   		-----------
   		작업번호>>
   		
 */
public class JdbcTest06 {
	private Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		new JdbcTest06().memberStart();
	}
	
	public void memberStart() {
		while(true) {
			int choice = displayMenu();
			switch(choice) {
				case 1 : insertMember();			// 추가
					break;
				case 2 : deleteMember();			// 삭제
					break;
				case 3 : updateMember();			// 수정
					break;
				case 4 : displayMember();			// 전체 출력
					break;
				case 0 :
					System.out.println();
					System.out.println("프로그램을 종료합니다.");
					return;
				default :
					System.out.println("잘못 선택했습니다. 다시 입력하세요.");
					System.out.println();
			}
		}
	}
	
	private int displayMenu() {
		System.out.println();
		System.out.println("  -- 작업 선택 --");
		System.out.println(" 1. 자료 추가");
		System.out.println(" 2. 자료 삭제");
		System.out.println(" 3. 자료 수정");
		System.out.println(" 4. 전체 자료 출력");
		System.out.println(" 0. 작업 끝.");
		System.out.println("----------------");
		System.out.print(" 작업 선택 >> ");
		
		return scan.nextInt();
	}
	
	// 회원 정보를 수정하는 메서드
	private void updateMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.print("수정할 회원 ID >> ");
		String memId = scan.next();
		
		int count = getMemberCount(memId);
		
		if(count==0) {
			System.out.println(memId + "는 없는 회원 ID 입니다.");
			System.out.println("수정 작업 종료");
			return;
		}
		
		System.out.print("새로운 회원 이름 : ");
		String memName = scan.next();
		
		System.out.print("새로운 전화번호 : ");
		String memTel = scan.next();
		
		scan.nextLine();
		System.out.print("새로운 회원 주소 : ");
		String memAddr = scan.nextLine();
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "update mymember set mem_name = ? , mem_tel = ?, "
					+ "mem_addr = ? where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memName);
			pstmt.setString(2, memTel);
			pstmt.setString(3, memAddr);
			pstmt.setString(4, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("update 작업 성공~~~");
			}else {
				System.out.println("수정 작업 실패!!!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close(); }catch(SQLException e) {}
			if(conn!=null) try { conn.close(); }catch(SQLException e) {}
		}
	}
	
	
	// 회원 정보를 삭제하는 메서드
	private void deleteMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요.");
		System.out.print("삭제할 회원 ID >> ");
		String memId = scan.next();
		
		int count = getMemberCount(memId);
		if(count==0) {
			System.out.println(memId + "는 없는 회원 ID 입니다.");
			System.out.println("삭제 작업 종료");
			return;
		}
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "delete from mymember where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("삭제 작업 성공!!!");
			}else {
				System.out.println("삭제 작업 실패~~~");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close(); }catch(SQLException e) {}
			if(conn!=null) try { conn.close(); }catch(SQLException e) {}
		}
		
	}
	
	
	// 회원 정보를 추가하는 메서드
	private void insertMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요.");
		int count = 0;
		
		String memId = null;
		do {
			System.out.print("회원 ID : ");
			memId = scan.next();
			
			count = getMemberCount(memId);
			
			if(count>0) {
				System.out.println(memId + "은(는) 이미 등록된 ID입니다.");
				System.out.println("다른 회원 ID를 입력하세요.");
				System.out.println();
			}
			
		}while(count>0);
		
		System.out.print("회원 이름 : ");
		String memName = scan.next();
		
		System.out.print("전화번호 : ");
		String memTel = scan.next();
		
		scan.nextLine();  // 입력 버퍼 비우기
		System.out.print("회원 주소 : ");
		String memAddr = scan.nextLine();
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "insert into mymember (mem_id, mem_name, mem_tel, mem_addr) "
					+ " values (?, ?, ?, ?) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println(memId + "회원 정보 추가 성공!!");
			}else {
				System.out.println("추가 작업 실패~~~~");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close(); }catch(SQLException e) {}
			if(conn!=null) try { conn.close(); }catch(SQLException e) {}
		}
		
	}
	
	// 매개변수로 회원ID를 받아서 해당 회원ID의 개수를 반환하는 메서드
	private int getMemberCount(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;   // 회원ID의 개수가 저장될 변수
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select count(*) cnt from mymember where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
			
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		} finally {
			if(rs!=null) try { rs.close(); }catch(SQLException e) {}
			if(pstmt!=null) try { pstmt.close(); }catch(SQLException e) {}
			if(conn!=null) try { conn.close(); }catch(SQLException e) {}
		}
		
		return count;
	}
	
	// 전체 회원 정보를 출력하는 메서드
	private void displayMember() {
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println(" ID    이름       전화번호         주소");
		System.out.println("--------------------------------------");
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
//			conn = DBUtil.getConnection();
			conn = DBUtil2.getConnection();
			
			String sql = "select * from mymember";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				
				System.out.println(memId + "\t" + memName + 
						"\t" + memTel + "\t" + memAddr);
			}
			System.out.println("--------------------------------------");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try { rs.close(); }catch(SQLException e) {}
			if(stmt!=null) try { stmt.close(); }catch(SQLException e) {}
			if(conn!=null) try { conn.close(); }catch(SQLException e) {}
		}
		
	}

}








