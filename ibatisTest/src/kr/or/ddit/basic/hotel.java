package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Scanner;

import javax.sound.midi.Synthesizer;

public class hotel {
	static hotel ht=new hotel();
	private Scanner sc=new Scanner(System.in);
	private HashMap<Integer,hotels>map=new HashMap<>();
	
public static void main(String[] args) {
	
	ht.start();
}

private void start() {
	while(true) {
	int choi= display();
	
	switch (choi) {
	case 1:		//체크인
		checkin();
		break;
	case 2:			//체크아웃
		checkout();
		break;
	case 3:	//객실상태
		roomview();
		break;
	case 4:			
		System.out.println("업무를 종료합니다");
		return;

	default:
		System.out.println("번호를 잘못입력했습니다.");
	}
}
	}

private void roomview() {
	System.out.println("-------------------------------");
	System.out.println("객실상태");
	System.out.println("-------------------------------");
	System.out.println("방 번호       방종류         투숙객이름");
	System.out.println("-------------------------------");
	
	for(int key:map.keySet()) {
		hotels value =map.get(key);
		System.out.println(value);
		
	}

	
	
}

private void checkout() {
	System.out.println("----------------------------------------------");
	System.out.println("   체크아웃 작업");
	System.out.println("----------------------------------------------");
	System.out.println("체크아웃할 방 번호를 입력하세요.");
	System.out.print("방번호 입력 >> ");
	int num= sc.nextInt();
	
	if(!map.containsKey(num)) {
		System.out.println(num+"호는 존재하지 않는 객실입니다. ");
		return;
	}else if(!map.get(num).getName().equals("-")) {
		System.out.println(num+"호 객실은 이미손님이있어");
		return;
	}

	System.out.println(num+"객실의"+map.get(num).getName()+"님이 체크아웃을 완료했습니다");
	map.put(num,new hotels(num, map.get(num).getRoomtype(), "-"));
	
		
}

	
	

	


private void checkin() {

	
	System.out.println("--------------------------");
	System.out.println("체크인작업");
	System.out.println("--------------------------");
	System.out.println("*201~209:싱글룸");
	System.out.println("*301~309:더블룸");
	System.out.println("*401~409: 스위트룸");
	System.out.println("---------------------------");
	
	System.out.println("방번호 입력:");
	int num= sc.nextInt();
	
	if(!map.containsKey(num)) {
		System.out.println(num+"호는 존재하지 않는 객실입니다. ");
		return;
	}else if(!map.get(num).getName().equals("-")) {
		System.out.println(num+"호 객실은 이미손님이있어");
		return;
	}
	
	System.out.println("누구를 체크인 하시겠습니까?");
	sc.nextLine();
	System.out.println("이름입력");
	String name1 = sc.nextLine();

	
	map.put(num,new hotels(num, map.get(num).getRoomtype(), name1));
	System.out.println("체크인이 완료되었습니다");
		
	
}


private int display() {
	
	int room1=200;
	for(int i=0;i<9;i++) {
		room1++;
		map.put(room1,new hotels(room1,"싱글룸","-"));
	}
	int room2=300;
	for(int i=0;i<9;i++) {
		room2++;
		map.put(room2,new hotels(room2,"더블룸","-"));
	}
	int room3=400;
	for(int i=0;i<9;i++) {
		room3++;
		map.put(room3,new hotels(room3,"스위트룸","-"));
	}
	
	System.out.println("--------------------");
	System.out.println("어떤 업무를 하시겠습니까");
	System.out.println("1.체크인 \t2.체크아웃\t3.객실상태\t4업무종료");
	System.out.println("-----------------------------------");
	System.out.println("선택>>");
	
	int num = sc.nextInt();
	return num;
	
}
}
class hotels{
	
	private int roomnum;
	private String roomtype;
	private String name;
	
	public hotels(int roomnum,String roomtype ,String name) {
		super();
		this.roomnum=roomnum;
		this.roomtype= roomtype;
		this.name=name;
		
		
	}

	public int getRoomnum() {
		return roomnum;
	}

	public void setRoomnum(Integer roomnum) {
		this.roomnum = roomnum;
	}

	public String getRoomtype() {
		return roomtype;
	}

	public void setRoomtype(String roomtype) {
		this.roomtype = roomtype;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return roomnum+"\t"+ roomtype+"\t"+ name;
	}

	


	}