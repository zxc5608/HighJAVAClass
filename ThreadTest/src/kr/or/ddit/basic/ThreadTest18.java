package kr.or.ddit.basic;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

/*
 	Vector , hashtable 등의 예전부터 존재하던 Collection객체들은 내부의 동기화 처리가 되어있다.
 	
 	그런데 , 최근에 새로 구성된 Collection들은 동기화 처리가 되어있지않다.
 	
 	그래서, 동기화가 필요한 프로그램에서 이런 Collection들을 사용하려면 동기화 처리를 한 후 사용해야한다
 	
 
 */
public class ThreadTest18 {

	public static void main(String[] args) {
		
		Vector<Integer>vec =new Vector<>();
		
		//동기화 처리가 되지 않은List
		List<Integer>list1= new ArrayList<>();
		
		//동기화 처리를 한경우            컬렉션. 싱크로나이즈리스트
		List<Integer>list2= Collections.synchronizedList(new ArrayList<>());
		
		//익명구현체로 스레드구현
		Runnable r= new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<10000;i++) {
					//vec.add(i);
					//list1.add(i);
					list2.add(i);
				}
				
			}
		};
		//-------------------------------
		Thread[] ths= new Thread[] {
			new Thread(r),new Thread(r),new Thread(r),new Thread(r),new Thread(r)	
		};
		
		for(Thread th: ths) {
			th.start();
		}
		for(Thread th: ths) {
			try {
				th.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		//System.out.println("vec의 개수:"+vec.size());
		//System.out.println("list1의 개수:"+list1.size());
		System.out.println("list2의 개수:"+list2.size());

	}

}
