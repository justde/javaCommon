package threadPoolDemo;

import java.util.concurrent.*;

/**
 * @author: zhangjd
 * @Date: 2019/6/29 00:05
 * @Description:
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService poolExecutor = new ThreadPoolExecutor(3, 5, 1L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        try {

            for (int i = 0; i < 9; i++) {
                poolExecutor.execute(()->{
                    System.out.println(Thread.currentThread().getName() +"\t 启动");
            });
            }
        }catch (Exception e){
            throw e;
        }finally {
            poolExecutor.shutdown();
        }

    }
}
