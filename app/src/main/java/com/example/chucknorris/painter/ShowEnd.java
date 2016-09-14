package com.example.chucknorris.painter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ShowEnd extends AppCompatActivity {

    private double result = 0.0;
    private TextView tv;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_end);

        //get value with the name of calculator to choose right display method
        String controlVal = getIntent().getStringExtra("control");
        controlActivity(controlVal);

    }

    private void controlActivity(String control){
        switch (control){
            case "BMI":
                showResultBMI();
                break;
            case "BMR":
                showResultBMR();
                break;
            case "WHR":
                showResultWHR();
                break;
            case "FAT":
                showResultFAT();
                break;
            case "WEIGHT":
                showResultWeight();
                break;
            default:
                break;
        }
    }

    private void showResultWeight(){
        tv = (TextView) findViewById(R.id.displayMessage);
        tv1 = (TextView) findViewById(R.id.displayMessage2);

        result = getIntent().getDoubleExtra("com.example.chucknorris.painter.WEIGHT", 0);
        tv.setText("" + String.format("%.1f",result));
        tv1.setText("Your perfect weight");
        result = 0.0;
    }

    private void showResultFAT(){
        tv = (TextView) findViewById(R.id.displayMessage);
        tv1 = (TextView) findViewById(R.id.displayMessage2);

        result = getIntent().getDoubleExtra("com.example.chucknorris.painter.FAT", 0);
        tv.setText("" + String.format("%.1f",result));
        tv1.setText("Your percent fat in body");
        result = 0.0;
    }

    private void showResultWHR(){
        tv = (TextView) findViewById(R.id.displayMessage);
        tv1 = (TextView) findViewById(R.id.displayMessage2);

        result = getIntent().getDoubleExtra("com.example.chucknorris.painter.WHR", 0);
        tv.setText("" + String.format("%.3f",result));
        tv1.setText("Your Waist Hip Ratio");
        result = 0.0;
    }

    private void showResultBMR(){
        tv = (TextView) findViewById(R.id.displayMessage);
        tv1 = (TextView) findViewById(R.id.displayMessage2);

        result = getIntent().getDoubleExtra("com.example.chucknorris.painter.BMR", 0);
        tv.setText("" + String.format("%.1f",result));
        tv1.setText("Your Basal Metabolic Rate");
        result = 0.0;
    }

    private void showResultBMI(){
        tv = (TextView) findViewById(R.id.displayMessage);
        tv1 = (TextView) findViewById(R.id.displayMessage2);

        result = getIntent().getDoubleExtra("com.example.chucknorris.painter.ShowEnd", 0);
        tv.setText("" + String.format("%.1f",result));
        tv1.setText("Your Body Mass Index");
        result = 0.0;
    }
}
