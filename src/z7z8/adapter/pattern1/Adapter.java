package z7z8.adapter.pattern1;

/**
 * 
 * @author cash
 * @date 2017年9月6日 下午8:23:35
 * @decription 适配器类
 * 继承被适配器类,同时实现标准接口
 */
public class Adapter extends Adaptee implements Target  {

	@Override
	public void request() {
		// TODO Auto-generated method stub
		super.specificRequest();
		
	}

}
