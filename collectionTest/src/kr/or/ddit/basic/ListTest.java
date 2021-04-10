package kr.or.ddit.basic;

import java.util.ArrayList;

public class ListTest {

	public static void main(String[] args) {
		//ArrayList는 기본적인 사용법ㅇ이 Vector와 같다
		ArrayList list1= new ArrayList<>();
		
		// add()메서드를 이용해서 데이터 추가한다. 
		list1.add("aaa");
		list1.add("bbb");
		list1.add(123);
		list1.add('k');
		list1.add(false);
		list1.add(123.45);
		
		System.out.println("list의 size :"+list1.size());
		System.out.println("list1:"+list1);
		
		//get()메소드로 데이터터를 꺼내온다
		System.out.println("1번째 자료:"+list1.get(1));
		
		//데이터 끼워넣기
		list1.add(3,"zzzz");
		System.out.println("list1:"+list1);
		
		//데이터 변경하기
		String temp=(String)list1.set(3, "xxxx");
		System.out.println("temp:"+temp);
		System.out.println("list1:"+list1);
		
		//삭제도 같다
		list1.remove(3);
		System.out.println("삭제후 list1:"+list1);
		
		list1.remove("bbb");
		System.out.println("삭제후 list1:"+list1);
		System.out.println("_______________________");

		
		//제네릭을 사용할수 있다
		ArrayList<String>list2= new ArrayList<>();
		list2.add("AAAA");
		list2.add("BBBB");
		list2.add("CCCC");
		list2.add("DDDD");
		list2.add("EEEE");
		System.out.println();
		
		for(int i=0;i<list2.size();i++) {
			System.out.println(i+"==>"+ list2.get(i));
		}
		System.out.println("=======================");
		
		
		
		for(String str :list2) {
			System.out.println(str);
		}
		System.out.println("__________________________________");
		
		
		//contains(비교객체) ==>리스트에 '비교객체가'있으면 true,없으면 false 반환
		System.out.println("DDDD값: "+list2.contains("DDDD"));
		System.out.println("ZZZZ값: "+list2.contains("ZZZZ"));
		
		//indexOf (비교객체)
		//==>리스트에 '비교객체'가 있으면 '비교객체가'있는 위치의 index값이 반환된다.
		// 없으면 -1을 반환한다.
		System.out.println("DDDD의 위치값:"+ list2.indexOf("DDDD"));
		System.out.println("ZZZZ의 위치값:"+ list2.indexOf("ZZZZ")); //데이터가 없어서 -1
		System.out.println("____________________________________");
		
		//toArray()
		//==> 리스트안의 데이터를 배열로 변환하여 반환한다.
		//==> 기본적으로 Object형 배열로 반환한다.
		
		//toArray(new 제네릭타입[0])==>제네릭타입의 배열로 변환한다.
		
		Object[]strArr= list2.toArray();
		
		System.out.println("배열의 개수: "+strArr.length);
		for(int i=0;i<strArr.length;i++) {
			System.out.println(i+"번째 개수"+strArr[i]);
		}
		
		String[] strArr2=list2.toArray(new String[0]);
		for(String str:strArr2) {
			System.out.println(str);
		}
		
		
	}

}
