package QueueDemo;

import javax.swing.text.TabableView;
import javax.xml.stream.FactoryConfigurationError;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: zhangjd
 * @Date: 2019/6/28 00:01
 * @Description:
 */
class MyResource {
    volatile boolean flag = true;
    AtomicInteger num = new AtomicInteger();
    BlockingQueue<String> queue =null;

    public MyResource(BlockingQueue<String> queue) {
        this.queue = queue;
        System.out.println(queue.getClass().getName());
    }

    public void prod(){
        String a = null;
        boolean offer = false;
        while (flag){
            a = num.getAndIncrement()+"";
            offer = queue.offer(a);
            if (offer){
                System.out.println("prod 生产成功，a = "+a);
            }else {
                System.out.println("prod 失败 a= "+a);
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("stop");
    }

    public void cum(){
        String poll = null;
        while (flag){
            try {
                poll = queue.poll(2, TimeUnit.SECONDS);
                System.out.println("cum 消费成功 poll = "+poll);
            } catch (InterruptedException e) {
                System.out.println("超时退出");
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("tuichu");
    }
    public void stop(){
        flag = false;
    }
}

public class ProdComsumerBQDemo {
    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<String>(2));
        new Thread(()->{
            myResource.prod();
        }).start();

        new Thread(()->{
            myResource.cum();
        }).start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myResource.stop();
    }
}

