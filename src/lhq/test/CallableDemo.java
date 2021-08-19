package lhq.test;

import java.util.concurrent.*;
class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("********** ");
        return 1024;
    }
}
public class CallableDemo{
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }
}
