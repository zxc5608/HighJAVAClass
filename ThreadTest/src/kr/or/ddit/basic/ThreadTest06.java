package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class ThreadTest06 {

	public static void main(String[] args) {
		Thread th1= new DataInput();
		Thread th2= new CountDown();
		
		th1.start();
		th2.start();
	}

}
//데이터를 입력하는 쓰레드
class DataInput extends Thread{
	//입력력부를 확인하기위한 변수 선언 ==> 쓰레드에서 공통으로 사용할 변수
	public static boolean inputcheck; //기본으로 false장착
	
	
	@Override
	public void run() {
		String str=JOptionPane.showInputDialog("아무거나입력하셍");
		inputcheck=true; //트루로 바꿔
		System.out.println("입력한 값:"+str);
	}
}
//카운트 다운을 진행하는 쓰레드
class CountDown extends Thread{
	@Override
	public void run() {
		for(int i=10;i>=1;i--) {
			System.out.println(i);
			
			//입력이 완료되었는지 여부를 검사해서 입력이 완료 되면 쓰레드를 종료시킨다.
			
			if(DataInput.inputcheck==true) { 
				
				return;	//run메서드가 종료되면 해당 쓰레드로 종료된다.
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		//카운트다운이 끝나면
		System.out.println("10초가 지났습니다. 프로그램을 종료합니다.");
		System.exit(0);
	}
}