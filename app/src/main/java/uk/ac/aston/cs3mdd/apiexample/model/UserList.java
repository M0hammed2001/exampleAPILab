package uk.ac.aston.cs3mdd.apiexample.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class UserList implements Serializable {
    private ArrayList<User> results;


    public ArrayList<User> getResults() {
        return results;
    }
}
