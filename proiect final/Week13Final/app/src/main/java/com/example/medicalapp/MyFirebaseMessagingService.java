package com.example.medicalapp;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import com.example.medicalapp.utility.ClientCommunicationHandler;
import com.example.medicalapp.utility.TokenAsync;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import org.jetbrains.annotations.NotNull;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    static String username = null;
   static String token = null;
    @Override
    public void onNewToken(@NonNull @NotNull String s) {
        super.onNewToken(s);
        Log.d("TAG", "THE TOKEN IS" + s);
        token = s;
        TokenAsync tokenAsync = new TokenAsync();
        tokenAsync.delegate = null;
        if(username!=null)
            tokenAsync.execute(username);
        //System.out.println("-----------GOOT NEW TOOOOOOKENNNNNN----------");
    }

    public static String getToken() {
        return token;
    }

    public static void setUsername(String username) {
        MyFirebaseMessagingService.username = username;
    }

//    @Override
//    public void onMessageReceived(@NonNull @NotNull RemoteMessage remoteMessage) {
//        super.onMessageReceived(remoteMessage);
//        System.out.println("---------MSG RECV--------");
//    }
}

