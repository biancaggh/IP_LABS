package com.example.medicalapp.users.pacienti;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medicalapp.R;
import com.example.medicalapp.users.Login;
import com.example.medicalapp.utility.AsyncResponse;
import com.example.medicalapp.utility.ClientCommunicationHandler;
import com.example.medicalapp.utility.async_pacienti.ManualDGAsync;
import com.example.medicalapp.utility.async_pacienti.StatisticiAsync;
import com.example.medicalapp.utility.async_pacienti.StatisticiAsyncData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Statistici extends AppCompatActivity implements AsyncResponse {
    private Button Sync;
    private String user;
    private String cnp;

    private TextView Somn;
    private TextView Calorii;
    private TextView Pasi;
    private TextView Puls;
    private TextView Oxigen;
    private TextView Data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistici);
        String value = getIntent().getStringExtra("EXTRA_USER");

        int position = value.indexOf(",");
        int length = value.length();

        user = value.substring(0, position);
        cnp = value.substring(position + 1, length);
        System.out.println(cnp);

        Somn = (TextView) findViewById(R.id.somn);
        Calorii = (TextView) findViewById(R.id.calorii);
        Pasi = (TextView) findViewById(R.id.pasi);
        Puls = (TextView) findViewById(R.id.puls);
        Oxigen = (TextView) findViewById(R.id.oxigen);
        Data = (TextView) findViewById(R.id.data);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        Data.setText(formattedDate);

        StatisticiAsync asyncTask = new StatisticiAsync();
        asyncTask.delegate = this;
        asyncTask.execute(cnp);

        Sync = (Button) findViewById(R.id.button5);
        Sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View Sync) {
                Intent intent = new Intent(com.example.medicalapp.users.pacienti.Statistici.this, com.example.medicalapp.users.pacienti.Statistici.class);
                intent.putExtra("EXTRA_USER", user + "," + cnp);


                try {

                    //Bracelet stats

                    Connection c = null;
                    Class.forName( "org.sqldroid.SQLDroidDriver" );
                    c = DriverManager.getConnection( "jdbc:sqldroid:" + "/data/data/com.example.medicalapp/files/export" );
                    c.setAutoCommit( false );

                    String hr = String.valueOf( CollectData.getAvgHeartrate(c) );
                    String calories = String.valueOf( CollectData.getTodayCalorie(c) );
                    String steps = String.valueOf( CollectData.getTodaySteps(c) );
                    Log.d("hr", hr);
                    Log.d("calories", calories);
                    Log.d("steps", steps);

                    StatisticiAsyncData asyncTask = new StatisticiAsyncData();
                    asyncTask.delegate = this;
                    asyncTask.execute(user, hr , calories, steps);

                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }

                startActivity(intent);
            }
        });

        Sync = (Button) findViewById(R.id.button7);
        Sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View Sync) {
                Intent intent = new Intent(com.example.medicalapp.users.pacienti.Statistici.this, com.example.medicalapp.users.pacienti.Statistici.class);
                intent.putExtra("EXTRA_USER", user + "," + cnp);


                try {
                    // Mobile stats

                    String hr = String.valueOf((int) PrelucrareDate.heartBeats(0));
                    String calories = String.valueOf((int) PrelucrareDate.calories(0));
                    String steps = String.valueOf((int) PrelucrareDate.nrSteps(0));

                    StatisticiAsyncData asyncTask = new StatisticiAsyncData();
                    asyncTask.delegate = this;
                    asyncTask.execute(user, hr , calories, steps);

                    Log.d("hr", hr);
                    Log.d("calories", calories);
                    Log.d("steps", steps);
//

//
                } catch (IOException e) {
                    e.printStackTrace();
                }

                startActivity(intent);
            }
        });

        findViewById(R.id.chart).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Statistici.this, ChartPuls.class);
                intent.putExtra("EXTRA_USER", user + "," + cnp);
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

            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Statistici.this, Profil.class);
            intent.putExtra("EXTRA_USER", user + "," + cnp);
            startActivity(intent);
            return true;
        } else if (id == R.id.item2) {
            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Statistici.this, com.example.medicalapp.users.pacienti.Diagnostic.class);
            intent.putExtra("EXTRA_USER", user + "," + cnp);
            startActivity(intent);
            return true;
        } else if (id == R.id.item3) {
            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Statistici.this, com.example.medicalapp.users.pacienti.Statistici.class);
            intent.putExtra("EXTRA_USER", user + "," + cnp);
            startActivity(intent);
            return true;
//        } else if (id == R.id.item4) {
//            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Statistici.this, com.example.medicalapp.users.pacienti.Anomalii.class);
//            intent.putExtra("EXTRA_USER", user + "," + cnp);
//            startActivity(intent);
//            return true;
        } else if (id == R.id.item5) {
            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Statistici.this, com.example.medicalapp.users.pacienti.IstoricMedical.class);
            intent.putExtra("EXTRA_USER", user + "," + cnp);
            startActivity(intent);
            return true;
        } else if (id == R.id.item6) {
            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Statistici.this, com.example.medicalapp.users.pacienti.Manual_Data_Introduction.class);
            intent.putExtra("EXTRA_USER", user + "," + cnp);
            startActivity(intent);
            return true;
        } else if (id == R.id.item7) {
            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Statistici.this, Login.class);
            intent.putExtra("EXTRA_USER", user + "," + cnp);
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


        Calorii.setText(output.getString("calorii"));

        Somn.setText(output.getString("calitate"));

        Pasi.setText(output.getString("nr_pasi"));

        Puls.setText(output.getString("puls"));

        Oxigen.setText(output.getString("value"));


    }

    @Override
    public void processFinish(Boolean output) throws JSONException {

    }

    @Override
    public void processFinish(ArrayList<String> output) {

    }
}