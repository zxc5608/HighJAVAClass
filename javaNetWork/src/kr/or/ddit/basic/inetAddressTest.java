package kr.or.ddit.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class inetAddressTest {
	
	public static void main(String[] args) throws UnknownHostException {
		// InetAddress클래스 ==> ip주소를 다루기 위한 클래스
		
		//www.naver.com 의 Ip정보가져오기
		
		InetAddress naverIp= InetAddress.getByName("www.naver.com");
		
		System.out.println("HostName:"+ naverIp.getHostName());
		System.out.println("HostAddress:"+naverIp.getHostAddress()); 
		System.out.println();
		
		//자신의 컴퓨터의Ip정보 가져오기
		InetAddress localIp= InetAddress.getLocalHost();
		
		System.out.println("내 컴퓨터의 hostName:"+localIp.getHostName());
		System.out.println("내컴퓨터의 HostAddress:"+localIp.getHostAddress());
		System.out.println();
		
		//Ip주소가 여러개인 호스트정보가져오기
		InetAddress[] navers=InetAddress.getAllByName("www.google.com");
		
		for (InetAddress nip: navers) {
			System.out.println(nip.toString());
		}
	}

}
