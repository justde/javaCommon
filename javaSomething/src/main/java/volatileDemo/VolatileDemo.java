package volatileDemo;

import java.util.concurrent.TimeUnit;

/**
 * @author: zhangjd
 * @Date: 2019/6/3 23:46
 * @Description:
 */
public class VolatileDemo {
    public static void main(String[] args) throws InterruptedException {
        MyData myData = new MyData();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.add60();
            System.out.println(Thread.currentThread().getName()+ "  线程内修改num的值为 ：" + myData.number);
        }, "Thread1").start();

        while (myData.number == 0) {

        }
        TimeUnit.SECONDS.sleep(4);
        System.out.println(Thread.currentThread().getName()+ "   主线程从循环中跳出");
    }
}

class MyData {
    volatile int number = 0;

    void add60() {
        this.number = 60;
    }
}
