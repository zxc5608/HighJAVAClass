package kr.or.ddit.inf;

import java.rmi.Remote;
import java.rmi.RemoteException;

//서버용 인터페이스
public interface SeverchatInf extends Remote{
	
	//접속한 클라이언트 정보를  List에 추가하는 메서드
	public void setClient(ClientChatInf client)throws RemoteException;
	
	//접속이 해제된 클라이언트 정보를 List에서 작성하는 메서드
	public void disConnect(ClientChatInf client) throws RemoteException;
	
	//하나의 클라이언트가 보내온 메시지를 List에 등록된 모든 클라이언트에게 전달하는 메서드
	public void sendToAll(String msg) throws RemoteException;
}
