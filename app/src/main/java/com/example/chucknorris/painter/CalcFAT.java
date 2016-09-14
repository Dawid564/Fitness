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

public class CalcFAT extends AppCompatActivity {

    private double setWaist = 0.0;
    private double setWeight = 0.0;
    private boolean alertBtnFlag = false;

    private final String MESSAGE = "com.example.chucknorris.painter.FAT";
    private final String CONTROL = "control";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_fat);
        start();
    }

    private double getValueFromRadioButton(){
        RadioGroup rg = (RadioGroup) findViewById(R.id.fatRadio);
        int radioBtn = rg.getCheckedRadioButtonId();

        switch(radioBtn){
            //female
            case 2131558512:
                return 76.76;
            //male
            case 2131558513:
                return 98.42;
            default:
                return 1;
        }
    }

    private void start(){
        Button whrBtn = (Button) findViewById(R.id.fatCalculateBtn);
        assert whrBtn != null;
        whrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getDimVar()){
                    Intent i = new Intent(CalcFAT.this, ShowEnd.class);
                    i.putExtra(CONTROL, "FAT");
                    i.putExtra(MESSAGE, calcFat(setWaist, setWeight));
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
            EditText getWaist = (EditText) findViewById(R.id.fatGetWaist);
            EditText getWeight = (EditText) findViewById(R.id.fatGetWeight);

            String tempHeight = getWaist.getText().toString();
            String tempWeight = getWeight.getText().toString();

            setWaist = Double.parseDouble(tempHeight);
            setWeight = Double.parseDouble(tempWeight);

            //Toast.makeText(this, "Fill all fields " + setHeight+ " " + setWeight + " " + setAge, Toast.LENGTH_LONG).show();
            return true;
        }catch (Exception e){
            createAlertButton();
            //Toast.makeText(this, "Fill all fields ", Toast.LENGTH_LONG).show();
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

    private double calcFat(double waist, double weight){
        double fat = ((((4.15*waist)/2.54) - (0.082*weight*2.2) - getValueFromRadioButton())/(weight*2.2))*100;
        //double fat = getValueFromRadioButton();
        return fat;
    }
}
