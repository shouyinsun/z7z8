package z7z8.runtime;

import java.io.IOException;

public class RuntimeTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//虚拟机可用处理器个数
		int availablePro =  Runtime.getRuntime().availableProcessors();
		System.out.println("虚拟机可用的处理器个数: " + availablePro);
		
		Thread thread1 = new Thread() {
			public void run() {
				System.out.println("thread1...");
			}
		};

		Thread thread2 = new Thread() {
			public void run() {
				System.out.println("thread2...");
			}
		};

		Thread shutdownThread = new Thread() {
			public void run() {
				System.out.println("shutdownThread...");
			}
		};

		//增加一个jvm关闭的钩子 
		Runtime.getRuntime().addShutdownHook(shutdownThread);

		thread1.start();
		thread2.start();
		
		//exec(String[] cmdarray, String[] envp, File dir) 
		String [] cmd={"cmd","/C","cp cash cash"};  
		Process proc =Runtime.getRuntime().exec(cmd); 
		
		//进程的内存情 况
		Long free=Runtime.getRuntime().freeMemory(); 
		System.out.println("free memory Mbs:"+(free/1024/1024));
		
		Long total=Runtime.getRuntime().totalMemory();
		System.out.println("total memory Mbs:"+(total/1024/1024));
		
		Long max=Runtime.getRuntime().maxMemory();
		System.out.println("max memory Mbs:"+(max/1024/1024));

	}

}
