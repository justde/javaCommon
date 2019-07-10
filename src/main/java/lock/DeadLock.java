package lock;

import java.util.concurrent.TimeUnit;

/**
 * @author: zhangjd
 * @Date: 2019/7/10 22:53
 * @Description:
 */

public class DeadLock {
    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        String lock1 = "one";
        String lock2 = "two";
            new Thread(()->{
                deadLock.lock(lock1,lock2);
            },"A").start();

            new Thread(()->{
                deadLock.lock(lock2,lock1);
            },"b").start();
    }

    public void lock(String lock1, String lock2 ){
        synchronized (lock1){
            System.out.println(Thread.currentThread().getName() + "in lock one ");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2){
                System.out.println(Thread.currentThread().getName() + "in lock two");
            }
        }
    }
}
