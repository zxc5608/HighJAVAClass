package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class ThreadTest05 {

	public static void main(String[] args) {
		String str= JOptionPane.showInputDialog("아무거나입력하세요");
		System.out.println("입력한값:"+str);

		for(int i= 10;i>=1;i--) {
			System.out.println(i);
			try {
				Thread.sleep(1000);//1초동안 잠시 멈춘다 
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}

}
