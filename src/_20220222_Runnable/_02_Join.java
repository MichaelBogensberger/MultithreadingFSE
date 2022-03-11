package _20220222_Runnable;

public class _02_Join {
    public static void main(String[] args) {
        String threadName = Thread.currentThread().getName();
        System.out.println("I am: "+threadName);
        Thread t1 = new Thread(new Job2(10000), "Thread 1");
        Thread t2 = new Thread(new Job2(10000), "Thread 2");
        // run() würde zwar das Runnable ausführen, aber ohne einen neuen Thread zu starten
        //t1.run();
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
            DataStorage.counter++;
        }        
    }
    
}

class DataStorage {
    static int counter=0;
}