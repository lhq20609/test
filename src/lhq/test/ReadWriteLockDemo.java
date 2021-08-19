package lhq.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 */
class Cache{
    private volatile Map<String,String> map=new HashMap();
    private ReentrantReadWriteLock readWriteLockock =  new ReentrantReadWriteLock();
    public void put(String key,String value){
        readWriteLockock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t正在加入");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName()+"\t加入完成");
        }catch (Exception e){
        } finally{
            readWriteLockock.writeLock().unlock();
        }

    }

    public void get(String key){
        readWriteLockock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t正在获取");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t获取完成");
        }catch (Exception e){}
        finally {
            readWriteLockock.readLock().unlock();
        }

    }
}
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        Cache cache = new Cache();
        for (int i = 0; i < 5; i++) {
            final int temp=i;
            new Thread(()->{
                cache.put(String.valueOf(temp),String.valueOf(temp));
            },String.valueOf(temp)).start();
        }
        for (int i = 0; i < 5; i++) {
            final int temp=i;
            new Thread(()->{
                cache.get(String.valueOf(temp));
            },String.valueOf(temp)).start();
        }
    }
}
