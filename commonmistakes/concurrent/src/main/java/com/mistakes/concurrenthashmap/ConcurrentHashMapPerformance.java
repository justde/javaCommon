package com.mistakes.concurrenthashmap;

import org.springframework.objenesis.SpringObjenesis;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Zhangjd
 * @title: ConcurrentHashMapPerformance
 * @description: Map 来统计 Key 出现次数
 * @date 2020/3/2910:47
 */
@RestController
@RequestMapping("concurrent/performance")
public class ConcurrentHashMapPerformance {
    private static int LOOP_COUNT = 10 * 1000 * 1000;
    private static int THREAD_COUNT = 10;
    private static int TIME_COUNT = 10;

    @GetMapping("count")
    private Map<String, Long> normalUse() throws InterruptedException {
        ConcurrentHashMap<String, Long> freqs = new ConcurrentHashMap<>(TIME_COUNT);
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(() -> {
            IntStream.rangeClosed(1, LOOP_COUNT).parallel().forEach(i -> {
                String key = "key" + ThreadLocalRandom.current().nextInt(TIME_COUNT);
                //synchronized (freqs){
                if (freqs.containsKey(key)) {
                    freqs.put(key, freqs.get(key) + 1);
                } else {
                    freqs.put(key, 1L);
                }
                //}
            });
        });
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        return freqs;

    }

    @GetMapping("performance")
    private Map<String, Long> goodUse() throws InterruptedException {
        ConcurrentHashMap<String, LongAdder> map = new ConcurrentHashMap<>(THREAD_COUNT);
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(()->IntStream.rangeClosed(1, LOOP_COUNT).parallel().forEach(i ->{
            String key = "key" + ThreadLocalRandom.current().nextInt(TIME_COUNT);
            //如果key不存在则执行 k->new LongAdder()，然后在执行increment；
            //如果key存在，直接执行后面的increment；
            map.computeIfAbsent(key, k -> new LongAdder()).increment();

        }));
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        return map.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e ->e.getValue().longValue()
                ));
    }

}
