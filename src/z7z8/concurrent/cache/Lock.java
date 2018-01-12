package z7z8.concurrent.cache;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 
 * @author xuc
 * @time 2017年3月9日 下午12:13:40
 * @description 读写锁实现
 */
public class Lock implements ICache {

	ReentrantReadWriteLock rw = new ReentrantReadWriteLock();

	@Override
	public Object getData(String key) {
		rw.readLock().lock();// 在读前先上读锁
		Object result = null;
		try {
			result = map.get(key);
			if (result == null) {
				// 如果内存中没有所要数据
				rw.readLock().unlock();
				rw.writeLock().lock();
				if (result == null) {
					try {
						// 我们用这个代替对数据库访问得到数据的步骤
						result = "new";
						map.put(key,result);
					} finally {
						rw.writeLock().unlock();
					}
					rw.readLock().lock();
				}
			}
		} finally {
			rw.readLock().unlock();
		}
		return result;
	}

}
