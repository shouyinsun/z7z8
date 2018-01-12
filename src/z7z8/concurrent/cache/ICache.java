package z7z8.concurrent.cache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author xuc
 * @time 2017年3月9日 下午12:07:15
 * @description 缓存 获取缓存
 */
public interface ICache {
	public ConcurrentHashMap<String, Object> map=new ConcurrentHashMap<>();

	public Object getData(String key);
}
