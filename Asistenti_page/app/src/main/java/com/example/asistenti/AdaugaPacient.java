package com.example.asistenti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class AdaugaPacient extends AppCompatActivity {

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
    }
    public void radioButtonhandler(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.male:
                if (checked)
                    break;
            case R.id.female:
                if (checked)
                    break;
            case R.id.other:
                if (checked)
                    // Ninjas rule
                    break;
        }
    }

    public void submitbuttonHandler(View view) {
        //Decide what happens when the user clicks the submit button

    }
}