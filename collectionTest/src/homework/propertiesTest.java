package homework;

import java.util.Properties;

public class propertiesTest {

	public static void main(String[] args) {
		/*Properties객체 Map보다 축소된 기능의 객체
			
			-Map은 Key값하고value값에 모든 형태의 객체를 사용할 수 있지만
			properties는 key값과value 값에 String만 사용할수있다.
			
			-Map은 put(),get()메서드를 이용하여 데이터를 입출력하지만
			properties는 setProperty() ,getProperty()메서드를 통해서 데이터를 입출력한다.
			
			-properties는 데이터를 파일로 입출력 할 수있다.
			
			
		*/
		Properties prop= new Properties();
		
		prop.setProperty("name", "홍길동");
		prop.setProperty("age","20");
		prop.setProperty("tel", "010-1010-1010");
		prop.setProperty("addr", "대전");
		//추가
		///////////////////////////////////////////
		
		String name = prop.getProperty("name");
		int age = Integer.parseInt(prop.getProperty("age"));
		String tel =prop.getProperty("tel");
		String addr = prop.getProperty("addr");
		
		System.out.println("이름:"+name);
		System.out.println("나이:"+age);
		System.out.println("전화:"+tel);
		System.out.println("주소:"+addr);
		
		//꺼내오기
	}

}
