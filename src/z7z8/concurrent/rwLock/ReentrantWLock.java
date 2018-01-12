package z7z8.concurrent.rwLock;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author xuc
 * @time 2017年3月9日 下午1:27:13
 * @description 支持重入的写锁
 * 
 */
public class ReentrantWLock {
	private Map<Thread, Integer> readingThreads = new HashMap<Thread, Integer>();
	//判断当前线程能够获取写操作权限时通过计数的方法实现的
	private int writeAccesses = 0;
	private int writeRequests = 0;
	private Thread writingThread = null;
	
    public synchronized void lockWrite() throws InterruptedException{  
    	writeRequests++;
		Thread callingThread=Thread.currentThread();
		while(!canGrantWriteAccess(callingThread)){
			wait();
		}
		writeRequests--;
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
}
