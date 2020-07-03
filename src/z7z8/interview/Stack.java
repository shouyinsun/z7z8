package z7z8.interview;

import java.util.LinkedList;

/**
 * @description: 下面的代码在绝大部分时间内都运行得很正常
 * 请问在什么情况下会出现问题
 * 问题的根源在哪里
 * @author: cash
 * @create: 2020/6/15 10:02
 **/

/***
 *   //1.存在死锁,synchronized修饰普通方法,修饰的是this对象,先pop后push,会一直阻塞在wait,wait只是释放了this锁,list的仍然有锁
 *   //2. if( list.size() <= 0 ) {
 *           wait();
 *         }
 *         有问题,notify唤醒之后,如果被其他线程抢先pop了,程序从wait()后开始执行,removeLast会异常
 */
public class Stack {
    LinkedList list = new LinkedList();

    public synchronized  void push(Object x) {
        synchronized(list) {
            list.addLast( x );
            notify();
        }
    }

    public synchronized  Object pop()
            throws Exception {
        synchronized(list) {
            if( list.size() <= 0 ) {
                wait();
            }
            //这里改成 while(list.size() <=0) wait();
            return list.removeLast();
        }
    }

    public static void main(String[] args) throws Exception {
        Stack stack =new Stack();
        Thread t1=new Thread(() -> {
            try {
                stack.pop();
                System.out.println("pop");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread t2=new Thread(() -> {
            try {
                stack.push(new Object());
                System.out.println("push");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();

        System.out.println("done");
    }
}
