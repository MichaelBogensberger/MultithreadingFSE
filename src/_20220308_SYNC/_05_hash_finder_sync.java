package _20220308_SYNC;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static _20220308_SYNC.HashFinder.calculateHash;


public class _05_hash_finder_sync {
    public static void main(String[] args) {

        String data = "Transaktion472";
        String difficulty = "00000";

        ArrayList<Thread> threads = new ArrayList<>();
        Lock lock = new ReentrantLock();

        int thread_count = 5;

        for(int i = 0; i < thread_count; i++){
            threads.add(new Thread(new Job5(data,difficulty,lock, thread_count, i), "Thread-"+String.valueOf(i)));
        }

        for (Thread thread : threads) {
            thread.start();

        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}



class Job5 implements Runnable {

    String data;
    String difficulty;
    Lock lock;


    boolean isFound = false;
    long nonce;
    int threads;




    public Job5(String data,String difficulty, Lock lock, int threads, int nonce){
        this.data= data;
        this.difficulty = difficulty;
        this.lock = lock;
        this.nonce = nonce;
        this.threads = threads;
    }

    @Override
    public void run() {



        while(!isFound && nonce < Long.MAX_VALUE) {




            //String strToHash = data + nonce;
            String strToHash = data + nonce;
            String hash = calculateHash(strToHash);
            //System.out.println(hash + " | " + Thread.currentThread().getName());

            // try {
            // 	Thread.sleep(1000);
            // } catch (InterruptedException e) {
            // 	e.printStackTrace();
            // }
            if(hash.startsWith(difficulty)){
                //isFound = true;
                System.out.println("found" + " | " + Thread.currentThread().getName() + " | " + hash + " | " +nonce );
                // System.out.println("found" + " | " + Thread.currentThread().getName() + " | " + hash + " | " +nonce );
            }


            nonce = nonce + threads;


        }


    }



    public static String calculateHash(String strToHash) {

        byte[] bytesOfMessage;
        try {
            bytesOfMessage = strToHash.getBytes("UTF-8");
            MessageDigest md;
            md = MessageDigest.getInstance("SHA-256");

            byte[] thedigest = md.digest(bytesOfMessage);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < thedigest.length; i++) {
                String hex = Integer.toHexString(0xff & thedigest[i]);
                if(hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }






}