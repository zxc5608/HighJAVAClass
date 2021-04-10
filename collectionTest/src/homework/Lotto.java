package homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Lotto {
	Scanner sc= new Scanner(System.in);

	public static void main(String[] args) {
		
		Lotto si =new Lotto();
		si.lottoStart();

	}
	//로또프로그램이 시작되는 메서드 
	private void lottoStart() {
		while (true) {
			int choice= displayMenu();
			
			switch (choice) {
			case 1: //구매
				lottobuy();
				break;
			case 2: //프로그램 종료
				System.out.println("감사합니다.");
				return;//방법1
		
			default : System.out.println("메뉴번호를 잘못입력 했습니다. 다시입력해주세요");
			}
			
		}
	
	}
	//로또를 구매하는 메서드
	private void lottobuy() {
		System.out.println("lotto구입시작");
		System.out.println("1000원에 로또번호 한줄입니다");
		System.out.println("금액입력: ");
		
		int money =sc.nextInt();
		if(money<1000) {
			System.out.println("금액이 너무 작습니다.로또구입실패!");
			
		}else if(money>=101000){
			System.out.println("금액이 너무많습니다. 로또구입실패!");
			
		}else {
			//로또번호를 생성해서 ㄹ출력하는 메서드 호출
			lottoNum(money);
			
		}
		
	}
	//로또번호를 생성해서 출력하고 거스름돈을 출력하는  메서드 호출
	private void lottoNum(int money) {
		System.out.println("행운의 로또번호는 아래와 같습니다.");
		for(int i=0;i<money/1000;i++) {
			HashSet<Integer> lotto=new HashSet<>();
			while (lotto.size()<6) {
				lotto.add((int)(Math.random()*45+1)); //1~45사이의 난수만들기
				
			}
			//만들어진 로또번호를 list에 넣는다
			ArrayList<Integer>lottolist=new ArrayList<>(lotto);
			// list의 데이터를 정렬한다.
			Collections.sort(lottolist);
			System.out.println("로또번호"+i+":"+lotto);
		}
		
		System.out.println();
		System.out.println("받은금액은 "+money+"원이고 거스름돈은"+ (money%1000)+"원 입니다");
	}
	
	
	//메뉴를 출력하고 작업번호를 입력받아 반환하는메서드
	private int displayMenu() {

		System.out.println("=========================");
		System.out.println("Lotto 프로그램");
		System.out.println("-------------------------");
		System.out.println("1.로또구입 2. 프로그램 종료");
		System.out.println("==========================");
		
		int num = sc.nextInt();
		return num;

	}
}




