package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalcController implements ActionListener{//관제탑
	//뷰들을 등록
	CalcView view;
	
	/*
	   ★★★<컨트롤러의 역할>
	   1. 사용자 요청을 분석
	   2. 뷰를 통해 입력된 데이터를 얻어오기
	   3. 모델객체생성★
	   		-3_1. 메소드호출
	   		-3_2. 결과값을 변수에 저장
	   4. 페이지(JFrame)이동 : f1.setVisible(false); f2.setVisible(true);
	   
	     추가(옵션): 유효성검사(뷰에도 위치할 수도 있음)
	           : 우리가 정한 범위내의 데이터가 입력되어쓴ㄴ지 확인.
	                   예) int age;
	              --->범위설정 : 1~100
	              ---> 101 : 유효하지 못한 데이터!!
	 */
	
	public CalcController() {
		view = new CalcView();
		
		view.bt_calc.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//if(e.getSource()==view.bt_calc){}//1.계산 요청이 들어왔다면~!!
		int su1 = Integer.parseInt(view.tf_su1.getText());//2.
		int su2 = Integer.parseInt(view.tf_su2.getText());//2.
		String oper = (String)view.combo.getSelectedItem();//2.
		//String oper = view.combo.getSelectedItem().toString();
		
		//Calculator cal = new Calculator(int su1, int su2, String oper);
		Calculator cal = new Calculator(su1, su2, oper);//3.
		String str = cal.getResult();//3_1, 3_2
		
		view.la_result.setText(str);
	}
	public static void main(String[] args) {
		new CalcController();
	}

}
