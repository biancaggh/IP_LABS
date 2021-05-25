package com.example.medicalapp.users;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicalapp.R;

import com.example.medicalapp.users.pacienti.Profil;
import com.example.medicalapp.utility.MyApplication;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;

import com.example.medicalapp.users.asistenti.MainPageAsistent;
import com.example.medicalapp.users.doctori.MainPageDoctori;
import com.example.medicalapp.utility.AsyncResponse;
import com.example.medicalapp.utility.LoginAsync;

public class Login extends AppCompatActivity implements View.OnClickListener, AsyncResponse, AdapterView.OnItemSelectedListener {
    private String user;
    private String password;
    private Button button;
    private EditText User;
    private EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = (Button) findViewById(R.id.button_log);
        User = (EditText) findViewById(R.id.userid);
        Password = (EditText) findViewById(R.id.parola);

        button.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_log:
                try {
                    userLogin();
                } catch (JSONException | MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void userLogin() throws JSONException, MalformedURLException {
        user = User.getText().toString().trim();

        password = Password.getText().toString().trim();

        if (user.isEmpty()) {
            User.setError("User is required!");
            User.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            Password.setError("Password is required!");
            Password.requestFocus();
            return;
        }

        LoginAsync asyncTask = new LoginAsync();


        asyncTask.delegate = this;
        asyncTask.execute(user, password);
    }

    @Override
    public void processFinish(Integer output) {
        if (output % 10 == 1) {
            if (output / 10 == 2) {
                Intent intent = new Intent(com.example.medicalapp.users.Login.this, MainPageAsistent.class);
                intent.putExtra("EXTRA_USER", user);
                startActivity(intent);
            } else if (output / 10 == 3) {
                Intent intent = new Intent(com.example.medicalapp.users.Login.this, MainPageDoctori.class);
                intent.putExtra("EXTRA_USER", user);
                startActivity(intent);
            } else if (output / 10 == 1) {
                System.out.println("MUAHAHAHAHAHAHAHAHAHAHAHAHAHA");
                Intent intent = new Intent(com.example.medicalapp.users.Login.this, Profil.class);
                intent.putExtra("EXTRA_USER", user);
                startActivity(intent);
            }
        } else {
            Toast.makeText(com.example.medicalapp.users.Login.this, "Conectare esuata! ", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void processFinish(JSONObject output) throws JSONException {

    }

    @Override
    public void processFinish(Boolean output) throws JSONException {

    }

    @Override
    public void processFinish(ArrayList<String> output) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}