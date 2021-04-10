package kr.or.ddit.basic;

import java.util.Vector;

public class VectorTest {

	public static void main(String[] args) {

		Vector v1 = new Vector();

		System.out.println("크기" + v1.size());
		// 데이터 추가 : add(추가할 데이터)
		// 반환 값 : 추가 성공(true), 실패 (false)

		v1.add("aaaa");
		v1.add(new Integer(111)); // 정수형을 사용하고싶으면
		v1.add(123); // 얘도
		v1.add('a');
		v1.add(true);

		boolean r = v1.add(3.14);
		System.out.println("현재크기:" + v1.size());
		System.out.println("반환값:" + r);

		// 데이터 추가2 : addElement(추가할 데이터) ==> 이전 버전에서 사용하던 메서드
		// 이전버전의 프로그램도 사용할수있도록 하기 위해서 남아있는 메서드
		v1.addElement("CCC");

		System.out.println("v1:" + v1); // toString은 생략가능

		// 데이터 추가 3: add(index , 데이터)
		// ==> 'index' 번째에 '데이터'를 끼워 넣는다
		// ==> 'index' 는 0부터 시작한다
		// ==> 반환값이 없다

		v1.add(1, "kkk");
		System.out.println("v1:" + v1); // 끼워넣음

		// 데이터 수정: set(index, 새로운데이터);
		// ==>'index' 번쨰의 데이터를 '새로운 데이터'로 덮어쓴다
		// ==> 반환값 : 원래의 데이터 (변경전 데이터)

		String temp = (String) v1.set(0, "zzzz");
		System.out.println("v1:" + v1);
		System.out.println("반환값(원래의 데이터) :" + temp); // zzzz로 바뀌기 전 데이터

		// 데이터 삭제: remove(index)
		// ==> 'index'번째의 데이터를 삭제한다.
		// ==> 반환값 : 삭제된데이터

		v1.remove(0);
		System.out.println("삭제후 v1:" + v1);
		System.out.println();

		temp = (String) v1.remove(0);
		System.out.println("삭제된 자료:" + temp);
		System.out.println("삭제후 v1:" + v1);

		// 데이터 삭제 :remove (삭제할 데이터)
		// ==> '삭제할 데이터'를 찾아서 삭제한다
		// ==> '삭제할데이터' 가 여러개이면 앞에서 부터 한번에 하나씩 삭제된다.
		// ==> 반환값: 삭제성공(true) 삭제실패 (false)
		// ==> 삭제할 데이터가'정수형'이거나 'char'형인경우에는 반드시 객체 형으로 변환해서 사용해야한다.
		v1.remove("CCC");
		System.out.println("삭제후 v1:" + v1);

		// v1.remove(123);
		v1.remove(new Integer(123));
		System.out.println("삭제후 v1:" + v1);

		// v1.remove('a');
		v1.remove(new Character('a'));
		System.out.println("삭제후 v1:" + v1);

		v1.remove(3.14);
		v1.remove(true);
		System.out.println("삭제후 v1:" + v1);

		// 데이터 꺼내오기 :get(index)
		// ==> 'index'번째 데이터를 반환한다.
		System.out.println("--------------");

		int data = (int) v1.get(0);
		System.out.println("0번째 자료:" + data);

		System.out.println("==================================");
		/*
		 * 제네릭타입(generic Type)==> 객체를 선언할 떄 <> 괄호 안에 Collection이 사용할 데이터의 타입을 정해주는 것을
		 * 말한다 이런식으로 객체를 선언하게 되면 그 데이터 타입 이외의 다른 종류의 데이터를 저장할수없다. 단 . 제넥릭으로 선언 될수 있는 데이터
		 * 타입은 클래스형 이어야한다. 그래서, int는 Integer ,boolean은boolean ,char은 charactor등으로 대체해서
		 * 사용해야한다 제네릭 타입으로 선언하게 되면 데이터를 꺼내올때 형변환이 필요없다.
		 * 
		 */
		Vector<String> v2 = new Vector<>(); // String만 저장할수있는 Vector객체 선언
		Vector<Integer> v3 = new Vector<>(); // int형 데이터만 저장할 Vector객체 선언

		v2.add("안녕하세요");
//		v2.add(123); // 오류: 제네릭 타입과 다른 종류의 데이터

		String temp2 = v2.get(0);
		
		Vector<Vector>vv =new Vector<>();
		Vector<Vector<Vector>> vvv = new Vector<>();
		
		System.out.println("v2의 clear 전 size:"+v2.size());
		//데이터 전체 삭제: clear()
		v2.clear();
		System.out.println("v2의 clear 후 size:"+v2.size());
		System.out.println("-----------------------------------------");
		
		v2.add("AAA");
		v2.add("BBB");
		v2.add("CCC");
		v2.add("DDD");
		v2.add("EEE");
		 
		Vector<String> v4= new Vector<>();
		v4.add("BBB");
		v4.add("EEE");
		
		System.out.println("v2:"+v2);
		System.out.println("v4:"+v4);
		
		//데이터 삭제: removeAll(Collection객체)
		// ==> 'Collection객체'가 가지고있는 데이터를 Vector에서 찾아서 모두 삭제한다.
		//==> 반환값: 작업성송(true), 작업실패(false)
		
		v2.removeAll(v4);  // v4가 가지고있는 것을 v2에서 지워라
		System.out.println("삭제후 v2:"+v2);
		System.out.println("삭제후 v4:"+v4);
		System.out.println("_____________________");
		
		v2.clear();
		v2.add("AAA");
		v2.add("BBB");
		v2.add("CCC");
		v2.add("DDD");
		v2.add("EEE");
		
		//벡터의 데이터를 순서대로 가져와 모두 사용하고 싶으면 반복문을 사용하면 된다. 
		//(주로 for문을 사용한다)
		
		for(int i=0;i<v2.size();i++) {
			System.out.println(i+"번째 자료:"+v2.get(i));
		}
		
		System.out.println("------------------------------");
		
		//향상된 for문
		
		for(String str: v2) {
			System.out.println(str);
		}
		
	}

}
