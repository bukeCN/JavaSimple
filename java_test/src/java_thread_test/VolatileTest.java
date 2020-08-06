package java_thread_test;

/**
 * volatile 关键字测试，测试其两个特性
 * 1. 保证线程之间的可见性
 * 2. 禁止指令重排序
 * 具体操作，启用一定数量的线程对一变量进行特定次数的累加，查看最后得出的结果能否符合所有线程累加操作次数的总和
 * 如：20 个线程，进行 10000 次累加，最终结果应该为 200000，但是由于 java 的复制操作并不是原子性的，因此
 * 最终的结果总是少于 200000
 */
public class VolatileTest {
    private volatile static int count = 0;

    private static final int THREAD_COUNT = 20;

    private static void accumulation(){
        count++;
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        accumulation();
                    }
                }
            });
            threads[i].start();
        }
        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println("结果:"+count);
    }
}
