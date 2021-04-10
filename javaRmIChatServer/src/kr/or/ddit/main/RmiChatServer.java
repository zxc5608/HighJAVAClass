package kr.or.ddit.main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.inf.ClientChatInf;
import kr.or.ddit.inf.SeverchatInf;



public class RmiChatServer extends UnicastRemoteObject implements SeverchatInf {

	// 접속한 클라이언트 정보가 저장될 list
	List<ClientChatInf> clientList = new ArrayList<>();

	public RmiChatServer() throws RemoteException {
	}
	
	//접속한 클라이언트 정보를 List에 추가하는 메서드
	@Override
	public void setClient(ClientChatInf client) throws RemoteException {
		clientList.add(client);
		System.out.println("등록완료");
		System.out.println("현재 접속자 수:"+clientList.size()+"명");
		
	}
	//접속을 해제한 클라이언트 정보를 List에서 삭제하는 메서드
	@Override
	public void disConnect(ClientChatInf client) throws RemoteException {
		clientList.remove(client);
		System.out.println("접속해제 완료...");
		System.out.println("현재 접속자수:"+clientList.size()+"명");
		
		
	}
	//list에 등록된 모든 클라이언트들에게 메시지보내기
	@Override
	public void sendToAll(String msg) throws RemoteException {
		for(ClientChatInf client : clientList) {
			client.printMessge(msg);
		}
	}
	

	public static void main(String[] args) {
		try {
			SeverchatInf server= new RmiChatServer();
			
			Registry reg = LocateRegistry.createRegistry(1099);
			
			reg.rebind("rmiChat", server);
			System.out.println("채팅서버 준비완료...");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
