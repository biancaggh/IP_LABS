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
import utility.ProfilAsync;

public class Diagnostic extends AppCompatActivity implements AsyncResponse {

    private String user;

    private TextView Id;
    private TextView Afectiune;
    private TextView Medicamente;
    private TextView Administrare;
    private TextView Indicatii;
    private TextView Contraindicatii;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnostic);

        user = getIntent().getStringExtra("EXTRA_USER");

        Id = (TextView) findViewById(R.id.viewID);
        Afectiune = (TextView) findViewById(R.id.viewAfectiune);
        Medicamente = (TextView) findViewById(R.id.viewMedicamente);
        Administrare = (TextView) findViewById(R.id.viewAdministrare);
        Indicatii = (TextView) findViewById(R.id.viewIndicatii);
        Contraindicatii = (TextView) findViewById(R.id.viewContraindicatii);

        DiagnosticAsync asyncTask = new DiagnosticAsync();
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

            Intent intent = new Intent(Diagnostic.this, Profil.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);

            //Intent intent = new Intent(Diagnostic.this, Profil.class);
            //startActivity(intent);
            return true;
        } else if (id == R.id.item2) {

            Intent intent = new Intent(Diagnostic.this, Diagnostic.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);

            //Intent intent = new Intent(Diagnostic.this, Diagnostic.class);
            //startActivity(intent);
            return true;
        } else if (id == R.id.item3) {
            Intent intent = new Intent(Diagnostic.this, Statistici.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.item4) {
            Intent intent = new Intent(Diagnostic.this, Anomalii.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.item5) {

            Intent intent = new Intent(Diagnostic.this, IstoricMedical.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);

            //Intent intent = new Intent(Diagnostic.this, IstoricMedical.class);
            //startActivity(intent);
            return true;
        } else if (id == R.id.item6) {
            Intent intent = new Intent(Diagnostic.this, Login.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void processFinish(JSONObject output) throws JSONException {

        Id.setText(user);
        Afectiune.setText(output.getString("diagnostic"));
        Administrare.setText(output.getString("descriere0"));

        int medNr = Integer.parseInt(output.getString("msize"));
        StringBuilder meds = new StringBuilder();

        meds.append(output.getString("medicament" + 0));

        for (int i = 1; i < medNr; ++i) {
            meds.append(System.getProperty("line.separator"));
            meds.append(output.getString("medicament" + i));
        }

        Medicamente.setText(meds.toString());

        int indNr = Integer.parseInt(output.getString("isize"));
        StringBuilder indi = new StringBuilder();

        indi.append(output.getString("i" + 0));

        for (int i = 1; i < indNr; ++i) {
            indi.append(System.getProperty("line.separator"));
            indi.append(output.getString("i" + i));
        }

        Indicatii.setText(indi.toString());

        int contraindiNr = Integer.parseInt(output.getString("csize"));
        StringBuilder contraindi = new StringBuilder();

        contraindi.append(output.getString("c" + 0));

        for (int i = 1; i < contraindiNr; ++i) {
            contraindi.append(System.getProperty("line.separator"));
            contraindi.append(output.getString("c" + i));
        }

        Contraindicatii.setText(contraindi.toString());

    }

    @Override
    public void processFinish(Integer output) {

    }
}