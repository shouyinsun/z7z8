package z7z8.reflect.accessible;

import java.lang.reflect.Method;

/**
 * 
 * @author cash
 * @date 2017年5月24日 上午10:20:44
 * @decription 不管是public,default,protect还是private方法,通过反射都可以自由调用
 */
public class Test {

	public static void main(String[] args) throws Exception {
		A a = HiddenC.makeA();
		a.f();
		System.out.println(a.getClass().getName());
		// Oops! Reflection still allows us to call g():
		callHiddenMethod(a, "g");
		// And even methods that are less accessible!
		callHiddenMethod(a, "u");
		callHiddenMethod(a, "v");
		callHiddenMethod(a, "w");
	}

	static void callHiddenMethod(Object a, String methodName) throws Exception {
		Method g = a.getClass().getDeclaredMethod(methodName);
		g.setAccessible(true);
		g.invoke(a);
	}

}
