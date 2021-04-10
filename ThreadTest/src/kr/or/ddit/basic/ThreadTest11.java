package kr.or.ddit.basic;

//yield메서드 연습

public class ThreadTest11 {

	public static void main(String[] args) {
		YieldThread th1 =new YieldThread("1번 쓰레드");  //"" 네임값으로 들어간다
		YieldThread th2 =new YieldThread("2번 쓰레드");

		th1.start();
		th2.start();
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("==============================================");
		
		th1.work= false;
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("888888888888888888888888888888888888888888888888");
		
		th1.work= true;
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		
		th1.stop= true;
		th2.stop= true;
	}

}
//yield()메서드 연습용 쓰레드
class YieldThread extends Thread{
	public boolean stop = false;  //쓰레드이 종료여부를 나타내는 값을 저장하는 변수선언
	public boolean work = true;	  // 작업을 처리하는 여부를 나타내는 값을 저장하는 변수선언
	//생성자
	public YieldThread(String name) {
		super(name);
		
	}
	@Override
	public void run() {
		while(!stop) { //stop이 되면 반복문이 종료된다.  반복문은 거짓이되야 끝난다.
			if(work) {
				//getName메서드 == 현재쓰레드의 name값을반환한다.
				System.out.println(getName()+"작업중");
				
			}else {
				System.out.println(getName()+"양보...");
				Thread.yield();
				
			}
			
		}
		
	}
	
}