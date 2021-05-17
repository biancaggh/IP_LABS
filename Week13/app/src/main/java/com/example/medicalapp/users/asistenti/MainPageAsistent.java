package com.example.medicalapp.users.asistenti;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicalapp.R;

public class MainPageAsistent extends AppCompatActivity {
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_a);

        user = getIntent().getStringExtra("EXTRA_USER");

        button1= (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openListaPacienti();
            }
        });

        button2= (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddPacient();
            }
        });

        button3= (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrarGarda();
            }
        });

        button4= (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAsistentProfil();
            }
        });
    }
    public void openListaPacienti(){
        Intent intent=new Intent(com.example.medicalapp.users.asistenti.MainPageAsistent.this, com.example.medicalapp.users.asistenti.ListaPacienti.class);
        intent.putExtra("EXTRA_USER", user);
        startActivity(intent);
    }

    public void openAddPacient(){
        Intent intent=new Intent(com.example.medicalapp.users.asistenti.MainPageAsistent.this,AdaugaPacient.class);
        intent.putExtra("EXTRA_USER", user);
        startActivity(intent);
    }

    public void openOrarGarda(){
        Intent intent=new Intent(com.example.medicalapp.users.asistenti.MainPageAsistent.this,OrarGarda.class);
        intent.putExtra("EXTRA_USER", user);
        startActivity(intent);
    }

    public void openAsistentProfil(){
        Intent intent=new Intent(com.example.medicalapp.users.asistenti.MainPageAsistent.this, com.example.medicalapp.users.asistenti.ProfilAsistent.class);
        intent.putExtra("EXTRA_USER", user);
        startActivity(intent);
    }
}