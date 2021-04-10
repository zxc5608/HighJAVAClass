package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.ibatis.sqlmap.engine.mapping.statement.UpdateStatement;

public class LprodIbatisTest {
	//ibatis를 이용하여 DB자료를 처리하는 순서 및 방법 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan= new Scanner(System.in);
		
		//1.iBatis의 환경설정 파일(sqlMapConfig.xml)을 읽어와서 실행한다. 
		try {
			//1-1 문자 인코딩 캐릭터셋  설정하기
			Charset charset= Charset.forName("UTF-8");
			Resources.setCharset(charset);
			
			//1-2.환경설정 파일을 읽어온다.
			Reader rd= Resources.getResourceAsReader("sqlMapConfig.xml");
			
			//1-3. 위에서 읽어온 Reader객체를 이용하여 실제 환경설정을 완성한 후 
			// 		SQL문을 호출해서 실행할 수 있는 객체를 생성한다. 
			SqlMapClient smc= SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close(); //Reader객체 닫기
			
			/*
			//환경설정 끝. .
			//-----------------------------------------------------------------------
			
			//2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 작업을 수행한다.
			
			//2-1. insert 연습
			System.out.println("insert작업시작.....");
			System.out.println("lprod_id 입력:");
			int lprodId= scan.nextInt();
			
			System.out.println("lprod_gu 입력:");
			String lprodGu=scan.next();
			
			System.out.println("lprod_nm 입력:");
			String lprodNm=scan.next();
			
			//insert할 데이터들을 VO객체에 담는다. 
			LprodVO lprodVo= new LprodVO();
			lprodVo.setLprod_id(lprodId);
			lprodVo.setLprod_gu(lprodGu);
			lprodVo.setLprod_nm(lprodNm);
			
			//2. sqlMapClient 객체 변수를 이용해서 처리할 쿼리문을 호출하여 실행한다.
			// 형식) sqlMapClient 객체 변수.insert("namespace값.id속성값",파라미터클래스);
			//		반환값 :insert성공 : null ,insert실패 : 오류객체
			
			Object obj = smc.insert("lprod.insertLprod",lprodVo);
			if(obj==null) {
				System.out.println("insert 작업성공!!");
			}else {
				System.out.println("insert 작업 실패!!");
			}
			System.out.println("------------------------------------------------------");
			*/
			
			/*
			//2-2.update연습 
			System.out.println("update시작...");

			System.out.println("수정할 lprod_gu 입력:");
			String lprodGu=scan.next();
			
			System.out.println("새로운 lprod_id 입력:");
			int lprodId= scan.nextInt();
			
			System.out.println("새로운 lprod_nm 입력:");
			String lprodNm=scan.next();
			
			//수정할 데이터를 VO객체에 담는다
			LprodVO lprodVo2= new LprodVO();
			lprodVo2.setLprod_gu(lprodGu);
			lprodVo2.setLprod_id(lprodId);
			lprodVo2.setLprod_nm(lprodNm);
			
			//2) sqlMapClient 객체변수 .update("namespace값. id속성값", 파라미터 클래스);
			//  		==> 반환값 : 작업에 성공한 레코드 수 
			int cnt = smc.update("lprod.updateLprod",lprodVo2);
			if(cnt>0) {
				System.out.println("update작업성공");
			}else {
				System.out.println("작업 실패");
			}
			*/
			
			/*//2-3 .delete연습
			System.out.println("delete 작업시작...");
			
			System.out.println("삭제할 lprod_gu 입력");
			String lprodGu= scan.next();
			
			//1) sqlMapClient객체 변수 . delete("namespace값.id속성값",파라미터클래스)
			// 				반환값: 작업에 성공한 레코드 수
			
			int cnt2= smc.delete("lprod.deleteLprod",lprodGu);
			if(cnt2>0) {
				System.out.println("삭제성공");
			}else {
				System.out.println("작업 실패");
			}
			*/
			
			/*
			//2-4. select 연습
			// 1) 응답결과가 여러개의 레코드인 경우
			//    ==> 	응답경과가 여러개일 경우에는 queryForList()메서드를 사용하는데 
			// 		  	이 메서드는 여러개의 레코드를 각각을 VO객체에 담은 후에 이 VO객체를 List에 추가해서
			// 			추가하는 작업을 자동으로 수행한다. 
			// 형식) sqlMapClient 객체변수. queryForList("namespace값. id속성값", 파라미터클래스)
			//	  ==	SQL문에 전달할데이터가 없으면'파라미터클래스'값을 생략할수 있다.
			System.out.println("1) select 연습(결과가 여러개일 경우...)");
			
			List<LprodVO>lprodList= smc.queryForList("lprod.getAllLprod");
			
			for(LprodVO lpvo:lprodList) {
				System.out.println("ID:"+ lpvo.getLprod_id());
				System.out.println("GU:"+ lpvo.getLprod_gu());
				System.out.println("NM:"+ lpvo.getLprod_nm());
			}
			System.out.println("출력 끝...");
			*/
			
			//2)응답 결과가 1개일 경우
			// ==> 응답결과가 1개일경우에는 queryForObject()메서드를 사용한다
			// 형식) sqlMapClient 객체변수 . queryForObject("namespace.id속성값",파라미터 클래스)
			
			System.out.println("2) select연습시작(결과가 1개일경우 )...");
			System.out.print("검색할 lprod_gu입력:");
			String lprodGu= scan.next();
							// qfo를 사용할 떄 형변환을 해준다
			LprodVO lprodVo= 
					(LprodVO) smc.queryForObject("lprod.getLprod",lprodGu);
			
			if(lprodVo==null) { //검색한 결과가 하나도없으면 null을 반환한다.
				System.out.println("검색한 데이터가 하나도 없습니다.");
							
			}else {
				System.out.println("ID:"+lprodVo.getLprod_id());
				System.out.println("GU:"+lprodVo.getLprod_gu());
				System.out.println("NM:"+lprodVo.getLprod_nm());
				System.out.println("출력끝..");
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
