package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest05 {
	//한글이저장된 파일 읽어오기
	//(한글의 인코딩을 지정해서 읽어 오는 예제)
	public static void main(String[] args) {
		 try {
			//FileReader fr =new FileReader("d:/D_Other/test_ansi.txt");
			//FileReader fr =new FileReader("d:/D_Other/test_utf8.txt");
			 
			 //파일을 읽어올때나 저장할때 인코딩 방식을 지원하는 스트림
			 //입력용: InputStreamReader
			 //출력용 : OutputStreamReader
			 
			 //byte 기반의 파일 입력용 스트림 객체생성
			 FileInputStream fin = 
					 new FileInputStream("d:/D_Other/test_ansi.txt");
			 		 //new FileInputStream("d:/D_Other/test_utf8.txt");
			 //기본 인코딩 방식으로 처리한다 ==> 인코딩 방식을 별도로 지정하지 않은경우
			 //InputStreamReader isr =new InputStreamReader(fin);
			 
			 //인코딩 방싱을 지정해서 읽어오기
			 //인코딩 방식 예시
			 // -MS949 ==> 윈도우의 기본한글 인코딩 방식 (ANSI방식과같다)
			 // -UTF-8 ==> 유니코드UTF -8 인코딩방식
			 // -US-ASCII ==> 영문전용 인코딩방식
			 //InputStreamReader isr =new InputStreamReader(fin,"ms949");
			 InputStreamReader isr =new InputStreamReader(fin,"utf-8");
			 
			
			int c;
			/*
			while((c=fr.read())!= -1) {
				System.out.print((char)c);
			}
			fr.close();
			*/
		
			while((c=isr.read())!= -1) {
				System.out.print((char)c);
			}
			isr.close();
		
			
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
