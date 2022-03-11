package _20220222_Runnable;

public class _01_Runnable {
    public static void main(String[] args) {
        String threadName = Thread.currentThread().getName();
        System.out.println("I am: "+threadName);
        Thread t1 = new Thread(new Job(50), "Thread 1");
        Thread t2 = new Thread(new Job(50), "Thread 2");
        // run() würde zwar das Runnable ausführen, aber ohne einen neuen Thread zu starten
        //t1.run();
        t1.start();
        t2.start();
    }
}

class Job implements Runnable {

    int runs;

    public Job(int runs){
        this.runs = runs;
    }

    @Override
    public void run() {
        for (int i = 0; i < runs; i++) {
            String threadName = Thread.currentThread().getName();
            System.out.println("I am: "+threadName);
        }        
    }
    
}
