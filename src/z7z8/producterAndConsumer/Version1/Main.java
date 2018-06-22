package z7z8.producterAndConsumer.Version1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

import z7z8.producterAndConsumer.Data;

/***
 * 使用阻塞队列
 * @author cash
 * @date 2018年6月22日 下午3:04:33
 * @decription
 */

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		BlockingQueue<Data> queue = new LinkedBlockingDeque<Data>(10);
		Producer p1 = new Producer(queue);
		Producer p2 = new Producer(queue);
		Producer p3 = new Producer(queue);
		Consumer c1 = new Consumer(queue);
		Consumer c2 = new Consumer(queue);
		Consumer c3 = new Consumer(queue);
		ExecutorService service=Executors.newCachedThreadPool();
		service.execute(p1);
		service.execute(p2);
		service.execute(p3);
		service.execute(c1);
		service.execute(c2);
		service.execute(c3);
		Thread.sleep(10*1000);
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
