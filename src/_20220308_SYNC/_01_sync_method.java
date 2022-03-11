package _20220308_SYNC;

public class _01_sync_method {
    public static void main(String[] args) {
        String threadName = Thread.currentThread().getName();
        System.out.println("I am: "+threadName);
        // synchronized muss sich unbedingt im selben Objekt befinden
        Runnable job = new Job2(10000);
        Thread t1 = new Thread(job, "Thread 1");
        Thread t2 = new Thread(job, "Thread 2");
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

class Job2 implements Runnable {

    int runs;

    public Job2(int runs){
        this.runs = runs;
    }

    @Override
    public void run() {
        for (int i = 0; i < runs; i++) {
            job();
        }  
    }

    // So wenig Code wie möglich synchronisieren    
    synchronized public void job(){        
            DataStorage.counter++;       
    }
    
}

class DataStorage {
    static int counter=0;
}