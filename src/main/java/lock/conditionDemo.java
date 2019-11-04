package lock;

import javax.swing.plaf.TableHeaderUI;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: zhangjd
 * @Date: 2019/6/27 23:32
 * @Description: 按照顺序 从a-》b-》c 输出。
 */
class print{
    Lock lock = new ReentrantLock();
    Condition acon = lock.newCondition();
    Condition bcon = lock.newCondition();
    Condition ccon = lock.newCondition();
    String flag = "a";
    public void a(){
        try {
            lock.lock();
            while (!"a".equals(flag)){
                acon.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println("AAAA");
            }
            flag = "b";
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            bcon.signal();
            lock.unlock();
        }
    }
    public void b(){
        try {
            lock.lock();
            while (!"b".equals(flag)){
                bcon.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println("BBBB"+i);
            }
            flag = "c";
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ccon.signal();
            lock.unlock();
        }
    }
    public void c(){
        try {
            lock.lock();
            while (!"c".equals(flag)){
                ccon.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println("CCCC"+i);
            }
            flag = "a";
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            acon.signal();
            lock.unlock();
        }
    }
}
public class conditionDemo {

    public static void main(String[] args) {
        print print = new print();
        new Thread(()->{
            print.c();
        }).start();
        new Thread(()->{
            print.b();
        }).start();
        new Thread(()->{
            print.a();
        }).start();
    }
}
