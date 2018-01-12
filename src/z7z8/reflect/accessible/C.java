package z7z8.reflect.accessible;

/**
 * 
 * @author cash
 * @date 2017年5月24日 上午10:14:07
 * @decription
 */
public class C implements A {

	@Override
	public void f() {
		// TODO Auto-generated method stub
		System.out.println("public C.f()"); 

	}

	public void g() {
		System.out.println("public C.g()");
	}

	protected void v() {
		System.out.println("protected C.v()");
	}

	void u() {
		System.out.println("package C.u()");
	}

	private void w() {
		System.out.println("private C.w()");
	}

}
