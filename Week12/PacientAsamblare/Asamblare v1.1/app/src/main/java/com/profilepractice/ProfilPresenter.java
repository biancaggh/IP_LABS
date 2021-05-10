package com.profilepractice;

import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;

public class ProfilPresenter {

    private ProfilService service;
    private ProfilView view;
    private TextView Nume;

    public ProfilPresenter(ProfilView view, ProfilService service) {
        this.view = view;
        this.service = service;
    }

    public void processFinish(JSONObject output) throws MalformedURLException, JSONException {


        //Nume.setText(output.getString("nume"));
        //view.getNume()
        //String nume=null;
        //nume="test";


        view.setNume();
        String nume1=view.getNume();
        if(nume1.isEmpty()){
            view.showNumeError(R.string.nume_error);
            return;
        }

        if(nume1.contains("+-@;:")){
            view.showNumeError(R.string.nume_error);
            return;
        }

        view.showNumeError(R.string.nume_error);
    }
}
