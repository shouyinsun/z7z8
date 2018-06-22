package z7z8.producterAndConsumer.Version2;

import java.util.List;
import java.util.Random;

import z7z8.producterAndConsumer.Data;

public class Consumer implements Runnable {
	private volatile boolean isRunning=true;

	private List<Data> queue;
	private int length;
	private static final int SLEEPTIME = 1000;

	
	
	public Consumer(List<Data> queue, int length) {
		super();
		this.queue = queue;
		this.length = length;
	}



	@Override
	public void run() {
		Random r = new Random();
		System.out.println("start Consumer name :"
				+ Thread.currentThread().getName());
		try {
			while (isRunning) {
				if (Thread.currentThread().isInterrupted()) {
					break;
				}
				synchronized (queue) {
					if (queue.size() == 0) {
						System.out.println(Thread.currentThread().getName()+"加入等待");
						queue.wait();
						System.out.println(Thread.currentThread().getName()+"喚醒并拿到鎖");
					}
					if(queue.size()>0){
						Data data = queue.remove(0);
						if (null != data) {
							System.out.println("消费data" + data);
						}
						if(queue.size()==length-1){
							queue.notifyAll();
						}
					}
				}
				Thread.sleep(r.nextInt(SLEEPTIME/10));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	public void stop(){
		isRunning=false;
	}

}
