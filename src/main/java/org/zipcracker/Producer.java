package org.zipcracker;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{
    private String passwd="aaa";
    private BlockingQueue<String> blockingQueue;
    public Producer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public Producer(BlockingQueue<String> blockingQueue, String password) {
        this.blockingQueue = blockingQueue;
        this.passwd = password;
    }

    @Override
    public void run() {
        while(true){
            if(blockingQueue.size()<=100){
                try {
                    passwd = incrementString(passwd);
                    String pw = processSpecialChar(passwd);
                    blockingQueue.put(pw);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private String processSpecialChar(String passwd) {
        char[] speChars = new char[]{'\"'};
        for(char c:speChars){
            passwd = passwd.replaceAll(c+ "", "\\"+c);
        }
        return passwd;
    }

    private String incrementString(String initialStr) {
        byte[] bytes = initialStr.getBytes();
        int index = getIndex(bytes);
        if(index==bytes.length){
            bytes = initiateByte(bytes.length+1);
        } else {
            bytes[index]+=1;
            for(int i=index+1;i<bytes.length;i++){
                bytes[i]=33;
            }
        }
        return new String(bytes);
    }

    private byte[] initiateByte(int length) {
        byte[] newByte = new byte[length];
        for(int i=0;i<length;i++){
            newByte[i]=33;
        }
        return newByte;
    }

    private int getIndex(byte[] bytes) {
        for(int i=bytes.length-1;i>=0;i--){
            if(bytes[i]<126){
                return i;
            }
        }
        return bytes.length;
    }
}
