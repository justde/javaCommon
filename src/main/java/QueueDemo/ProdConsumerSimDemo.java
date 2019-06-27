package QueueDemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: zhangjd
 * @Date: 2019/6/27 00:30
 * @Description:
 * 传统版 生产者消费者模式  一个初始值为零的变量，两个线程对其交替操作，一个加一一个减一，进行5轮。
 */
class ShareDate{
    public int getSum() {
        return sum;
    }

    private int sum = 0;
    private Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    public void increment(){
        lock.lock();
        try {
            while (sum!=0){
                condition.await();
            }
            sum++;
            System.out.println(Thread.currentThread().getName()+"\t 加法  sum:" + sum);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void decrement(){
        lock.lock();
        try {
            while (sum == 0){
                condition.await();
            }
            sum--;
            System.out.println(Thread.currentThread().getName()+"\t 减法  sum:" + sum);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
public class ProdConsumerSimDemo {
    public static void main(String[] args) {
        ShareDate shareDate = new ShareDate();
        new Thread(()->{
            //for (int i = 0; i < 5; i++) {
                shareDate.increment();
            //}
        },"t1").start();

        new Thread(()->{
            //for (int i = 0; i < 5; i++) {
                shareDate.decrement();
           // }
        },"t2").start();


    }
}
