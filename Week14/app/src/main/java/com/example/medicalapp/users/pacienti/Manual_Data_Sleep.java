package com.example.medicalapp.users.pacienti;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.medicalapp.R;
import com.example.medicalapp.utility.AsyncResponse;
import com.example.medicalapp.utility.async_pacienti.ManualDSAsync;

public class Manual_Data_Sleep extends AppCompatActivity implements AsyncResponse {
    private EditText startHourField;
    private EditText startMinutesField;
    private EditText endHourField;
    private EditText endMinutesField;
    private String user;
    private String cnp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual__data__sleep);

        startHourField = (EditText) findViewById(R.id.Field_Start_Hour);
        startHourField.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "23")});

        startMinutesField = (EditText) findViewById(R.id.Field_Start_Minutes);
        startMinutesField.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "59")});

        endHourField = (EditText) findViewById(R.id.Field_End_Hour);
        endHourField.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "23")});

        endMinutesField = (EditText) findViewById(R.id.Field_End_Minutes);
        endMinutesField.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "59")});
        String value = getIntent().getStringExtra("EXTRA_USER");

        int position = value.indexOf(",");
        int length = value.length();

        user = value.substring(0, position);
        cnp = value.substring(position + 1, length);

        System.out.println("--------------------------------------------------");
        System.out.println(user);
        System.out.println("--------------------------------------------------");

    }


    public void verify(View view) {
        int startHourFieldInt, startMinutesFieldInt, endHourFieldInt, endMinutesFieldInt;
        int totalHours, totalMinutes;
        String startHourFieldString, startMinutesFieldString, endHourFieldString, endMinutesFieldString, Transmit;

        startHourFieldString = startHourField.getText().toString();
        startMinutesFieldString = startMinutesField.getText().toString();
        endHourFieldString = endHourField.getText().toString();
        endMinutesFieldString = endMinutesField.getText().toString();

        if (startHourFieldString.length() == 0 || startMinutesFieldString.length() == 0 || endHourFieldString.length() == 0 || endMinutesFieldString.length() == 0) {
            Toast.makeText(com.example.medicalapp.users.pacienti.Manual_Data_Sleep.this, "Please Insert All Fields!", Toast.LENGTH_SHORT).show();
        } else
        {
            startHourFieldInt = Integer.parseInt(startHourFieldString);
            startMinutesFieldInt = Integer.parseInt(startMinutesFieldString);
            endHourFieldInt = Integer.parseInt(endHourFieldString);
            endMinutesFieldInt = Integer.parseInt(endMinutesFieldString);

            if (startMinutesFieldInt > endMinutesFieldInt) {
                endHourFieldInt--;
                endMinutesFieldInt = endMinutesFieldInt + 60;
            }
            totalMinutes = Math.abs(startMinutesFieldInt - endMinutesFieldInt);
            if (startHourFieldInt > endHourFieldInt) {
                totalHours = 24 - startHourFieldInt + endHourFieldInt;
            } else {
                totalHours = endHourFieldInt - startHourFieldInt;
            }
            Transmit = Integer.toString(totalHours * 60 + totalMinutes);

            ManualDSAsync asyncTask = new ManualDSAsync();
            asyncTask.delegate = this;
            asyncTask.execute(user, Transmit);

            System.out.println("-------------------------------Sleep Time---------------------------------");
            Log.d("Hours:", Integer.toString(totalHours));
            Log.d("Minutes:", Integer.toString(totalMinutes));
            System.out.println("--------------------------------------------------------------------------");
        }
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

            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Manual_Data_Sleep.this, Profil.class);
            intent.putExtra("EXTRA_USER", user + "," + cnp);
            startActivity(intent);
            return true;
        } else if (id == R.id.item2) {
            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Manual_Data_Sleep.this, com.example.medicalapp.users.pacienti.Diagnostic.class);
            intent.putExtra("EXTRA_USER", user + "," + cnp);
            startActivity(intent);
            return true;
        } else if (id == R.id.item3) {
            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Manual_Data_Sleep.this, Statistici.class);
            intent.putExtra("EXTRA_USER", user + "," + cnp);
            startActivity(intent);
            return true;
        } else if (id == R.id.item4) {
            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Manual_Data_Sleep.this, com.example.medicalapp.users.pacienti.Anomalii.class);
            intent.putExtra("EXTRA_USER", user + "," + cnp);
            startActivity(intent);
            return true;
        } else if (id == R.id.item5) {
            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Manual_Data_Sleep.this, com.example.medicalapp.users.pacienti.IstoricMedical.class);
            intent.putExtra("EXTRA_USER", user + "," + cnp);
            startActivity(intent);
            return true;
        } else if (id == R.id.item6) {
            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Manual_Data_Sleep.this, com.example.medicalapp.users.pacienti.Manual_Data_Introduction.class);
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

    }

    @Override
    public void processFinish(Boolean output) throws JSONException {
        if(output==true)
        {
            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Manual_Data_Sleep.this, com.example.medicalapp.users.pacienti.Manual_Data_Introduction.class);
            intent.putExtra("EXTRA_USER", user + "," + cnp);
            Toast.makeText(com.example.medicalapp.users.pacienti.Manual_Data_Sleep.this,"Sleep file sent!",Toast.LENGTH_SHORT).show();
            startActivity(intent);

        }
        else
        {
            Toast.makeText(com.example.medicalapp.users.pacienti.Manual_Data_Sleep.this,"Sleep file not sent!", Toast.LENGTH_SHORT).show();
        }
    }
}