package com.example.medicalapp.utility.async_pacienti;

import android.os.AsyncTask;

import com.example.medicalapp.utility.AsyncResponse;

import com.example.medicalapp.utility.ClientCommunicationHandler;
import org.json.JSONException;
import org.json.JSONObject;

public class DiagnosticAsync extends AsyncTask<String, Integer, JSONObject> {

    public AsyncResponse delegate = null;

    @Override
    protected JSONObject doInBackground(String... strings) {
        JSONObject jsonObject= ClientCommunicationHandler.getDiagnostic(strings[0]);
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
