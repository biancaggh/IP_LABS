package com.example.medicalapp.users.pacienti;

public class Date {
    String date;
    float heartrate;

    public Date(String date, float heartrate) {
        this.date = date;
        this.heartrate = heartrate;
    }

    public String getDate() {
        return date;
    }

    public float getHeartrate() {
        return heartrate;
    }

    public String toString() {
        return this.date + " " + this.heartrate;
    }


    public void setDate(String date) {
        this.date = date;
    }

    public void setHeartrate(float heartrate) {
        this.heartrate = heartrate;
    }
}
