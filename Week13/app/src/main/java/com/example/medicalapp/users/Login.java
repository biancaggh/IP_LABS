package com.example.medicalapp.users;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;

import com.example.medicalapp.users.asistenti.MainPageAsistent;
import com.example.medicalapp.users.doctori.MainPageDoctori;
import com.example.medicalapp.users.pacienti.MainActivity;
import com.example.medicalapp.utility.AsyncResponse;
import com.example.medicalapp.utility.LoginAsync;

public class Login extends AppCompatActivity implements View.OnClickListener, AsyncResponse, AdapterView.OnItemSelectedListener {
    private String user;
    private String password;
    private Button button;
    private EditText User;
    private EditText Password;
    private Spinner spinner;
    private static final String[] paths = {"Asistent", "Doctor", "Pacient"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button= (Button) findViewById(R.id.button_log);
        User = (EditText) findViewById(R.id.userid);
        Password = (EditText) findViewById(R.id.parola);

        button.setOnClickListener(this);

        spinner = (Spinner)findViewById(R.id.spinnerlog);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Login.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


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
        String text = spinner.getSelectedItem().toString();
        Intent intent;
        if(output==1)
        {
            if(text=="Asistent"){
                intent = new Intent(com.example.medicalapp.users.Login.this, MainPageAsistent.class);
                intent.putExtra("EXTRA_USER", user);
                startActivity(intent);
            }
            else if (text=="Doctor"){
                intent = new Intent(com.example.medicalapp.users.Login.this, MainPageDoctori.class);
                intent.putExtra("EXTRA_USER", user);
                startActivity(intent);
            }
            else{
                  intent = new Intent(com.example.medicalapp.users.Login.this, MainActivity.class);
                intent.putExtra("EXTRA_USER", user);
                startActivity(intent);
            }
        }
        else {
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