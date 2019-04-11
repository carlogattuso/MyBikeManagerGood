package edu.upc.dsa.models;

import java.util.LinkedList;

public class User {
    private String idUser;
    private String name;
    private String surname;
    private LinkedList<Bike> bikes = new LinkedList<>();

    public User() {
    }

    public User(String idUser, String name, String surname) {
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LinkedList<Bike> getBikes() {
        return bikes;
    }

    public void setBikes(LinkedList<Bike> bikes) {
        this.bikes = bikes;
    }

    @Override
    public String toString() {
        return "User [id="+idUser+", name=" + name + ", surname=" + surname + ", bikes=" + bikes.size() +"]";
    }
}
