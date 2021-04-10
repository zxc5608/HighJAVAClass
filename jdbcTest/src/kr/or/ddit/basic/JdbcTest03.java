package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//문제2)lprod_id값을 2개를 차례로 입력 받아서 두 값중 작은 값 부터 큰값 사이의 자료들을 출력하시오.

public class JdbcTest03 {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		
		System.out.println("첫번째 값입력");
		int n1 = sc.nextInt();
		
		System.out.println("두번째 값입력");
		int n2= sc.nextInt();

		int max, min;
		if (n1 > n2) {
			max = n1;
			min = n2;
		} else {
			max = n2;
			min = n1;
		}
		
//		max=Math.max(n1, n2);
//		min=Math.min(n1, n2);
//		이런 방법이있다.
		
		Connection conn= null;
		Statement stmt= null;
		ResultSet rs= null;
		
		try {
			//1 드라이버 로딩 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. DB연결 ==> Connection 객체생성
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"yongyong",
					"java");  //db에 연결이되면 커넥션객체가 연결이됨
			//3-1.실행할 SQL문 작성
			
			
			String sql = "select *from lprod " 
						
					//+ " where lprod_id >=" + min + "and lprod_id<=" + max;
					+ " where lprod_id between" + min + "and" + max;
					//between으로 가능하당
			
			//3-2.Statement객체 생성 ==> Connection 객체를 이용한다.
			stmt= conn.createStatement();  //커넥션 객체를 만들어주는 클래스
			
			//4. SQL문을 DB서버로 전송해서 실행하고 결과를 얻어온다.
			//(지금은 실행할 SQL문이 select문이기 때문에 결과가 ResultSet 객체에 저장되어 반환된다.)
			rs=stmt.executeQuery(sql);
			
			//5.결과처리하기 ==> 한 레코드씩 화면에 출력하기
			//		==> ResultSet의 저장되어있는 데이터를 차례로 꺼내오려면 반복문과 next()메서드를 이용한다.
			System.out.println("== 처리 결과 출력 ==");
			
				/*
				  rs.next() ==> ResultSet 객체의 데이터를 가리키는 포인터를  다음 레코드 자리로 이동시키고
				  				그곳에 데이터가 있으면 true, 없으면 false를 반환한다.
				  				
				 */
			while(rs.next()) {
				//포인터가 가리키는 곳의 데이터를 가져오는 방법
				//형식1) rs.get자료형 이름 ("컬럼명")
				//형식2) rs.get자료형 이름(컬럼번호) ==> 컬럼번호는 1부터시작
				//형식3) rs.get자료형 이름("컬럼의 alias명")
					
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
		}
			
			
			
			
			
			
		}
	


	}


