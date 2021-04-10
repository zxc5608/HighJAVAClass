package kr.or.ddit.tcp;
//이 클래스는 소켓에서 메시지를 받아서 화면에 출력하는 역할을 담당한다. 

import java.io.DataInputStream;
import java.net.Socket;

public class Receiver extends Thread{
	private Socket socket;
	private DataInputStream dis;
	//생성자
	public Receiver(Socket socket) {
		super();
		this.socket = socket;
		try {
			dis= new DataInputStream(socket.getInputStream());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void run() {
		while(dis!=null) {
			try {
				System.out.println(dis.readUTF());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	

}
