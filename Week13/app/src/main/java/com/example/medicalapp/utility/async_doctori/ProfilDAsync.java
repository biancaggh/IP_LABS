package com.example.medicalapp.utility.async_doctori;


import android.os.AsyncTask;

import com.example.medicalapp.utility.AsyncResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfilDAsync extends AsyncTask<String, Integer, JSONObject> {

    public AsyncResponse delegate = null;

    @Override
    protected JSONObject doInBackground(String... strings) {
        JSONObject jsonObject= utility.ClientCommunicationHandler.getDoctor(strings[0]);
        System.out.println(jsonObject);
        return jsonObject;
    }

    protected void onProgressUpdate(Integer... progress) {
    }

    protected void onPostExecute(JSONObject jsonObject) {
        try {
            delegate.processFinish(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}