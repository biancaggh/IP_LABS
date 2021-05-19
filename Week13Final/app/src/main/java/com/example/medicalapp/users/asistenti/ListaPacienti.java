package com.example.medicalapp.users.asistenti;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.medicalapp.R;
import com.example.medicalapp.users.Login;
import com.example.medicalapp.utility.AsyncResponse;

public class ListaPacienti extends AppCompatActivity implements AsyncResponse {
    //private Button pacient1;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacienti_a);

        //pacient1= (Button) findViewById(R.id.button1);

        user = getIntent().getStringExtra("EXTRA_USER");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_asistenti, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.item1) {

            Intent intent = new Intent(com.example.medicalapp.users.asistenti.ListaPacienti.this, com.example.medicalapp.users.asistenti.ListaPacienti.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);

            //Intent intent = new Intent(Profil.this, Profil.class);
            //startActivity(intent);
            return true;
        } else if (id == R.id.item2) {

            Intent intent = new Intent(com.example.medicalapp.users.asistenti.ListaPacienti.this, AdaugaPacient.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);

            //Intent intent = new Intent(Profil.this, Diagnostic.class);
            //startActivity(intent);
            return true;
        } else if (id == R.id.item3) {
            Intent intent = new Intent(com.example.medicalapp.users.asistenti.ListaPacienti.this, OrarGarda.class);
            // intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        } else if (id == R.id.item4) {
            Intent intent = new Intent(com.example.medicalapp.users.asistenti.ListaPacienti.this, com.example.medicalapp.users.asistenti.ProfilAsistent.class);
            // intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        } else if (id == R.id.item5) {

            Intent intent = new Intent(com.example.medicalapp.users.asistenti.ListaPacienti.this, Login.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);

            //Intent intent = new Intent(Profil.this, IstoricMedical.class);
            //startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void processFinish(Integer output) {

    }

    @Override
    public void processFinish(JSONObject output) throws JSONException {

    }

    @Override
    public void processFinish(Boolean output) throws JSONException {

    }
}