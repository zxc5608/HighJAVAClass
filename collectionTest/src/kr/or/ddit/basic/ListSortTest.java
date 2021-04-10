package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest {
	public static void main(String[] args) {
		
		ArrayList<String>list= new ArrayList<>();
		
		list.add("일지매");
		list.add("김봉준");
		list.add("감스트");
		list.add("킹기훈");
		list.add("킴성태");
		 
		System.out.println("정렬전:"+list);
		
		//정렬은 Collection.sort()메서드를 이용하여 정렬한다.
		//문자열의 정렬은 기본적으로 오름차순으로 정렬을 수행한다.
		Collections.sort(list);
		System.out.println("정렬 후:"+list);
		
		Collections.shuffle(list); //데이터 섞기
		System.out.println("자료 섞기후 :"+list);
		
		//외부 정렬 기준을 클래스를 지정해서 정렬하기
		Collections.sort(list,new Desc());
		System.out.println("내림차순 정렬후 :"+list);
		
		
	
}
}


// 정렬 방식을 정해주는Class를 작성한다.(*외부 정렬 기준 클래스라고 한다.)
//Comparator 인터페이스를 구현해서 작성한다
class Desc implements Comparator<String>{
	//compare()메서드를 재 정의 해서 정렬하고자 하는 기준을 정한다.
	
	//compare() 메서드의 반환값
	//반환값이 0 ==> 두 값이 같다. 
	//반환값이 양수 ==> 앞 ,뒤 값의 순서를 변경한다.
	//반환값이 음수==> 순서를 변경하지 않는다
	
	//예 ) 오름차순일 경우 ==> 앞의 값이 크면 양수, 같으면 0 , 뒤의값이 크면 음수를반환하도록 하면된다
	//String 객체에는 정렬을 위해서 compareTo()메서드가 구현되어있는데
	//이 메서드의 반환값은 오름차순에 맞게 반환하도록 구현되어있다
	//형식) 앞문자열 .compareTo(뒤 문자열);
	
	//(Wrapper클래스와 Date, File클래스에도 구현이되어있다)
	
	@Override
	public int compare(String str1, String str2) {
		//내림차순으로 정렬되도록 구현하기
//		if(str1.compareTo(str2)>0) {
//			return -1;
//		}else if(str1.compareTo(str2)==0) {
//			return 0;
//		}else {
//			return 1;	
//		}
		
		return -str1.compareTo(str2)*-1;
	}
	
}