package kr.or.ddit.basic;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.print.attribute.standard.Fidelity;

import org.omg.PortableInterceptor.DISCARDING;

public class FileTest03 {

	public static void main(String[] args) {
		FileTest03 test= new FileTest03();
		
		File viewFile= new File("d:/D_Other"); //보고 싶은 디렉토리 설정

		test.displayFileList(viewFile);
	}


//디렉토리(폴더)를 매개값으로 받아서 해당 디렉토리(폴더)에 있는 
//모든 파일과 디렉토리 (폴더) 목록을 출력하는 메서드

public void displayFileList(File dir) {
	if(!dir.isDirectory()) {
		System.out.println("디렉토리 (폴더)만 가능합니다.");
		return;
		
	}
	System.out.println("["+dir.getAbsolutePath()+"] 디렉토리 내용");
	System.out.println();
	
	//해당 디렉토리 안에있는 모든 파이로가 다른 디렉토리 목록을 구해온다.
	File[] file= dir.listFiles();
	//String[] filestrs= dir.list();
	
	SimpleDateFormat df= new SimpleDateFormat("yyyy-mm-dd a HH:mm"); //날짜 형식을 만들어논다
	
	//가져온 파일과 디렉토리 목록 개수만큼 반복처리 
	for(int i=0; i<file.length;i++) {
		String fileName= file[i].getName();
		String attr="";  	//파일의 속성(읽기, 쓰기 , 히든, 디렉토리를 구분)
		String size="";		//파일의 크기
		
		if(file[i].isDirectory()) {
			attr="<DIR>";
			
		}else {
			size= file[i].length()+"";
			attr= file[i].canRead()?"R":"";
			attr+= file[i].canWrite()?"W":"";
			attr+= file[i].isHidden()?"H":"";
		}
		System.out.printf("%s %5s %12s %s\n", df.format(new Date(file[i].lastModified())),
				attr,size,fileName);
	}
}
}