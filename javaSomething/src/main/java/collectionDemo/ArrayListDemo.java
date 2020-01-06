package collectionDemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author: zhangjd
 * @Date: 2019/6/12 23:31
 * @Description:
 */
public class ArrayListDemo {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        List<String> objects = Collections.synchronizedList(new ArrayList<>());
        List<String> arrayList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },"Thread:"+i).start();
        }
    }
    /**
     * 1、故障现象
     *      java.util.ConcurrentModificationException
     * 2、导致原因
     *      arrayList的add操作没有synchronized，所以是线程不安全的
     *      并发争抢修改导致，一个线程正在写入，另一个线程也在争抢，导致数据不一致异常，并发修改异常
     *  3、解决办法
     *      - Collections.synchronized
     *      - CopyOnWriteArrayList<>
     *          写时复制 copyOnWrite容器即写实复制的容器，往一个容器添加元素的额时候，不直接往当前容器object[]添加，而是先将当前容器object[]进行copy，
     *          复制出一个新的容器object[] newElements,然后往新的容器Object[] newElements里添加元素，添加完元素之后，再将原容器的引用指向新的
     *          容器setArray(newElements);这样做的好处就是可以对copyOnWrite容器进行并发的读，而不需要加锁，因为的那个亲啊容易不会添加任何元素，
     *          所以copyOnWrite容器也是一种读写分离的思想，读和写不同的容器。
     *
     *      -
     *
     */
}
