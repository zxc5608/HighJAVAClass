package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class SetTest {

	public static void main(String[] args) {
		/*
		 * -List와 Set의 차이점
		 * 
		 * 1. List 특징 - 데이터의 순서(index)가 있다 - 중복되는 데이터를 저장할수있다. 2. Set - 데이터의 순서(index)가
		 * 없다 - 중복되는 데이터를 저장할 수 없다.
		 * 
		 * 
		 */
		HashSet hs1 = new HashSet<>();

		// Set에 데이터를 추가할때 add() 메서드를 사용한다.
		// add()메서드의 반환값: 추가성공 true, 추가실패 false
		hs1.add("DD");
		hs1.add("AA");
		hs1.add(2);
		hs1.add("CC");
		hs1.add("BB");
		hs1.add(1);
		hs1.add(3);

		System.out.println("Set 데이터:" + hs1);
		System.out.println("Set의 개수" + hs1.size());

		// Set에 중복되는 데이터를 추가하면false를 반환하고 데이터는 추가되지 않는다.

		boolean isAdd = hs1.add("FF");
		System.out.println("중복되지 않을때 :" + isAdd);
		System.out.println("Set데이터:" + hs1);
		System.out.println();

		isAdd = hs1.add("CC");
		System.out.println(" 중복될때 :" + isAdd);

		// Set의 데이터를 수정하려면 수정하는 명령이 따로 없기때 문에
		// 수정하려는 데이터를 삭제한 후에 새로운데이터를 추가하는 방법을 사용한다.

		// 삭제하는 메서드: remove (삭제할 데이터)
		// 반환값: 삭제 성공이면(true) 삭제 실패면(false)
		// 전체 자료삭제 메서드: clear();

		// "FF"데이터를 "EE"라는 데이터로 변경하기

		hs1.remove("FF");
		System.out.println("삭제 후 Set데이터:" + hs1);
		hs1.add("EE");
		System.out.println("Set데이터:" + hs1);
		System.out.println();

		// hs1.clear();
		// System.out.println("clear후 Set데이터:"+hs1);
		// System.out.println();

		/*
		 * Set데이터는 순서 (index) 가 없기 떄문에 List처럼 index로 데이터를 하나씩 불러올수 없다. 그래서 데이터를 하나씩 얻기
		 * 위해서는 Iterator 형의 객체로 변환해야한다.
		 * 
		 * -Set형의 데이터 들을 Iterator형 객체로 변환해주는 메서드 ==> iterator()
		 * 
		 */
		Iterator it = hs1.iterator(); // Set 데이터를 Iterator 로 변환하기

		// Iterator의 hasnext()메서드
		// ==> Iterator의 포인터가 가리키는 것의 다음번쨰 자리에 데이터가 있으면 true,
		// 없으면 false가된다

		// Iterator 의 next()메서드
		// ==> Iterator의 포인터를 다음번째 위치로 이동한 후 그곳의 데이터를 반환한다.

		while (it.hasNext()) {
			System.out.println(it.next());
		}
		System.out.println("--------------------------------");
		// -----------------------------
		// 향상된 포문을 사용하면 Iterator를 사용하지 않고 처리할수있다

		for (Object obj : hs1) {
			System.out.println(obj);
		}

		// ------------------------------

		// 우리반 학생들중 번호를 이용하여 추첨하는 프로그램작성하기
		// 번호는 1번부터 25번까지 있고 ,추첨할 인원은 3명이다.
		// 당첨자를 출력하시오.

		HashSet<Integer> testSet = new HashSet<>();

		while (testSet.size() < 6) {
			testSet.add((int)(Math.random()*(49-1+1)+1));

		}
		System.out.println("당첨자 번호:"+testSet);
		
		/*
		        최소값~최대값 사이의 정수형 난수만들기
		    (int)Math.random()*(최대값-최소값+1)+최소값)
		 */
System.out.println();
		//Set유형의 자료를 List형으로 변환하기
		
		ArrayList<Integer> testList=new ArrayList<>(testSet);
		
		System.out.println("List 데이터출력...");
		for(int i=0;i<testList.size();i++) {
			System.out.println(testList.get(i));
		}
		
	}

}
