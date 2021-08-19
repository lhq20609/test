package lhq.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class BlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.add("a");
        blockingQueue.add("b");
        blockingQueue.add("c");
        blockingQueue.add("d");
        BlockingQueue<String> stringBlockingQueue = new  SynchronousQueue();
    }
}
