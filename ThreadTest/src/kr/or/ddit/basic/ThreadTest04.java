package kr.or.ddit.basic;
/*
	1~20억 까지의 합계를 구하는 프로그램을
	하나의 쓰레드가 단독적으로 처리했을 때와 
	여러개의 쓰레드가 협력해서 처리할때의 처리시간을 비교해 보자.
	
	
*/

public class ThreadTest04 {

	public static void main(String[] args) {
		// 단독으로 처리하는 쓰레드
		sumThread sm= new sumThread(1L,2_000_000_000L);
		
		//여럿이 협력해서 처리하는 쓰레드
		sumThread[]sums=new sumThread[] {
				new sumThread(1L, 5_000_000_00L),
				new sumThread( 5_000_000_00L, 10_000_000_00L),
				new sumThread(10_000_000_00L, 15_000_000_00L),
				new sumThread(15_000_000_00L, 20_000_000_00L)
		};
		//단독으로 처리하기
		long startTime = System.currentTimeMillis();
		sm.start();
		try {
			sm.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		long endTime = System.currentTimeMillis();
		System.out.println("단독으로 처리했을때 시간:"+(endTime-startTime));
		System.out.println();
		
		//여러 쓰레드가 협력해서 처리하는 경우
		startTime = System.currentTimeMillis();
		
		for(int i=0;i<sums.length;i++) {
			sums[i].start();
		}
		for(sumThread th :sums) {
			try {
				th.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		endTime= System.currentTimeMillis();
		
		System.out.println("협력해서 처리했을때 시간:"+(endTime-startTime));
	}

}
class sumThread extends Thread{
	//합계를 구할 영역의 시작값과 종료값을 저장할 변수 선언
	private long startNum;
	private long endNum;
	
	
	//생성자에서 초기화하는 작업
	public sumThread(long stratNum, long endNum) {
		super();
		this.startNum = startNum;
		this.endNum = endNum;
	}



	@Override
	public void run() {
		
		long sum=0L;
		for(long i=startNum;i<endNum;i++) {
			sum+=i; 
			
		}
		System.out.println("합계:"+sum);
	}
}

