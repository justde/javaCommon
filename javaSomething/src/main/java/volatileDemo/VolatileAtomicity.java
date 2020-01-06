package volatileDemo;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: zhangjd
 * @Date: 2019/6/4 00:18
 * @Description: 原子性
 */
public class VolatileAtomicity {
    public static void main(String[] args) {
        MyDatas myDatas = new MyDatas();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myDatas.numPlusPlus();

                }
            }).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(myDatas.number);
        System.out.println(myDatas.i);
    }
}

class MyDatas {
     AtomicInteger number = new AtomicInteger();
     int i =0;
     void numPlusPlus() {
        number.getAndIncrement();
        i++;
    }
}
