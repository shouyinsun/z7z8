package z7z8.adapter.pattern2;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 使用普通功能类
		Target concreteTarget = new ConcreteTarget();
		concreteTarget.request();

		// 使用特殊功能类 即适配类,
		// 需要先创建一个被适配类的对象作为参数
		Target adapter = new Adapter(new XxAdaptee());
		adapter.request();
		
		Target adapter2 = new Adapter(new YyAdaptee());
		adapter2.request();

	}

}
