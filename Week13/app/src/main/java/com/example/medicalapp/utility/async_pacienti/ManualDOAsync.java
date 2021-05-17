package com.example.medicalapp.utility.async_pacienti;



import android.os.AsyncTask;

import com.example.medicalapp.utility.AsyncResponse;

import org.json.JSONException;

public class ManualDOAsync extends AsyncTask<String, Integer, Boolean> {

    public AsyncResponse delegate = null;

    @Override
    protected Boolean doInBackground(String... strings) {
        try {
            utility.ClientCommunicationHandler.sendOxigen(strings[0], strings[1]);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    protected void onProgressUpdate(Integer... progress) {
    }

    protected void onPostExecute(Boolean result) {
        try {
            delegate.processFinish(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}