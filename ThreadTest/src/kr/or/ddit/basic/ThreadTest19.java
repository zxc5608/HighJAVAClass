package kr.or.ddit.basic;

/*
 	wait(), notify()메서드를 이용한 예제
 	(두 쓰레드가 번갈아 한번씩 실행하는 예제)
 	
 	- wait(), notify() notifyAll()메서드는 동기화 영역에서 사용 가능하다
 
 */
public class ThreadTest19 {

	public static void main(String[] args) {
		
		WorkObject workobj=new WorkObject();
		
		ThreadA tha= new ThreadA(workobj);
		ThreadB thb= new ThreadB(workobj);
		
		thb.start();
		tha.start();
		

	}

}
//공통으로 사용할 객체
class WorkObject{
	public synchronized void testMethod1() {
		System.out.println("testMethod1()메서드 실행중...");
		notify();
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
	}
	public synchronized void testMethod2() {
		System.out.println("testMethod1()메서드 작업 실행중...");
		notify();
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
}
}

//workObject의 testMethod1()메서드만 실행하는 메서드

class ThreadA extends Thread{
	private WorkObject workobj;

	public ThreadA(WorkObject workobj) {
		super();
		this.workobj = workobj;
	}
	@Override
	public void run() {
		for(int i=0;i<10;i++) {
			workobj.testMethod1();
		}
		//마지막에 wait상태를 꺠워준다
		synchronized (workobj) {
			workobj.notify();
			
		}
		
	}
}
//workObject의 testMethod2()메서드만 실행하는 메서드
class ThreadB extends Thread{
	private WorkObject workobj;

	public ThreadB(WorkObject workobj) {
		super();
		this.workobj = workobj;
	}
	@Override
	public void run() {
		for(int i=0;i<10;i++) {
			workobj.testMethod2();
		}
		//마지막에 wait상태를 꺠워준다
		synchronized (workobj) {
			workobj.notify();
			
		}
	}
}