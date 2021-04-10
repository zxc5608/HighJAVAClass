package kr.or.ddit.member.vo;
/**
 * VO는 DB테이블에 있는 컬럼의 기준으로 데이터를 객체화할 클래스이다.
 * DB테이브르이 '컬럼명'이  이 VO 클래스의 '멤버변수명' 이 된다.
 * 
 * DB테이블의 컬럼과 클래스의 멤버변수를 매필하는 역할을 수행한다.
 * 
 */
public class MemberVO {
	
	private String mem_id;
	private String mem_name;
	private String mem_tel;
	private String mem_addr;
	
	//만약 기본생성자 이외의 생성자를 생성할떄에는 반드시 기본 생성자도 생성해 주도록한다
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_tel() {
		return mem_tel;
	}
	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
	}
	public String getMem_addr() {
		return mem_addr;
	}
	public void setMem_addr(String mem_addr) {
		this.mem_addr = mem_addr;
	}
	
} 





