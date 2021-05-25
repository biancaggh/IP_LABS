package com.example.medicalapp.utility.async_asistenti;


import android.os.AsyncTask;

import com.example.medicalapp.utility.AsyncResponse;


import com.example.medicalapp.utility.ClientCommunicationHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListaPacAsync extends AsyncTask<String, Integer, ArrayList<String>> {

    public AsyncResponse delegate = null;

    @Override
    protected ArrayList<String> doInBackground(String... strings) {
        JSONObject jsonObject= ClientCommunicationHandler.getPacientList();

        ArrayList<String> listPacient=new ArrayList<>();
        int size= 0;
        try {
            size = Integer.parseInt(jsonObject.getString("psize"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for(int i=0; i<size;++i){
            try {
                JSONObject js=ClientCommunicationHandler.getPacientByCNP(jsonObject.getString("p"+i));
                listPacient.add(jsonObject.getString("p"+i)+", "+js.getString("nume")+" "+js.getString("prenume")+"\n");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        System.out.println(jsonObject);
        System.out.println(listPacient);
        return listPacient;
    }

    protected void onProgressUpdate(Integer... progress) {
    }

    protected void onPostExecute(ArrayList<String> stringArrayList) {
        delegate.processFinish(stringArrayList);
    }

}