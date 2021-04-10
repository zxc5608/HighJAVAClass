package kr.or.ddit.basic;

import java.io.File;

public class FileTest02 {

	public static void main(String[] args) {
		File f1= new File("d:/D_Other/test.txt");
		System.out.println(f1.getName()+"의 크기:"+f1.length()+"byte");
		System.out.println("path:"+f1.getPath());   //경로
		System.out.println("AbsolutePath:"+f1.getAbsolutePath());//절대경로
		System.out.println();
		
		File f2=new File(".");  // . 은 현재폴더를 의미함
		System.out.println("Path:"+f2.getPath());
		System.out.println("AbsolutePath:"+f2.getAbsolutePath());  // . 지금 class의 현재 폴더를 나타낸다.
		
		//f1 지정해둔 test.txt파일
		if(f1.isFile()) {
			System.out.println(f1.getName()+"은 파일입니다");
		}else if(f1.isDirectory()) {
			System.out.println(f1.getName()+"은 디렉토리(폴더 )입니다.");
		}else {
			System.out.println(f1.getName()+"은 뭘까?");
		}
		System.out.println();
		
		//f2 현재파일
		
		if(f2.isFile()) {
			System.out.println(f2.getName()+"은 파일입니다");
		}else if(f2.isDirectory()) {
			System.out.println(f2.getName()+"은 디렉토리(폴더 )입니다.");
		}else {
			System.out.println(f2.getName()+"은 뭘까?");
		}
		System.out.println();
		
		//현재 존재하지 않은 파일을 지정했을때  
		File f3 =new File("d:/D_Other/sample.exe");
		if(f3.isFile()) {
			System.out.println(f3.getName()+"은 파일입니다");
		}else if(f3.isDirectory()) {
			System.out.println(f3.getName()+"은 디렉토리(폴더 )입니다.");
		}else {
			System.out.println(f3.getName()+"은 뭘까?");
		}
		System.out.println();
	}

}
