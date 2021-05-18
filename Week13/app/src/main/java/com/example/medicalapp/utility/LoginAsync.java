package com.example.medicalapp.utility;

import android.os.AsyncTask;

import com.example.medicalapp.utility.AsyncResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginAsync extends AsyncTask<String, Integer, Integer> {

    public AsyncResponse delegate = null;
    protected int value;

    @Override
    protected Integer doInBackground(String... strings) {
        try {
            int nr = utility.ClientCommunicationHandler.login(strings[0], strings[1]);
            JSONObject jsonObject = utility.ClientCommunicationHandler.getRol(strings[0]);

            String rol = jsonObject.getString("rol");
            value = nr;

            if (rol.equals("pacient")) {
                value = 10 + nr;
            } else if (rol.equals("asistent")) {
                value = 20 + nr;
            } else {
                value = 30 + nr;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return value;
    }

    protected void onProgressUpdate(Integer... progress) {
    }

    protected void onPostExecute(Integer result) {
        delegate.processFinish(result);
    }

}

