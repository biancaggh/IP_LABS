package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import org.jetbrains.annotations.NotNull;

public class ProfileActivity extends AppCompatActivity {

    TextView Nume;
    TextView Prenume;
    TextView User;
    TextView Sex;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button logout = (Button) findViewById(R.id.btnLogout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
            }
        });

        Nume = (TextView) findViewById(R.id.tvnume);
        Prenume = (TextView) findViewById(R.id.tvprenume);
        User = (TextView) findViewById(R.id.tvuser);
        Sex = (TextView) findViewById(R.id.tvsex);

        firebaseFirestore=FirebaseFirestore.getInstance();

        DocumentReference documentReference=firebaseFirestore.collection("pacient").document();


    }
}
