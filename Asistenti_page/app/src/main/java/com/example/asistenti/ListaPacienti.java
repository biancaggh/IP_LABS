package com.example.asistenti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListaPacienti extends AppCompatActivity {
    private Button pacient1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacienti);

        pacient1= (Button) findViewById(R.id.button1);
        pacient1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPacient1ProfilPage();
            }
        });
    }
    public void openPacient1ProfilPage(){
        Intent intent= new Intent(ListaPacienti.this,Pacient1.class);
        startActivity(intent);
    }
}