package org.boge;

import org.boge.db.DataSet;
import org.boge.db.Database;

import javax.xml.crypto.Data;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Database db = Database.getInstance();
        Lock lock = new ReentrantLock();

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 50; i++) {
            executorService.execute(new Job(1,lock));
        }



        executorService.shutdown();


        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
        }

        System.out.println("FINISH ----------------------");
        System.out.println("Size: " + db.getAll().size());

        List<DataSet> dsList = db.getAll();

        for(int i=0;i<dsList.size();i++){
            System.out.println("ID: " + dsList.get(i).getPrimaryKey() + "  ->  " + dsList.get(i).getData());

        }




        /*
        db.insert("first");
        db.insert("sec");
        db.insert("third");
        db.insert(4,"fourth");

        for (int i = 1; i < 5; i++) {
            System.out.println("Data: " +db.get(i).getData() + " -> " + db.get(i).getPrimaryKey());
        } */



    }
}



class Job implements Runnable {
    Database db = Database.getInstance();
    Lock lock;
    int runs;

    public Job(int runs, Lock lock){
        this.runs = runs;
        this.lock = lock;
    }


    @Override
    public void run() {
        for (int i=0; i<runs; i++) {
            String message = "Message " + Thread.currentThread().getName();
            lock.lock();
            db.insert(message);
            lock.unlock();
            System.out.println("Inserted: " + message);
        }

    }

}
