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
import com.example.medicalapp.utility.async_pacienti.ManualDOAsync;

public class Manual_Data_General extends AppCompatActivity implements AsyncResponse {
    private EditText hearthField;
    private EditText stepsField;
    private EditText caloriesField;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual__data__general);
        user = getIntent().getStringExtra("EXTRA_USER");

        hearthField = (EditText) findViewById(R.id.Field_Hearth_Rate_Level);
        hearthField.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "200")});
        stepsField = (EditText) findViewById(R.id.Field_Steps_Count);
        caloriesField = (EditText) findViewById(R.id.Field_Calories_Burned);
    }

    public void verify(View view)
    {
        String hearthFieldString = hearthField.getText().toString();
        String stepsFieldString = stepsField.getText().toString();
        String caloriesFieldString = caloriesField.getText().toString();

        if (hearthFieldString.length() == 0 || stepsFieldString.length() == 0 || caloriesFieldString.length() == 0)
        {
            Toast.makeText(com.example.medicalapp.users.pacienti.Manual_Data_General.this, "Please Complete All Fields!", Toast.LENGTH_SHORT).show();
        } else {
            ManualDOAsync asyncTask = new ManualDOAsync();
            asyncTask.delegate = this;
            asyncTask.execute(user, hearthFieldString,caloriesFieldString,stepsFieldString);

            System.out.println("-------------------------------General Levels---------------------------------");
            Log.d("Hearth:", hearthFieldString);
            Log.d("Steps:", stepsFieldString);
            Log.d("Calories:", caloriesFieldString);
            System.out.println("------------------------------------------------------------------------------");
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

            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Manual_Data_General.this, Profil.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item2) {
            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Manual_Data_General.this, com.example.medicalapp.users.pacienti.Diagnostic.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item3) {
            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Manual_Data_General.this, Statistici.class);
            //intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        }else if (id == R.id.item4) {
            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Manual_Data_General.this, Anomalii.class);
            //intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item5) {
            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Manual_Data_General.this, IstoricMedical.class);
            intent.putExtra("EXTRA_USER", user);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item6) {
            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Manual_Data_General.this, com.example.medicalapp.users.pacienti.Manual_Data_Introduction.class);
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
            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Manual_Data_General.this, com.example.medicalapp.users.pacienti.Manual_Data_Introduction.class);
            intent.putExtra("EXTRA_USER", user);
            Toast.makeText(com.example.medicalapp.users.pacienti.Manual_Data_General.this,"General Data Sent!",Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
        else
        {
            Toast.makeText(com.example.medicalapp.users.pacienti.Manual_Data_General.this,"General Data Not Sent!", Toast.LENGTH_SHORT).show();
        }
    }

}