package z7z8.rpc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 返回获取、挂起和唤醒线程
 * author cash
 * create 2019-03-03-19:47
 **/
public class RpcResponseFuture {

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private Long requestId;

    public RpcResponseFuture(Long requestId) {
        this.requestId = requestId;
    }

    public byte[] get() {
        byte[] bytes = RpcContainer.getResponse(requestId);
        if (bytes == null || bytes.length < 0) {
            lock.lock();
            try {
                System.out.println("请求id:" + requestId + ",请求结果尚未返回,线程挂起");
                //挂起
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        System.out.println("请求id:" + requestId + ",请求结果返回,线程挂起结束");
        return RpcContainer.getResponse(requestId);
    }

    public void rpcIsDone() {
        lock.lock();
        try {
            //唤醒
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }
}
