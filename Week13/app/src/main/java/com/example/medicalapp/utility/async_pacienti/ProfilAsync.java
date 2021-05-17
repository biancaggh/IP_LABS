package com.example.medicalapp.utility.async_pacienti;



import android.os.AsyncTask;

import com.example.medicalapp.utility.AsyncResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfilAsync extends AsyncTask<String, Integer, JSONObject> {

    public AsyncResponse delegate = null;

    @Override
    protected JSONObject doInBackground(String... strings) {
            JSONObject jsonObject= utility.ClientCommunicationHandler.getPacient(strings[0]);
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