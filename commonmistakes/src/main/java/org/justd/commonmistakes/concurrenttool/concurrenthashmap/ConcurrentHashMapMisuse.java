package org.justd.commonmistakes.concurrenttool.concurrenthashmap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * @author Zhangjd
 * @title: ConcurrentHashMapMisuse
 * @description: 有一个含 900 个元素的 Map，现在再补充 100 个元素进去，这个补充操作由 10 个线程并发进行。
 * @date 2020/3/291:35
 */
@RestController
@RequestMapping("concurrent")
@Slf4j
public class ConcurrentHashMapMisuse {
    //线程个数
    private static int THREAD_COUNT = 10;
    //总元素数量
    private static int TIME_COUNT = 1000;

    //获取指定数量元素的concurrentHashMap
    private ConcurrentHashMap<String, Long> getData(int count) {
        return LongStream.rangeClosed(1, count) //获取一个longStream, 从1 到 count的有序流，包括1和count
                .boxed() //基本数据类型不支持collect操作，因此需要将基础数据类型的元素装箱为包装类型
                .collect(Collectors.toConcurrentMap(i -> UUID.randomUUID().toString(), Function.identity(),//Function.identity()为获取对象本身，此例中为Long
                        (o1, o2) -> o1, ConcurrentHashMap::new)); // 当o1,o2相同，即key相同，保留第一个元素，
    }

    @GetMapping("wrong")
    public String wrong() throws InterruptedException {
        //初始900个元素
        ConcurrentHashMap<String, Long> data = getData(TIME_COUNT - 100);
        log.info("init size:{}", data.size());
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, 10).parallel().forEach(i -> {
            int gap = TIME_COUNT - data.size();
            log.info("gap size:{}", gap);
            data.putAll(getData(gap));
        }));

        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        log.info("finish size:{}", data.size());
        return "over";
    }

    @GetMapping("right")
    public String wirht() throws InterruptedException {
        //初始900个元素
        ConcurrentHashMap<String, Long> data = getData(TIME_COUNT - 100);
        log.info("init size:{}", data.size());
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, 10).parallel().forEach(i -> {
            synchronized (data) {
                int gap = TIME_COUNT - data.size();
                log.info("gap size:{}", gap);
                data.putAll(getData(gap));
            }
        }));

        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        log.info("finish size:{}", data.size());
        return "over";
    }
}
