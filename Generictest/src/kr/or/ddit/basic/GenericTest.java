package kr.or.ddit.basic;

/*
 * -제네릭을 사용하는 클래스를 만드는 방법
 * 형식)
 * class 클래스명 <제네릭 타입글자>{
 * 		제네릭타입글자 변수명; 		//변수 선언에 제네릭을 사용할경우
 * 		...
 * 		제네릭타입글자 메서드명(매개변수들...){  //메서드의 반환값으로 제네릭을 사용할경우 
 * 		실행문들
 * 		return 값;		
 * 		}
 * 		...
 * 			
 * 		반환값 타입 메서드명(제네릭타입글자 변수명){  //메서드의 매개변수에 제네릭을 사용할 경우
 * 			실행문들...
 * 			
 * 			return 값;
 * 	
 *		 }
 * }
 *   //제네릭 타입 글자로 사용하는 것들
 *   T ==>Type
 *   K ==>Key
 *   V ==> Value
 *   E ==> Element
 * 
 */
//제네릭을 사용하는 class
class GenericClass<T>{
	private T val;
	
	public void setVal(T val) {
		this.val =val;
	}
	public T getVal() {
		return val;
	}
}

//제네릭을 사용하지 않는 클래스
class NonGenericClass{
	private Object val;
	
	public void setVal(Object val) {
		this.val=val;
	}
	public Object getVal() {
		return val;

	}
}
public class GenericTest {

	public static void main(String[] args) {
		//클래스객체만듬
		NonGenericClass ng1=new NonGenericClass();
		ng1.setVal("가나다라");
		
		NonGenericClass ng2= new NonGenericClass();
		ng2.setVal(100);
		
		String str1 = (String)ng1.getVal(); //데이터를 꺼내온다// 형 변환을 반드시 해주어야한다
		System.out.println("문자열 반환값 str1="+str1);
		Integer str2= (Integer)ng2.getVal();
		System.out.println("정수형반환값 str2="+str2);
		System.out.println("-----------------------------");
		System.out.println();
		
		
		//
		
		GenericClass<String> gc1 = new GenericClass();
		GenericClass<Integer> gc2 = new GenericClass();
		
		gc1.setVal("우리나라"); //제네릭과  다른 데이터를 사용하면 오류가난다
		
		gc2.setVal(100);
		
		String str3= gc1.getVal();   //형변환을 해줄필요가 없디
		int num2=gc2.getVal();
		
		System.out.println("제네릭 문자열 반환값 str3="+str3);
		System.out.println("제네릭 정수형 반환값 num2="+num2);
	}

}
