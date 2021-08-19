package lhq.test;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i=1;i<=6;i++){
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t抢到了车位");
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println(Thread.currentThread().getName()+"\t车停车三秒后离开了车位");
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
