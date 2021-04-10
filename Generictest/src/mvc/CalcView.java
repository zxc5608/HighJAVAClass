package mvc;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CalcView extends JFrame{
	public JTextField tf_su1, tf_su2;
	public JComboBox<String> combo;
	public JButton bt_calc;
	
	public JLabel la_result;//결과값 출력   예) 결과값: 2+3-5
	
	public CalcView() {
		setTitle("MVC간단계산기");
		tf_su1 = new JTextField(4);
		tf_su2 = new JTextField(4);
		combo = new JComboBox<String>();
			combo.addItem("+");
			combo.addItem("-");
			combo.addItem("*");
			combo.addItem("/");
		
		bt_calc = new JButton("계산");
		
		la_result = new JLabel("결과값: 2+3=5");
		
		setLayout(new FlowLayout());
		add(tf_su1);
		add(combo);
		add(tf_su2);
		add(bt_calc);
		add(la_result);
		
		setBounds(600,600,300,100);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);//사이즈 고정
	}//생성자

}
