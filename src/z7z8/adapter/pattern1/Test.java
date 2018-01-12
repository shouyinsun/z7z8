package z7z8.adapter.pattern1;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 使用普通功能类
		Target concreteTarget = new ConcreteTarget();
		concreteTarget.request();

		// 使用特殊功能类，即适配类
		Target adapter = new Adapter();
		adapter.request();

	}

}
