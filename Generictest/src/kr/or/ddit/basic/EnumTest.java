package kr.or.ddit.basic;
/*

	enum(열거형)==> 서로 관련있는 상수들의 집합을 나타낸다. 
			  ==> 클래스 처럼 보이게하는 상수
			  ==> 열거형은 클래스처럼 독립된java 파일에 만들수 있고,
			  	    하나의 java파일에 다른 클래스와 같이 만들수있고,
			  	    클래스 안에 내부클래스처럼 만들수있다. 
			  	    
	-열거형의 속성 및 메서드 
	name()	 	==> 열거형의 상수의 이름을 문자열로 반환한다. 
	ordinal()	==> 열거형의 상수가 정의된 순서값(일종의 index값)을 반환한다.(0부터시작)
	valueOf("열거향상수명") 	==> 지정된 열거형에서 '열거형 상수명'과 일치하는 상수를 반환한다.
	열거형이름. 상수명 ==> valueOf("상수명")와 같다. 
	
	-열거형 선언하기 
	방법1)
		enum 열거형 이름{상수명1,상수명 2,.... }
			  	  
	방법2)
		enum 열거형 이름{
			상수명1(값들...),
			상수명2(값들...),
			...
			상수명n(값들...);
			//'값'들이 저장될 변수를 선언한다.(보통 private로 선언한다.)
			 private 자료형 이름 변수명 ;
			 
			 //열거형의 생성자를 만든다. 
			 //열거형의 생성자는 '열거형 상수'값들'을 셋팅하는 역할을 수행한다.
			 //열거형 생성자는 묵시적으로 private이다. 
			  
			  //'변수명'은'값들' 과 개수가 같고 , 각각의 '값들'과 자료형이 맞아야한다
			  private 열거형이름 (자료형 변수명, ...){
			  	위에 선언된 변수들의 초기화작업...
				}
				//구성된 '값들'을 외부에서 불러올수 있는 getter메서드를 만든다.
				//(위에 선언된 변수들의 getter를 만든다.)
				 
*/
public class EnumTest {
	//1.생성자
	public enum Color {RED,green,blue}
	
	public enum Num{one,two,three}
	
	public enum Season{
		봄(3,"5월"),   //상수명(값들...) 형식의 선언
		여름(6,"8월"),
		가을(9,"11월"),
		겨울(12,"2월");
		
		private int startMonth;//값들이 저장될 변수선언
		private String endMonth;
		
		//생성자
		Season(int sMonth,String eMonth){ //private Season(int sMonth,String eMonth){ 와 같다.
			startMonth= sMonth;
			endMonth= eMonth;
			
			
		}
		//getter 메서드작성
		public int getstartMonth() {
			return startMonth;
		}
		public String getEndMonth() {
			return endMonth;
		}
		
		
		
		
	}
	public static void main(String[] args) {
		/*
		System.out.println("red:"+ConstTest.RED);
		System.out.println("three:"+ConstTest.three);

		if(ConstTest.RED==ConstTest.two) {
			System.out.println("~~~~~");
		}else {
			System.out.println("@@@@@@");
		}*/
		
		
		//열거형이름 변수 = 열거형이름  . 함수
		Color mycol=Color.valueOf("green");  //방법1
		Color mycol1= Color.green;		//방법2
		
		Num myNum=Num.one;
		
		System.out.println("mycol:"+mycol.name());
		System.out.println("myname:"+myNum.name());
		System.out.println();
		
		System.out.println("mycol의 ordinal:"+mycol.ordinal());
		System.out.println("myNum의 ordinal:"+myNum.ordinal());
		System.out.println();
		//서로 다른 종류의 열거형끼리의 비교는 불가하다. 
//		if (Color.blue == Num.three) {
//			System.out.println("............");
//		}
		if(mycol==Color.RED) {
			System.out.println("같다");
			
		}else {
			System.out.println("다르다");
		}
		
		switch (mycol) {
		case RED:		//case문 옆에 지정한 '상수명'은 '열거형.상수명'에서'열거형'이름을 빼고 작성한다.
			System.out.println("red");
			break;
		case blue:
			System.out.println("blue");
			break;
		case green:
			System.out.println("green");
			break;

		}
		System.out.println();
		
		//방법2
		//Season ss= Season.valueOf("봄");
		Season ss= Season.가을;
		System.out.println("name: "+ss.name());
		System.out.println("ordinal:"+ss.ordinal());
		System.out.println("startMonth:"+ss.getstartMonth());
		System.out.println("endMonth: "+ss.getEndMonth());
	
		//열거형명.values() ==> 열거형에 있는 모든 상수들을 배열로 가져온다.
		for(Season time:Season.values()) {
			System.out.println(time.name()+"==>"+time+"==>"+time.getstartMonth()+"월부터"+time.getEndMonth()+"까지");
		}						// 네임 			//상수명
		System.out.println();
		
		for(Color col: Color.values()) {
			System.out.println(col+"==>"+col.ordinal());
		}
	
	}
	

}
