package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest02 {

	public static void main(String[] args) {
		byte[] inSrc= {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc=null;
		
		byte[] temp= new byte[4]; //4개 짜리 배열 생성
		
		ByteArrayInputStream input= new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream output= new ByteArrayOutputStream();
		
		try {
			//읽어올 데이터가 있는지 확인
			while(input.available() > 0) {
				/*input.read(temp);
				//temp배열의 데이터를 출력 스트림으로 출력한다.
				output.write(temp);*/
				
				int len =input.read(temp);  //반환값: 실제 읽어온 데이터의 byte수를 반환한다
				
				//읽어온 데이터가 저장된 temp배열에서 0번째 부터 읽어온 개수만큼 출력한다. 
				output.write(temp, 0, len);
				
				
				System.out.println("반복문 안에서 temp=>"+Arrays.toString(temp));
			}
			outSrc= output.toByteArray();
			
			System.out.println();
			System.out.println("inSrc=>"+Arrays.toString(inSrc));
			System.out.println("outSrc=>"+Arrays.toString(outSrc));
			
			input.close();
			output.close();
			
		} catch (IOException e) {
			
		}
		
		
	}

}
