package com.profilepractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Statistici extends AppCompatActivity {
    private Button Sync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistici);
        Sync = (Button) findViewById(R.id.button5);
        Sync.setOnClickListener(new View.OnClickListener()
        {
        @Override
        public void onClick(View Sync) {
            Intent intent=new Intent(Statistici.this, Statistici.class);
            startActivity(intent);
        }
    });
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

            Intent intent = new Intent(Statistici.this, Profil.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item2) {
            Intent intent = new Intent(Statistici.this, Diagnostic.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item3) {
            Intent intent = new Intent(Statistici.this, Statistici.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.item4) {
            Intent intent = new Intent(Statistici.this, Anomalii.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item5) {
            Intent intent = new Intent(Statistici.this, IstoricMedical.class);
            startActivity(intent);
            return true;
        }
        //else
//        if (id == R.id.item5) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}