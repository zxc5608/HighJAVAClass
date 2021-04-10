package kr.or.ddit.basic;
/*
   문제) 학번 (int), 이름(string) , 국어점수 ,영어점수, 수학점수, 총점 ,등수를 멤버로 갖는
    student 클래스를 만든다
         이 Student 클래스의 생성자에서는 학번, 이름 , 국어점수 , 영어점수 ,수학점수만 매개변수로
          받아서 초기화 처리를한다. 
    
         - 이 Student 객체는 List 에 저장하여 관리한다
    	- List에 저장된 데이터들을 학번의 오름차순으로 정렬할수있는 내부정렬기준을 구현하고,
    	
    	
    	총점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬이 되는 
    	
    	외부 정렬기준클래스를 작성하여 정렬된 결과를 출력하시오
    	(단. 등수는 List에 전체 데이터가 추가된 후에 저장되도록 한다.)    
    
 
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest3 {
	
	public void SetRanking(ArrayList<Student>stuList) {
		for(Student stu: stuList) { //기준데이터를 구하기위한 반복문
			int rank =1;
			for(Student stu2:stuList) {//비교대상을 나타내는 반복문
				//기준보다 비교대상이 값이 크면 rank값을 증가시킨다.
				if(stu.getsum()<stu2.getsum()) {
					rank++;
				}
				
			}
			stu.setrank(rank);
			
		}
		
		
	}

	public static void main(String[] args) {
		ListSortTest3 stuTest= new ListSortTest3();
		
		ArrayList<Student>stuList= new ArrayList<>(); 
		
		stuList.add(new Student(1, "김보고", 50, 50, 40));
		stuList.add(new Student(2, "안보고", 40, 50, 50));
		stuList.add(new Student(3, "장보고", 40, 50, 50));
		stuList.add(new Student(4, "문보고", 60, 40, 60));
		stuList.add(new Student(5, "도보고", 70, 90, 40));

		//등수를  구하는 메서드를 호출해서 등수를 구한다.
		stuTest.SetRanking(stuList);
		
		System.out.println("처음데이터");
		for(Student stu: stuList) {
			System.out.println(stu);
			
		}
		Collections.sort(stuList, new ScoreName());// 내부정렬기준으로 정렬한다. 
		

		
		System.out.println("학번의 오름차순정렬후 ..");
		for(Student stu1: stuList) {
			System.out.println(stu1);
		}
		System.out.println("--------------------");
		
		
		
		
		
	}
	
//스튜던트객체생성
}                //정렬기준
class Student implements Comparable<Student>{
	private int num;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int sum;
	private int rank;
	
	
	
	public Student(int num, String name, int kor, int eng, int math) {
		super();
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.sum = sum;
		this.rank= rank;
	}
	public int getrank() {
		return rank;
	}
	public void setrank(int rank) {
		this.rank=rank;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}

	public int getsum() {
		return kor+eng+math;
	
	}
	public void setsum(int sum) {
		this.sum = sum;
	}
	
	//출력하는것
	@Override
	public String toString() {
		return "Student [num=" + num + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math + " , sum=" + getsum() + 
				","+"rank="+rank
				+ "]";
	}
	
	
//학번의 오름차순 정렬기준
	@Override
	public int compareTo(Student stu) {
		
		return Integer.compare(this.num,stu.getNum());
	}
	
	
}
//학번 오름차순
//이름
class ScoreName implements Comparator<Student>{
	 @Override
	 
	public int compare(Student stu1, Student stu2) {
		 
		 if(stu1.getsum()>stu2.getsum()) {
			 return-1;
		 
		 }else if(stu1.getsum()==stu2.getsum()) {
			 
			 if(stu1.getName().compareTo(stu2.getName())<0) {
				 return -1;
			 }else if(stu1.getName().compareTo(stu2.getName())==0) {
				return 0; 
			 }else {
				 return 1;
			 }
		 }else {
			 return 1;	 
		 }
		
	}
}





