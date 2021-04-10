package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class jdbcTest04 {

	//은행계좌 번호 정보를 추가하는 예제 
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		//selct문이 안쓰이면  리절트를 쓰지않는다
		
		Connection conn= null;
		Statement stmt= null;
		PreparedStatement pstmt= null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn= DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"yongyong",
					"java"
					);
			System.out.println("계좌번호 정보 추가하기");
			
			System.out.println("계좌번호:");
			String bankno=sc.next();
			
			System.out.println("은 행 명 :");
			String bankName=sc.next();
			
			System.out.println("예금주명:");
			String userName= sc.next();
			
			/*	
			//statement객체를 이용하여 추가하기
			
//			insert into bankinfo (bank_no,bank_name,bank_user_name, bank_date)
//			values('123-456-54','하나은행','홍길동',sysdate);
					
			String sql = " insert into bankinfo "
					+ " (bank_no,bank_name,bank_user_name, bank_date)"
					+" values('"+bankno+"', '"+ bankName+"', '"+ userName+"',sysdate)";
			System.out.println(sql); //쿼리문이 잘만들어졋나확인
			System.out.println();
			
			stmt=conn.createStatement();
			
			//SQL문이 select문일 경우에는 executeQuery()메서드를 사용했는데
			//SQL문이 select 문이 아닐 경우에는 executeUpdate()메서드를 사용한다
			
			//executeUpdate()메서드의 반환값==> 해당작업에 성공한 레코드수
			int cnt= stmt.executeUpdate(sql);
			*/
		//----------------------------------------------------------
		
		//preparedStatment 객체를 이용하여추가하기
		//==> SQL문에서 데이터가 들어갈 자리를 물음표(?)로 표시해서 작성한다
			
			String sql = " insert into bankinfo "
					+ " (bank_no,bank_name,bank_user_name, bank_date)"
					+" values( ?,?,?,sysdate)";
		
		//preparedStatment 객체 생성하기
			//==> 객체를 생성할때 처리할 SQL문을 넣어준다. 
			
			pstmt=conn.prepareStatement(sql);
			
		//SQL문의 물음표(?)자리에 들어갈 데이터를 세팅한다.
		//형식) pstmt.set자료형 이름(물음표 순번, 셋팅할 데이터)
		pstmt.setString(1,bankno);
		pstmt.setString(2,bankName);
		pstmt.setString(3,userName);
		
		//데이터셋팅이완료되면 SQL문을 실행하여 결과를 얻어온다.
		int cnt= pstmt.executeUpdate();
		
			
			System.out.println("반환값 cnt="+ cnt);
			
		} catch (SQLException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
		
	}

	}
}
