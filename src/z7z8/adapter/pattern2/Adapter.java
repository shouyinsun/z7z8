package z7z8.adapter.pattern2;

/**
 * 
 * @author cash
 * @date 2017年9月6日 下午8:30:49
 * @decription 适配器类
 * s
 */
public class Adapter implements Target {
	/**
	 * 被适配 field
	 */
	private Adaptee adaptee;

	public Adapter(Adaptee adaptee) {
		super();
		this.adaptee = adaptee;
	}

	@Override
	public void request() {
		// TODO Auto-generated method stub
		this.adaptee.specificRequest();
	}
	
	

}
