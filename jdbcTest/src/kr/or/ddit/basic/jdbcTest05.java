package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.crypto.spec.PSource;

import kr.or.ddit.util.DBUtil;

/*
 * MVC패턴에 대하여 조사 학습하기
 * 
 * 
	lprod테이블에 새로운 데이터 추가하기
	
	추가할 데이터중 lprod_gu와 lprod_nm은 직접 입력 받아서 처리하는데 
	입력받은 lprod_gu가 이미 등록되어 있으면 다시 입력받아서 처리한다.
	그리고,lprod_id값은 현재의 lprod_id값중 제일 큰값보가 1증가된 값으로한다
 */
public class jdbcTest05 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "yongyong", "java");

			conn= DBUtil.getConnection();
			
			
			
			// lprod_id값 구하기==> 현재의 lprod_id값 중에서 제일 큰 값보다 1크게 한다.
			String sql = "select nvl(max(lprod_id),0)from lprod";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			// rs의 값을 꺼내올때 rs.next를 써준다
			int maxNum = 0;

			if (rs.next()) {
				maxNum = rs.getInt(1); // 컬럼이용
				// maxNum=rs.getInt(maxNum); //알리아스이용
			}
			maxNum++;

			// 입력받은 lprod_gu가 이미등록된 데이터면 다시 입력받아서 처리한다.

			String gu; // 상품분류코드 (lprod_gu)가 저장될변수

			int count = 0; // 입력한 상품 분류 코드의 개수가 저장될변수

			do {
				System.out.println("상품분류코드 입력:");
				gu = sc.next();

				String sql2 = "select count(*) cnt from lprod where lprod_gu=?";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, gu);

				rs = pstmt.executeQuery(); // select문일 경우

				if (rs.next()) {
					count = rs.getInt("cnt");

				}
				if (count > 0) {
					System.out.println("입력한 상품분류코드" + gu + "는 이미 등록된 코드 입니다");
					System.out.println("다시입력하세요");

				}

			} while (count > 0);

			System.out.println("상품분류명 입력: ");

			String nm = sc.next();
			String sql3 = " insert into lprod(lprod_id, lprod_gu, lprod_nm)" + " values(?,?,?)";
			pstmt = conn.prepareStatement(sql3);

			pstmt.setInt(1, maxNum);
			pstmt.setString(2, gu);
			pstmt.setString(3, nm);

			int cnt = pstmt.executeUpdate();


			if (cnt > 0) {
				System.out.println("DB추가 성공!!");

			} else {
				System.out.println("전송실패");
			}

		} catch (SQLException e) {
			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}

		}

	}
}