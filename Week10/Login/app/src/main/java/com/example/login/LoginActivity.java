package com.example.login;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText User;
    private Button Login;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseFirestore = FirebaseFirestore.getInstance();

        User = (EditText) findViewById(R.id.etUser);
        Login = (Button) findViewById(R.id.btnLogin);

        Login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String user = User.getText().toString().trim();

        if (user.isEmpty()) {
            User.setError("User is required!");
            User.requestFocus();
            return;
        }

        firebaseFirestore.collection("pacient").document(user).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                        if (task.getResult().exists()) {
                            startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "Login Failed!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}

