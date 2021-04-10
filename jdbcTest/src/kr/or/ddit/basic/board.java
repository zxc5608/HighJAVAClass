package kr.or.ddit.basic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.omg.Messaging.SyncScopeHelper;

import kr.or.ddit.util.DBUtil3;

public class board {
	Scanner sc = new Scanner(System.in);
	
	
	Connection conn= null;
	Statement stmt= null;
	PreparedStatement pstmt = null;
	ResultSet rs= null;
	
	
	public static void main(String[] args) {
		 new board().start();
		
	}
	
	
	private void start() {
		while(true) {
			int input= display();
			
			switch (input) {
			case 1:
				insert();
				break;
			case 2:
				select();
				break;
			case 3:
				reserch();
				break;
			case 0:
				System.out.println("작업을 종료합니다");
				System.exit(0);
				
		
			}
			
		}
	}


	private void select() {
		System.out.println("원하는 게시물 조회 번호:");
		int num =sc.nextInt();
		System.out.println();
		
		System.out.println(num+"번 내용");
		System.out.println("-------------------------");
		
		try {
			conn= DBUtil3.getConnection();
			String sql ="UPDATE jdbc_board set board_cnt = nvl(board_cnt,0)+1 where board_no=?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			int cnt = pstmt.executeUpdate();
			
			String sql1= "SELECT * FROM jdbc_board WHERE board_no = ?";
			pstmt=conn.prepareStatement(sql1);
			pstmt.setInt(1, num);
			
			rs= pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.println("제목"+rs.getString("board_title"));
				System.out.println("작성자"+rs.getString("board_writer"));
				System.out.println("내용"+rs.getString("board_content"));
				System.out.println("작성일"+rs.getString("board_date"));
				System.out.println("조회수"+rs.getString("board_cnt"));
				

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)try {rs.close();}catch(SQLException e) {} 
			if(stmt!=null)try {stmt.close();}catch (SQLException e) {}
			if(conn!=null) try { conn.close(); }catch(SQLException e) {}
		}
		System.out.println("----------------------------------------");
		System.out.println("메뉴 : 1.수정\t2.삭제\t3.리스트로 가기");
		System.out.print("작업선택 : ");
		int input = sc.nextInt();
		
		switch (input) {
		case 1:
			update(num);
			break;
		case 2:
			delete(num);
			break;
		case 3:
			
			return;
		}
	}
	private void reserch() {
		System.out.println("검색 작업");
		System.out.println("----------------------------------------");
		System.out.print("검색할 제목 입력 : ");
		String title = sc.next();
		System.out.println();
		
		System.out.println("----------------------------------------");
		System.out.println("No\t제목\t작성자\t조회수");
		System.out.println("----------------------------------------");
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "select * from jdbc_board where board_title = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (title != null) {
					System.out.println(rs.getInt("board_no") + "\t" + rs.getString("board_title") + "\t"
							+ rs.getString("board_writer") + "\t" + rs.getInt("board_cnt"));
				}
				System.out.println("----------------------------------------");
				System.out.println("메뉴 : 1.새글작성\t2.게시글보기\t3.검색\t0.작업끝");
				System.out.print("작업선택 : ");
				int input = sc.nextInt();

				switch (input) {
				case 1:
					insert();
					break;
				case 2:
					select();
					break;
				case 3:
					reserch();
					break;
				case 0:
					System.out.println("작업을 종료합니당");
					System.exit(0);
				}

			} else {
				System.out.println("입력한 제목이 없습니다.");
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try { rs.close(); }catch(SQLException e) {}
			if(pstmt!=null) try { pstmt.close(); }catch(SQLException e) {}
			if(conn!=null) try { conn.close(); }catch(SQLException e) {}
		}
		System.out.println("----------------------------------------");
		
	}

		
	


	private void update(int num) {
		System.out.println("수정 작업하기");
		System.out.println("-------------------------------");
		System.out.print("제목 수정 : ");
		String title = sc.next();
		sc.nextLine();
		System.out.print("내용 수정 : ");
		String con = sc.nextLine();
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "update jdbc_board set board_title = ?, board_content = ? where board_no =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, con);
			pstmt.setInt(3, num);
			
			pstmt.executeUpdate();
			System.out.println(num + "번글이 수정되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) try { pstmt.close(); }catch(SQLException e) {}
			if(conn!=null) try { conn.close(); }catch(SQLException e) {}
		}
		
	}

	private void delete(int num) {
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "delete from jdbc_board where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
			System.out.println(num + "번글이 삭제되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) try { pstmt.close(); }catch(SQLException e) {}
			if(conn!=null) try { conn.close(); }catch(SQLException e) {}
		}
		
	}


	private int display() {
		System.out.println("--------------------------------");
		System.out.println("NO \t제목\t작성자\t조회수");
		try {
			conn= DBUtil3.getConnection();
			
			
			String sql =" SELECT * FROM jdbc_board"; 
			stmt= conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
			System.out.println( rs.getInt(1)+"\t"
						+rs.getString(2)+"\t"
						+rs.getString(3)+"\t"
						+rs.getString(5)+"\t");
			
			}
		}catch (SQLException e) {
	
			e.printStackTrace();
		}
		
		
		finally {
			if(rs!=null)try {rs.close();}catch(SQLException e) {} 
			if(stmt!=null)try {stmt.close();}catch (SQLException e) {}
			if(conn!=null) try { conn.close(); }catch(SQLException e) {}
		}
		
		System.out.println("-------------------------------");
		System.out.println("메뉴: 1.새글작성 /t2게시글보기/t3.검색/t0.작업끝");
		System.out.println("작업선택>");
		
		return sc.nextInt();
	}


	private void insert() {
		
		System.out.println("추가할 회원정보를 입력하세요");
		
		System.out.println("제목");
		String title = sc.next();
		System.out.print("작성자 : ");
		String writer = sc.next();
		sc.nextLine();
		System.out.println("내용 : ");
		String con = sc.nextLine();
		
		try {
			
			conn = DBUtil3.getConnection();
			
			String sql= "INSERT into JDBC_BOARD values (board_seq.nextval, ?, ?, sysdate, 0, ?)";
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, con);
			
			int cnt= pstmt.executeUpdate();
			
			System.out.println(cnt+"개의 게시물이 등록되었습니다.");
			
		} catch (SQLException e) {
			// TODO: handle exception
		}finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
		
	}
}
