package com.example.medicalapp;

import android.app.Service;
import androidx.annotation.NonNull;
import com.google.firebase.messaging.FirebaseMessagingService;
import org.jetbrains.annotations.NotNull;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull @NotNull String s) {
        super.onNewToken(s);
    }
}
