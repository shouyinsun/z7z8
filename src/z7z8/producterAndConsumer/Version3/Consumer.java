package z7z8.producterAndConsumer.Version3;

import java.util.List;
import java.util.Random;

import z7z8.producterAndConsumer.Data;

public class Consumer implements Runnable {
	
	private volatile boolean isRunning=true;

	private List<Data> queue;
	private static final int SLEEPTIME = 1000;
	
	public Consumer(List<Data> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		Random r = new Random();
		System.out.println("start Consumer name :"
				+ Thread.currentThread().getName());
		while(isRunning){
			if (Thread.currentThread().isInterrupted()) {
				break;
			}
			Main.lock.lock();
			try {
				if (queue.size() == 0) {
					Main.empty.await();
				}
				if(queue.size()>0){
					Data data = queue.remove(0);
					if (null != data) {
						System.out.println("消费data" + data);
					}
					Main.full.signalAll();
				}
				Thread.sleep(r.nextInt(SLEEPTIME/10));
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				Main.lock.unlock();
			}
			
		}
		
	}
	
	public void stop(){
		isRunning=false;
	}
}
