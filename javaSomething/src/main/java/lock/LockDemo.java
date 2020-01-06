package lock;

import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.zip.CheckedInputStream;

/**
 * @author: zhangjd
 * @Date: 2019/6/24 22:51
 * @Description:
 */
class phone implements Runnable{
    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        get();
    }

    public void get() {
        lock.lock();
        lock.lock();

        try {
            System.out.println(Thread.currentThread().getName()+"  get ()");
            set();
        } finally {
            lock.unlock();

        }
    }
    public void set(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+" set()");
        } finally {
            lock.unlock();
        }
    }
}

public class LockDemo {
    public static void main(String[] args) {
        phone phone = new phone();
        Thread thread = new Thread(phone,"t1");
        thread.start();
        Thread thread1 = new Thread(phone,"t2");
        thread1.start();
    }





}
