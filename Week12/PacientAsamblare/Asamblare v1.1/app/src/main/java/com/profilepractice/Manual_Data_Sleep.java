package com.profilepractice;

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

import utility.AsyncResponse;
import utility.ManualDSAsync;

public class Manual_Data_Sleep extends AppCompatActivity  implements AsyncResponse {
    int startHour, startMinutes, endHour, endMinutes;
    private EditText Start_Hour;
    private EditText Start_Minutes;
    private EditText End_Hour;
    private EditText End_Minutes;
    private String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual__data__sleep);

        Start_Hour = (EditText) findViewById(R.id.Field_Start_Hour);
        Start_Hour.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "23")});

        Start_Minutes = (EditText) findViewById(R.id.Field_Start_Minutes);
        Start_Minutes.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "59")});

        End_Hour = (EditText) findViewById(R.id.Field_End_Hour);
        End_Hour.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "23")});

        End_Minutes = (EditText) findViewById(R.id.Field_End_Minutes);
        End_Minutes.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "59")});
        user = getIntent().getStringExtra("EXTRA_USER");

    }


    public void Verify(View view) {
        int hours, minutes,Transmit;
        String Value1,Value2,Value3,Value4;
        Value1 = Start_Hour.getText().toString();
        Value2 = Start_Minutes.getText().toString();
        Value3 = End_Hour.getText().toString();
        Value4 = End_Minutes.getText().toString();

        if (Value1.length() == 0 || Value2.length() == 0 || Value3.length() == 0 || Value4.length() == 0) {
            Toast.makeText(Manual_Data_Sleep.this, "Please Insert All Fields!", Toast.LENGTH_SHORT).show();
        } else
        {
            startHour = Integer.parseInt(Value1);
            startMinutes = Integer.parseInt(Value2);
            endHour = Integer.parseInt(Value3);
            endMinutes = Integer.parseInt(Value4);

            if (startMinutes > endMinutes) {
                endHour--;
                endMinutes = endMinutes + 60;
            }
            minutes = Math.abs(startMinutes - endMinutes);
            if (startHour > endHour) {
                hours = 24 - startHour + endHour;
            } else {
                hours = endHour - startHour;
            }
            Transmit = hours * 60 + minutes;

            try {
                ManualDSAsync asyncTask = new ManualDSAsync();
                asyncTask.delegate = this;
                asyncTask.execute(user, String.valueOf(Transmit));

                Log.d("Hours:", Integer.toString(hours));
                Log.d("Minutes:", Integer.toString(minutes));

                Thread.sleep(50);
            }   catch (InterruptedException Exception){
                Exception.printStackTrace();
            }


            Toast.makeText(Manual_Data_Sleep.this, "You have slept " + Integer.toString(hours) + " hours and " + Integer.toString(minutes) + " minutes.", Toast.LENGTH_SHORT).show();
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

            Intent intent = new Intent(Manual_Data_Sleep.this, Profil.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        } else if (id == R.id.item2) {
            Intent intent = new Intent(Manual_Data_Sleep.this, Diagnostic.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        } else if (id == R.id.item3) {
            Intent intent = new Intent(Manual_Data_Sleep.this, Statistici.class);
            //intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        } else if (id == R.id.item4) {
            Intent intent = new Intent(Manual_Data_Sleep.this, Anomalii.class);
            //intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        } else if (id == R.id.item5) {
            Intent intent = new Intent(Manual_Data_Sleep.this, IstoricMedical.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        } else if (id == R.id.item6) {
            Intent intent = new Intent(Manual_Data_Sleep.this, Manual_Data_Introduction.class);
            intent.putExtra("EXTRA_USER", user);
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
            Intent intent = new Intent(Manual_Data_Sleep.this, Manual_Data_Introduction.class);
            intent.putExtra("EXTRA_USER", user);
            Toast.makeText(Manual_Data_Sleep.this,"Sleep file sent!",Toast.LENGTH_SHORT).show();
            startActivity(intent);

        }
        else
        {
            Toast.makeText(Manual_Data_Sleep.this,"Sleep file not sent!", Toast.LENGTH_SHORT).show();
        }
    }
}