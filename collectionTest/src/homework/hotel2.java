package homework;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class hotel2 {
	Map<Integer, Room> hmap;
	Scanner sc;

	private boolean dataSave;
	// 생성자
	public hotel2() {
		
		
		hmap = new HashMap<Integer, Room>();
		sc = new Scanner(System.in);

		// 객실 초기화
		// hmap.put(201,new Room(201, "싱글룸"));

		for (int i = 2; i <= 4; i++) {
			String roomType = null;
			switch (i) {
			case 2:
				roomType = "싱글룸";
				break;
			case 3:
				roomType = "더블룸";
				break;
			case 4:
				roomType = "스위트룸";
				break;

			}
			for (int j = 1; j <= 9; j++) {
				int roomNum = i * 100 + j;
				Room room = new Room(roomNum, roomType);
				hmap.put(roomNum, room);
			}
		}



	}
	private void save() {
		try {
			FileOutputStream fout = new FileOutputStream("d:/d_other/hotel.dat");
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			ObjectOutputStream oos = new ObjectOutputStream(bout);
			
			System.out.println("호텔 정보 저장 시작!!!");
			oos.writeObject(hmap);
			System.out.println("호텔 정보 저장 완료!");
			dataSave = false;
			
			oos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	public static void main(String[] args) {

		new hotel2().hotelStart();
	}

	private void hotelStart() {
		// 메뉴를 출력하고 작업번호를 입력받아 반환하는메서드
		while (true) {

			int choi = display();

			switch (choi) {
			case 1:
				checkin();
				break;
			case 2:
				checkout();
				break;

			case 3:
				showRoom();
				break;

			case 4:
				System.out.println("000000000000000000000");
				System.out.println("호텔문을 닫았습니다");
				return;
			default:
				System.out.println("작업번호를 잘못입력하였습니다.");
			}

		}

	}

	// 객실상태
	private void showRoom() {
		System.out.println("-------------------------------");
		System.out.println("객실상태");
		System.out.println("-------------------------------");
		System.out.println("방 번호       방종류         투숙객이름");
		System.out.println("-------------------------------");

		// 방 번호를 순서대로 나오게 하기 위해서 방번호 (map의key값)만 list에 넣어서 정렬하여 사용한다.
		ArrayList<Integer> roomNumList = new ArrayList<>();
		for (int num : hmap.keySet()) { // Map의key값을 List에추가하기
			roomNumList.add(num);
		}
		Collections.sort(roomNumList); // 방번호를 오름차순으로 정렬하기

		System.out.println();
		System.out.println("---------------------------");
		System.out.println("현재 객실상태");
		System.out.println("---------------------------");
		System.out.println("방번호\t방종류\t투숙객이름");
		System.out.println("---------------------------");

		// list에서 방번호를 하나씩 꺼내와 Map에서 해당번호에 해당하는 Room객체를 구해서 출력한다.

		for (int i = 0; i < roomNumList.size(); i++) {
			Room r = hmap.get(roomNumList.get(i));
			System.out.print(r.getRoomNum() + "\t" + r.getRoomType() + "\t");
			String name = "-";
			if (r.getgName() != null) { // 방에 손님이 있으면..
				name = r.getgName();
			} // 방에손님이없으면
			System.out.println(name);
		}
		System.out.println("---------------------------");
		System.out.println();
	}

	private void checkout() {
		System.out.println("--------------------------");
		System.out.println("체크아웃작업");
		System.out.println("--------------------------");
		System.out.println("체크아웃할거");
		System.out.println("방번호 입력:");
		int roomNum = sc.nextInt();
		if (!hmap.containsKey(roomNum)) {
			System.out.println(roomNum + "호에 객실은 존재하지 않습니다");
		}
		if (hmap.get(roomNum).getgName() == null) {
			System.out.println(roomNum + "호 객실에는 체크아웃할손님이없습니다");
			return;

		}
		// 체크 아웃작업은 해당객실의 손님의 이름을 null로 변경하는 것을ㄹ말한다
		String name = hmap.get(roomNum).getgName();
		hmap.get(roomNum).setgName(null); // 손님이름을 null로 설정한다

		System.out.println(roomNum + "호 객실의 " + name + "님이 체크 아웃을 완료하였습니다");

	}

	// 체크인
	private void checkin() {
		System.out.println("--------------------------");
		System.out.println("체크인작업");
		System.out.println("--------------------------");
		System.out.println("*201~209:싱글룸");
		System.out.println("*301~309:더블룸");
		System.out.println("*401~409: 스위트룸");
		System.out.println("---------------------------");

		System.out.println("방번호 입력:");
		int roomNum = sc.nextInt();

		// 입력한 방번호가 있는지 검사 (키값으로 입력한 번호가있는지검사)

		if (!hmap.containsKey(roomNum)) {
			System.out.println(roomNum + "호에 객실은 존재하지 않습니다");
			return;
		} else if (hmap.get(roomNum).getgName() != null) { // 해당 객실에 손님이있는지검사
			System.out.println(roomNum + "호 객실에는 이미 손님이있습니다.");
			return;
		} else {
			System.out.println("누구를 체크인하시겠습니까?");
			System.out.println("이름입력>>");
			String name = sc.next();
			// 입력받은 객실의 투숙객명단에 입력받은 이름을 저장한다
			hmap.get(roomNum).setgName(name);
			System.out.println(name + "씨가 " + roomNum + "호 객실에 체크인되었습니다");
		}
	}

	private int display() {

		System.out.println("--------------------");
		System.out.println("어떤 업무를 하시겠습니까");
		System.out.println("1.체크인 \t2.체크아웃\t3.객실상태\t4업무종료");
		System.out.println("-----------------------------------");
		System.out.println("선택>>");

		int num = sc.nextInt();
		return num;

	}

}

class Room {

	private int roomNum;
	private String roomType;
	private String gName;

	public Room(int roomNum, String roomType) {
		super();
		this.roomNum = roomNum;
		this.roomType = roomType;

	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	@Override
	public String toString() {
		return roomNum + "\t" + roomType + "\t";
	}
}


