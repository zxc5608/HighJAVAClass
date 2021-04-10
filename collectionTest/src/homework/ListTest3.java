package homework;

import java.util.ArrayList;
import java.util.Scanner;

public class ListTest3 {

	public static void main(String[] args) {
		//5개의 별명 길이
		
		Scanner sc =new Scanner(System.in);
		
		ArrayList<String>alias =new ArrayList<>();
		
		System.out.println("서로 다른길이의 별명을 쓰셈");
		for(int i=1;i<=5;i++) {
			System.out.println(i+"번째 별명");
			String aliass= sc.nextLine();
			alias.add(aliass);
		}
		System.out.println();
		
		//제일 긴 별명이 저장될 변수 선언==> list의 첫번째 데이터로 초기화 한다.
		String maxAlias = alias.get(0);  
		//여기 0번쨰 넣어놧기떄문에 1부터
		for(int i=0;i<alias.size();i++) {
			if(maxAlias.length() < alias.get(i).length())
				maxAlias= alias.get(i);
				//길면 집어 넣어라
		}
		System.out.println(maxAlias);
	}
	
}
