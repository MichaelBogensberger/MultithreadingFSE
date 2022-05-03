package _20220419_Executor_Generics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class _02_Executor {

    public static void main(String[] args) {
        
        System.out.println("Es laufen gerade Threads: " + Thread.activeCount());

        // ExecutorService exec = Executors.newCachedThreadPool();

        ExecutorService exec = Executors.newFixedThreadPool(5);

        System.out.println("Es laufen gerade Threads: " + Thread.activeCount());

        

        int counter = 0;

        while(true) {

            if(counter < 10){
                Job run = new Job(10, "Runnable " + counter);
                System.out.println("Adding Job " + counter);
                exec.execute(run);
            }

            System.out.println("Es laufen gerade Threads: " + Thread.activeCount());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            counter ++;
            
        }


    }
    
}

class Job implements Runnable {

    int runs;
    String name;

    public Job (int runs, String name){
        this.runs = runs;
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < runs; i++) {
            try {
                System.out.println(name + " running in " + Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(name + " done! - " + Thread.currentThread().getName());
        
    }

}
