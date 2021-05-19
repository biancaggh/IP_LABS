package com.example.medicalapp.utility.async_pacienti;

import android.os.AsyncTask;
import com.example.medicalapp.utility.AsyncResponse;

import com.example.medicalapp.utility.ClientCommunicationHandler;

import org.json.JSONException;
import org.json.JSONObject;

public class StatisticiAsync extends AsyncTask<String, Integer, JSONObject> {

    public AsyncResponse delegate = null;

    @Override
    protected JSONObject doInBackground(String... strings) {
        JSONObject jsonObject = new JSONObject();
        String size;
        Integer value;

        JSONObject pulss = ClientCommunicationHandler.getPuls(strings[0]);
        JSONObject pasi = ClientCommunicationHandler.getPasi(strings[0]);
        JSONObject calorii = ClientCommunicationHandler.getCalorii(strings[0]);
        JSONObject calitateSomn = ClientCommunicationHandler.getCalitateSomn(strings[0]);
        JSONObject oxigen = ClientCommunicationHandler.getNivelOxigen(strings[0]);

        try {
            size = pulss.getString("size").toString();
            value = Integer.parseInt(size) - 1;
            String lastPuls = pulss.getString("puls" + value);

            size = pasi.getString("size").toString();
            value = Integer.parseInt(size) - 1;
            String lastPasi = pasi.getString("nr_pasi" + value);

            size = calorii.getString("size").toString();
            value = Integer.parseInt(size) - 1;
            String lastCalorii = calorii.getString("calorii" + value);

            size = calitateSomn.getString("size").toString();
            value = Integer.parseInt(size) - 1;
            String lastSomn = calitateSomn.getString("calitate" + value);

            size = oxigen.getString("size").toString();
            value = Integer.parseInt(size) - 1;
            String lastOxigen = oxigen.getString("value" + value);

            jsonObject.putOpt("puls", lastPuls);
            jsonObject.putOpt("nr_pasi", lastPasi);
            jsonObject.putOpt("calorii", lastCalorii);
            jsonObject.putOpt("calitate", lastSomn);
            jsonObject.putOpt("value", lastOxigen);

            System.out.println(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
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