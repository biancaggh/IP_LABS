package com.example.try_01;

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
import org.jetbrains.annotations.NotNull;

public class ProfileActivity extends AppCompatActivity {


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

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        String userID = firebaseUser.getUid();

        final TextView Nume = (TextView) findViewById(R.id.tvnume);
        final TextView Prenume = (TextView) findViewById(R.id.tvprenume);
        final TextView Email = (TextView) findViewById(R.id.tvemail);
        final TextView Cnp = (TextView) findViewById(R.id.tvcnp);

        databaseReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null) {

                    String nume = userProfile.getNume();
                    String prenume = userProfile.getPrenume();
                    String email = userProfile.getEmail();
                    String cnp = userProfile.getCNP();

                    Nume.setText(nume);
                    Prenume.setText(prenume);
                    Email.setText(email);
                    Cnp.setText(cnp);

                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

                Toast.makeText(ProfileActivity.this, "Sometjing wrong happened!", Toast.LENGTH_LONG).show();

            }
        });
    }
}