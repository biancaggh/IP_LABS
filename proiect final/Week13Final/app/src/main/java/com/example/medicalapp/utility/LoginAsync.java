package com.example.medicalapp.utility;

import android.content.Context;
import android.os.AsyncTask;


import android.util.Log;
import androidx.annotation.NonNull;
import com.example.medicalapp.MyFirebaseMessagingService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class LoginAsync extends AsyncTask<String, Integer, Integer> {

    public static String username;
    public AsyncResponse delegate = null;
    protected int value;
    Context context;

    @Override
    protected Integer doInBackground(String... strings) {
        try {

            int nr = ClientCommunicationHandler.login(strings[0], strings[1]);
            JSONObject jsonObject = ClientCommunicationHandler.getRol(strings[0]);
           username = strings[0];


            String rol = jsonObject.getString("rol");
            value = nr;
            if(value==1){
                MyFirebaseMessagingService.setUsername(username);
            }
           FirebaseMessaging.getInstance().deleteToken();
           FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
               @Override
               public void onComplete(@NonNull @NotNull Task<String> task) {
                   if(task.isSuccessful()) {
                       System.out.println("Token Complete");

                   }
                   else{
                       System.out.println("TOKEN NOT COMPLETE!");
                   }
               }
           });


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

