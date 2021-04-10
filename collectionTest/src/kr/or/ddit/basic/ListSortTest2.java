package kr.or.ddit.basic;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest2 {

	public static void main(String[] args) {
		ArrayList<Member> memList= new ArrayList<>(); //멤버객체를 저장
		
		memList.add(new Member(1,"홍길동","010-1111-1111"));
		memList.add(new Member(5,"이순신","010-2222-1111"));
		memList.add(new Member(10,"성춘향","010-3333-1111"));
		memList.add(new Member(3,"강감찬","010-4444-1111"));
		memList.add(new Member(6,"일지매","010-5555-1111"));
		memList.add(new Member(2,"변학도","010-6666-1111"));
		
		System.out.println("처음 데이터....");
		for(Member mem: memList) {
			System.out.println(mem);
		}
		System.out.println("-----------------------------");
		
		Collections.sort(memList);
		
		System.out.println("회원 이름의 오름차순후 정렬....");
		for(Member mem: memList) {
			System.out.println(mem);
		}
		System.out.println("-----------------------------");
		
		Collections.sort(memList,new SortNumDesc());
		
		System.out.println("회원 이름의 오름차순후 정렬....");
		for(Member mem: memList) {
			System.out.println(mem);
		}
		System.out.println("-----------------------------");
	}

}
//내부정렬기준  . 사용자가 작성하는 클래스 내부에 정렬기준을 지정하려면 Comparable 인터페이스를 구현해서 작성해야한다.
//(내부정렬기준을 넣어서 클래스를 작성한다.

//Comparable 인터페이스는 compareTo()메서드가 선언되어있다.
//그래서 이compareT()메서드를 재정의 해서 정렬 기준을 지정한다 

//예) Member클래스의 회원이름을 기준으로 오름차순이 되도록 하는 내부 정렬 기준 추가하기 
// (Comparable 인터페이스를 구현해서 작성한다)
class Member implements Comparable<Member>{
	private int num;
	private String name;
	private String tel;  //alt +shift + s누르고 using filed
	
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}
	
	@Override
	public int compareTo(Member mem) {
		return this.getName().compareTo(mem.getName());
	}
	
	
}

// Member 클래스의 회원번호 (num)의 내림차순으로 정렬할 외부 정렬 기준클래스작성 
class SortNumDesc implements Comparator<Member>{
	
	public int compare(Member mem1, Member mem2) {
//		if(mem1.getNum()>mem2.getNum()) {
//			return -1;
//		}else if(mem1.getNum()==mem2.getNum()) {
//			return 0;
//		}else {
//			return 1;
//		}
		//Wrapper클래스를이용하는 방법
//		return new Integer(mem1.getNum()).compareTo(mem2.getNum())*-1;
		
		//Wrapper 클래스를 이용하는 방법 2
		return Integer.compare(mem1.getNum(),mem2.getNum())*-1;
		
	}
}