package kr.or.ddit.basic;

import java.io.File;

import javax.sound.midi.Synthesizer;

public class Filetest01 {

	public static void main(String[] args) {
		//File객체 만들기 연습
		
		//1.new File(String 파일 또는 경로);
		//		==> 디렉토리와 디렉토리 사이 또는 디렉토리와 파일명 사이의 구분문자는 
		// 			역슬래시('\')를 사용하거나 슬레쉬('/')를 사용할수있다.
		
		File file1= new File("D:/D_Other/test.txt");	//구분문자로 '/'사용한경우
		//File file1= new File("D:\\D_Other\\test.txt"); //구분문자로 '\'사용한 경우 \\2개를써야함
		
		File file2= new File("d:/d_other");
		System.out.println("파일명:"+file2.getName());
		System.out.println("디렉토리일까?:"+file2.isDirectory());
		System.out.println("파일일까?:"+file2.isFile());
		System.out.println();
		
		//2.new File(File parent, String child)
		//		==> 'parent' 디렉토리 안에있는 'child'파일을 나타낸다.
		File file3= new File(file2,"test.txt");
		
		System.out.println("파일명:"+file3.getName());
		System.out.println("디렉토리일까?:"+file3.isDirectory());
		System.out.println("파일일까?:"+file3.isFile());
		System.out.println();
		
		//3.new File(String parent, String child)
		//			==> 'parent'디렉토리 안에있는'child'파일을 나타낸다
		
		File file4 =new File("d:/D_Other","test.txt");
		
		System.out.println("파일명:"+file4.getName());
		System.out.println("디렉토리일까?:"+file4.isDirectory());
		System.out.println("파일일까?:"+file4.isFile());
		System.out.println();
		System.out.println("==================================");
		
		//디렉토리 (폴더)만들기
		/*
		 	-mkdir() ==> File객체의 경로 중 마지막 위치의 디렉토리를 생성한다.
		 			 ==> 반환값: 만들기 성공(true), 만들기 실패(false)
		 			 ==> 중간부분의 경로가 모두 만들어져 있어야 마지막 위치의 폴더를 만들수 있다.
		 			 
		 	-mkdirs()==> 중간 부분의 경로가 없으면 중간부분의 경로도 같이 만들어준다.
		 */
		File file5 =new File("D:/D_Other/연습용");
		System.out.println("파일명:"+file5.getName());
		System.out.println("디렉토리일까?:"+file5.isDirectory());
		System.out.println("파일일까?:"+file5.isFile());
		System.out.println();
		System.out.println(file5.getName()+"의 존재여부"+file5.exists());
		
		//연습용 폴더를 만들고싶으면
		if(file5.mkdir()) {
			System.out.println(file5.getName()+"디렉토리 만들기 성공~");
			
		}else {
			System.out.println(file5.getName()+"디렉토리만들기 실패");
		}
		System.out.println();
		
		                          //만들려면 부모폴더가 있어야 만들어진다
		File file6 =new File("d:/D_Other/test/java/src");
		if(file6.mkdirs()) {
			System.out.println(file6.getName()+"디렉토리 만들기성공");
		}else {
			System.out.println(file6.getName()+"디렉토리만들기 실패");
		}
		System.out.println();
	}

	
}
