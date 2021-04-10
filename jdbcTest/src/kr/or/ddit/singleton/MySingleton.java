package kr.or.ddit.singleton;
/*
 - singleton 패턴 ==> 객체가 1개만 만들어지게 하는 방법
  					(외부에서 new명령을 사용하지 못하게 한다.)
 - singleton클래스를 만드는 방법(필수 구성 요소)
   1.자신 class에 참조값이 저장될 변수를 private static으로 선언한다.
   
   2. 생성자의 접근제한자를 private으로 한다.
   
   3.자신 class의 인스턴스를 생성하고, 생성된 인스턴스의 참조값을 반환하는 메서드를
   	public static 으로 작성한다. 
   	(이 메서드의 이름은 보통 genInstance()로 한다. 
   
  					
 */
public class MySingleton {
	//1번
	private static MySingleton single;

	//2번
	private MySingleton() { 
		System.out.println("생성자 입니다.");
		
	}
	//3번
	
	public static MySingleton getInstance() {
		
		if(single==null) single =new MySingleton();
		
		return single;
		
	}
	
	//기타 이 클래스가 처리할 내용을 작성한다.
	public void displayTest() {
		System.out.println("이 메서드는 싱글톤 클래서의 메서드 처리 내용입니다");
	}
}
