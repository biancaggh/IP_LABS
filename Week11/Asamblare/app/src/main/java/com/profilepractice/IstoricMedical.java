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
import org.w3c.dom.Text;
import utility.AsyncResponse;
import utility.DiagnosticAsync;
import utility.IstoricAsync;

public class IstoricMedical extends AppCompatActivity implements AsyncResponse {

    private String user;

    private TextView Boli;
    private TextView Internari;
    private TextView Doctori;
    private TextView Spitale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_istoric_medical);

        user = getIntent().getStringExtra("EXTRA_USER");

        Boli = (TextView) findViewById(R.id.viewBoli);
        Internari = (TextView) findViewById(R.id.viewInternari);
        Doctori = (TextView) findViewById(R.id.viewDoctori);
        Spitale = (TextView) findViewById(R.id.viewSpitale);

        IstoricAsync asyncTask = new IstoricAsync();
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
            Intent intent = new Intent(IstoricMedical.this, Profil.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);

            //Intent intent = new Intent(IstoricMedical.this, Profil.class);
            //startActivity(intent);
            return true;
        } else if (id == R.id.item2) {

            Intent intent = new Intent(IstoricMedical.this, Diagnostic.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);

            //Intent intent = new Intent(IstoricMedical.this, Diagnostic.class);
            //startActivity(intent);
            return true;
        } else if (id == R.id.item3) {
            Intent intent = new Intent(IstoricMedical.this, Statistici.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.item4) {
            Intent intent = new Intent(IstoricMedical.this, Anomalii.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.item5) {

            Intent intent = new Intent(IstoricMedical.this, IstoricMedical.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);

            //Intent intent = new Intent(IstoricMedical.this, IstoricMedical.class);
            //startActivity(intent);
            return true;
        } else if (id == R.id.item6) {
            Intent intent = new Intent(IstoricMedical.this, Login.class);
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

        int size = Integer.parseInt(output.getString("size"));

        StringBuilder boli = new StringBuilder();

        for (int i = 0; i < size; ++i) {
            boli.append(output.getString("diagnostic" + i));
            boli.append(System.getProperty("line.separator"));
        }

        Boli.setText(boli.toString());

        StringBuilder internari = new StringBuilder();

        for (int i = 0; i < size; ++i) {
            internari.append(output.getString("data_internare" + i));
            internari.append("-");
            internari.append(output.getString("data_externare" + i));
            internari.append(System.getProperty("line.separator"));
        }

        Internari.setText(internari.toString());

        StringBuilder doctori = new StringBuilder();

        for (int i = 0; i < size; ++i) {
            doctori.append(output.getString("cnp_doctor" + i));
            doctori.append(System.getProperty("line.separator"));
        }

        Doctori.setText(doctori.toString());

        StringBuilder spitale = new StringBuilder();

        for (int i = 0; i < size; ++i) {
            spitale.append(output.getString("spital" + i));
            spitale.append(System.getProperty("line.separator"));
        }

        Spitale.setText(spitale.toString());

    }
}