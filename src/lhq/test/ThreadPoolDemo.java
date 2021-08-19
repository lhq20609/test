package lhq.test;

import java.util.concurrent.*;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        //自定定义线程池
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1,
                 TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        try {
            for (int i = 0; i < 10; i++) {
                int temp = i+1;
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+" \t"+temp);
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            threadPool.shutdown();
        }

    }

    private static void ThreadPoolInit() {
        //一个线程池里有五个线程
        // ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //一个线程池里有一个线程
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //一个线程池里有个N个线程，可能为一个，也可能为多个
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 10; i++) {
                int temp = i+1;
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+" \t"+temp);
                });
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }catch (Exception e){}
        finally {
            threadPool.shutdown();
        }
    }
}
