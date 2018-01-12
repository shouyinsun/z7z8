package z7z8.concurrent.rwLock;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author xuc
 * @time 2017年3月9日 下午1:06:51
 * @description 支持重入的读锁
 */
public class ReentrantRLock {
	private int writers=0;//当前读
	private int writeRequests=0;//当前读请求数
	private Map<Thread,Integer> readingThreads=new HashMap<>();//线程的读次数
	
	//读加锁
	public synchronized void lockRead() throws InterruptedException{
		Thread callingThread=Thread.currentThread();
		while(!canGrantReadAccess(callingThread)){
			wait();
		}
		int cnt=getReadAccessCount(callingThread);
		readingThreads.put(callingThread, (cnt+1));

	}
	
	//读解锁
	public synchronized void unlockRead(){ 
		Thread callingThread=Thread.currentThread();
		int cnt=getReadAccessCount(callingThread);
		if(1==cnt){
			readingThreads.remove(callingThread);
		}else{
			readingThreads.put(callingThread, (cnt+1));
		}
		//全部唤醒
		notifyAll();
	}
	
	
	
	
	private boolean canGrantReadAccess(Thread callingThread){
		if(writers>0) return false;
		//读重进入只有在当前没有线程对资源进行写操作时才可能被允许
		//另外,假如调用线程对象已经有了读访问权限,那么它的读重进入优先级将高于任何写访问请求
		if(isReader(callingThread))return true;
		if(writeRequests>0) return false;
		
		return true;
	}
	
	//获得线程的读次数
	private int getReadAccessCount(Thread callingThread){
		Integer accessCount=readingThreads.get(callingThread);
		if(null==accessCount) return 0;
		return accessCount.intValue();
	}
	
	//判断线程是否正在读
	private boolean isReader(Thread callingThread){
		return readingThreads.get(callingThread)!=null;
	}
	
	
}
