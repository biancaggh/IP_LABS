package com.profilepractice;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONObject;
import utility.LoginAsync;
import utility.AsyncResponse;
import org.json.JSONException;

import java.net.MalformedURLException;

public class Login extends AppCompatActivity implements View.OnClickListener, AsyncResponse {
    private EditText User;
    private EditText Password;
    private Button Login;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        User = (EditText) findViewById(R.id.etUser);
        Login = (Button) findViewById(R.id.btnLogin);
        Password=(EditText) findViewById(R.id.etPassword);

        Login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
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

        String password = Password.getText().toString().trim();

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
            Intent intent = new Intent(Login.this, Profil.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            //startActivity(new Intent(Login.this, Profil.class));
        }
        else {
            Toast.makeText(Login.this, "Conectare esuata! ", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void processFinish(JSONObject output) throws JSONException {

    }

    @Override
    public void processFinish(Boolean output) throws JSONException {

    }
}

