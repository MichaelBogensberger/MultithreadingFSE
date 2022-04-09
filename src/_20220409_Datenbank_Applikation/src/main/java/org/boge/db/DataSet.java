package org.boge.db;

public class DataSet {
    private int primaryKey;
    private String data;

    public DataSet(String data) {
        this.data = data;
    }

    public DataSet(int primaryKey, String data) {
        this.primaryKey = primaryKey;
        this.data = data;
    }



    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


}
