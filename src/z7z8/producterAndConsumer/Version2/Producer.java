package z7z8.producterAndConsumer.Version2;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import z7z8.producterAndConsumer.Data;

public class Producer implements Runnable {
	private volatile boolean isRunning=true;
	
	private List<Data> queue;
	private int length;
	
	private static AtomicInteger count=new AtomicInteger();
	private static final int SLEEPTIME=1000;
	
	
	public Producer(List<Data> queue, int length) {
		super();
		this.queue = queue;
		this.length = length;
	}


	@Override
	public void run() {
		
		Random r=new Random();
		System.out.println("start producting name:"+Thread.currentThread().getName());
		while(isRunning){
			if(Thread.currentThread().isInterrupted()){
				break;
			}
			try{
				synchronized (queue) {
					if(queue.size()>=length){
						System.out.println(Thread.currentThread().getName()+"加入等待");
						queue.wait();
						System.out.println(Thread.currentThread().getName()+"喚醒并拿到鎖");
					}
					if(queue.size()<length){
						String name=Thread.currentThread().getName()+"#Data#"+count.incrementAndGet();
						Data data=new Data(name);
						System.out.println(data+" 加入队列");
						queue.add(data);
						if(queue.size()==1){
							queue.notifyAll();
						}
					}				
				}
				Thread.sleep(r.nextInt(SLEEPTIME));
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}

	}
	
	public void stop(){
		isRunning=false;
	}

}
