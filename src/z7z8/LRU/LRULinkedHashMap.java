package z7z8.LRU;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author cash
 * @date 2017年9月15日 下午3:43:55
 * @decription 利用LinkedHashMap实现简单的缓存LRU  LinkedHashMap 是双向链表
 * 自定义实现removeEldestEntry方法
 * LRU：Least Recently Used 最近最少使用  算法
 */
public class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {
	/**
	 * @author cash
	 * @date 2017年9月15日 下午4:12:22
	 * @decription 
	 */
	private static final long serialVersionUID = -5097913705691983750L;
	
	private final int maxCapacity;
	private static final float DEFAULT_LOAD_FACTOR=0.75f;
	private final Lock lock=new ReentrantLock();
	
	public LRULinkedHashMap(int maxCapacity) {
		super(maxCapacity,DEFAULT_LOAD_FACTOR);
		this.maxCapacity = maxCapacity;
	}
	

    @Override 
    protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {  
        return size() > maxCapacity;  
    }
    
    public boolean containsKey(Object key){
    	try{
        	lock.lock();
        	return super.containsKey(key);
    	}finally{
        	lock.unlock();
    	}

    }
    
    @Override 
    public V get(Object key) {  
        try {  
            lock.lock();  
            return super.get(key);  
        } finally {  
            lock.unlock();  
        }  
    }  
   
    @Override 
    public V put(K key, V value) {  
        try {  
            lock.lock();  
            return super.put(key, value);  
        } finally {  
            lock.unlock();  
        }  
    }  
   
    public int size() {  
        try {  
            lock.lock();  
            return super.size();  
        } finally {  
            lock.unlock();  
        }  
    }  
   
    public void clear() {  
        try {  
            lock.lock();  
            super.clear();  
        } finally {  
            lock.unlock();  
        }  
    }  
   
    public Collection<Map.Entry<K, V>> getAll() {  
        try {  
            lock.lock();  
            return new ArrayList<Map.Entry<K, V>>(super.entrySet());  
        } finally {  
            lock.unlock();  
        }  
    }  
	
	
	
}
