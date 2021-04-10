package kr.or.ddit.basic;

public class ThreadTest20 {

	public static void main(String[] args) {
		DataBox box=new DataBox();
		
		producerThread th1 = new producerThread(box);
		ConsumerThread th2 = new ConsumerThread(box);
		
		th2.start();
		th1.start();
		
		

	}

}
//데이터를 공통으로 관리하는 클래스

class DataBox{
	private String data;
	//데이터를 가져가는 메서드
//  처리 과정==> data변수의 값이 null이면 data변수에 문자열이 채워질 때까지 기다리고,
//	data변수의 값이 있으면 해당문자열을 반환한다.
//	data변수의 값을 반환한 후에는 data변수의 값을null로 만든다.	
	
	public synchronized String getData() {
		if(data==null) {
			try {
				wait();       //데이터가 null이면 기다린다
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		String returnData = data;
		System.out.println("쓰레드가 읽은 데이터:"+returnData);
		
		data = null;
		notify();
		return returnData;
	}
	
	//데이터를 넣어주는 메서드
	//처리과정 ==> 데이터 변수의 값이 있으면 data변수의 값이 null이될때까지 기다린다.
	//			data변수의 값이 null이되면  data변수에 새로운 값으로 저장한다. 
	public synchronized void setData(String data) {
		if(this.data != null) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		this.data =data;
		System.out.println("Thread에서 새로 저장한 데이터:"+ data);
		
		notify();
		
	}
}

//데이터를 넣어주는 쓰레드
class producerThread extends Thread{
	private DataBox box;

	public producerThread(DataBox box) {
		this.box = box;
	}
	@Override
	public void run() {
		for(int i=1;i<5;i++) {
			box.setData("공급데이터"+i);
		}
	}
}

//데이터를 꺼내서 사용하는쓰레드
class ConsumerThread extends Thread{
	private DataBox box;

	public ConsumerThread(DataBox box) {
		this.box = box;
	}
	@Override
	public void run() {
		for(int i=1;i<5;i++) {
			String data= box.getData();
		}
	}
}