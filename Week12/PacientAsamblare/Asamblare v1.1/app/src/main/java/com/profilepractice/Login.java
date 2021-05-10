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

public class Login extends AppCompatActivity implements View.OnClickListener, AsyncResponse, LoginView {
    private EditText User;
    private EditText Password;
    private Button Login;
    private String user;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        User = (EditText) findViewById(R.id.etUser);
        Login = (Button) findViewById(R.id.btnLogin);
        Password=(EditText) findViewById(R.id.etPassword);

        Login.setOnClickListener(this);
        presenter = new LoginPresenter(this, new LoginService());
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
    @Override
    public String getUsername() {
        return User.getText().toString().trim();
    }

    @Override
    public void showUsernameError(int resId) {
        User.setError(getString(resId));
    }
    @Override
    public String getPassword() {
        return Password.getText().toString().trim();
    }

    @Override
    public void showPasswordError(int resId) {
        Password.setError(getString(resId));
    }

    @Override
    public void startActivity() throws MalformedURLException, JSONException {
        userLogin();
    }

    @Override
    public void showLoginError(int resId) {
        Toast.makeText(Login.this, getString(resId), Toast.LENGTH_LONG).show();
    }
}

