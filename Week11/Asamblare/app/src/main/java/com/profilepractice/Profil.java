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
import utility.AsyncResponse;
import utility.CCH;
import utility.LoginAsync;
import utility.ProfilAsync;

public class Profil extends AppCompatActivity implements AsyncResponse {

    private String user;
    private TextView Nume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        user=getIntent().getStringExtra("EXTRA_USER");
        Nume=(TextView) findViewById(R.id.viewNume);

        System.out.println("----------------------------------------------------");
        System.out.println(user);
        System.out.println("----------------------------------------------------");

        ProfilAsync asyncTask =new ProfilAsync();
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
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item2) {
            Intent intent = new Intent(Profil.this, Diagnostic.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item3) {
            Intent intent = new Intent(Profil.this, Statistici.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.item4) {
            Intent intent = new Intent(Profil.this, Anomalii.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item5) {
            Intent intent = new Intent(Profil.this, IstoricMedical.class);
            startActivity(intent);
            return true;
        }
        else
        if (id == R.id.item6) {
            Intent intent = new Intent(Profil.this, Login.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void processFinish(JSONObject output) throws JSONException {
        System.out.println("----------------------------------------------------");
        System.out.println(output.getString("adresa"));
        System.out.println("----------------------------------------------------");
        Nume.setText(output.getString("adresa"));
    }

    @Override
    public void processFinish(Integer output) {}
}