package cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: zhangjd
 * @Date: 2019/6/5 23:51
 * @Description:
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5, 10) + "\t current data : " +atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5,9) + "\t current data : " +atomicInteger.get());
        atomicInteger.getAndIncrement();
    }
}
