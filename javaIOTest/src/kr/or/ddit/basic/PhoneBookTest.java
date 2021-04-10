package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class PhoneBookTest {

	/*
	  문제: 이름, 주소, 전화번호를 멤버로 갖는 phone클래스를 만들고1
	  	Map을 이용해서 전화번호 정보를 관리하는 프로그램을 작성하세요
	  	이 프로그램에는 전화번호를 등록, 수정 ,삭제 ,검색,전체를 출력하는 기능이있다
	  	
	  	(Map의 구조는 key값으로 '이름'을 사용하고, value값으로 'phone클래스의 인스턴스'로 한다.)
	  	
	  	-삭제, 검색기능은 '이름'으로 입력받아 처리한다.
	  	
	  	-추가조건)
	  	1)메뉴에 '6. 전화번호 저장 메뉴'를 추가하고 전화번호를 저장하는 기능을 구현한다
	  	(저장 파일명: phoneData.dat)
	  	2)프로그램이 시작될때 저장된 파일이 있으면 그 데이터를 읽어와 Map에 저장한다.
	  	3) 프로그램을 종료할때 전화번호 데이터가 변경되거나 추가 또는 삭제 되었으면
	  	변경된 데이터를 저장한 후에 종료되도록한다.
	  	(즉. 데이터가 변경되었는데 저장이되지 않는 상태이면 저장한다) 
	  	
	  	--------------------------------
	  
	 */
	Scanner sc= new Scanner(System.in);
	static HashMap<String, phone>board=new HashMap<>();
	private String fileName= "d:/D_Other/phoneData.dat";
	//데이터가 변경되었는지 여부를 나타내는 변수선언
	private static boolean dataChange;
	public PhoneBookTest() {
		board = load();
		
		if(board==null) {
			board=new HashMap<>();
		}
	}
		
	
	public static void main(String[] args) {
		
		PhoneBookTest pb= new PhoneBookTest();
		pb.phonestart();
			
	}
	private void phonestart() {
		System.out.println("전화번호관리 프로그램");
		System.out.println("+++++++++++++++++++++++++++");
		System.out.println("");
		
		while (true) {
			int choi= display();
			
			switch (choi) {
			case 1:
				phoneinsert();
				break;
			case 2:
				phoneupdate();
				break;
			case 3:
				phonedelete();
				break;
			case 4:
				search1();
				break;
			case 5:
				view();
				break;
			case 6:
				save(); //전화번호 저장메뉴
				break;
			case 0:
				
			System.out.println("프로그램종료");
				return;
			default :System.out.println("번호잘못입력");
				
			
			}
		}
	}
	
	private int display() {
		board.put("홍길동",new phone("홍길동","010-3404-5608","대전중구대흥동"));
		System.out.println("----------------------------");
		System.out.println("다음메뉴를 선택하세요");
		System.out.println("1.전화번호 등록");
		System.out.println("2.전화번호 수정");
		System.out.println("3.전화번호 삭제");
		System.out.println("4.전화번호 검색");
		System.out.println("5.전화번호 전체출력");
		System.out.println("6.전화번호 저장메뉴");
		System.out.println("0.프로그램 종료 ");
		System.out.println("---------------");
		
		
		int num= sc.nextInt();
		return num;
	}
	//파일에 저장된 전화번호 정보를 읽어오는 메서드
	public HashMap<String,phone> load(){
		HashMap<String,phone>pMap= null; 	//읽어온 데이터가 저장될 변수
		
		File file= new File(fileName);
		//저장된파일이없으면
		if(!file.exists()) {
			return null;
		}
		//저장된 파일이 있으면 처리되는 곳...
		ObjectInputStream ois= null;
		try {
			//입력용 스트림 객체생성
			ois= new ObjectInputStream(
					new BufferedInputStream(
						new FileInputStream(fileName)));
			
			pMap= (HashMap<String, phone>) ois.readObject();
			
			return pMap;
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
			return null;
			
		}finally {
			if(ois!=null)try {ois.close();}catch (IOException e) {}
		}
		
	}
	
	
	//전화번호 정보를 파일에 저장하는 메서드
	private void save() {
		ObjectOutputStream oos=null;
		try {
			//객체를 출력하기위한 출력용 스트림생성
			oos= new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(fileName))
					);
			//Map객체를 파일로 저장한다
			oos.writeObject(board);
			System.out.println("저장이 완료되었습니다");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			//사용했던 스트림 닫기
			if(oos!=null)try {oos.close();}catch(IOException e) {}
		}
		
	
	}
	private void view() {
		System.out.println("---------------------");
		if(board.size()==0) {
			System.out.println("등록된 데이터가 없습니다");
		}else {
		
		int count=1;              /////////////////////선생님꺼 따라해보기
		for(String key:board.keySet()) {
			phone value =board.get(key);
			
			System.out.println(count+"\t"+key+"\t"+value);
			count++;
		}
		}
	}
	private void search1() {
		Scanner sc= new Scanner(System.in);
		System.out.println("검색할 이름:");
		String name= sc.nextLine();
		//등록된 전화번호정보가 있는지검사
		
		if(!board.containsKey(name)) {
			System.out.println(name+"씨의 전화번호 정보는 없습니다");
			return;
		}
		// 검색한 데이터가 있으면 해당 key값에 맞는 valuㄷ값을 구한다
		phone p= board.get(name);
		
		System.out.println("-------------------------");
		System.out.println("name\t tel\t addr");
		System.out.println(name+"\t"+board.get(name));
		System.out.println("-------------------------");
		
		}
	
	private void phonedelete() {
		Scanner sc =new Scanner(System.in);
		System.out.println("삭제할 이름");
		System.out.println("이름:");
		String name= sc.nextLine();
		if(!board.containsKey(name)) {
			System.out.println(name+"씨의 전화번호 정보는 없습니다");
			System.out.println("삭제작업불가!");
			return;
		}
		board.remove(name);
		System.out.println(name+"씨의 전화번호 정보를 삭제했습니다.");
	
		
		
		System.out.println("삭제되었습니다");
	}
	//전화번호 정보를 수정하는메서드 
	private void phoneupdate() {
		Scanner sc= new Scanner(System.in);
		System.out.println("수정할 정보를 입력하세요");
		
		System.out.println("이름:");
		String name=sc.next();
		
		//수정할 데이터 있는지 검사
		if(!board.containsKey(name)) {
			System.out.println(name+"씨의 전화번호 정보가 없습니다.");
			System.out.println("수정작업 불가!!");
			return;
		}
		System.out.println("새로운 전화번호:");
		String newtel=sc.next();
		
		System.out.println("새로운 주소:");
		String newaddr=sc.nextLine();
		
		//방법1
		//같은 키값에 새로운 전화번호 정보를 저장==> 수정작업
		/*board.put(name, new phone(name,newtel, newaddr));
		*/
		//방법2
		phone p = board.get(name);// 키값을 이용해서value값을 구한다
		p.setTel(newtel); 	//구해온 phone객체의 각 각 데이터를 변경한다
		p.setAddr(newaddr); //
		
		System.out.println(name+"씨의 전화번호정보를 변경하였습니다.");
		
		
		System.out.println("게시물이 수정되었습니다");
	}
	
	private static void phoneinsert() {
		Scanner sc= new Scanner(System.in);

		System.out.println("이름:");
		String name=sc.nextLine();
		
		//이미 등록된 사람인지 검사
		if(board.containsKey(name)) {
			System.out.println(name+"씨는 이미등록된 사람입니다");
			return;  //메서드를 끝낸다
		}
		System.out.println("전화번호:");
		String tel=sc.nextLine();
		
		//입력버퍼비우기 
		sc.nextLine();
		
		System.out.println("주소:");
		String addr=sc.nextLine();
		
		board.put(name, new phone(name, tel, addr));
		System.out.println(name+"등록완료");
		dataChange= true;
	}
	/*
		-Scanner의 입력받는 메서드의 특징
		1. next(), nextInt() ,nextDouble()...
			==> 데이터를 사이띄기 ,tap키 Enter키 값으록 구분한다.
			
		가나다
		마바사
		
		가나다  마바사
		
		가나다	마바사
		
		변수1= scan.next();
		변수2= scan.next();
		
		2.nextLine(); ==>한 줄 단위로 입력한다.
			즉. 자료를 입력하교 Enter키를 누르면Enter키값까지 읽어간다.
		
	*/
}
class phone implements Serializable{
	

	
	private static final long serialVersionUID = 2185389327507380583L;
	private String name;
	private String tel;
	private String addr;
	
	
	
	public phone(String name,String tel, String addr) {
		super();
		this.name=name;
		this.tel=tel;
		this.addr=addr;
		
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return  tel +"\t"+ addr;
	}
	
	
	
}
