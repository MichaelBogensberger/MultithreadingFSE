package _20220308_SYNC;

public class _02_sync_block {
    public static void main(String[] args) {
        String threadName = Thread.currentThread().getName();
        System.out.println("I am: "+threadName);
        Object syncObject = new Object();
        Thread t1 = new Thread(new Job3(10000,syncObject), "Thread 1");
        Thread t2 = new Thread(new Job3(10000,syncObject), "Thread 2");
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

class Job3 implements Runnable {

    int runs;
    Object syncObject;

    public Job3(int runs, Object syncObject){
        this.runs = runs;
        this.syncObject = syncObject;
    }

    @Override
    public void run() {
        for (int i = 0; i < runs; i++) {
            synchronized(syncObject){
                DataStorage.counter++;
            }
        }  
    }    
    
}

