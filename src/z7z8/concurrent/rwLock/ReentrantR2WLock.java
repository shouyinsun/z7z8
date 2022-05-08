package z7z8.concurrent.rwLock;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author xuc
 * @time 2017年3月9日 下午1:41:41
 * @description 
 * 某些时候一个持有读访问权限的线程需要同时持有写访问权限
 * 这种情况只有在该线程是    唯一   的读操作者时才是有可能的
 */
public class ReentrantR2WLock {
	private Map<Thread, Integer> readingThreads = new HashMap<Thread, Integer>();
	//判断当前线程能够获取写操作权限时通过计数的方法实现的
	private int writeAccesses = 0;
	private Thread writingThread = null;
	
    public synchronized void lockWrite() throws InterruptedException{  
		Thread callingThread=Thread.currentThread();
		while(!canGrantWriteAccess(callingThread)){
			wait();
		}
		writeAccesses++;
		writingThread=callingThread;
	}
    
    public synchronized void unlockWrite()throws InterruptedException{  
    	writeAccesses--;
    	if(0==writeAccesses){
    		writingThread=null;
    	}
    	notifyAll();
    }
	 
	private boolean canGrantWriteAccess(Thread callingThread) {
		if(isOnlyReader(callingThread)) return true;
		if (hasReaders())
			return false;
		if (writingThread == null)
			return true;
		if (!isWriter(callingThread))
			return false;
		return true;
	}

	private boolean hasReaders() {
		return readingThreads.size() > 0;
	}

	private boolean isWriter(Thread callingThread) {
		return writingThread == callingThread;
	}
	
	private boolean isOnlyReader(Thread callingThread){
		if(1==readingThreads.size()&&readingThreads.get(callingThread)!=null){
			return true;
		}
		return false;
	}
}
