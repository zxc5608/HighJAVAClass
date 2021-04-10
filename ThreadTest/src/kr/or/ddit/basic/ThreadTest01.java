package kr.or.ddit.basic;

public class ThreadTest01 {
	//싱글 쓰레드(메인메서드 하나만있는경우)
	public static void main(String[] args) {
		for(int i=0;i<200;i++) {
			System.out.print("*");
			
		}
		System.out.println();
		System.out.println();
		for(int j=0;j<200;j++) {
			System.out.print("$");
		}
	}

}
