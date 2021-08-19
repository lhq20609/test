package lhq.test;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import  java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

public class VolatileDemo {
    volatile int number=0;
    //具有原子性的Integer
    AtomicInteger atomicInteger= new AtomicInteger();
    public void addPlusPlus(){
        number = number+1;
        atomicInteger.getAndAdd(1);
    }

    public static void main(String[] args) {
        int a,b,x=0,y=0;
    }

    private static void test() {

        VolatileDemo volatileDemo = new VolatileDemo();
        for(int i=1;i<=100;i++){
            new Thread(() -> {
                for (int j=1;j<=1000;j++) {
                    volatileDemo.addPlusPlus();
                }
            },String.valueOf(i)).start();
        }

        while(Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(volatileDemo.number);
        System.out.println(volatileDemo.atomicInteger);
    }
}
