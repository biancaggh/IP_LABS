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


public class Manual_Data_Sport extends AppCompatActivity {
    private EditText Field1;
    private EditText Field2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual__data__sport);

        Field1 = (EditText) findViewById(R.id.Field_Steps_Count);

        Field2 = (EditText) findViewById(R.id.Field_Calories_Burned);
    }


    public void Verify(View view) {
        String Value1 = Field1.getText().toString();
        String Value2 = Field2.getText().toString();

        if (Value1.length() == 0 || Value2.length() == 0) {
            if (Value1.length() == 0 && Value2.length() == 0) {
                Toast.makeText(Manual_Data_Sport.this, "Insert the steps count and the calories burned!", Toast.LENGTH_SHORT).show();
            }
            else if (Value1.length() == 0) {
                Toast.makeText(Manual_Data_Sport.this, "Insert the steps count!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(Manual_Data_Sport.this, "Insert the calories burned!", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            int Intreg1 = Integer.parseInt(Value1);
            int Intreg2 = Integer.parseInt(Value2);

            JSONObject Obj = new JSONObject();
            try {
                Obj.put("Steps",Intreg1);
                Obj.put("Calories",Intreg2);

                String jsonString = Obj.toString();

                FileOutputStream Fos = this.openFileOutput("Sport.json", Context.MODE_PRIVATE);
                Fos.write(jsonString.getBytes());
                Fos.close();

                Log.d("JSON:", jsonString);
                Log.d("Location:", Manual_Data_Sport.this.getFilesDir().getAbsolutePath());
                Toast.makeText(Manual_Data_Sport.this,"File Created!",Toast.LENGTH_SHORT).show();
                Thread.sleep(1000);
                Intent intent = new Intent(Manual_Data_Sport.this, Manual_Data_Introduction.class);
                startActivity(intent);
            }   catch (IOException|JSONException|InterruptedException Exception){
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

            Intent intent = new Intent(Manual_Data_Sport.this, Profil.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item2) {
            Intent intent = new Intent(Manual_Data_Sport.this, Diagnostic.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item3) {
            Intent intent = new Intent(Manual_Data_Sport.this, Statistici.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.item4) {
            Intent intent = new Intent(Manual_Data_Sport.this, Anomalii.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item5) {
            Intent intent = new Intent(Manual_Data_Sport.this, IstoricMedical.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item6) {
            Intent intent = new Intent(Manual_Data_Sport.this, Manual_Data_Introduction.class);
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