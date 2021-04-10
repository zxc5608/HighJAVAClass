package kr.or.ddit.basic;

public class ArgsTest {
/*
 * 가변형인수==> 메서드의 매개변수에 주어지는 인수의 개수 실행될때마다 다른때 사용한다.
 * 	-가변형인수는 메서드 내부에서는 배열로 처리한다.
 *  -가변형 인수는 한기지 자료형만 사용할수있다.
 */
	public static void main(String[] args) {
		ArgsTest test =new ArgsTest();
		
		int[] num= {100,200,300};
		int[] num2= new int[] {100,200,300};
		
		System.out.println(test.sumData(num));
		System.out.println(test.sumData(new int[] {1,2,3,4,5}));
		System.out.println("----------------------------------");
		System.out.println(test.sumArgs(100,200,300));
		System.out.println(test.sumArgs(1,2,3,4,5));
		System.out.println(test.sumArgs2("이순신",80,90,40,10
				));
		
//		int k =100;
//		test.test1(k);

	}
//	public void test1(int a) {
//		
//	}
	
	//매개 변수들의 합을계산해서 반환하는 메서드 ==>배열을 이용한메서드
	public int sumData(int[] data) {
		int sum =0;
		for(int i=0;i<data.length;i++) {
			sum +=data[i];
		}
		return sum;
	}
	
	//매개 변수들의 합을계산해서 반환하는 메서드 ==>가변형 인수를 이용한메서드]
	// 가변형 인수와 일반인수를 같이 사용할 경우에는 가변형인수를 뒤쪽에 배치한다 
	public int sumArgs(int...data) {
		//이 메서드 안에서 변수 'int...data'는'int[] data와 같다 .즉 배열과 같다
		int sum=0;
		for(int i=0;i<data.length;i++) {
			sum +=data[i];
		}
		return sum;
	}
	// 가변형 인수와 일반인수를 같이 사용할 경우에는 가변형인수를 뒤쪽에 배치한다
	public String sumArgs2(String name, int...data) {
		
		int sum=0;
		for(int i=0;i<data.length;i++) {
			sum +=data[i];
		}
		return name+"씨의 합계"+sum;
	
	}
}
