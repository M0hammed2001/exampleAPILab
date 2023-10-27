package uk.ac.aston.cs3mdd.apiexample.model;

import java.io.Serializable;
import java.util.jar.Attributes;



public class User implements Serializable {
    // The "easy" fields.  These are all just text

    private String gender;
    private String email;
    private String phone;
    private String cell;
    private String nat;
    private Name name;

    private Picture picture;

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    @Override

    public  String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getname().getTitle());
        sb.append(" ");
        sb.append(getname().getFirst());
        sb.append(" ");
        sb.append(getname().getLast());
        return sb.toString();
    }

    private uk.ac.aston.cs3mdd.apiexample.model.Name getname() {
        return name;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }


    public Name getName() {
        return name;
    }
}
