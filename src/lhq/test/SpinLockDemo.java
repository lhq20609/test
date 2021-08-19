package lhq.test;

import java.util.concurrent.atomic.AtomicReference;
public class SpinLockDemo {
    AtomicReference<Thread> atomicReference=new AtomicReference<>();
    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName()+"进来了");
        //如果atomicReference里面的线程为null，则将thread替换掉，且不进入循环
        //如果atomicReference里面的线程不为null，则循环等待到atomicReference里面的线程为null时退出循环
        while(!atomicReference.compareAndSet(null,thread)){
        }
    }
    public void myUnLock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName()+"出去了");
        //如果atomicReference里面的线程跟为thread，则将atomicReference里面的线程变为null，且不进入循环
        //如果atomicReference里面的线程不是当前线程，
        //则循环等待到atomicReference里面的线程为当前线程时将atomicReference里面的线程变为null，并退出循环
        while(!atomicReference.compareAndSet(thread,null)){
        }
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(()->{
            spinLockDemo.myLock();
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();
        },"AA").start();
        new Thread(()->{
            spinLockDemo.myLock();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();
        },"BB").start();
    }
}
