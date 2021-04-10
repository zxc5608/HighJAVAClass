package test;

import java.util.Scanner;

public class star {

	public static void main(String[] args) {
		
		Scanner sc= new Scanner(System.in);
		
		System.out.println("가로 입력:");
		int a= sc.nextInt();
		System.out.println("세로입력:");
		int b= sc.nextInt();
		
		for(int i=0;i<b;i++) {
			System.out.print("*");
			for(int j=1;j<a;j++) {
				System.out.print("*");
		}
			System.out.println();
		}
		System.out.println();
		System.out.println(a+b);
	}

}

