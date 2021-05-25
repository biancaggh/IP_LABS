//package com.example.medicalapp.users.pacienti;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.medicalapp.R;
//import com.example.medicalapp.users.Login;
//
//public class Anomalii extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_anomalii);
//
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.meniu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.item1) {
//
//            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Anomalii.this, Profil.class);
//            startActivity(intent);
//            return true;
//        }else
//        if (id == R.id.item2) {
//            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Anomalii.this, com.example.medicalapp.users.pacienti.Diagnostic.class);
//            startActivity(intent);
//            return true;
//        }else
//        if (id == R.id.item3) {
//            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Anomalii.this, Statistici.class);
//            startActivity(intent);
//            return true;
//        }else if (id == R.id.item4) {
//            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Anomalii.this, com.example.medicalapp.users.pacienti.Anomalii.class);
//            startActivity(intent);
//            return true;
//        }else
//        if (id == R.id.item5) {
//            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Anomalii.this, IstoricMedical.class);
//            startActivity(intent);
//            return true;
//        }
//        else if (id == R.id.item6) {
//            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Anomalii.this, com.example.medicalapp.users.pacienti.Manual_Data_Introduction.class);
//            startActivity(intent);
//            return true;
//        } else if (id == R.id.item7) {
//            Intent intent = new Intent(com.example.medicalapp.users.pacienti.Anomalii.this, Login.class);
//            startActivity(intent);
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//}