package z7z8.z7z8Test;

public class TestShutdownHook {
	private static Object finishNotify = new Object();
	
	public static void main(String[] args) {
		final Boolean flag=false;
		
		// 定义线程1
		Thread thread1 = new Thread() {
			public void run() {
				try {
					Thread.currentThread().sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("thread1...");
			}
		};
		// 定义线程2
		Thread thread2 = new Thread() {
			public void run() {
				try {
					Thread.currentThread().sleep(15000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("thread2...");
			}
		};
		// 定义关闭线程
		Thread shutdownThread = new Thread() {
			public void run() {
				while(!flag){
					synchronized (finishNotify) {
	                    try {
	                        finishNotify.wait(10000);
	    					System.out.println("shutdownThread...");
	                        break;
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                        Thread.currentThread().interrupt();
	                    }
	                }
				}
				
				System.out.println("shutdownThread......");

				
			}
		};
		// jvm关闭的时候先执行该线程钩子
		Runtime.getRuntime().addShutdownHook(shutdownThread);
		thread1.start();
		thread2.start();
	}

}
