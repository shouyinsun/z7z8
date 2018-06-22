package z7z8.softReference;

import java.lang.ref.ReferenceQueue;
import java.util.concurrent.ConcurrentHashMap;


/****
 * 软引用缓存的基本实现
 * @author cash
 * @date 2018年6月1日 下午4:24:58
 * @decription
 * 
 * 
 * 通过软引用占有的对象，JVM会将其标记为软可达
 * 在JVM发生垃圾回收时，会对软可达的对象区别对待
 * 只要当JVM即将发生内存溢出时，才会将软可达的对象回收
 * 软引用可以配合引用队列进行使用，当软引用占有的对象被回收后，JVM会将该软引用对象放入引用队列中
 */
public class SoftRefCache<K, V> implements Cache<K, V> {

	// 缓存 用软引用记录
	private ConcurrentHashMap<K, ExtraInfoReference<V>> cache = new ConcurrentHashMap<K, ExtraInfoReference<V>>();
	private ReferenceQueue<V> refQueue = new ReferenceQueue<V>();

	public V get(K key) {
		V value = null;

		if (cache.containsKey(key)) {
			ExtraInfoReference<V> refValue = cache.get(key);
			value = refValue.get();
		}
		// 如果软引用被回收
		if (value == null) {
			// 清除软引用队列
			clearRefQueue();
			// 创建值并放入缓存
			value = createValue(key);
			ExtraInfoReference<V> refValue = new ExtraInfoReference<V>(key,value, refQueue);
			cache.put(key, refValue);
		}

		return value;
	}

	/**
	 * 实现set方法
	 */
	public boolean set(K key, V value) {
		ExtraInfoReference<V> refValue = new ExtraInfoReference<V>(key, value,
				refQueue);
		cache.put(key, refValue);
		return true;
	}

	/**
	 * 定义创建值的方法
	 * 
	 * @return
	 */
	protected V createValue(K key) {
		return (V) key;
	}

	/**
	 * 从软引用队列中移除无效引用， 同时从cache中删除无效缓存
	 */
	protected void clearRefQueue() {
		ExtraInfoReference<V> refValue = null;
		while ((refValue = (ExtraInfoReference<V>) refQueue.poll()) != null) {
			K key = (K) refValue.getExtraInfo();
			cache.remove(key);
		}
	}

}
