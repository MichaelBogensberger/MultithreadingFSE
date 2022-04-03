package _20220322_wait_notify;



import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class _03_blocking_queue {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Strating!");

        Lock lock = new ReentrantLock();

        DataShareQueue ds = new DataShareQueue();


        Runnable sender = () -> {
            for(int i = 0; i < 1000; i++){
                String str = "Message: "+i;
                ds.setDataString(str);
            }

        };





        Runnable receiver = () -> {

            for(int i = 0; i < 1000; i++){
                ds.getDataString();
            }

        };



        new Thread(sender).start();

        new Thread(receiver).start();
        new Thread(receiver).start();
        new Thread(receiver).start();



    }


}


class DataShareQueue {

    private String dataString;
    boolean isSet = false;
    ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(2);


    public String getDataString(){
        String returnVal = null;

        while(true){
            sleep(1000);

            try {
                returnVal = blockingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            ausgabe(returnVal, true);


            return returnVal;
        }


    }

    synchronized void ausgabe(String value, boolean receiving) {
        // true = recieving
        // false = sending
        //String header = receiving ? "Recieving" : "Sending";

        if (receiving == true) {

            //System.out.println("\t\t\t\t---------- Recieving ----------");
            System.out.println("\t\t\t\t\t\t\t\t\t\t" + Thread.currentThread().getName() + " recieving: "+value);
            //System.out.println("\t\t\t\t-------------------------------");


        } else if (receiving == false) {

            //System.out.println("---------- Sending ----------");
            System.out.println("Sending: "+value);
            //System.out.println("-----------------------------");

        }


    }



    public void setDataString(String str){


            //System.out.println("Sending --> "+str);

            ausgabe(str, false);

            try {
                blockingQueue.put(str);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


    }




    private void sleep(long timeMillis) {
        try {
            Thread.sleep(timeMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



}


