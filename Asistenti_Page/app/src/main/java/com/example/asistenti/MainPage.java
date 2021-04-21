package com.example.asistenti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainPage extends AppCompatActivity {
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

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
        Intent intent=new Intent(MainPage.this,ListaPacienti.class);
        startActivity(intent);
    }

    public void openAddPacient(){
        Intent intent=new Intent(MainPage.this,AdaugaPacient.class);
        startActivity(intent);
    }

    public void openOrarGarda(){
        Intent intent=new Intent(MainPage.this,OrarGarda.class);
        startActivity(intent);
    }

    public void openAsistentProfil(){
        Intent intent=new Intent(MainPage.this,ProfilAsistent.class);
        startActivity(intent);
    }
}