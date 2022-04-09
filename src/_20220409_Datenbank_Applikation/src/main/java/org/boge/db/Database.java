package org.boge.db;


import java.util.LinkedList;
import java.util.List;

public class Database {

    private static Database instance;

    public Database() {
    }

    public static Database getInstance() {
        if(instance == null) {
            instance = new Database();
        }

        return instance;
    }


    LinkedList<DataSet> dataSets = new LinkedList<>();

    public void insert(String data) {

        if(dataSets.size() == 0) {
            dataSets.add(new DataSet(1, data));
        } else {
           int newKey =  dataSets.getLast().getPrimaryKey() +1;
            dataSets.add(new DataSet(newKey,data));
        }
    }


    /*
    public void insert(int primaryKey, String data) {

        if(this.get(primaryKey) != null) {
            throw new IllegalArgumentException("ID existierts bereits");
        } else {
            dataSets.add(new DataSet(primaryKey,data));
        }
    } */


    public DataSet get(int primaryKey) {

        for (int i = 0; i < dataSets.size(); i++) {
            if (dataSets.get(i).getPrimaryKey() == primaryKey) {
                return dataSets.get(i);
            }
        }

        return null;
    }



    public List<DataSet> getAll() {
            List<DataSet> dsList = new LinkedList<>();

            for (int i = 0; i < dataSets.size(); i++) {
                dsList.add(dataSets.get(i));
            }

            return dsList;
    }






}
