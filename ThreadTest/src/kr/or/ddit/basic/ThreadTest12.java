package kr.or.ddit.basic;
//3개의 쓰래드가 각각 알파벳을 A~Z까지 출력하는데
// 출력을 끝낸 순서대로 결과를 나타내는 프로그램 작성하기

public class ThreadTest12 {

	public static void main(String[] args) {
		DisplayCharacter[] player = new DisplayCharacter[] {
				new DisplayCharacter("홍길동"),
				new DisplayCharacter("이순신"),
				new DisplayCharacter("강감찬")
		};
		for(DisplayCharacter players :player) {
			players.start();
			
		}
		for(DisplayCharacter players :player) {
			try {
				players.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			
		}
		System.out.println();
		System.out.println("경기결과:"+DisplayCharacter.rank);

	}

}
//A~Z까지 출력하는 쓰레드
class DisplayCharacter extends Thread{
	public static String rank ="";  //빨리 출력한 순서대로 저장할 변수선언
	private String name;
	
	public DisplayCharacter(String name) {
		this.name= name;
	}
	
	@Override
	public void run() {
		for(char c='A';c<='Z';c++) {
		System.out.print
		(name+"의 출력문자:"+c);
		
		try {
			//101부터 500사이의 난수 설정하기
			Thread.sleep((int)(Math.random()*400+101));
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
	}//for문
		System.out.println(name+ "출력 끝...");
		DisplayCharacter.rank+=name+"  ";
	}
	

}