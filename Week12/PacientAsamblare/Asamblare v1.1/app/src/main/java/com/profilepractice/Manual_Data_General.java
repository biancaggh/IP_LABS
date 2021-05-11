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
import utility.ManualDOAsync;

public class Manual_Data_General extends AppCompatActivity implements AsyncResponse {
    private EditText Field1;
    private EditText Field2;
    private EditText Field3;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual__data__general);
        user = getIntent().getStringExtra("EXTRA_USER");

        Field1 = (EditText) findViewById(R.id.Field_Hearth_Rate_Level);
        Field1.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "200")});
        Field2 = (EditText) findViewById(R.id.Field_Steps_Count);
        Field3 = (EditText) findViewById(R.id.Field_Calories_Burned);
    }

    public void Verify(View view)
    {
        String Value1 = Field1.getText().toString();
        String Value2 = Field2.getText().toString();
        String Value3 = Field3.getText().toString();

        if (Value1.length() == 0 || Value2.length() == 0 || Value3.length() == 0)
        {
            Toast.makeText(Manual_Data_General.this, "Please Complete All Fields!", Toast.LENGTH_SHORT).show();
        } else {
            ManualDOAsync asyncTask = new ManualDOAsync();
            asyncTask.delegate = this;
            asyncTask.execute(user, Value1,Value3,Value2);

            Log.d("Hearth:", Value1);
            Log.d("Steps:", Value2);
            Log.d("Calories:", Value3);

            try {
                Thread.sleep(50);
            } catch (InterruptedException Exception){
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

            Intent intent = new Intent(Manual_Data_General.this, Profil.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item2) {
            Intent intent = new Intent(Manual_Data_General.this, Diagnostic.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item3) {
            Intent intent = new Intent(Manual_Data_General.this, Statistici.class);
            //intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        }else if (id == R.id.item4) {
            Intent intent = new Intent(Manual_Data_General.this, Anomalii.class);
            //intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item5) {
            Intent intent = new Intent(Manual_Data_General.this, IstoricMedical.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item6) {
            Intent intent = new Intent(Manual_Data_General.this, Manual_Data_Introduction.class);
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
    public void processFinish(Boolean output) throws JSONException{
        if(output==true)
        {
            Intent intent = new Intent(Manual_Data_General.this, Manual_Data_Introduction.class);
            intent.putExtra("EXTRA_USER", user);
            Toast.makeText(Manual_Data_General.this,"General Data Sent!",Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
        else
        {
            Toast.makeText(Manual_Data_General.this,"General Data Not Sent!", Toast.LENGTH_SHORT).show();
        }
    }

}