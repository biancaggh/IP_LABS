package com.example.medicalapp.users.pacienti;

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
import com.example.medicalapp.utility.async_pacienti.ProfilAsync;

import java.util.ArrayList;

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
    //private ProfilPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        String value = getIntent().getStringExtra("EXTRA_USER");

        int position = value.indexOf(",");
        int length = value.length();

        if (position != -1) {
            user = value.substring(0, position);
            cnp = value.substring(position + 1, length);
            System.out.println(cnp);
        } else
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

        //presenter = new ProfilPresenter(this, new ProfilService());


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

            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Profil.this, com.example.medicalapp.users.pacienti.Profil.class);
            intent.putExtra("EXTRA_USER", user + "," + cnp);
            startActivity(intent);

            //Intent intent = new Intent(Profil.this, Profil.class);
            //startActivity(intent);
            return true;
        } else if (id == R.id.item2) {

            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Profil.this, com.example.medicalapp.users.pacienti.Diagnostic.class);
            intent.putExtra("EXTRA_USER", user + "," + cnp);
            startActivity(intent);

            //Intent intent = new Intent(Profil.this, Diagnostic.class);
            //startActivity(intent);
            return true;
        } else if (id == R.id.item3) {
            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Profil.this, com.example.medicalapp.users.pacienti.Statistici.class);
            intent.putExtra("EXTRA_USER", user + "," + cnp);
            startActivity(intent);
            return true;
//        } else if (id == R.id.item4) {
//            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Profil.this, com.example.medicalapp.users.pacienti.Anomalii.class);
//            intent.putExtra("EXTRA_USER", user + "," + cnp);
//            startActivity(intent);
//            return true;
        } else if (id == R.id.item5) {

            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Profil.this, com.example.medicalapp.users.pacienti.IstoricMedical.class);
            intent.putExtra("EXTRA_USER", user + "," + cnp);
            startActivity(intent);

            //Intent intent = new Intent(Profil.this, IstoricMedical.class);
            //startActivity(intent);
            return true;
        } else if (id == R.id.item6) {
            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Profil.this, com.example.medicalapp.users.pacienti.Manual_Data_Introduction.class);
            intent.putExtra("EXTRA_USER", user + "," + cnp);
            startActivity(intent);
            return true;
        } else if (id == R.id.item7) {
            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Profil.this, Login.class);
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

        cnp = output.getString("cnp");
        //verify();

    }

    @Override
    public void processFinish(Boolean output) throws JSONException {
    }

    @Override
    public void processFinish(ArrayList<String> output) {

    }
}
//
//    public void verify(){
//
//    }
//
//
//    @Override
//    public String getNume() {
//
//        return Nume.getText().toString().trim();
//    }
//
//    @Override
//    public void setNume(){
//
//        Nume.setText("test");
//    }
//
//    @Override
//    public void showNumeError(int resId) {
//        Nume.setError(getString(resId));
//    }
//
//    @Override
//    public void setGreutate(){
//        Greutate.setText("50");
//    }
//
//    @Override
//    public String getGreutate() {
//        return Greutate.getText().toString().trim();
//    }
//
//    @Override
//    public void showGreutateError(int resId) {
//        Greutate.setError(getString(resId));
//    }
//
//
//}