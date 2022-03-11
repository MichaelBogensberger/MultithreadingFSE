package _20220222_Runnable;

public class _03_Sleep {
    public static void main(String[] args) {
        String threadName = Thread.currentThread().getName();
        System.out.println("I am: "+threadName);
        Thread t1 = new Thread(new Job3(50), "Thread 1");
        Thread t2 = new Thread(new Job3(50), "Thread 2");
        // run() würde zwar das Runnable ausführen, aber ohne einen neuen Thread zu starten
        //t1.run();
        t1.start();
        t2.start();
    }
}

class Job3 implements Runnable {

    int runs;

    public Job3(int runs){
        this.runs = runs;
    }

    @Override
    public void run() {
        for (int i = 0; i < runs; i++) {
            String threadName = Thread.currentThread().getName();
            System.out.println("I am: "+threadName);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }        
    }
    
}
