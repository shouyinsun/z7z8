package z7z8.concurrent.rwLock;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 通过 synchronized 实现  读写锁
 * 锁能降级,不能升级,持有写锁,同一线程要可以获取读,持有读同一线程不能获取写
 * @Author cash
 * @Date 2022/4/19 4:48 PM
 **/
public class ReentrantRWLock {

    private Map<Thread, Integer> readingThreads = new HashMap<Thread, Integer>();

    private int writeAccesses = 0;
    private Thread writingThread = null;

    public synchronized void lockRead() throws InterruptedException {
        Thread callingThread = Thread.currentThread();
        while (!canGrantReadAccess(callingThread)) {
            wait();
        }

        readingThreads.put(callingThread, (getReadAccessCount(callingThread) + 1));
    }

    private boolean canGrantReadAccess(Thread callingThread) {
        if (isWriter(callingThread)) {
            return true;
        }
        if (hasWriter()) {
            return false;
        }
        return true;
    }

    public synchronized void unlockRead() {
        Thread callingThread = Thread.currentThread();
        if (!isReader(callingThread)) {
            throw new IllegalMonitorStateException(
                "Calling Thread does not" + " hold a read lock on this ReadWriteLock");
        }
        int accessCount = getReadAccessCount(callingThread);
        if (accessCount == 1) {
            readingThreads.remove(callingThread);
        } else {
            readingThreads.put(callingThread, (accessCount - 1));
        }
        notifyAll();
    }

    public synchronized void lockWrite() throws InterruptedException {
        Thread callingThread = Thread.currentThread();
        while (!canGrantWriteAccess(callingThread)) {
            wait();
        }
        writeAccesses++;
        writingThread = callingThread;
    }

    public synchronized void unlockWrite() throws InterruptedException {
        if (!isWriter(Thread.currentThread())) {
            throw new IllegalMonitorStateException("Calling Thread does not" +
                " hold the write lock on this ReadWriteLock");
        }
        writeAccesses--;
        if (writeAccesses == 0) {
            writingThread = null;
        }
        notifyAll();
    }

    private boolean canGrantWriteAccess(Thread callingThread) {
        if (isOnlyReader(callingThread)) {return true;}
        if (hasReaders()) {return false;}
        if (writingThread == null) {return true;}
        if (!isWriter(callingThread)) {return false;}
        return true;
    }

    private int getReadAccessCount(Thread callingThread) {
        Integer accessCount = readingThreads.get(callingThread);
        if (accessCount == null) {return 0;}
        return accessCount.intValue();
    }

    private boolean hasReaders() {
        return readingThreads.size() > 0;
    }

    private boolean isReader(Thread callingThread) {
        return readingThreads.get(callingThread) != null;
    }

    private boolean isOnlyReader(Thread callingThread) {
        return readingThreads.size() == 1
            && readingThreads.containsKey(callingThread);
    }

    private boolean hasWriter() {
        return writingThread != null;
    }

    private boolean isWriter(Thread callingThread) {
        return writingThread == callingThread;
    }

}
