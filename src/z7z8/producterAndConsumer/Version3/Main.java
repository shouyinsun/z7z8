package z7z8.producterAndConsumer.Version3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import z7z8.producterAndConsumer.Data;

/***
 * Lock的condition await/signalAll
 * @author cash
 * @date 2018年6月22日 下午5:27:08
 * @decription
 */
public class Main {
	
	public static ReentrantLock lock=new ReentrantLock();
	public static Condition empty=lock.newCondition();
	public static Condition full=lock.newCondition();

	public static void main(String[] args) throws InterruptedException {
		List<Data> queue=new ArrayList<Data>();
		int length=5;
		Producer p1 = new Producer(queue, length);
		Producer p2 = new Producer(queue, length);
		Producer p3 = new Producer(queue, length);
		Consumer c1 = new Consumer(queue);
		Consumer c2 = new Consumer(queue);
		Consumer c3 = new Consumer(queue);
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(p1);
		service.execute(p2);
		service.execute(p3);
		service.execute(c1);
		service.execute(c2);
		service.execute(c3);
		Thread.sleep(20*1000);
		p1.stop();
		p2.stop();
		p3.stop();
		c1.stop();
		c2.stop();
		c3.stop();	
		Thread.sleep(10*1000);
		service.shutdown();

	}

}
