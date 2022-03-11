package _20220222_Runnable;

public class _04_Interrupts {
    public static void main(String[] args) {
        Runnable runner = () -> {
            while(!Thread.currentThread().isInterrupted())
            {
                System.out.println("I am running: "+Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted");
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread t1 = new Thread(runner, "Thread 1");
        t1.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t1.interrupt();
    }
}
