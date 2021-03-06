package day1129;

/**
 * 상속 관계의 부모클래스 : 모든 자식이 가져야 할 공통 특징 정의
 * 부모에게 정의된 코드는 자식에게서 사용할 수 있다.
 * @author owner
 */
public class TestSuper {

	public int pub_i;//어디에 있는 자식이든 자식에서 사용가능
	protected int pro_i;//같은 패키지의 자식, 다른 패키지의 자식에서 사용 가능
	@SuppressWarnings("unused")
	private int pri_i;//어디에 있든간에 부모클래스 안에서만 사용가능
	int def_i;//같은 패키지의 자식에서 사용가능하지만 패키지가 다르면 사용불가.
	
	public TestSuper() {
		System.out.println("부모클래스의 기본생성자");
	}//TestSuper
	
	public void superMethod() {
		System.out.println("부모클래스의 method");
	
	}
	
}//class
