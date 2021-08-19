package lhq.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class ShareData {
    private volatile boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;
    public ShareData(BlockingQueue<String> blockingQueue){
        this.blockingQueue=blockingQueue;
    }
    public void myProduce() throws Exception {
        String data=null;
        boolean offer;
        while (flag){
            data = atomicInteger.incrementAndGet()+"";
            offer = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
            if(offer){
                System.out.println(Thread.currentThread().getName()+"\t 插入队列成功"+data);
            }else{
                System.out.println(Thread.currentThread().getName()+"\t 插入队列失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"生产线程停止");
    }
    public void myConsumer() throws Exception {
        String poll=null;
        boolean offer;
        while (flag){
            poll = blockingQueue.poll(2, TimeUnit.SECONDS);
            if(poll == null || "".equals(poll)){
                flag = false;
                System.out.println(Thread.currentThread().getName()+"\t 超过2s获取不到队列，消费退出\n");
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t 获取队列成功"+poll);

        }
    }
}
public class BlockQueueDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData(new ArrayBlockingQueue<String>(10));
        new Thread(()->{
            System.out.println("生产者线程1启动");
            try {
                shareData.myProduce();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"生产者1").start();
        new Thread(()->{
            System.out.println("生产者线程2启动");
            try {
                shareData.myProduce();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"生产者2").start();
        new Thread(()->{
            System.out.println("消费者线程1启动");
            try {
                shareData.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"消费者1").start();
        new Thread(()->{
            System.out.println("消费者线程1启动");
            try {
                shareData.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"消费者2").start();
    }
}
