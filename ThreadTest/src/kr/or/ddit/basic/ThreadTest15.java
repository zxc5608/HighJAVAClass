package kr.or.ddit.basic;

//쓰레드에서 객체를 공통으로 사용하는 예제

/*
   		원주율을 계산하는 쓰레드와 
   		계산된원주율을 출력하는 쓰레드가 있다.
   	
    	원주율을 저장하는 객체가 필요하다
    	이 객체를 두쓰레드에서 공통으로 사용해서 처리한다.
    	
 */
public class ThreadTest15 {

	public static void main(String[] args) {
		//공통으로 사용할 객체생성
		ShareData sd= new ShareData();
		//스레드 객체생성하고 , 공통으로 사용할 객체를 쓰레드에 주입한다.
		PrintPIThread printPi= new PrintPIThread(sd);
		
		CalcPIThread calcp=new CalcPIThread();
		calcp.setSd(sd);
		
		System.out.println("계산시작...");
		calcp.start();
		printPi.start();

	}

}
//원주율을 계산하는 쓰레드
class CalcPIThread extends Thread{
	private ShareData sd;
	//생성자
//	public CalcPIThread() {
//		this.sd=sd;
//	}
	
	//setter를 이용하는 방법
	public void setSd(ShareData sd) {
		this.sd=sd;
	}
	@Override
	public void run() {
		/*
		 	원주율 = (1/1 - 1/3 + 1/5 - 1/7 + 1/9 -....)*4
		 	
		 			1 - 3 + 5 - 7 + 9 ...
		 			0	1	2	3	4   ==>2로나눈 몫
		 			
		 			
		 */
			double sum= 0.0;  //계산해서 저장할변수
			for(int i=1;i<=2000000000;i+=2) {
				if((i/2)%2==0) {  //2로 나눈 몫이 짝수이면..
					sum+=1.0/i;
				}else {
					sum-=(1.0/i);
				}
			}
			sd.result= sum * 4;	//계산된원주율 저장
			sd.isOk=true;		//계산이 완료됨을 설정한다.
	}
}


//원주율을 출력하는 쓰레드
class PrintPIThread extends Thread{
	private ShareData sd;		//공통으로 사용할 객체의 참조값이 저장될 변수선언
	
	public PrintPIThread(ShareData sd) {
		this.sd=sd;
		
		
	}
	@Override
	public void run() {
		while(true) {
			if(sd.isOk==true) { //계산이 완료되었는지검사
				break;
			}
		
		}	
		System.out.println();
		System.out.println("결과:"+sd.result);  //계산한값
		System.out.println("PI:"+Math.PI);		//실제 파이값
	}
}


//공통으로 사용할 클래스==> 원주율을 관리하는 클래스
class ShareData{
	public double result; 	//계산된 원주율이 저장될 변수
	
	//volatile ==> 이 키워드가 붙은 변수는 컴파일러 의 최적화 대상에서 제외된다.
	//			즉. 값이 변경되는 즉시 변수에 적용시킨다.
	public volatile boolean isOk;	//계산이 완료되었는지 여부를 나타내는 변수
	     
}