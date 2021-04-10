package kr.or.ddit.Client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.inf.RmiTestInterface;
import kr.or.ddit.vo.FileInfoVO;
import kr.or.ddit.vo.TestVO;

public class RemoteClient {

	public static void main(String[] args) {
		Scanner scan= new Scanner(System.in);
		//RMI용 객체를 서버에서 구해와서 사용하는 순서
		try {
			
			//1. 서버에서 등록한 RMI용 객체를 찾기위해 Registry객체를 생성한다.
			//	(getRegistry()메서드에 서버의 ip주소와 포트번호를 지정하여 생성한다.)
			Registry reg=LocateRegistry.getRegistry("localhost",9988);
			
			//2. 서버에 등록한 '객체의 Alias 명'을 이용하여 객체를 불러온다.
			//형식) Registry객체변수 .lookup('객체의 Alias명')
			RmiTestInterface inf= (RmiTestInterface) reg.lookup("server");
			
			//3. 이제부터 불러온 객체의 메서드를 호출해서 사용할수있다. 
			System.out.println("서버로 전송할 메시지 입력>>");
			String msg= scan.nextLine();
			
			int a = inf.doRemoteprint(msg);
			System.out.println("서버의 반환값:"+a);
			System.out.println();
			
			List<String>list= new ArrayList<>();
			list.add("복숭아");
			list.add("사과");
			list.add("배");
			list.add("대추");
			list.add("감");
			
			inf.doprintList(list);
			System.out.println("List전송 끝..");
			
			TestVO testvo= new TestVO();
			testvo.setId("a001");
			testvo.setNum(12345);
			inf.doprintVo(testvo);
			System.out.println("VO객체 전송끝...");
			System.out.println();
			
			//파일 전송하기
			File file =new File("D:/D_Other/Koala.jpg");
			if(!file.exists()) {
				System.out.println(file.getName()+"파일이 없습니다");
				return;
			}
			FileInfoVO filevo= new FileInfoVO();
			
			//파일의 용량을 구해서 FileInfoVO의 fileData배열의 길이를 결정한다.
			int len =(int) file.length();
			byte[] data= new byte[len];
			
			//파일 입력용 스트림 객체생성
			FileInputStream fin = new FileInputStream(file);
			
			//파일 내용을 읽어와 byte형 배열에 저장한다
			fin.read(data);
			
			//구해온 정보를 FileInfoVO 객체에 셋팅한다
			filevo.setFileName(file.getName()); //파일명 저장
			filevo.setFileData(data);	//파일내용저장
			
			//서버의 파일 전송용 메서드를 호출한다
			inf.transFile(filevo);
			System.out.println("파일 전송 작업 끝....");
			
 		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
