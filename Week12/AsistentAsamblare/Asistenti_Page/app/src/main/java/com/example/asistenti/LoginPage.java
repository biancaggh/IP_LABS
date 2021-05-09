package com.example.asistenti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;

import utility.AsyncResponse;
import utility.LoginAsync;

public class LoginPage extends AppCompatActivity implements View.OnClickListener, AsyncResponse {
    private  String user;
    private Button button;
    private EditText User;
    private EditText Password;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        button= (Button) findViewById(R.id.button_log);
        User = (EditText) findViewById(R.id.user);
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

        LoginAsync asyncTask =new LoginAsync();
        asyncTask.delegate = this;
        asyncTask.execute(user,password);
    }

    @Override
    public void processFinish(Integer output) {
        if(output==1)
        {
            Intent intent = new Intent(LoginPage.this, MainPage.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            //startActivity(new Intent(Login.this, Profil.class));
        }
        else {
            Toast.makeText(LoginPage.this, "Conectare esuata! ", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void processFinish(JSONObject output) throws JSONException {

    }

    @Override
    public void processFinish(Boolean output) throws JSONException {

    }

}