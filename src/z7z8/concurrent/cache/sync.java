package z7z8.concurrent.cache;

/**
 * 
 * @author xuc
 * @time 2017年3月9日 下午12:13:17
 * @description 同步代码实现
 */
public class sync implements ICache {

	@Override
	public Object  getData(String key) {
		// TODO Auto-generated method stub
		synchronized(key){
			 Object result = map.get(key);  
		        if(result ==null){  
		            result = "new";//用这步代替访问数据库得数据  
		        }  
		     return result;  
		}
	}

}
