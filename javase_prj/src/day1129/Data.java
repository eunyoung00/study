package day1129;

/**
 * 이름,나이,이메일,성의 이니셜 한자를 저장하는 클래스
 * @author owner
 */
public class Data {
	private String name;
	private int age;
	private String email;
	private char initial;
	
	public Data() {
		System.out.println("Data Default Constructor");
	}//Data
	
	public Data(String name, int age, String email, char initial) {
	//생성자의 오버로딩(인자있는 생성자)
		System.out.println("Data Constructor");
		this.name=name;
		this.age=age;
		this.email=email;
		this.initial=initial;
	}//Data
		
	public void setName(String name) {
		this.name=name;//heap에다 올려주는 과정
	}//setName
	public void setAge(int age) {
		this.age=age;
	}//setAge
	public void setEmail(String email) {
		this.email=email;
	}//setEmail
	public void setInitial(char initial) {
		this.initial=initial;
	}//setInitial
	
	
	public String getName() {
		return name;
	}//getName
	public int getAge() {
		return age;
	}//getAge
	public String getEmail() {
		return email;
	}//getEmail
	public char getInitial() {
		return initial;
	}//getInitial
	
	
	public String toString() {
		return "이름 : "+name+", 나이  :"+age+", 이메일 : "+email+"이니셜 :"+initial;
	}


	
}//class
//얘로 배열을 만들것..~!~!=> 객체 배열
//레코드로 테이블을 만든것과 같당


