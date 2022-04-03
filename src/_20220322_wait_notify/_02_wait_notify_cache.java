package _20220322_wait_notify;

import java.util.Arrays;

public class _02_wait_notify_cache {

    public static void main(String[] args) {
        System.out.println("Strating!");

        DataShareCache ds = new DataShareCache();

        /*
        Cache<String> cache = new Cache<>(3);
        cache.add("Hallo");
        cache.add("Welt");
        cache.add("Michi");
        System.out.println(cache.toString());
        System.out.println(cache.take());
        System.out.println(cache.toString());
        */



        Runnable sender = () -> {
            for(int i = 0; i < 50; i++){
                String str = "Message: "+i;
                ds.setDataString(str);
            }
        };

        Runnable receiver = () -> {
            for(int i = 0; i < 50; i++){
                ds.getDataString();
            }
        };

        new Thread(sender).start();
        new Thread(receiver).start();
    }


}


class DataShareCache{

    private String dataString;
    private boolean isSet = false;

    Cache<String> cache = new Cache<>(30);



   synchronized public String getDataString() {

        //System.out.println(Thread.currentThread().getState());
        //System.out.println(cache.isEmpty());


       while (true) {

           //sleep(1500);

           while (cache.size() == 0) {
               try {
                   wait();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }

           String val = cache.take();
           System.out.println("\t\t\t\t\tReceiving: " + val);
           notify();
           return val;


       }







    }

    synchronized public void setDataString(String str) {

        while (cache.isFull()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        System.out.println("Sending: " +str);
        cache.add(str);
        notify();

        //isSet = true;
        //notify();

    }


    private void sleep(long timeMillis) {
        try {
            Thread.sleep(timeMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}


class Cache<T> {
    private T [] values;
    private int capacity;
    private int size = 0;

    public Cache(int capacity) {
        this.capacity = capacity;
        this.values = (T[]) new Object[capacity];
    }

   public void add (T element) {
        values[size] = element;
        size++;

        if (size == capacity) {
            //System.out.println(toString());
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t Cache VOLL");
        }

    }

    public int size() {
        return size;
    }

    public boolean isFull() {
        if (size == capacity) {
            return true;
        } else {
            return false;
        }

    }

    public T get(int index) {
        return values[index];
    }

    public T take() {

        if (size > 0) {
            T head = values[0];
            values[0] = null;

            // [x][x][x]
            // [null][x][x]
            //

            for (int i = 1; i < size; i++) {
                //System.out.println(i);
                values[i-1] = values[i];
            }

            size--;
            values[size] = null;

            return head;

        }

        throw new IllegalArgumentException("Liste ist leer");

    }

    @Override
    public String toString() {
        return "Cache{" +
                "values=" + Arrays.toString(values) +
                ", capacity=" + capacity +
                ", size=" + size +
                '}';
    }


}