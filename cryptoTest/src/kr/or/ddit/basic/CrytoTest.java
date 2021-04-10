package kr.or.ddit.basic;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import kr.or.ddit.util.AEC256Util;
import kr.or.ddit.util.CrytoUtil;

public class CrytoTest {

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		CrytoUtil cryto= new CrytoUtil();
		
		//String plainText= "Hello World";
		String plainText= "안녕하세요, 반갑습니다";
		
		System.out.println("MD5 :"+cryto.md5(plainText));
		System.out.println("SHA-256 :"+cryto.sha256(plainText));
		System.out.println("SHA-512 :"+cryto.sha512(plainText));
		
		AEC256Util aes256= new AEC256Util();
		String str = aes256.encrypt(plainText);
		System.out.println("원래의 데이터 =>" +plainText);
		System.out.println("AES256암호화=>"+str);
		System.out.println("암호화 문자열 길이:"+ str.length());
		System.out.println("AES256복호화=>"+aes256.decrypt(str));
		
		System.out.println("-------------------------------------");
		String tmp="";
		for(int i=0;i<=9;i++) {
			for(int j=0;j<=9;j++) {
				tmp+=j;
				str= aes256.encrypt(tmp);
				System.out.println(i+"tmp=>"+tmp);
				System.out.println("암호화=>"+str);
				System.out.println("암호화 길이=>"+ str.length());
				String deStr= aes256.decrypt(str);
				System.out.println("복호화=> "+deStr);
				System.out.println();
			}
		}
		
		
		
	}

}
