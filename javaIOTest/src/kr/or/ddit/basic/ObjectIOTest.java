package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectIOTest {
	
	//객체를 파일에 저장하는예제
	public static void main(String[] args) {
		// Member의 인스턴스 생성
		Member mem1= new Member("홍길동",20, "대전");
		Member mem2= new Member("홍길서",30, "서울");
		Member mem3= new Member("홍길남",40, "인천");
		Member mem4= new Member("홍길북",50, "울산");
		
		try {
			//객체 저장하기 
			FileOutputStream fout= new FileOutputStream("d:/D_Other/memObj.bin");
			BufferedOutputStream bout= new BufferedOutputStream(fout);
			ObjectOutputStream oos= new ObjectOutputStream(bout);
			
			//쓰기작업
			System.out.println("객체저장하기 시작...");
			oos.writeObject(mem1);
			oos.writeObject(mem2);
			oos.writeObject(mem3);
			oos.writeObject(mem4);
			System.out.println("객체 저장 작업끝///");
			
			oos.close(); //스트림닫기
			//--------------------------------저장하는 작업
			
			//저장된 객체를 읽어와 그 내용을 화면에 출력하기
			
			//입력용 스트림 객체 생성
			ObjectInputStream ois= new ObjectInputStream(
					new BufferedInputStream(
							new FileInputStream("d:/D_Other/memObj.bin")
							)		
			);
			Object obj; 	//읽어온 객체를 저장할 변수
			
			try {
				System.out.println("객체 읽기작업시작...");
				//readobject()메서드가 데이터의 끝까지 다 읽어오면 EOFException을 발생한다
				while((obj=ois.readObject())!=null) {
					//읽어온 데이터를 원래의 객체형으로 형변환후 사용해야한다.
					Member mem =(Member)obj;
					
					System.out.println("이름:"+mem.getName());
					System.out.println("나이:"+mem.getAge());
					System.out.println("주소:"+mem.getAddr());
					System.out.println("-------------------");
					
				}
				
			} catch (EOFException e) {
				System.out.println("객체 읽기작업끝");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				ois.close();// 파이널리쓰고 스트림닫기
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
//저장용 class 만들기(직렬화처리를 해야하는 class)  이름나이주소
class Member implements Serializable{
	
	//transient ==> 직렬화가 되지 않을 변수에 지정한다.
	//			==> 직렬화가 되지 않는 멤버변수는 기본값으로 저장된다
	//			==> 기본값(참조형 변수: null,숫자형변수:0 , 논리형 변수: false)
	
	private String name;
	//직렬화대상 제외
	private transient int age;
	private transient String addr;
	
	public Member(String name, int age, String addr) {
		this.name = name;
		this.age = age;
		this.addr = addr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
}
