package com.profilepractice;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Manual_Data_Introduction extends AppCompatActivity  {

    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual__data__introduction);
        user = getIntent().getStringExtra("EXTRA_USER");
    }

    public void SwitchToSleep(View view) {
        Intent intent = new Intent(Manual_Data_Introduction.this, Manual_Data_Sleep.class);
        intent.putExtra("EXTRA_USER", user);
        startActivity(intent);
    }

    public void SwitchToOxigen(View view) {
        Intent intent = new Intent(Manual_Data_Introduction.this, Manual_Data_Oxygen.class);
        intent.putExtra("EXTRA_USER", user);
        startActivity(intent);
    }

    public void SwitchToGeneral(View view) {
        Intent intent = new Intent(Manual_Data_Introduction.this, Manual_Data_General.class);
        intent.putExtra("EXTRA_USER", user);
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

            Intent intent = new Intent(Manual_Data_Introduction.this, Profil.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item2) {
            Intent intent = new Intent(Manual_Data_Introduction.this, Diagnostic.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item3) {
            Intent intent = new Intent(Manual_Data_Introduction.this, Statistici.class);
            //intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        }else if (id == R.id.item4) {
            Intent intent = new Intent(Manual_Data_Introduction.this, Anomalii.class);
            //intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item5) {
            Intent intent = new Intent(Manual_Data_Introduction.this, IstoricMedical.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item6) {
            Intent intent = new Intent(Manual_Data_Introduction.this, Manual_Data_Introduction.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}