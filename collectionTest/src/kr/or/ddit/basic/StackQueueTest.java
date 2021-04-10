package kr.or.ddit.basic;

import java.util.LinkedList;

public class StackQueueTest {
	/* 자료구조
	  Stack ==> 후입선출(LIFO) last in first out
	   
	  Queue ==> 선입선출(FIFO) first in first out
	  
	  stack과 Queue는 LinkedList 로 구현해서 사용할수 있다
	   
	 */

	public static void main(String[] args) {
		//Stack명령
		//1. 자료 입력 : push(입력데이터);
		//2. 자료 출력: pop(); ==> 자료를 꺼내온후 꺼내온 데이터를 Stack에서 지운다.
		//			 peek(); ==> 삭제 없이 자료를 꺼내온다.
		
		LinkedList<String> stack =new LinkedList<>();
		
		stack.push("홍길동");
		stack.push("안용현");
		stack.push("리순신");
		stack.push("김봉준");
		
		System.out.println("stack 값:"+stack);
		//데이터를 꺼내오자 pop으로
		String data= stack.pop();
		
		System.out.println("꺼내온 값:"+data);
		System.out.println("꺼내온 값:"+stack.pop());
		System.out.println("stack 값"+stack);
		
		stack.push("성춘향");
		System.out.println("추가할 stack값:"+ stack);
		System.out.println();
		System.out.println("꺼내온값:"+stack.pop());
		System.out.println("stack 값:"+stack);
		System.out.println();
		
		System.out.println("삭제없이 꺼내온 값:"+stack.peek());
		System.out.println("stack 값:"+stack);
		System.out.println("=======================================");
		/*
		 	Queue명령
		 	1.자료입력 : offer(입력데이터)
		 	2.자료출력 : poll(); ==> 자료를 꺼내온후 꺼내온 데이터를 Queue에서 삭제를 한다.
		 	 		  peek();  ==> 삭제없이 데이터를 꺼내온다.
		 	 		  
		 */
		LinkedList<String> queue= new LinkedList<>();
		
		queue.offer("홍길동");
		queue.offer("안용현");
		queue.offer("리순신");
		queue.offer("김인직");
		
		System.out.println("queue값:"+queue);
		
		String temp = queue.poll();
		System.out.println("꺼내온값:"+temp);
		System.out.println("꺼내온값:"+queue.poll());
		System.out.println("queue 값:"+queue);
		
		queue.offer("성춘향");
		System.out.println("queue 값:"+queue);
		System.out.println();
		System.out.println("꺼내온값:"+queue.poll());
		System.out.println("queue값:"+queue);
		System.out.println();
		
		System.out.println("삭제없이 꺼내온 값:"+queue.peek());
		System.out.println("queue값:"+queue);
		
	}

}
