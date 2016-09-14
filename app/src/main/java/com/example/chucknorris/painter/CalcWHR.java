package com.example.chucknorris.painter;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class CalcWHR extends AppCompatActivity {

    private double setWaist = 0.0;
    private double setHip = 0.0;
    private boolean alertBtnFlag = false;

    private final String MESSAGE = "com.example.chucknorris.painter.WHR";
    private final String CONTROL = "control";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_whr);
        start();
    }

    private double getValueFromRadioButton(){
        RadioGroup rg = (RadioGroup) findViewById(R.id.whrRadio);
        int radioBtn = rg.getCheckedRadioButtonId();

        switch(radioBtn){
            //female
            case 2131558511:
                return 0.9999;
            //male
            case 2131558512:
                return 1;
            default:
                return 1;
        }
    }

    private void start(){
        Button whrBtn = (Button) findViewById(R.id.whrCalculateBtn);
        assert whrBtn != null;
        whrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getDimVar()){
                    Intent i = new Intent(CalcWHR.this, ShowEnd.class);
                    i.putExtra(CONTROL, "WHR");
                    i.putExtra(MESSAGE, calcWhr(setWaist, setHip));
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
            EditText getWaist = (EditText) findViewById(R.id.whrGetWaist);
            EditText getHip = (EditText) findViewById(R.id.whrGetHip);

            String tempHeight = getWaist.getText().toString();
            String tempWeight = getHip.getText().toString();

            setWaist = Double.parseDouble(tempHeight);
            setHip = Double.parseDouble(tempWeight);

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

    private double calcWhr(double waist, double hip){
        double whr = (waist/hip)*getValueFromRadioButton();
        return whr;
    }
}
