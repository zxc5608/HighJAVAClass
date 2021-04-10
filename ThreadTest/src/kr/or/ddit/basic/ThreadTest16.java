package kr.or.ddit.basic;

public class ThreadTest16 {

	public static void main(String[] args) {
		ShareObject sobj= new ShareObject();
		
		TestThread th1 = new TestThread("1번쓰레드",sobj);
		TestThread th2 = new TestThread("2번쓰레드",sobj);
		
		th1.start();
		th2.start();
	
	}

}

class TestThread extends Thread{
	private ShareObject sObj;

	//생성자
	public TestThread(String name,ShareObject sObj) {
		super(name);	//쓰레드에 name을 설정한다. 
		this.sObj = sObj;
	}
	@Override
	public void run() {
		for(int i=0;i<10;i++) {
			sObj.add(); //호출
		}
	}
	
	
}
//공통으로 사용할 클래스
class ShareObject {
	private int sum=0;
	
	//public synchronized void add() {  //방법 1==>메서드 동기화 설정을 한다.
	public void add() {
		synchronized (this) {		//동기화블럭으로 설정한다.
			
		
		int n = sum;
		
		n+= 10;
		
		sum = n;
		
		System.out.println(Thread.currentThread().getName()+"합계:"+ sum);
	}
  }
}