package kr.or.ddit.basic;

// 쓰레드 상태를 출력하는 예제
public class ThreadTest10 {

	public static void main(String[] args) {
		TagetThread t= new TagetThread();
		StatePrintThread th = new StatePrintThread(t);
		
		th.start();

	}

}
//쓰레드 상태의 검사 대상이 되는 쓰레드 
class TagetThread extends Thread{
	@Override
	public void run() {
		for(long i= 1L;i<=20_000_000_000L;i++) { } //시간지연용
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		for(long i= 1L;i<=20_000_000_000L;i++) { } //시간지연용
	}
	
}

//TagetThread의 상태를 출력하는 쓰레드
class StatePrintThread extends Thread{
	private TagetThread target; //TagetThread 가 저장될 변수선안
	
	//생성자
	public StatePrintThread(TagetThread target) {
		this.target=target;
	}
	
	@Override
	public void run() {
		while(true) {
			//TagetThread의 상태구하기
			Thread.State state = target.getState();
			System.out.println("tagetThread의 현재 상태:"+state);
			
			if(state==Thread.State.NEW) { //쓰레드의 현재 상태가 NEW상태이면...
				target.start();
			}
			
			if(state==Thread.State.TERMINATED) { //쓰레드가 종료상태면..
				break;
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			
		}
	}
	
}