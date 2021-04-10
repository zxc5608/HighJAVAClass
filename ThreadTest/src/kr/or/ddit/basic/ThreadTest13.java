package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
 	문제 1)10마리의 말들이 경주하는 경마 프로그램 작성하기
 	
 		경주말 horse 라는 이름의 클래스로 구성하고 
 		이 클래스는 말이름(string),등수(int),현재위치(int)를 멤버변수로 갖는다.
 		그리고, 이 클래스에는등수를 오름차순으로 처리하는 내부정렬기준이있다.
 			(Comparable 인터페이스 구현하기 )
 		-이 Horsr클래스는 쓰래드로 작성한다. 
 		
 		- 경기 구간은 1~50구간으로 되있다. 
 		- 경기 중 중간중간에 각 말들의 위치를 나타내시오.
 		예)
 		01번말:---->-----------------------
 		02번말:--->------------------------
 		...
 		10번말:------>---------------------
 		
 		경기가 끝나면 등수순으로 경기결과를 출력한다. 
 */
public class ThreadTest13 {

	public static void main(String[] args) {
		Horse[] horses=new Horse[] {
				new Horse("01번말"),
				new Horse("02번말"),
				new Horse("03번말"),
				new Horse("04번말"),
				new Horse("05번말"),
				new Horse("06번말"),
				new Horse("07번말"),
				new Horse("08번말"),
				new Horse("09번말"),
				new Horse("10번말")
		};
		//현재경기 객체생성
		GameSate gs= new GameSate(horses);
		
		System.out.println("경기시작...");
		for(Horse h: horses) {
			h.start();
		}
		gs.start();
		for(Horse h:horses) {
			try {
				
				h.join(); //모든말이 경기가끝날때까지 기다려준다
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		try {
			gs.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("경기끝...");
		/*
		//배열을 직접 정렬하고 출력하기
		//정렬
		Arrays.sort(horses);
		//출력
		
		for(Horse h:horses) {
			System.out.println(h);
		}
		*/
		//방법2 배열의 데이터를list에 담고list를 정렬하여 출력하기
		ArrayList<Horse> horseList= new ArrayList<>();
		for(Horse h: horses) {
			horseList.add(h);
		}
		Collections.sort(horseList);
		
		for(Horse h: horseList) {
			System.out.println();
		}
	}
	
}
/*경주말 horse 라는 이름의 클래스로 구성하고 
	이 클래스는 말이름(string),등수(int),현재위치(int)를 멤버변수로 갖는다.
	그리고, 이 클래스에는등수를 오름차순으로 처리하는 내부정렬기준이있다.
		(Comparable 인터페이스 구현하기 )
	-이 Horse클래스는 쓰래드로 작성한다.
	 
*/
//상속을 받고				등수의 오름차순으로 받고 내부정렬을 만들어라
class Horse extends Thread implements Comparable<Horse>{
	
	public static int currentRank=0;  //말들의 등수를 계산에 사용되는 변수선언
	private String horseName;	//말이름
	private int rank; 			//등수
	private int position; 		//현재위치
	
	
	//생성자
	public Horse(String horseName) {
		super();
		this.horseName = horseName;
	}


	public String getHorseName() {
		return horseName;
	}


	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}


	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}


	public int getPosition() {
		return position;
	}


	public void setPosition(int position) {
		this.position = position;
	}


	@Override
	public String toString() {
		return "경주마" + horseName + "등수는" + rank + "등 입니다.";
		
	}
	//등수를 오름차순
	@Override
	public int compareTo(Horse horse) {
		return Integer.compare(rank, horse.getRank());
	}
	//말이 달리는 부분을 쓰레드로 처리한다
	@Override
	public void run() {
		for(int i=1;i<=50;i++) {
			position= i;     //말의 현재위치를 저장한다.
			try {                  //지연시켜야된다
				Thread.sleep((int)(Math.random()*500));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}//for문
		
		//한마리의 말이 경주가끝나면 등수를 구해서 rank에 설정한다.
		currentRank++;
		rank=currentRank;   //랭크에다 커렌트랭크값을 저장한다.
		
		
		
	}
	

}

/*	예)
 		01번말:---->-----------------------
 		02번말:--->------------------------
 		...
 		10번말:------>---------------------
 		*/
// 경기중 말의 현재 위치를 출력하는 쓰레드

class GameSate extends Thread{
	private Horse[] ho;  //열마리 말들이 저장된배열
	
	public GameSate(Horse[]ho) {
		this.ho=ho;
	}
	
	@Override
	public void run() {
		while(true) {
			
			//모든 말들의 경주가 끝났는지 여부를 검사
			if(Horse.currentRank==ho.length) {
				break;
			}
			//빈줄출력하기
			for(int i=1;i<=15;i++) {
				System.out.println();
			}
			
			for(int i=0;i<ho.length;i++) {
				System.out.print(ho[i].getHorseName()+":");
				
				for(int j=1;j<=50;j++) {
					if(ho[i].getPosition()==j) {
						System.out.print(">");
					}else {
					System.out.print("-");
					}
				}
				System.out.println();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
}