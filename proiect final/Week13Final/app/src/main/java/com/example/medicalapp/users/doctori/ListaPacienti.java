package com.example.medicalapp.users.doctori;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medicalapp.R;
import com.example.medicalapp.users.Login;
import com.example.medicalapp.utility.AsyncResponse;
import com.example.medicalapp.utility.async_asistenti.ListaPacAsync;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListaPacienti extends AppCompatActivity implements AsyncResponse {

    private String user;
    private ListView listaPacienti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacienti_d);

        user = getIntent().getStringExtra("EXTRA_USER");
        listaPacienti=(ListView) findViewById(R.id.listPacienti);

        ListaPacAsync asyncTask = new ListaPacAsync();
        asyncTask.delegate = this;
        asyncTask.execute();
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

            Intent intent = new Intent(com.example.medicalapp.users.doctori.ListaPacienti.this, com.example.medicalapp.users.doctori.ListaPacienti.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);

            return true;
        } else if (id == R.id.item2) {

            Intent intent = new Intent(com.example.medicalapp.users.doctori.ListaPacienti.this, com.example.medicalapp.users.doctori.AdaugareDiagnostic.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);

            return true;
        } else if (id == R.id.item3) {
            Intent intent = new Intent(com.example.medicalapp.users.doctori.ListaPacienti.this, com.example.medicalapp.users.doctori.OrarGarda.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        } else if (id == R.id.item4) {
            Intent intent = new Intent(com.example.medicalapp.users.doctori.ListaPacienti.this, DoctoriProfil.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        } else if (id == R.id.item5) {

            Intent intent = new Intent(com.example.medicalapp.users.doctori.ListaPacienti.this, Login.class);
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

    }

    @Override
    public void processFinish(Boolean output) throws JSONException {

    }

    @Override
    public void processFinish(ArrayList<String> output) {

        listaPacienti.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, output));

    }
}