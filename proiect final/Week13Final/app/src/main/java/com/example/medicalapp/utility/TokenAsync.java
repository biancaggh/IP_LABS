package com.example.medicalapp.utility;

import android.os.AsyncTask;
import com.example.medicalapp.MyFirebaseMessagingService;
import org.json.JSONException;

public class TokenAsync extends AsyncTask<String, Integer, Integer> {
    public AsyncResponse delegate = null;
    @Override
    protected Integer doInBackground(String... strings) {
        try {
            if((MyFirebaseMessagingService.getToken()!=null))
                ClientCommunicationHandler.sendToken(strings[0], MyFirebaseMessagingService.getToken());
            System.out.println("token sent!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 1;
    }


}
