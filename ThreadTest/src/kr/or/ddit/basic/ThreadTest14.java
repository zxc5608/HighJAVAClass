package kr.or.ddit.basic;

public class ThreadTest14 {

	/*
	  	Thread 의 stop()메서드를 호출하면 쓰레드가 바로멈춘다
	  	이때 사용하던 자원을 정리하지 못하고 프로그램이 종료도어
	  	나중에는실행되는 프로그램에 영향을 줄수있다
	  	그래서 stop()메서드는 비추천으로 되어있다.
	  	
	 */
	public static void main(String[] args) {
		/*ThreadStopTest1 th1 = new ThreadStopTest1();
		th1.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		//th1.stop();
		th1.setStop(true);
		*/
		
		
		//interrupt()메서드를 이용한 쓰레드 멈추기
		ThreadStopTest2 th2= new ThreadStopTest2();
		th2.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		th2.interrupt();
	}

}

//쓰레드를 멈추게하는 연습용 쓰레드
class ThreadStopTest1 extends Thread{
	private boolean stop; //쓰레드의 종료를 제어할떄 사용하는 변수선언
	
	public void setStop(boolean stop) {
		this.stop=stop;
	}
	
	@Override
	public void run() {
		while(!stop) {
			System.out.println("쓰레드 실행중...");
		}
		System.out.println("자원정리...");
		System.out.println("쓰레드 종료...");
	}
	
}

//interrupt()메서드를 이용하여 쓰레드를 멈추게 하는 방법
class ThreadStopTest2 extends Thread{
	@Override
	public void run() {
		//방법1)interrupt()메서드와 sleep()메서드를 이용하는 방법
		//쓰레드가 일시정지 상태에서 interrupt()메서드가 호출되면
		//해당 쓰레드는 일시정지 상태에서 풀려나고 동시에 InterruptedException을 발생시킨다. 
		
		/*try {
			while(true) {
				System.out.println("...실행중...");
				Thread.sleep(1);
			}
		} catch (InterruptedException e) {
			
		}
		System.out.println("자원정리.........");
		System.out.println("쓰레드종료........");
*/
		//방법2
		while(true) {
			System.out.println("Thread실행중...");
			
			//interrupt()메서드가 호출되었는지 검사한다
			
			//검사방법1) 쓰레드의 인스턴스 메서드인 isInterrupted()메서드를 이용한다.
			// isInterrupted()메서드는interrupt()메서드가 호출되면 true를 반환한다.
			/*
			if(this.isInterrupted()) {
				break;
			}*/
			
			//검사방법2) 쓰레드의 정적메서드인 interrupt()메서드이용하기
			//interrupt()메서드 ==> interrupt()메서드가  호출되면true 반환한다
			
			if(Thread.interrupted()) {
				break;
			}
		}
		System.out.println("자원정리.........");
		System.out.println("쓰레드종료........");
	}
}