package com.profilepractice;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import org.json.JSONException;
import org.json.JSONObject;
import utility.*;

public class Profil extends AppCompatActivity implements AsyncResponse {

    private String user;
    private String cnp;
    private TextView User;
    private TextView Nume;
    private TextView Prenume;
    private TextView Gen;
    private TextView Id;
    private TextView Adresa;
    private TextView Data;
    private TextView Inaltime;
    private TextView Greutate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        user = getIntent().getStringExtra("EXTRA_USER");

        User = (TextView) findViewById(R.id.viewUser);
        Nume = (TextView) findViewById(R.id.viewNume);
        Prenume = (TextView) findViewById(R.id.viewPrenume);
        Gen = (TextView) findViewById(R.id.viewGen);
        Id = (TextView) findViewById(R.id.viewID);
        Adresa = (TextView) findViewById(R.id.viewAdresa);
        Data = (TextView) findViewById(R.id.viewData);
        Inaltime = (TextView) findViewById(R.id.viewInaltime);
        Greutate = (TextView) findViewById(R.id.viewGreutate);


        ProfilAsync asyncTask = new ProfilAsync();
        asyncTask.delegate = this;
        asyncTask.execute(user);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.meniu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.item1) {

            Intent intent = new Intent(Profil.this, Profil.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);

            //Intent intent = new Intent(Profil.this, Profil.class);
            //startActivity(intent);
            return true;
        } else if (id == R.id.item2) {

            Intent intent = new Intent(Profil.this, Diagnostic.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);

            //Intent intent = new Intent(Profil.this, Diagnostic.class);
            //startActivity(intent);
            return true;
        } else if (id == R.id.item3) {
            Intent intent = new Intent(Profil.this, Statistici.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.item4) {
            Intent intent = new Intent(Profil.this, Anomalii.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.item5) {

            Intent intent = new Intent(Profil.this, IstoricMedical.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);

            //Intent intent = new Intent(Profil.this, IstoricMedical.class);
            //startActivity(intent);
            return true;
        } else if (id == R.id.item6) {
            Intent intent = new Intent(Profil.this, Login.class);
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
        Adresa.setText(output.getString("adresa"));
        Data.setText(output.getString("data_nastere"));
        Inaltime.setText(output.getString("inaltime"));
        Greutate.setText(output.getString("greutate"));

    }

}