package _20220419_Executor_Generics;

public class _01_interrupt {

    public static void main(String[] args) {
        Runnable runner = () -> {
 
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Es laufen gerade Threads: " + Thread.activeCount());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Interrupted "+Thread.interrupted());
 
        };
 
        Thread t1 = new Thread(runner);
        t1.start();
 
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
 
        t1.interrupt();
    }
}
