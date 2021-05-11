package com.profilepractice;

import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;

public class ProfilPresenter {

    private ProfilService service;
    private ProfilView view;
    private TextView Nume;
    private TextView Greutate;

    public ProfilPresenter(ProfilView view, ProfilService service) {
        this.view = view;
        this.service = service;
    }

    public void processFinish(JSONObject output) throws MalformedURLException, JSONException {


        //verifyNameEmpty();
        //verifyNameInvalid();

        //view.showNumeError(R.string.nume_error);

        //verifyWeight();

        //view.showGreutateError(R.string.greutate_error);
    }

    public void verifyNameEmpty() {

        view.setNume();
        String nume1 = view.getNume();
        if (nume1.isEmpty()) {
            view.showNumeError(R.string.nume_error);
        }
    }

    public void verifyNameInvalid() {
        view.setNume();
        String nume1 = view.getNume();
        if (nume1.contains("+-@;:")) {
            view.showNumeError(R.string.nume_error);

        }
    }

    public void verifyWeight() {

        view.setGreutate();
        String greutate1 = view.getGreutate();
        if (Double.parseDouble(greutate1) < 0.0) {
            view.showGreutateError(R.string.greutate_error);
        }

    }
}
