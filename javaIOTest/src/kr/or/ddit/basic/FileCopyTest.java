package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 	'd:/D_Other/' 폴더에있는 '극한직업.jpg파일을
 	'd:/D_Other/'연습용 폴더에 '극한직업_복사본.jpg'이름으로 복사 하는 프로그램을 작성하시오
 */
public class FileCopyTest {

	public static void main(String[] args) {
		//원본
		String filename="극한직업.jpg";
		File file = new File("d:/D_Other/"+filename);
		
		if(!file.exists()) {
			System.out.println(file.getPath() + "파일이없습니다");
			System.out.println("복사작업을 중지합니다");
			return;
		}
		try {
			FileInputStream fin = new FileInputStream(file);
			
			BufferedInputStream bin = new BufferedInputStream(fin);
			//복사될 파일 스트림 객체생성(저장될 파일용)
			FileOutputStream fout =new FileOutputStream("d:/D_Other/연습용/극한직업_복사본.jpg");
			
			BufferedOutputStream bout= new BufferedOutputStream(fout);
			
			System.out.println("복사시작...");
			
			int data; //읽어온데이터를 저장할 변수
			/*
			while((data = fin.read()) != -1) {
				fout.write(data);
				
			}
			fout.flush();
			fout.close();
			fin.close();
			 */
			while((data = bin.read()) != -1) {
				bout.write(data);
				
			}
			bout.flush();
			bin.close(); 
			fin.close();

			System.out.println("복사완료...");
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		

	}

}
