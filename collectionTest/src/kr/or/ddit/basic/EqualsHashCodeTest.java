package kr.or.ddit.basic;

import java.util.HashSet;

public class EqualsHashCodeTest {

	public static void main(String[] args) {
		person p1 = new person();

		p1.setId(1);
		p1.setName("홍길동");

		person p2 = new person();

//		p2.setId(2);
//		p2.setName("일지매");
		p2.setId(1);
		p2.setName("홍길동");
		person p3 = p1;

		System.out.println(p1 == p2);//주소값 비교?
		System.out.println(p1 == p3);
		
		System.out.println(p1.equals(p2));
		System.out.println("--------------------------------");
		
		HashSet<person>testSet = new HashSet<>();
		
		testSet.add(p1);
		testSet.add(p2);
		
		System.out.println("set의 크기:"+testSet.size());
		
		System.out.println("p1: "+p1.hashCode());
		System.out.println("p2: "+p2.hashCode());
		System.out.println("p3: "+p3.hashCode());
		/*
		 	-equals()메서드 ==>두 객체의 내용이 같은지 검사
		 	-hashCode()메서드 ==> 두 객체의 동일성을 검사
		 	
		 	-hashSet, Hashtable, hashmap과 같이 hash로 시작하는컬렉션 객체들은
		 	객체의 의미상의 동일성을 비교하기 위해서hashCode()메서드를호출하여 비교한다.
		 	그러므로, 객체가 같은지 여부를 결정하려면 hashCode()메서드를 재정의 해야한다. 
		 	
		 	-hashCode()메서드에서 사용하는 '해싱 알고리즘은 서로다른 객체들에대해서 같은 hashCode를 나타낼수있다.
		 */
	}

}

class person {
	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		person other = (person) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
		 
	}
	
	
//	@Override
//	public boolean equals(Object obj) {
//		if(obj==null)
//			return false;
//		if(this.getClass()!=obj.getClass())//같은 유형의 클래스인지 검사                   //클래스의 유형을 나타내주는 메서드
//			return false;
//		if(this ==obj)  //참조값 (주소값이) 같은지검사
//			return true;
//	
//		person myobj=(person)obj; //매개 변수의 객체를 현재 객체유형으로 형변환한다.
//		
//		if(this.name==null && myobj.name != null) {
//			return false;
//		}
//		if(this.id==myobj.id && this.name==myobj.name) {//이거다음 equals
//			return true;
//		}			
//		if(this.id==myobj.id && this.name.equals(myobj.name)) {
//			return true;
//		}
//		return false;
//	}
	
	
}





