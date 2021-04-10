package kr.or.ddit.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {
	
	public static void main(String[] args) {
		Scanner scan =new Scanner(System.in);
		
		// 송신용 패킷변수와 수신용 패킷변수 선언 
		DatagramPacket outpacket, inPacket;
		
		//수신 받은 데이터가 저장될 Byte형 배열 변수 선언
		byte[] bMsg= new byte[512];
		
		try {
			DatagramSocket socket= new DatagramSocket();
			
			//접속할 곳의 주소 객체생성
			//InetAddress address= InetAddress.getByName("localhost");
			InetAddress address= InetAddress.getByName("127.0.0.1");
			
			while(true) {
				//전송할 메시지입력
				System.out.println("보낼 메시지입력 : ");
				String msg= scan.nextLine();
				
				if("/end".equals(msg)) {
					break;
				}
				//전송할 패킷 객체생성
				outpacket= new DatagramPacket(msg.getBytes(), msg.getBytes().length,
								address, 8888);
				
				//데이터 전송
				socket.send(outpacket);
				//======================================
				//서버에서 온 데이터를 받아서 출력하기
				
				//수신용 패킷객체 생성
				inPacket= new DatagramPacket(bMsg, bMsg.length);
				
				//수신
				socket.receive(inPacket);
				
				System.out.println("서버에서 온 응답 데이터: "+ new String(bMsg,0,inPacket.getLength()) );
				System.out.println();
			}
			System.out.println("통신 끝");
			socket.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
