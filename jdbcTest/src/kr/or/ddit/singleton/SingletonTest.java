package kr.or.ddit.singleton;

public class SingletonTest {

	public static void main(String[] args) {
		//MySingleton test1= new MySingleton();  //외부에서 new 명령으로 생성불가 
		
		MySingleton test2= MySingleton.getInstance();   //.getInstance()로 불러와야한다 싱글톤 특징
		MySingleton test3= MySingleton.getInstance();   

		System.out.println("test2="+test2.toString());
		System.out.println("test3="+test3.toString());
		System.out.println();
		
		System.out.println(test2==test3);
		System.out.println(test2.equals(test3));
		
		test2.displayTest();
	}

}
