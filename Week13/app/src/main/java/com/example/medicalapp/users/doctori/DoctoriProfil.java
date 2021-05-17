package com.example.medicalapp.users.doctori;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.medicalapp.R;
import com.example.medicalapp.users.Login;
import com.example.medicalapp.utility.AsyncResponse;
import com.example.medicalapp.utility.async_doctori.ProfilDAsync;

public class DoctoriProfil extends AppCompatActivity implements AsyncResponse {
    private String user;
    private String cnp;
    private TextView User;
    private TextView Nume;
    private TextView Prenume;
    private TextView Gen;
    private TextView Id;
    private TextView Sectie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctori_profil);

        user = getIntent().getStringExtra("EXTRA_USER");

        User = (TextView) findViewById(R.id.duser);
        Nume = (TextView) findViewById(R.id.dnume);
        Prenume = (TextView) findViewById(R.id.dprenume);
        Gen = (TextView) findViewById(R.id.dgen);
        Id = (TextView) findViewById(R.id.did);
        Sectie = (TextView) findViewById(R.id.dsectie);


        ProfilDAsync asyncTask = new ProfilDAsync();
        asyncTask.delegate = this;
        asyncTask.execute(user);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_doctori, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.item1) {

            Intent intent = new Intent(com.example.medicalapp.users.doctori.DoctoriProfil.this, com.example.medicalapp.users.doctori.ListaPacienti.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);

            return true;
        } else if (id == R.id.item2) {

            Intent intent = new Intent(com.example.medicalapp.users.doctori.DoctoriProfil.this, com.example.medicalapp.users.doctori.AdaugareDiagnostic.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);

            return true;
        } else if (id == R.id.item3) {
            Intent intent = new Intent(com.example.medicalapp.users.doctori.DoctoriProfil.this, com.example.medicalapp.users.doctori.OrarGarda.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        } else if (id == R.id.item4) {
            Intent intent = new Intent(com.example.medicalapp.users.doctori.DoctoriProfil.this, com.example.medicalapp.users.doctori.DoctoriProfil.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        } else if (id == R.id.item5) {

            Intent intent = new Intent(com.example.medicalapp.users.doctori.DoctoriProfil.this, Login.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void processFinish(Integer output) {

    }

    @Override
    public void processFinish(JSONObject output) throws JSONException {

        User.setText(user);
        Nume.setText(output.getString("nume"));
        Prenume.setText(output.getString("prenume"));
        Gen.setText(output.getString("sex"));
        Id.setText(output.getString("cnp"));
        Sectie.setText(output.getString("sectie"));

    }

    @Override
    public void processFinish(Boolean output) throws JSONException {

    }
}