package org.zipcracker;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CrackZipFilePassword {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(200);
        Producer producer = new Producer(blockingQueue);
        Thread[] threads = new Thread[50];
        Arrays.setAll(threads, thread -> new Thread(new Consumer(blockingQueue)));
        Thread pThread = new Thread(producer);
        pThread.start();
        Arrays.stream(threads).forEach(t->t.start());
    }
}
