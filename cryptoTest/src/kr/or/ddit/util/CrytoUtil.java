package kr.or.ddit.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//단방향 암호화를 처리하는 클래스 
public class CrytoUtil {
	
	//문자열을 MD5방식으로 암호화한다. (자리수 :32byte)
	public String md5(String msg) throws NoSuchAlgorithmException {
		//암호화 방식을 지정한 객체 생성
		MessageDigest md= MessageDigest.getInstance("MD5");
		
		md.update(msg.getBytes()); 	//암호화 하기
		
		return byteToHexString(md.digest()); //암호화된 데이터를 가져와 16진수로 변환해서 반환
		
	}
	//문자열을 SHA-256방식으로 암호화한다.(자리수: 64byte)
	public String sha256(String msg) throws NoSuchAlgorithmException {
		MessageDigest sha256= MessageDigest.getInstance("SHA-256");
		sha256.update(msg.getBytes());
		
		return byteToHexString(sha256.digest());
	}
	//문자열을 SHA-512 방식으로 암호화한다.(자리수: 128byte)
	
	public String sha512(String msg) throws NoSuchAlgorithmException {
		MessageDigest sha512= MessageDigest.getInstance("SHA-512");
		sha512.update(msg.getBytes());
		return byteToHexString(sha512.digest());
	}
	
	
	// 0x111==>16진수
	// 0112==> 8진수
	// 1111==>10진수
	
	//0xff ==> 1111 1111 (f  f)
	
	//byte배열의 데이터를 16진수 값으로 변환하는 메서드
	public String byteToHexString(byte[] data) {
		StringBuilder sb= new StringBuilder();
		for(byte b: data) {					//비트연산자 &and  0xff는 16진수라는의미
			//(b & 0xff)+ 0x100)==> 16진수 2자리만들기
			//0xa & 0xff=> 0x0a ==> 0xa+ 0x100 ==> 0x10a =="10a ==> "0a"
			sb.append(Integer.toHexString((b & 0xff)+ 0x100).substring(1)); 
		}
		return sb.toString();
	}
}
