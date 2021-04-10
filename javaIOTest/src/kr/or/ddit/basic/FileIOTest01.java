package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileIOTest01 {
	public static void main(String[] args) {
		//FileInputStream객체를 이용한 파일 내용읽기
		
		try {
			//읽어올 파일을 매개밧으로 받는 FileInputStream 객체 생성하기
			//방법1
			//FileInputStream fin = new FileInputStream("D:/D_Other/test.txt");
			
			//방법2
			File file= new File("d:/D_Other/test.txt");
			FileInputStream fin =new FileInputStream(file);
			
			int c; // 읽어온 데이터가 저장될 변수
			
			while((c=fin.read())!=-1) {
				//읽어온 데이터를 화면에 출력하기
				System.out.print((char)c);
			}
			
			
			fin.close(); //스트림 닫기
			
		} catch (IOException e) {
			System.out.println("입출력 오류입니다...");
			e.printStackTrace();
		}
		

	}

}
