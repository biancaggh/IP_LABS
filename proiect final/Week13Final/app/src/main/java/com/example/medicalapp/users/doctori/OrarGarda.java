package com.example.medicalapp.users.doctori;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicalapp.R;
import com.example.medicalapp.users.Login;

public class OrarGarda extends AppCompatActivity {

    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orar_garda_d);

        user = getIntent().getStringExtra("EXTRA_USER");
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

            Intent intent = new Intent(com.example.medicalapp.users.doctori.OrarGarda.this, ListaPacienti.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);

            return true;
        } else if (id == R.id.item2) {

            Intent intent = new Intent(com.example.medicalapp.users.doctori.OrarGarda.this, AdaugareDiagnostic.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);

            return true;
        } else if (id == R.id.item3) {
            Intent intent = new Intent(com.example.medicalapp.users.doctori.OrarGarda.this, com.example.medicalapp.users.doctori.OrarGarda.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        } else if (id == R.id.item4) {
            Intent intent = new Intent(com.example.medicalapp.users.doctori.OrarGarda.this, DoctoriProfil.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        } else if (id == R.id.item5) {

            Intent intent = new Intent(com.example.medicalapp.users.doctori.OrarGarda.this, Login.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}