package edu.upc.dsa.models;

public class UserTO {
    private String idUser;
    private String name;
    private String surname;

    public UserTO() {
    }

    public UserTO(String idUser, String name, String surname) {
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
}
