package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Maptest {

	public static void main(String[] args) {
		/*
		  Map==> key값과 value값을 한쌍으로 관리하는 객체
		   - key값은 중복을 허용하지 않고 순서(index)가 없다.(Set 의 특징을 가지고있다.)
		   - value 값은 중복을 허용한다. 
		 
		 */
		HashMap<String,String>map= new HashMap<>();
		
		// 자료추가하기 ==> put(key값,value값)
		map.put("name","홍길동");
		map.put("addr", "대전");
		map.put("tel","010-2233-1123");
		
		System.out.println("map의 데이터:"+map);
		
		//자료 수정 ==>Map의 데이터를 수정하려면 put()메서드의 key값이 같게해서 추가한다.
		//		==> Map은 key값이 같으면 나중에 추가한 데이터가 남는다.
		map.put("addr", "서울");
		System.out.println("map의 데이터:"+map);
		
		//자료삭제 ==>remove(key값): key값이 같은 자료를 찾아서 삭제한다.
					//반환값: 삭제된 데이터의value값이 반환된다.
		
//		String moveTel= map.remove("tel");  //지운값을 문자열방에 
//		System.out.println("삭제후map:"+map);
//		System.out.println("삭제된value:"+moveTel); 
		
		//자료 읽기 ==> get(key값) : key값이 같은자료의 value값을 반환한다./////////
		//			==>key값이 없을 떄는 null을 반환한다.
		System.out.println("이름:"+map.get("name"));
		System.out.println("번호:"+map.get("tel"));
		System.out.println("주소:"+map.get("addr"));
		
		//key값의 존재여부를 확인하는 메서드: containsKey(key값)
		// 			=> 해당 key값이 있으면 true, 없으면  false를 반환한다.
		System.out.println("name 키값의 존재여부:"+map.containsKey("name"));
		System.out.println("age 키값의 존재여부:"+map.containsKey("age"));
		System.out.println();
		
		
		//Map에 저장된 데이터를 차례로 읽어와 데이터를 처리하는 방법
		//방법1) keyset()메서드이용하기
		// 		keyset()==> map의 키값들을 읽어와 Set형으로 반환한다.
		Set<String> keyset= map.keySet();
		
		Iterator<String> it= keyset.iterator();
		while (it.hasNext()) {
			String key=it.next(); //키값 가져오기
			String value = map.get(key);
			System.out.println(key+":"+value);
			
			
		}
		System.out.println();
		
		//방법2) keySet을 향상된for문으로 처리하기         *&*^제일 많이쓴다
		for(String key:map.keySet()) {
			String value= map.get(key);
			System.out.println(key+":"+value);
			
		}
		
		System.out.println();
		
		//방법3)value값만 읽어와서 처리하기 ==> value()메서드 이용   //value값만 나온다
		for(String value:map.values()) {
			System.out.println(value);
		}
		System.out.println("----------------------");
		/*
		  방법 4) ==> Map에는 Entry라는 내부 class 가 만들어져 있다
		  			이 Entry클래스는 key와value라는 멤버 변수로 구성되어있다.
		  			Map에서는 이 Entry클래스들을 Set형식으로 저장해서 관리한다.
		 	-Entry객체 전체를 가져와서 처리하기(==> 가져온 Entry들은 Set형식으로 되어 있다.)
		 	==> entrySet()메서드 이용
		 */
		//Entry라는 내부객체 전체 가져오기
		Set<Map.Entry<String,String>>mapSet= map.entrySet();
		Iterator<Map.Entry<String,String>> entryIt=mapSet.iterator();
		 while (entryIt.hasNext()) {
			 Map.Entry<String,String> entry =entryIt.next();
			 //entry객체에서 key값과 value값 구분하기
			 System.out.println("key값:"+entry.getKey());
			 System.out.println("value값:"+entry.getValue());
			 System.out.println();
			
			
		}
	}

}
