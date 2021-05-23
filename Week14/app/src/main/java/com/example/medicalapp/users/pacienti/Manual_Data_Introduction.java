package com.example.medicalapp.users.pacienti;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicalapp.R;

public class Manual_Data_Introduction extends AppCompatActivity {

    private String user;
    private String cnp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual__data__introduction);
        String value = getIntent().getStringExtra("EXTRA_USER");

        int position = value.indexOf(",");
        int length = value.length();

        user = value.substring(0, position);
        cnp = value.substring(position + 1, length);
        System.out.println(cnp);
    }

    public void switchToSleep(View view) {
        Intent intent = new Intent(com.example.medicalapp.users.pacienti.Manual_Data_Introduction.this, Manual_Data_Sleep.class);
        intent.putExtra("EXTRA_USER", user + "," + cnp);
        startActivity(intent);
    }

    public void switchToOxigen(View view) {
        Intent intent = new Intent(com.example.medicalapp.users.pacienti.Manual_Data_Introduction.this, Manual_Data_Oxygen.class);
        intent.putExtra("EXTRA_USER", user + "," + cnp);
        startActivity(intent);
    }

    public void switchToGeneral(View view) {
        Intent intent = new Intent(com.example.medicalapp.users.pacienti.Manual_Data_Introduction.this, Manual_Data_General.class);
        intent.putExtra("EXTRA_USER", user + "," + cnp);
        startActivity(intent);
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

            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Manual_Data_Introduction.this, Profil.class);
            intent.putExtra("EXTRA_USER", user + "," + cnp);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item2) {
            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Manual_Data_Introduction.this, com.example.medicalapp.users.pacienti.Diagnostic.class);
            intent.putExtra("EXTRA_USER", user + "," + cnp);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item3) {
            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Manual_Data_Introduction.this, Statistici.class);
            intent.putExtra("EXTRA_USER", user + "," + cnp);
            startActivity(intent);
            return true;
        }else if (id == R.id.item4) {
            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Manual_Data_Introduction.this, Anomalii.class);
            intent.putExtra("EXTRA_USER", user + "," + cnp);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item5) {
            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Manual_Data_Introduction.this, IstoricMedical.class);
            intent.putExtra("EXTRA_USER", user + "," + cnp);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item6) {
            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Manual_Data_Introduction.this, com.example.medicalapp.users.pacienti.Manual_Data_Introduction.class);
            intent.putExtra("EXTRA_USER", user + "," + cnp);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}