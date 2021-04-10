package kr.or.ddit.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//Receiver  sender
public class TcpServer2 {
	
	public static void main(String[] args) {
		// 소켓 서버를 만들고, 클라이언트가 접속해오면 소켓을만들어서
		// 메시지를 받는 클래스와  메시지를 보내느 클래스를 생성할때 이 소켓을 넘겨 준다.
		
		try {
			
			ServerSocket server= new ServerSocket(7788);
			System.out.println("서버가 준비중입니다...");
			
			Socket socket = server.accept();
			
			Sender sender = new Sender(socket);
			Receiver receiver= new Receiver(socket);
			
			
			
			sender.start();
			receiver.start();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
