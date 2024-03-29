package com.profilepractice;

import androidx.appcompat.app.AppCompatActivity;

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

import org.json.JSONException;
import org.json.JSONObject;
import utility.AsyncResponse;
import utility.ManualDOAsync;
import utility.ProfilAsync;

import java.io.FileOutputStream;
import java.io.IOException;

public class Manual_Data_Oxygen extends AppCompatActivity  implements AsyncResponse {
    private EditText Field;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual__data__oxygen);
        Field = (EditText) findViewById(R.id.Field_Oxygen_Level);
        user = getIntent().getStringExtra("EXTRA_USER");


    }


    public void Verify(View view) {
        String Value1 = Field.getText().toString();

        if(Value1.length() == 0) Toast.makeText(Manual_Data_Oxygen.this, "Insert the oxygen level!", Toast.LENGTH_SHORT).show();
        else {
            JSONObject Obj = new JSONObject();
            try {
                ManualDOAsync asyncTask = new ManualDOAsync();
                asyncTask.delegate = this;
                asyncTask.execute(user, Value1);
                int Intreg1 = Integer.parseInt(Value1);

                Obj.put("Oxigen",Intreg1);

                String jsonString = Obj.toString();

                FileOutputStream Fos = this.openFileOutput("Oxigen.json", Context.MODE_PRIVATE);
                Fos.write(jsonString.getBytes());
                Fos.close();

                Log.d("JSON:", jsonString);
                Log.d("Location:", Manual_Data_Oxygen.this.getFilesDir().getAbsolutePath());
                Toast.makeText(Manual_Data_Oxygen.this,"File Created!",Toast.LENGTH_SHORT).show();
                Thread.sleep(50);

            }   catch (IOException | JSONException |InterruptedException Exception){
                Exception.printStackTrace();
            }
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

            Intent intent = new Intent(Manual_Data_Oxygen.this, Profil.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item2) {
            Intent intent = new Intent(Manual_Data_Oxygen.this, Diagnostic.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item3) {
            Intent intent = new Intent(Manual_Data_Oxygen.this, Statistici.class);
            //intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        }else if (id == R.id.item4) {
            Intent intent = new Intent(Manual_Data_Oxygen.this, Anomalii.class);
            //intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item5) {
            Intent intent = new Intent(Manual_Data_Oxygen.this, IstoricMedical.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item6) {
            Intent intent = new Intent(Manual_Data_Oxygen.this, Manual_Data_Introduction.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        }
        //else
//        if (id == R.id.item5) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void processFinish(Integer output) {

    }

    @Override
    public void processFinish(JSONObject output) throws JSONException {

    }
    @Override
    public void processFinish(Boolean output) throws JSONException{
        if(output==true)
        {
            Intent intent = new Intent(Manual_Data_Oxygen.this, Manual_Data_Introduction.class);
            intent.putExtra("EXTRA_USER", user);
            Toast.makeText(Manual_Data_Oxygen.this,"Oxygen file sent!",Toast.LENGTH_SHORT).show();
            startActivity(intent);

        }
        else
        {
            Toast.makeText(Manual_Data_Oxygen.this,"Oxigen file not sent!", Toast.LENGTH_SHORT).show();
        }
    }
}