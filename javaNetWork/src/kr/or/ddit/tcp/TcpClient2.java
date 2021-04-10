package kr.or.ddit.tcp;

import java.net.Socket;

public class TcpClient2 {

	public static void main(String[] args) {
		//서버에 접속하는 소켓을 생성하고 , 서버와 접속이 성공하면
		//메시지를 보내는 클래스와 메시지를 받는ㄴ 클래스를 생성할때 이소켓을 넣어준다.
		
		try {
			Socket socket =new Socket("localhost",7788);
			System.out.println("서버에 연결되었습니다.");
			
			Sender sender= new Sender(socket);
			Receiver receiver= new Receiver(socket);
			
			sender.start();
			receiver.start();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
