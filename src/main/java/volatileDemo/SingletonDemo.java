package volatileDemo;

/**
 * @author: zhangjd
 * @Date: 2019/6/5 22:17
 * @Description:
 */
public class SingletonDemo {
    private static SingletonDemo instance = null;
    public SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"*******   我是构造方法");
    }
    //DCL double Check Lock 双端检索机制
    public static SingletonDemo getInstance(){
        if (instance == null){
            synchronized (SingletonDemo.class){
                if (instance == null){
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    /**
     * instance = new SingletonDemo;
     *
     * memory = allocate()  1.分配对象内存空间
     * instance(memory) 2. 初始化对象
     * instance = memory 3 设置instance指向刚分配的内存地址，此时instance != null
     *
     *  步骤2/3不存在数据依赖关系 因此在单线程中可以是互换顺序的
     *
     */

    public static void main(String[] args) {
        //单线程没问题
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                SingletonDemo.getInstance();
            }).start();
        }

    }
}
