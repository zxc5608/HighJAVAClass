package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest01 {

	public static void main(String[] args) {
		//byte배열의 데이터를 입력스트림으로 읽어서 출력스트림으로 출력하는 예제
		
		byte[] inSrc= {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc=null;
		
		
		//입력용 스트림객체생성
		ByteArrayInputStream input =new ByteArrayInputStream(inSrc);
		
		//출력용 스트림객체생성
		ByteArrayOutputStream output= new ByteArrayOutputStream();
		
		int data;  //읽어온 자료가 저장될변수
		 	//read()메서드==>더이상 읽어올 자료가 없으면 -1을 반환한다//-1이랑 같지않으면 저장해라
		while ((data=input.read())!=-1) {
			//읽어온 데이터를 사용하여 처리하는 내용을 기술한다.
			
			output.write(data);  //출력용 스트림을 이용하여 읽어온데이터를 출력한다.
			
			
		}
		
		//출력된 스트림값들을 배열로 변환
		outSrc=output.toByteArray();
		
		try {
			
			//사용했던 자원(스트림 객체)를 반납한다
			input.close();
			output.close();
		} catch (IOException e) {

		}
		System.out.println("inSrc =>"+Arrays.toString(inSrc));
		System.out.println("outSrc =>"+Arrays.toString(outSrc));
	}

}
