package com.profilepractice;

import android.widget.TextView;

import org.json.JSONException;

import java.net.MalformedURLException;

public class ProfilPresenter {

    private ProfilService service;
    private ProfilView view;
    private TextView Nume;
    public ProfilPresenter(ProfilView view, ProfilService service) {
        this.view = view;
        this.service = service;
    }

    public void processFinish(String output) throws MalformedURLException, JSONException {
        Nume.setText("test")
        String nume = Nume;
        if(nume.isEmpty()){
            
        }
    }
}
