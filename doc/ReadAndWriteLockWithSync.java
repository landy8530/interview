package com;

/**
 * @author landyl
 * @create 14:31 11/03/2019
 */
public class ReadAndWriteLockWithSync {

    public synchronized void get(Thread thread) {
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
    }

    public static void main(String[] args) {
        final ReadAndWriteLockWithSync lock = new ReadAndWriteLockWithSync();
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
