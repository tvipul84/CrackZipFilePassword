package org.zipcracker;

import java.util.Arrays;
import java.util.concurrent.*;

public class ExecutorServiceCrackZipPassword {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(200);

        ExecutorService service = Executors.newFixedThreadPool(200);
        Runnable[] runnables = new Runnable[200];
        //service.invokeAll(Arrays.);
        //service.invokeAll();
        Producer producer = new Producer(blockingQueue,"AAA");
        new Thread(producer).start();
        Arrays.setAll(runnables, runnable->new Consumer(blockingQueue));
        Arrays.stream(runnables).forEach(Runnable::run);

    }
}
