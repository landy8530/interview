package com;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author landyl
 * @create 14:34 11/03/2019
 */
public class ReadAndWriteLockWithLock {

    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public void get(Thread thread) {
        lock.readLock().lock();
        try{
            long startTime = System.currentTimeMillis();
            System.out.println("start time:"+startTime);
            for(int i=0; i<5; i++){
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(thread.getName() + ":正在进行读操作……");
            }
            System.out.println(thread.getName() + ":读操作完毕！");
            long endTime = System.currentTimeMillis();
            System.out.println("end time:"+endTime + "=== total: " + (endTime - startTime));
        }finally{
            lock.readLock().unlock();
        }
    }

    public static void main(String[] args) {
        final ReadAndWriteLockWithLock lock = new ReadAndWriteLockWithLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.get(Thread.currentThread());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.get(Thread.currentThread());
            }
        }).start();
    }

}
