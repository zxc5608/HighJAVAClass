package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import kr.or.ddit.test.resourceBundleTest;

//JDBC드라이버를 로딩하고, Connection객체를 생성하는 메서드로 구성된 class

//ResourceBundle 객체 이용하기 
public class DBUtil3 {
	
	static ResourceBundle bundle;
	
	//초기화블록
	static {
		bundle= ResourceBundle.getBundle("dbinfo");
		
		
		try {
		//	Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(bundle.getString("driver"));
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		try {
			//return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","yongyong","java");
			return DriverManager.getConnection(
					bundle.getString("url"),
					bundle.getString("user"),
					bundle.getString("pass"));
			
		} catch (SQLException e) {
			System.out.println("DB시스템 연결실패!");
			return null;
		}
	}
}
