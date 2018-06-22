package z7z8.producterAndConsumer.Version1;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

import z7z8.producterAndConsumer.Data;

public class Consumer implements Runnable {
	private volatile boolean isRunning=true;
	private BlockingQueue<Data> queue;
	private static final int SLEEPTIME=1000;
	
	

	public Consumer(BlockingQueue<Data> queue) {
		super();
		this.queue = queue;
	}



	@Override
	public void run() {
		Random r=new Random();
        System.out.println("start Consumer name :"+Thread.currentThread().getName());
        while(isRunning){
        	Data data;
			try {
				data = queue.take();
				if(null!=data){
	        		System.out.println("消费data"+data);
	        	}
				Thread.sleep(r.nextInt(SLEEPTIME/10));
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
