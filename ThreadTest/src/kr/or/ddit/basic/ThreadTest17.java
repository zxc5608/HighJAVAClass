package kr.or.ddit.basic;

//은행의 입출금을 쓰레드로 처리하는예제
//(동기화 처리예제)

public class ThreadTest17 {
	private int balance;  //잔액이저장될 변수
	
	
	public int getBalance() {  //잔액 꺼내온다
		return balance;
	}


	public void setBalance(int balance) { //잔액 설정한다
		this.balance = balance;
	}
//입금하는 메서드
	public void deposit(int money) {
		balance+=money;               //잔액+= 입금;
		
	}

//출금하는 메서드(출금이 성공하면 true 실패하면 false 반환)
	//public synchronized boolean withdraw(int money) {
	public boolean withdraw(int money) {
		synchronized (this) {
			
		
		if(balance >= money) { // 출금가능 여부를 판단한다  
			for(int i=1;i<10000000;i++) {} //시간지연용
			balance -=money; // 돈을뺀다 출금처리
			System.out.println("메서드 안에서 balance="+getBalance());
			return true;
			
		}else {								//출금실패
			return false;
			
		}
	}
}
	
	public static void main(String[] args) {
	
		ThreadTest17 acount=new ThreadTest17();
		acount.setBalance(10000);	//잔액을 10000원으로 설정
		
		//익명구현체로 쓰레드 구현
		
		Runnable test= new Runnable() {
			
			@Override
			public void run() {
				boolean result = acount.withdraw(6000);  //6000원을 출금하기
				System.out.println("쓰레드에서 result="+ result+","+"balance="+acount.getBalance());
				
			}
		};
//=-------------------- 익명구현체 
		Thread th1= new Thread(test);
		Thread th2= new Thread(test);
		
		th1.start();
		th2.start();
	}

}
