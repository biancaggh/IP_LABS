package com.example.try_01;

public class User {

    protected String nume;
    protected String prenume;
    protected String email;
    protected String cnp;

    public User() {

    }

    public User(String nume, String prenume, String email, String cnp) {
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.cnp = cnp;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getEmail() {
        return email;
    }

    public String getCNP() {
        return cnp;
    }
}
