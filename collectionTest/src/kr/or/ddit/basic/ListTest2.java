package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

//문제 )5명의 사람 이름을 입력받아서 ArrayList저장한 후에 이들중에서
	//'김'씨 성을 가진 사람의 이름을 모두출력하세요
	//(입력은 Scanner 객체를 이용한다.)
public class ListTest2 {
	public static void main(String[] args) {
		
//		
	Scanner sc= new Scanner(System.in);
	ArrayList<String>name =new ArrayList<>();
		
	for(int i= 0;i<5;i++) {
			System.out.println("입력");
			String name1 = sc.nextLine();
			
			name.add(name1);
							
		}
		System.out.println("이름"+name);
		ArrayList g = new ArrayList<>();
		
		for(int i= 0;i<name.size();i++) {
//		1.	if(name.get(i).substring(0,1) .equals("김")) {
//				//꺼내온다             0부터 1번 이전까지가져온다
//				System.out.println(name.get(i));
//			}
//		2.	if(name.get(i).charAt(0)=='김') {
//				System.out.println(name.get(i));
//				}
//		3.	if(name.get(i).indexOf("김")==0) {
//			          //김자의 위치를 반환
//				System.out.println(name.get(i));
//			}
			if(name.get(i).startsWith("김")==true) {
							//김자로 시작하면 트루가된다
				System.out.println(name.get(i));
			}
			
			
		}
	
		
		ArrayList<String> ab = new ArrayList<>();
		int longnm=0;
		for(int i=0;i<5;i++) {
			System.out.println("입력");
			String nm= sc.nextLine();
			
			ab.add(nm);
		}
		for(int i=0;i<ab.size();i++) {
			if( ab.get(i).length() >  ab.get(longnm).length())
				longnm=i;
		}
		System.out.println("가장긴 별명"+ab.get(longnm) );
		
	
		
	//문제 3 문제2에서 별명의 길이가 같은것이 여러개있을 경우를 처리하시오
		
		int max= ab.get(longnm).length();
		for(int i= 0;i<ab.size();i++) {
		if(ab.get(i).length()==longnm) {
			System.out.println(ab.get(i));
		}
		}

	
	}
	
}
		
		
	

