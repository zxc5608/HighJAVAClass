package kr.or.ddit.vo;

import java.io.Serializable;

/*
 	RMI통신에서 데이터를 전달용으로 사용할 객체 작성하기
 	==> 이 객체는 네트워크를 통해서 데이터가 전달되어야 하기 떄문에 직렬화가 필요하다
 	==> 그래서 Serializable을 구현하여 작성해야 한다.
 	
 */
public class TestVO implements Serializable{
	private String id;
	private int num;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
}
