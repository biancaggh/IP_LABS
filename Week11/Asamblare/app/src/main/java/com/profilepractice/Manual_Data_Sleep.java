package com.profilepractice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import java.io.FileOutputStream;
import java.io.IOException;

public class Manual_Data_Sleep extends AppCompatActivity {
    int startHour, startMinutes, endHour, endMinutes;
    private EditText Start_Hour;
    private EditText Start_Minutes;
    private EditText End_Hour;
    private EditText End_Minutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual__data__sleep);

        Start_Hour = (EditText) findViewById(R.id.Field_Start_Hour);

        Start_Minutes = (EditText) findViewById(R.id.Field_Start_Minutes);

        End_Hour = (EditText) findViewById(R.id.Field_End_Hour);

        End_Minutes = (EditText) findViewById(R.id.Field_End_Minutes);


    }


    public void Verify(View view) {
        startHour = Integer.parseInt(Start_Hour.getText().toString());
        startMinutes = Integer.parseInt(Start_Minutes.getText().toString());
        endHour = Integer.parseInt(End_Hour.getText().toString());
        endMinutes = Integer.parseInt(End_Minutes.getText().toString());

        int hours, minutes,Transmit;

        if (Start_Hour.getText().toString().trim().length() == 0 || Start_Minutes.getText().toString().trim().length() == 0) {
            Toast.makeText(Manual_Data_Sleep.this, "Insert the bed time!", Toast.LENGTH_SHORT).show();
        } else if (End_Hour.getText().toString().trim().length() == 0 || End_Minutes.getText().toString().trim().length() == 0) {
            Toast.makeText(Manual_Data_Sleep.this, "Insert the wake up time!", Toast.LENGTH_SHORT).show();
        } else if (startHour > 24 || startHour <= 0 || endHour > 24 || endHour <= 0 ||
                startMinutes > 59 || startMinutes < 0 || endMinutes > 59 || endMinutes < 0) {
            Toast.makeText(Manual_Data_Sleep.this, "Tha data inserted is incorrect!", Toast.LENGTH_SHORT).show();
        } else {
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

            JSONObject Obj = new JSONObject();
            try {
                Obj.put("Sleep",Transmit);

                String jsonString = Obj.toString();

                FileOutputStream Fos = this.openFileOutput("Sleep.json", Context.MODE_PRIVATE);
                Fos.write(jsonString.getBytes());
                Fos.close();

                Log.d("Hours:", Integer.toString(hours));
                Log.d("Minutes:", Integer.toString(minutes));
                Log.d("JSON:", jsonString);
                Log.d("Location:", Manual_Data_Sleep.this.getFilesDir().getAbsolutePath());
                Toast.makeText(Manual_Data_Sleep.this,"File Created!",Toast.LENGTH_SHORT).show();
                Thread.sleep(1000);
                Intent intent = new Intent(Manual_Data_Sleep.this, Manual_Data_Introduction.class);
                startActivity(intent);
            }   catch (IOException | JSONException |InterruptedException Exception){
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
            startActivity(intent);
            return true;
        } else if (id == R.id.item2) {
            Intent intent = new Intent(Manual_Data_Sleep.this, Diagnostic.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.item3) {
            Intent intent = new Intent(Manual_Data_Sleep.this, Statistici.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.item4) {
            Intent intent = new Intent(Manual_Data_Sleep.this, Anomalii.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.item5) {
            Intent intent = new Intent(Manual_Data_Sleep.this, IstoricMedical.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.item6) {
            Intent intent = new Intent(Manual_Data_Sleep.this, Manual_Data_Introduction.class);
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