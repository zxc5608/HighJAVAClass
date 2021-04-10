package homework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/*
 * 문제) Set과 list를 이용하여 숫자야구게임 프로그램을 작성하시오
 * 		컴퓨터의숫자는 난수를 이용하여 구한다.
 * 		(스트라이크 s, 볼은b로 나타낸다)
 * 		
 * 예시 )
 * 컴퓨터의 난수 ==> 9 5 7 
 * 
 * 실행예시 )
 * 	 숫자입력받고=> 3,5,8
 * 	358 ==> 1s 0b 
 * 	숫자입력 => 7 8 9
 *  789 => 0s2b
 */

public class baseballTest {
	ArrayList<Integer>numlist;//난수가 저장될 list
	ArrayList<Integer>userlist; //사용자가 입력한 값이 저장될 list
	
	int strike; //스트라이크개수가 저장될변수
	int ball; //볼개수가 저장될 변수
	
	Scanner sc= new Scanner(System.in);

	public static void main(String[] args) {
//		baseballTest baseball= new baseballTest();
//		baseball.gameStart();
		new baseballTest().gameStart();
	}
	
	//게임이 시작되는 메서드
	public void gameStart() {
		//난수를 만드는 메서드호출
		getNum();
		
		//확인용 출력
		System.out.println("컴퓨터의 난수:"+numlist);
		
		int cnt = 0;  //몇번만에 맞췄는지를 저장하는 변수 선언 및 초기화
		
		do {
			cnt++;
			//사용자로부터 입력받는 메서드호출
			inputNum();
			
			//볼 카운트 구하는 메서드호출
			ballCount();
			
			
		}while(strike!=3); //스트라이크가 3이 될때까지반복한다. 
		
		System.out.println();
		System.out.println("축하합니다");
		System.out.println("당신은"+cnt+"번째 만에 맞췄습니다.");
	}
	
	//1~9사이의 서로다른 난수 3개를 만들어서 리스트에 저장하는 메서드(set이용)

	public void getNum() {
		Set<Integer>numSet=new HashSet<>();
		
		//1~9사시의 난수 3개만들기
		while (numSet.size()<3) {
			
			numSet.add((int)(Math.random()*9+1));
			
		}
		//만들어진 난수를 list에 저장하기
		numlist =new ArrayList<>(numSet);
		
		//List의 데이터를 섞어준다.
		Collections.shuffle(numlist);
	}
	//사용자로부터 3개의 정수를 입력받아서 리스트에 저장하는 메서드
	//입력한 값들은 중복되지 않아야한다. 
	private void inputNum() {
		int n1,n2,n3; //입력한 정수가 저장될 변수선언
		
		do {
			System.out.println("숫자입력=>");
			n1 = sc.nextInt();
			n2 = sc.nextInt();
			n3 = sc.nextInt();
			
			if(n1==n2||n1==n3||n2==n3) { //중복 여부 검사
				System.out.println("중복 되는 숫자는 입력할수 없습니다. 다시 입력해주세요.");
			}
		}while(n1==n2||n1==n3||n2==n3);

		//입력받은 순서대로 리스트에 추가한다.
		userlist= new ArrayList<>();
		userlist.clear(); //이전에 추가 했던 자료를 모두 삭제한다. 
		userlist.add(n1);
		userlist.add(n2);
		userlist.add(n3);
		
		
	}
	
	//스트라이크와 볼을 판정하고 출력하는 메서드
	private void ballCount() {
		strike = 0;
		ball = 0;  //스트라이크와 볼의 개수 초기화
		
		for(int i=0;i<numlist.size();i++) {
			for(int j=0;j<userlist.size();j++) { 
				if(numlist.get(i)==userlist.get(j)) {  //먼저 값이같은지
					if(i==j) { //위치가 같은지 검사
						strike++; //같은값에서 위치가 같으면 스트라이크
					}else {
						ball++;  //같은 값에서 위치가 다르면 볼 
					}
					
					
				}//for-j
			}//for -i
			//볼카운트 결과 출력하기
			
		}	System.out.println(userlist.get(0)+","+userlist.get(1)+","+userlist.get(2)+"==>"+strike+"S"+ball+"B");
	}
	
	
}


