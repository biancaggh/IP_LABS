package com.example.medicalapp.users.asistenti;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicalapp.R;

public class AdaugaPacient extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private static final String[] paths = {"M", "F", "Altul"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adauga_pacient);

        EditText numeEditText = (EditText) findViewById(R.id.nume);
        String Nume = numeEditText.getText().toString();

        EditText prenumeEditText = (EditText) findViewById(R.id.prenume);
        String Prenume = prenumeEditText.getText().toString();

        EditText cnpEditText = (EditText) findViewById(R.id.id_pac);
        String CNP = cnpEditText.getText().toString();

        EditText adresaEditText = (EditText) findViewById(R.id.adresa);
        String adresa = adresaEditText.getText().toString();

        EditText data_nEditText = (EditText) findViewById(R.id.data_nas);
        String data_n = data_nEditText.getText().toString();

        EditText heightEditText = (EditText) findViewById(R.id.height);
        String height = heightEditText.getText().toString();

        EditText weightEditText = (EditText) findViewById(R.id.weight);
        String weight = weightEditText.getText().toString();


        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(com.example.medicalapp.users.asistenti.AdaugaPacient.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void submitbuttonHandler(View view) {
        //Decide what happens when the user clicks the submit button

    }

}