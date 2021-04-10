package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//문제1) 사용자로부터 lprod_id값을 입력 받아 입력한 값보다 lprod_id값이 큰 자료들을 출력하시오


public class JdbcTest02 {

	public static void main(String[] args) {
		
		Scanner sc= new Scanner(System.in);
		Connection conn= null;
		Statement stmt= null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		
		
		try {
			//1 드라이버 로딩 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. DB연결 ==> Connection 객체생성
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"yongyong",
					"java");  
			
			System.out.println("lprod값 입력:");
			String n1 = sc.nextLine();
			/*
			String sql = "select *from lprod where lprod_id >"+ n1;
					
			
			stmt= conn.createStatement();
			
			
			rs=stmt.executeQuery(sql);
			*/
			String sql = "select * from lprod where lprod_id > ?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, n1);
			
			rs=pstmt.executeQuery();
			
			
			System.out.println("== 처리 결과 출력 ==");
			
				/*
				  rs.next() ==> ResultSet 객체의 데이터를 가리키는 포인터를  다음 레코드 자리로 이동시키고
				  				그곳에 데이터가 있으면 true, 없으면 false를 반환한다.
				  				
				 */
			while(rs.next()) {
			
	
				System.out.println("Lprod_id:"+rs.getInt("lprod_id")); 
				System.out.println("Lprod_gu:"+rs.getString(2));
				System.out.println("Lprod_nm:"+rs.getString("lprod_nm"));
				System.out.println("------------------------------------");
				
			}
			
			System.out.println("전체자료 출력끝...");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//6. 사용 했던 자원 반납 하기
			if(rs!=null)try {rs.close();}catch(SQLException e) {}
			if(stmt!=null)try {stmt.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
		}
			
			
			
			
			
			
		}
	

}
