package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

public class ListTest4 {

	public static void main(String[] args) {
	//길이가 같은게 있는 경우 

Scanner sc =new Scanner(System.in);
		
		ArrayList<String>alias =new ArrayList<>();
		
		System.out.println("별명을 5번 입력");
		for(int i=1;i<=5;i++) {
			System.out.println(i+"번째 별명");
			String aliass= sc.nextLine();
			alias.add(aliass);
		}
		System.out.println();
		
		//제일 긴 별명의 글자 수가 저장될 변수를 선언하고
		//list의 첫번쨰 데이터의 글자수로 초기화 한다.
		
		int maxLength = alias.get(0).length();
		
		for(int i=1; i<alias.size();i++) {
			if(maxLength<alias.get(i).length()) {
				maxLength= alias.get(i).length();
			}
			
		}
		System.out.println("제일 긴 별명들:");
		for(int i=0; i<alias.size();i++) {
			if(alias.get(i).length()== maxLength) {
				System.out.println(alias.get(i));
			}
		}
		
		
	}

}
