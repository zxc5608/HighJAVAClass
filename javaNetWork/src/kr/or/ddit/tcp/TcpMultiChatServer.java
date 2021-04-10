package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TcpMultiChatServer {

	//접속한 클라이언트의 정보를 저장할 Map객체 변수 선언;
	//		==> key값: 접속한사람이름 value값:클라이언트와 접속된 Socket객체
	private Map<String,Socket> ClientMap;
	
	//생성자
	public TcpMultiChatServer(){
		//clientMap을 동기화 처리가 되도록 생성한다. 
		ClientMap = Collections.synchronizedMap(new HashMap<String, Socket>());	
	}//생성자 끝
	public static void main(String[] args) {
		new TcpMultiChatServer().serverstart();
	}
	//서버시작 메서드
	private void serverstart() {
		ServerSocket server= null;
		Socket socket=null;
		try {
			server= new ServerSocket(7777);
			System.out.println("서버가 시작되었습니다");
			while(true) {
			socket = server.accept();// 클라이언트의 접속을 기다린다.
			
			System.out.println("["+socket.getInetAddress()+":"+
					socket.getPort()+"]에서 접속했습니다.");
			
			// 서버용 스레드 시작
			
			ServerThread serverThred= new ServerThread(socket);
			serverThred.start();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if(server!=null)try {}catch(Exception e) {}
		}
	}//서버 시작 메서드 끝...
	
	
	//clientMap에 저장된 전체 사용자에게 메세지를 전송하는 메서드
	private void sendToAll(String msg) {
		//ClientMap 의 데이터 개수만큼 반복
		for(String name: ClientMap.keySet()) {
			try {
				DataOutputStream dos= new DataOutputStream(
						ClientMap.get(name).getOutputStream() //클라이언트와 연결된 소켓의 outputstream객체 구하기
				);
				dos.writeUTF(msg);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}//sendToAll()메서드 끝...
	
	//클라이언트와 연결된 소켓을 이용하여 하나의 클라이언트가 보내온 메시지를
	// 다른 클라이언트들에게 전송하는 Thread 를 inner Class 형태로 만든다. 
	
	class ServerThread extends Thread{
		private Socket socket;
		private DataInputStream dis;
		private DataOutputStream dos;
		
		//생성자
		public ServerThread(Socket socket) {
			this.socket= socket;
			try {
				//수신용 입력
				dis= new DataInputStream(socket.getInputStream());
				//송신용 출력
				dos= new DataOutputStream(socket.getOutputStream());
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}//생성자끝..
		@Override
		public void run() {
			String name= ""; //클라이언트가 보낸 이름을 저장할 변수선언
			try {
				//클라이언트가 보낸 첫번째 데이터는 사용자의 이름인데
				//이이름이 중복되는지 여부를 feedback으로 클라이언트에게 보내주고
				//이름이 중복되지 않으면 반복문을 탈출한다.
				while(true) {
					name= dis.readUTF(); //첫번째데이터 읽기(이름데이터)
					
					if(ClientMap.containsKey(name)) { // 이름이 중복될 때 면
						dos.writeUTF("이름중복");	//이름중복 메시지 전송
					}else {// 이름이 중복되지 않을때
						dos.writeUTF("ok"); //'ok'메세지전송
						break;  //이름이 중복되지 않으면 반복문을 멈춰야한다. 
					}
					
				}//while문 끝...
				//대화명을 받아서 전체 클라이언트에게 대화방 참여 메시지를 보낸다.
				sendToAll("["+name+"]님이 대화방에 입장했습니다...");
			
				//대화명과 클라이언트의 Socket객체를 Map에 저장한다.
				ClientMap.put(name,socket);
				
				System.out.println("현재 서버 접속자 수:"+ ClientMap.size()+"명");  
				
				//하나의 클라이언트가 보낸 메시지를 받아서 전체 클라이언트들에게 보낸다.
				while(dis!=null) {
					sendToAll(dis.readUTF());
				}
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}finally {
				// 이 finally 영역이 실행된다는 것은 클라이언트가 접속을 종료했다는 의미이다.
				sendToAll("["+name+"]님이 대화방을 나갔습니다.");
				
				//접속을종료한 클라이언트를 Map에서 삭제한다.
				ClientMap.remove(name);
				
				System.out.println("["+socket.getInetAddress()+":"+
					socket.getPort()+"]에서 접속을 종료했습니다.");
				
				System.out.println("현재 서버 접속자 수:"+ ClientMap.size()+"명");  
			}
		}
	}
}



