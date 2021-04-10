package kr.or.ddit.test;

import java.util.ResourceBundle;

/*
 	ResourceBundle객체 == > 파일의 확장자가. '.properties'인 파일의 내용을 읽어와서
 			key값과 value 값을 분리해서 정보를 갖는 객체
  
 */
public class resourceBundleTest {

	public static void main(String[] args) {
		//ResourceBundle 객체를 이용하여 파일정보 읽어오기
		
		//ResourceBundle객체 생성하기 
		// 		==> 읽어올 파일을 지정할 때 '파일명'만 입력하고 확장자는  지정하지 않는다
		//		(이유: 이 객체는 항상 확장자가 .properties인 파일만 읽어오기 때문에...)
		
		ResourceBundle bundle= ResourceBundle.getBundle("dbinfo");

		//읽어온 정보 출력하기
		System.out.println("driver :"+ bundle.getString("driver"));
		System.out.println("url :"+ bundle.getString("url"));
		System.out.println("user :"+ bundle.getString("user"));
		System.out.println("pass :"+ bundle.getString("pass"));
		
		
	}

}
