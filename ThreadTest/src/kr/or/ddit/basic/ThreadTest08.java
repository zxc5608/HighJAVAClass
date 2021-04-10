package kr.or.ddit.basic;

public class ThreadTest08 {

	public static void main(String[] args) {
		Thread th1 = new UpperThread();
		Thread th2 = new LowerThread();
		
		
		//우선순위는 start() 메서드를 호출하기 전에 변경해야한다.
		th1.setPriority(9);
		th2.setPriority(7); //우선순위가 더높게변경
		
		System.out.println("th1의 우선순위:" +th1.getPriority());
		System.out.println("th2의 우선순위:" +th2.getPriority());
		
		th1.start();
		th2.start();
		
		
	}

}
//대문자를 출력하는 쓰레드
class UpperThread extends Thread{
	@Override
	public void run() {
		for(char c='A';c<='Z';c++) {
			System.out.println(c);
			
			//아무것도하지않는 반복문 --시간때우기용
			for(int i=1;i<=2_000_000_000;i++) {}  
		}
	}
}
//소문자를 출력하는 쓰레드
class LowerThread extends Thread{
	
	@Override
	public void run() {
		for(char c='a';c<='z';c++) {
			System.out.println(c);
			
			//아무것도하지않는 반복문 --시간때우기용
			for(int i=1;i<=2_000_000_000;i++) {}  
		}
	}
}