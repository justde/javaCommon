package QueueDemo;


import java.security.cert.PolicyQualifierInfo;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhangjd
 * @Date: 2019/6/27 23:49
 * @Description:
 */

public class SynchroniousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue queue = new SynchronousQueue();
        new Thread(()->{
            try {
                queue.put("a");
                System.out.println("放置一个");
                TimeUnit.SECONDS.sleep(2);
                queue.put("b");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                System.out.println(queue.take());
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
