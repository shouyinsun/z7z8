package z7z8.producterAndConsumer.Version1;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import z7z8.producterAndConsumer.Data;

public class Producer implements Runnable {
	private volatile boolean isRunning=true;
	private BlockingQueue<Data> queue;
	private static AtomicInteger count=new AtomicInteger();
	private static final int SLEEPTIME=1000;
	
	

	public Producer(BlockingQueue<Data> queue) {
		super();
		this.queue = queue;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		Random r=new Random();
		System.out.println("start producting name:"+Thread.currentThread().getName());
		while(isRunning){
			try {
				String name=Thread.currentThread().getName()+"#Data#"+count.incrementAndGet();
				Data data=new Data(name);
				queue.put(data);
				System.out.println(data+" 加入队列");
				Thread.sleep(r.nextInt(SLEEPTIME));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
			
		}

	}
	
	public void stop(){
		isRunning=false;
	}

}
