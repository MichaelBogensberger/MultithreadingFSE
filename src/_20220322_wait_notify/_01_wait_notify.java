package _20220322_wait_notify;

public class _01_wait_notify {
    public static void main(String[] args) {
        System.out.println("Strating!");

        DataShare ds = new DataShare();

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
    }
}


class DataShare {

    private String dataString;
    boolean isSet = false;

    synchronized public String getDataString(){
        String returnVal = null;

        while(!isSet){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        returnVal = dataString;

        System.out.println("Message: "+returnVal);

        isSet = false;
        notify();

        return returnVal;
    }

    synchronized public void setDataString(String str){

        while(isSet){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Setting --> "+str);
        this.dataString = str;
        isSet = true;
        notify();

    }


}