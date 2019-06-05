package volatileDemo;

import java.util.concurrent.TimeUnit;

/**
 * @author: zhangjd
 * @Date: 2019/6/4 23:52
 * @Description:
 */
public class ReSoreSeqDemo {
    int a = 0;
    boolean flag = false;

    public void method1() {
        a = 1;
        flag = true;
    }

    public void method2() {
        if (flag) {
            a = a + 5;
            System.out.println("****value = " + a);
        }
    }
}

class test {
    public static void main(String[] args) {
        ReSoreSeqDemo r = new ReSoreSeqDemo();
        new Thread(()->{
            r.method2();
        }).start();
        new Thread(()->{
            r.method1();
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(r.a);
    }
}

