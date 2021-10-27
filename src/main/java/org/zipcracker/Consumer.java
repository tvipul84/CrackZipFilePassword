package org.zipcracker;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{
    private BlockingQueue<String> blockingQueue;
    private String taken = "";

    public Consumer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while(true){
            try {
                taken = blockingQueue.take();
                ZipFile zipFile = new ZipFile("C:\\aaa\\backup\\aaa.zip");
                zipFile.setPassword(taken.toCharArray());
                boolean exc = false;
                try {
                    zipFile.extractAll("c:\\aaa\\backup\\extract");
                } catch(ZipException e) {
                    exc = true;
                }
                if(!exc){
                    System.out.println("Password is "+taken);
                } else {
                    System.out.println(taken);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
