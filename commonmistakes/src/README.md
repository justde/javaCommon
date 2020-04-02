## 并发工具类库中 容易出现的问题

- 线程重用导致ThreadLocal脏数据：[threadLocal](src/main/java/com/mistakes/threadlocal)
- concurrentHashMap的非线程安全使用：[concurrentHashMapMisuse](src/main/java/com/mistakes/concurrenthashmap)
- 没有充分利用并发工具的特性，无法发挥优势：[concurrentHashMapPerformance](src/main/java/com/mistakes/concurrenthashmap)
    - `computeIfAbsent`为原子性方法，判断key是否存在value，如果不存在则吧lambda运行的结果放入map作为value
    - 由于 `computeIfAbsent`返回一个value是线程安全累加器，可以直接调用increment方法累加
- 没有认清工具使用场景，导致性能问题：[copyOnWriteListMisuse](src/main/java/com/mistakes/coptonwritearraylist)
    - 结论:  写操作：CopyOnWriteArray比SynchronizedList慢100倍
            读操作：copyOnWriteArray比SynchronizedList快5倍
    - 原因:  每次add操作，Arrays.copyOf创建一个新数组，内存消耗很大


