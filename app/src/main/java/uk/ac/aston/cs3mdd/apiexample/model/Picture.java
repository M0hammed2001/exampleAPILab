package uk.ac.aston.cs3mdd.apiexample.model;

import java.io.Serializable;

public class Picture  implements Serializable {
    private String large;
    private String medium;
    private String thumbnail;

    public String getMedium() {
        return medium;
    }
}
