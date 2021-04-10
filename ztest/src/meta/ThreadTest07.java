package meta;
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
	public static boolean inputCheck; //확인이 됐나안됐나

	public static void main(String[] args) {
		
		GameTimer gt =new GameTimer(); //객체를 생성해주고
		
		//run안에 들어가게한다
		//난수를 이용해서 컴퓨터의 가위바위보를 정한다.
		
		String[] data= {"가위 ","바위","보"};  //0번부터2번까지 난수를만들면 배열에서 꺼내올수있다. 
		int index =(int)(Math.random()*3);  //0부터 2사이의 난수
		String com= data[index];    //데이터에서 인덱스를 꺼내서 string com에 넣어준다
		
		//사용자로부터 가위바위보 입력받기
		gt.start(); //카운트 다운 쓰레드 시작
		String man;
		do {
			man= JOptionPane.showInputDialog("가위 바위 보!");
//		}while(!(man.equals("가위")||man.equals("바위")||man.equals("보")));  //가위바위보가 아니면! 입력이안되고 계속새로나온다
		}while(!man.equals("가위") && !man.equals("바위")&& !man.equals("보")); 
		
		inputCheck= true; //인풋체크가 완료되면 true로 변환
		
		//결과를  판정하기
		String result = ""; // 결과가 저장될 변수선언
		if(man.equals(com)) {
			result= "비겼습니다";
		}else if(man.equals("가위")&&com.equals("보")||
				man.equals("바위")&&com.equals("가위")||
				man.equals("보")&&com.equals("바위")){
			result="당신이 이겼습니다";
		}else {
			result ="당신이 졌습니다";
		}
		
		//결과 출력
		System.out.println();
		System.out.println("==결과==");
		System.out.println("컴퓨터:"+com);
		System.out.println("사용자:"+man);
		System.out.println("결과:"+result);
	}
	
}
class GameTimer extends Thread{
	@Override
	public void run() {
		System.out.println("카운트다운 시작...");
		for(int i=15;i>=1;i--) {
			if(ThreadTest07.inputCheck==true) {  //입력여부검사
				return;
			}
			
			
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println("시간이초과되어 당신이졌습니다.");
		System.exit(0);
	}
}