package z7z8.concurrent.rwLock;

/**
 * 
 * @author xuc
 * @time 2017年3月9日 下午12:47:36
 * @description 通过 synchronized实现简单的读写锁
 */
public class SimpleRWLock {

	private int readers = 0;
	private int writers = 0;
	//一个线程要进行写操作必须先进行写访问"请求"动作",请求"动作会先检查当前线程是否能真的进行写操作,一个线程只有在没有其他线程正在进行 读操作 或 写操作 的时候才能进行写操作
	private int writeRequests = 0;

	public synchronized void lockRead() throws InterruptedException {
		while (writers > 0 || writeRequests > 0) {
			wait();
		}
		readers++;
	}

	public synchronized void unlockRead() {
		readers--;
		notifyAll();
	}

	public synchronized void lockWrite() throws InterruptedException {
		writeRequests++;

		while (readers > 0 || writers > 0) {
			wait();
		}
		writeRequests--;
		writers++;
	}

	public synchronized void unlockWrite() throws InterruptedException {
		writers--;
		notifyAll();
	}
}
