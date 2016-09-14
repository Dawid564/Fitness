package com.example.chucknorris.painter;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class CalcWEIGHT extends AppCompatActivity {

    //private double setWaist = 0.0;
    private double setHeight = 0.0;
    private boolean alertBtnFlag = false;

    private final String MESSAGE = "com.example.chucknorris.painter.WEIGHT";
    private final String CONTROL = "control";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_weight);
        start();
    }

    private double getValueFromRadioButton(){
        RadioGroup rg = (RadioGroup) findViewById(R.id.weightRadio);
        int radioBtn = rg.getCheckedRadioButtonId();
        switch(radioBtn){
            //female
            case 2131558512:
                return 0.85;
            //male
            case 2131558513:
                return 0.9;
            default:
                return 1;
        }
    }

    private void start(){
        Button whrBtn = (Button) findViewById(R.id.weightCalculateBtn);
        assert whrBtn != null;
        whrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getDimVar()){
                    Intent i = new Intent(CalcWEIGHT.this, ShowEnd.class);
                    i.putExtra(CONTROL, "WEIGHT");
                    i.putExtra(MESSAGE, calcFat(setHeight));
                    startActivity(i);
                }else {
                    //wait until user fill all gap
                }
            }
        });
    }

    //get dimension from user and check if it is not empty
    private boolean getDimVar(){
        try{
            EditText getWaist = (EditText) findViewById(R.id.weightGetHeight);

            String tempHeight = getWaist.getText().toString();

            setHeight = Double.parseDouble(tempHeight);

            //Toast.makeText(this, "Fill all fields " + setHeight+ " " + setWeight + " " + setAge, Toast.LENGTH_LONG).show();
            return true;
        }catch (Exception e){
            createAlertButton();
            return false;
        }
    }

    //create textView for inform user he should fill gaps
    private void createAlertButton(){
        if(alertBtnFlag == false){
            TextView tv = new TextView(this);
            tv.setTextColor(Color.parseColor("#FF0000"));
            tv.setText("Fill all fields");
            alertBtnFlag = true;
            LinearLayout ll = (LinearLayout) findViewById(R.id.emergencyMessage);
            ll.addView(tv);
        }
    }

    private double calcFat(double height){
        double fat = (height - 100)*getValueFromRadioButton();
        return fat;
    }
}
