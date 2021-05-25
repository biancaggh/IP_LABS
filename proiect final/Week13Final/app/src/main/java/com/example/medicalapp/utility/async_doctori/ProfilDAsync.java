package com.example.medicalapp.utility.async_doctori;


import android.os.AsyncTask;

import com.example.medicalapp.utility.AsyncResponse;


import com.example.medicalapp.utility.ClientCommunicationHandler;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfilDAsync extends AsyncTask<String, Integer, JSONObject> {

    public AsyncResponse delegate = null;

    @Override
    protected JSONObject doInBackground(String... strings) {

        JSONObject jsonObject = ClientCommunicationHandler.getDoctor(strings[0]);


        System.out.println("----------------------------------------------------------------------");
        System.out.println(strings[0]);
        System.out.println("----------------------------------------------------------------------");

        System.out.println("----------------------------------------------------------------------");
        System.out.println(ClientCommunicationHandler.getDoctor("maxim.marius76"));
        System.out.println("----------------------------------------------------------------------");

        System.out.println("----------------------------------------------------------------------");
        System.out.println(jsonObject);

        System.out.println("--------------------------------------------------------------------------");
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