package com.example.chucknorris.painter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void bmiClick(View v){
        Intent i = new Intent(this, CalcBMI.class);
        startActivity(i);
    }

    public void bmrClick (View v){
        Intent i = new Intent(this, CalcBMR.class);
        startActivity(i);
    }

    public void whrClick (View v){
        Intent i = new Intent(this, CalcWHR.class);
        startActivity(i);
    }

    public void fatClick (View v){
        Intent i = new Intent(this, CalcFAT.class);
        startActivity(i);
    }

    public void weightClick (View v){
        Intent i = new Intent(this, CalcWEIGHT.class);
        startActivity(i);
    }
}
