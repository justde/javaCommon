package lock;


import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: zhangjd
 * @Date: 2019/6/26 00:26
 * @Description:
 */
public class ReadWritedLock {
    volatile HashMap<String, String> cache = new HashMap();
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key, String value) {
        lock.writeLock().lock();
        System.out.println(key + "  正在写入");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cache.put(key, value);
        System.out.println(key + "  写入完成");
        lock.writeLock().unlock();
    }

    public String get(String key) {
        lock.readLock().lock();
        System.out.println(key + "  正在get");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String s = cache.get(key);
        System.out.println(key + "  get完成,key：" + s);
        lock.readLock().unlock();
        return s;
    }


    public static void main(String[] args) {
        final ReadWritedLock readWritedLock = new ReadWritedLock();
        for (int i = 0; i < 5; i++) {
            final int finalI = i;
            new Thread(() -> {
                readWritedLock.put(finalI + "", finalI + "");
            }).start();
        }
        for (int i = 0; i < 5; i++) {
            final int finalI = i;
            new Thread(() -> {
                readWritedLock.get(finalI + "");
            }).start();
        }
    }
}
