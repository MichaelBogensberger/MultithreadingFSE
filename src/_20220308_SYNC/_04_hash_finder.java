package _20220308_SYNC;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class _04_hash_finder {
    public static void main(String[] args) {
        HashFinder.findHash("Transaktionen2", "0000");
   }
}

class HashFinder {

    public static void findHash(String transactionData, String difficulty){

        boolean isFound = false;
        // normal Long.MIN_VALUE
        long nonce = 0;

        while(!isFound && nonce < Long.MAX_VALUE){
            String strToHash = transactionData+nonce;
            String hash = calculateHash(strToHash);
            //System.out.printf("%d - %s - %s\n",nonce,Thread.currentThread().getName(),hash);
            
            if(hash.startsWith(difficulty)){
                isFound = true;
                System.out.printf("%d - %s - %s\n",nonce,Thread.currentThread().getName(),hash);
            }
                
            nonce ++;
        }

        System.out.println(calculateHash(transactionData));

    }

    /**
     * Calculates sha256 to a String
     * 
     * @param strToHash
     * @return
     */
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



