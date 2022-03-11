package _20220308_SYNC;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static _20220308_SYNC.HashFinder.calculateHash;


public class _05_hash_finder_sync {
    public static void main(String[] args) {

        String data = "Transaktion472";
        String difficulty = "000";

        Lock lock = new ReentrantLock();
        Thread t1 = new Thread(new Job5(data,difficulty,lock), "1");
        Thread t2 = new Thread(new Job5(data,difficulty,lock), "2");
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}


class Job5 implements Runnable {

    String data;
    String difficulty;
    Lock lock;
    DataStorage dataStorage = new DataStorage();

    boolean isFound = false;
    long nonce = 0;
    //long nonce = dataStorage.getNonce();



    public Job5(String data,String difficulty, Lock lock){
        this.data= data;
        this.difficulty = difficulty;
        this.lock = lock;
    }

    @Override
    public void run() {



        while(!isFound && dataStorage.getNonce() < Long.MAX_VALUE) {
            if (dataStorage.isWorkingOn() == true) {
                dataStorage.setNonce(dataStorage.getNonce()+1);
            }


            dataStorage.setWorkingOn(true);

            //String strToHash = data + nonce;
            String strToHash = data + dataStorage.getNonce();
            String hash = calculateHash(strToHash);
            //System.out.println(hash + " | " + Thread.currentThread().getName());

            if(hash.startsWith(difficulty)){
                //isFound = true;
                System.out.println("found" + " | " + Thread.currentThread().getName() + " | " + hash + " | " +dataStorage.getNonce() + " | " +dataStorage.isWorkingOn() );
            }

            dataStorage.setNonce(dataStorage.getNonce()+1);
            //nonce ++;
            dataStorage.setWorkingOn(false);
        }


    }



class DataStorage {
    private long nonce = 0;
    private boolean workingOn = false;

    public long getNonce() {
        return nonce;
    }
    public void setNonce(long nonce) {
        this.nonce = nonce;
    }

    public boolean isWorkingOn() {
        return workingOn;
    }

    public void setWorkingOn(boolean workingOn) {
        this.workingOn = workingOn;
    }

}







}