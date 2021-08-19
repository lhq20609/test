package lhq.test;

class HoldLockThread implements Runnable{
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t得到了"+lockA+"\t尝试获取"+lockB);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t得到了"+lockB+"\t尝试获取"+lockA);
            }
        }
    }
}
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA="LockA";
        String lockB="LockB";
        new Thread(new HoldLockThread(lockA,lockB),"ThreadA").start();
        new Thread(new HoldLockThread(lockB,lockA),"ThreadB").start();
    }
}
