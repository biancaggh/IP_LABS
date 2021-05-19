package com.example.medicalapp.utility.async_asistenti;


import android.os.AsyncTask;

import com.example.medicalapp.utility.AsyncResponse;

import com.example.medicalapp.utility.ClientCommunicationHandler;
import org.json.JSONException;
import org.json.JSONObject;

public class ProfilAAsync extends AsyncTask<String, Integer, JSONObject> {

    public AsyncResponse delegate = null;

    @Override
    protected JSONObject doInBackground(String... strings) {
        JSONObject jsonObject= ClientCommunicationHandler.getAsistent(strings[0]);
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