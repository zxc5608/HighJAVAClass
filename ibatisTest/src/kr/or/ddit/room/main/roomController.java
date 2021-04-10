package kr.or.ddit.room.main;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.room.service.IroomService;
import kr.or.ddit.room.service.roomServiceImpl;
import kr.or.ddit.room.vo.roomVO;

public class roomController {
	private Scanner scan;
	private IroomService service;
	private roomVO rovo;
	
	public roomController() {
		scan= new Scanner(System.in);
		service= roomServiceImpl.getInstance();
		
	}
	public static void main(String[] args) {
		new roomController().roomstart();
	}
	private void roomstart() {
		while(true) {
			
			int choi= displaymenu();
			switch (choi) {
			case 1:
				checkin();		//체크인
				break;
			case 2:
						//체크아웃
				break;
			case 3:
				roomdisplay();			//객실상태
				break;
			case 4:
			System.out.println("프로그램 종료");	//나가기
				return;

			default:
				break;
			}
			
		}
		
	}
	private void checkin() {
		System.out.println("객실번호 입력");
		int roomno= scan.nextInt();
		
		System.out.println("누구를 체크인 하시겠습니까");
		String roomnm=scan.nextLine();
		
		roomVO rovo =new roomVO();
		rovo.setRoom_nm(roomnm);
		
		int cnt= service.checkin(rovo);
		if(cnt>0){
			System.out.println("체크인되었습니다");
		}else {
			System.out.println("체크인 실패");
		}
		
		service.checkin(rovo);
		
		
		
		
	}
	private void roomdisplay() {
		List<roomVO>roomList= service.getAllList();
		
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println("방번호     방종류         투숙객이름");
		System.out.println("---------------------------------"
				+ "-----");
				
		if(roomList==null || roomList.size()==0) {
			System.out.println("정보가 없습니다");
		}else {
			for(roomVO rovo:roomList) {
				int roomNo = rovo.getRoom_no();
				String roomType= rovo.getRoom_type();
				String roomNm= rovo.getRoom_nm();
				
				System.out.println(roomNo+"\t"+roomType+"\t"+ roomNm);
			}
		}
		System.out.println("---------------------------------------------");
	}
	private int displaymenu() {
		System.out.println("체크인 작업");
		System.out.println("-----------------------------");
		System.out.println("* 201~209 : 싱글룸");
		System.out.println("* 301~309 : 더블룸");
		System.out.println("* 401~409 : 스위트룸");
		System.out.println("-----------------------------");
		System.out.println("1.체크인\t2. 체크아웃\t3.객실정보 \t0.나가기");
		
		return scan.nextInt();
	}
}
