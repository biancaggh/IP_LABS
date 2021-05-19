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

public class Manual_Data_Oxygen extends AppCompatActivity implements AsyncResponse {
    private EditText oxygenField;
    private String user;
    private String cnp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual__data__oxygen);
        String value = getIntent().getStringExtra("EXTRA_USER");

        int position = value.indexOf(",");
        int length = value.length();

        user = value.substring(0, position);
        cnp = value.substring(position + 1, length);

        System.out.println("--------------------------------------------------");
        System.out.println(user);
        System.out.println("--------------------------------------------------");

        oxygenField = (EditText) findViewById(R.id.Field_Oxygen_Level);
        oxygenField.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "100")});
    }


    public void verify(View view) {
        String oxygenFieldString = oxygenField.getText().toString();

        if(oxygenFieldString.length() == 0) Toast.makeText(com.example.medicalapp.users.pacienti.Manual_Data_Oxygen.this, "Insert the oxygen level!", Toast.LENGTH_SHORT).show();
        else {

            ManualDOAsync asyncTask = new ManualDOAsync();
            asyncTask.delegate = this;
            asyncTask.execute(user, oxygenFieldString);

            System.out.println("-------------------------------Oxygen Level---------------------------------");
            Log.d("Hours:", oxygenFieldString);
            System.out.println("----------------------------------------------------------------------------");
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

            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Manual_Data_Oxygen.this, Profil.class);
            intent.putExtra("EXTRA_USER", user + "," + cnp);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item2) {
            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Manual_Data_Oxygen.this, com.example.medicalapp.users.pacienti.Diagnostic.class);
            intent.putExtra("EXTRA_USER", user + "," + cnp);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item3) {
            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Manual_Data_Oxygen.this, Statistici.class);
            intent.putExtra("EXTRA_USER", user + "," + cnp);
            startActivity(intent);
            return true;
        }else if (id == R.id.item4) {
            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Manual_Data_Oxygen.this, Anomalii.class);
            intent.putExtra("EXTRA_USER", user + "," + cnp);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item5) {
            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Manual_Data_Oxygen.this, IstoricMedical.class);
            intent.putExtra("EXTRA_USER", user + "," + cnp);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item6) {
            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Manual_Data_Oxygen.this, com.example.medicalapp.users.pacienti.Manual_Data_Introduction.class);
            intent.putExtra("EXTRA_USER", user + "," + cnp);
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
            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Manual_Data_Oxygen.this, com.example.medicalapp.users.pacienti.Manual_Data_Introduction.class);
            intent.putExtra("EXTRA_USER", user + "," + cnp);
            Toast.makeText(com.example.medicalapp.users.pacienti.Manual_Data_Oxygen.this,"Oxigen Data Sent!",Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
        else
        {
            Toast.makeText(com.example.medicalapp.users.pacienti.Manual_Data_Oxygen.this,"Oxigen Data Not Sent!", Toast.LENGTH_SHORT).show();
        }
    }
}