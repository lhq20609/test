package lhq.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock的精确唤醒
 * 先让A线程执行5次，然后让B线程执行10次，再让C线程执行15次
 */
public class SyncAndLockDemo {

    public static void main(String[] args) {
        SyncAndLockDemo syncAndLockDemo = new SyncAndLockDemo();
        new Thread(()->{
            for (int i = 1; i < 5; i++) {
                syncAndLockDemo.print5();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 1; i < 5; i++) {
                syncAndLockDemo.print10();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 1; i < 5; i++) {
                syncAndLockDemo.print15();
            }
        },"C").start();
    }
    //1默认为A线程，2为B线程，3为C线程
    private int number = 1;
    private Lock lock = new ReentrantLock();
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    Condition conditionC = lock.newCondition();

    public  void print5(){
        try {
            lock.lock();
            while(number != 1){
                conditionA.await();
            }
            for (int i=1;i<=5;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            number=2;
            conditionB.signal();
        }catch (Exception e){}
        finally {
            lock.unlock();
        }
    }

    public  void print10(){
        try {
            lock.lock();
            while(number != 2){
                conditionB.await();
            }
            for (int i=1;i<=10;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            number=3;
            conditionC.signal();
        }catch (Exception e){}
        finally {
            lock.unlock();
        }
    }

    public  void print15(){
        try {
            lock.lock();
            while(number != 3){
                conditionC.await();
            }
            for (int i=1;i<=15;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            number=1;
            conditionA.signal();
        }catch (Exception e){}
        finally {
            lock.unlock();
        }
    }

}
