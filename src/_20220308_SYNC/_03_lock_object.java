package _20220308_SYNC;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class _03_lock_object {
    public static void main(String[] args) {
        String threadName = Thread.currentThread().getName();
        System.out.println("I am: "+threadName);
        Lock lock = new ReentrantLock();
        Thread t1 = new Thread(new Job4(10000,lock), "Thread 1");
        Thread t2 = new Thread(new Job4(10000,lock), "Thread 2");
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }        
        System.out.println("Counter hochgezählt bis: "+DataStorage.counter);
    }
}

class Job4 implements Runnable {

    int runs;
    Lock lock;

    public Job4(int runs, Lock lock){
        this.runs = runs;
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 0; i < runs; i++) {
            lock.lock();
                DataStorage.counter++;
            lock.unlock();
        }  
    }    
    
}
