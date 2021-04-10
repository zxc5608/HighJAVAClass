package kr.or.ddit.basic;
/*
 	문제) 컴퓨터와 가위바위보를 진행하는 프로그램을 작성하세요
		-컴퓨터의 가위바위보는 난수를 이용해서 구하고
		-사용자는 showInputDialog()메서드를 이용해서 입력받는다.
		-입력시간은 5초로 제한하고 카운트다운을 진행한다.
		-5초안에 입력이 없으면 게임에 진것으로 처리하고 끝낸다. 
		-5초안에 입력을 완료되면 승패를 구해서 출력한다. 
	
	5초 안에 입력이 완료되었을때 결과예시)
	--결과 --
	컴퓨터 :가위
	사용자: 바위
	결과: 당신이이겼습니다
	
	5초 안에 입력이 없었을때 결과예시
	 	시간이 초과되어 당신이졌습니다.
	
 */

import java.net.ServerSocket;
import java.util.Random;
import java.util.Vector;

import javax.swing.JOptionPane;

public class ThreadTest07 {
	

	public static void main(String[] args) {
	
	
		Thread th1= new DataInput1();
		Thread th2= new CountDown1();
		
		th1.start();
		th2.start();



	}
}
class DataInput1 extends Thread{

	public static boolean inputcheck; 
	
	
	@Override
	public void run() {
		int com =(int)(Math.random()*2+1);
		String con="";
		if(com==1) {
			con="가위";
		}else if(com==2) {
			con="바위";
			
		}else if(com==3) {
			con="보";
			
		}else {
			System.out.println("잘못입력했습니다");
		}
		
		String user= JOptionPane.showInputDialog("1:가위 2:바위 3:보");
		
		
		int use = 0;
		if(user.equals("가위")) {
			use=1;
		}else if (user.equals("바위")) {
			use=2;
		}else if(user.equals("보")) {
			use=3;
		}
		
		System.out.println("컴퓨터:"+con);
		System.out.println("나:"+ user);
		
		if(use==com) {
			System.out.println("무승부");
		}else if((use==1 && com==2)||(use==2 && com==3)||(use==3 && com==1)){
				//가위		바위		//바위		보 			보                  가위
			System.out.println("졌습니다");
		}else if((use==1 && com==3)||(use==2&&com==1)||(use==3&&com==2))
				//나 가위		보		나 주먹   		컴 가위	나 보		컴 바위
			System.out.println("이겼습니다.");
		inputcheck=true; //트루로 바꿔
		
		
	
		}
			
		
	}

//카운트 다운을 진행하는 쓰레드
class CountDown1 extends Thread{
	@Override
	public void run() {
		for(int i=10;i>=1;i--) {
			System.out.println(i);
			
			//입력이 완료되었는지 여부를 검사해서 입력이 완료 되면 쓰레드를 종료시킨다.
			
			if(DataInput1.inputcheck==true) { 
				return;	//run메서드가 종료되면 해당 쓰레드로 종료된다.
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		//카운트다운이 끝나면
		System.out.println("시간초과 당신은 졌습니다");
		System.exit(0);
	}
}