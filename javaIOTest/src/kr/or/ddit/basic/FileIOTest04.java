package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest04 {
	//콘솔에서 사용자가 입력한 내용을 그대로 파일에 저장하기
	//콘솔 가장 기본적인 입출력장치 
	public static void main(String[] args) {
		
		try {
			//System.out ==>콘솔(표준입출력장치)출력장치 ==> 바이트 기반 출력용 스트림
			//System.in  ==> 콘솔입력장치			==> 바이트 기반 입력용 스트림
			
			//바이트 기반의 스트림을 문자열 기반의 스트림으로 변환해주는 스트림 객체 생성
			//콘솔로 입력한 데이터를 가져오기 위한 스트림객체
			
			InputStreamReader isr = new InputStreamReader(System.in);
			// 파일로 저장하는 문자기반스트림 객체생성
			FileWriter fw= new FileWriter("D:/D_Other/testChar.txt");
			System.out.println("아무내용이나 입력하세요.(입력의 끝은 Ctrl+Z키입니다)");
			
			int c;
			
			while((c=isr.read())!=-1) {
				fw.write(c);		//콘솔로 입력한 값을 파일로 출력한다.
			}
			isr.close();
			fw.close();
		} catch (IOException e) { 
			// TODO: handle exception
		}
	}

}
