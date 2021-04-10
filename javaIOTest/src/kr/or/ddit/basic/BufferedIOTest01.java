package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedIOTest01 {

	public static void main(String[] args) {
		// 입출력의 성능 향상을 위해서 Buffered 스트림을 사용한다
		
		try {
			FileOutputStream fout= 
					new FileOutputStream("d:/D_Other/bufferTest.txt");
			
			//버퍼의 크기가 5인 Buffered 스트림객체 생성
			//버퍼의 크기를 지정하지 않으면 기본크기인 8192Byte로 버퍼의 크기가 정해진다.
			BufferedOutputStream bout =new BufferedOutputStream(fout,5);
			for(char i= '1';i<='9';i++) {
				bout.write(i);
				
			}
			bout.flush();     // 작업을 종료하기 전에 버퍼에 남아있는 데이터를 모두 출력시킨다.
			
			bout.close(); 	//보조스트림을 닫으면 보조스트림에서 사용한 기반의 되는 스트림도 자동으로 닫힌다.
			System.out.println("작업끝...");
			
			
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
