package lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: zhangjd
 * @Date: 2019/6/25 23:24
 * @Description:
 */
public class SpinLockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread +": come in lock");
        while (!atomicReference.compareAndSet(null,thread)){

        }
    }
    public void unLock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread + ": come in unlock");
        while (!atomicReference.compareAndSet(thread,null)){

        }
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(()->{
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.unLock();
        },"T1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            spinLockDemo.myLock();
            spinLockDemo.unLock();
        },"t2").start();
    }


}
