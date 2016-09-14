package com.example.chucknorris.painter;

import android.content.Intent;
import android.net.nsd.NsdManager;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class CalcBMI extends AppCompatActivity implements Results{

    private double setHeight;
    private double setWeight;
    private RadioGroup radioGroup;
    private int selectedId;
    private double genderVal = 0.0;
    private TextView tv;
    private double res = 0.0;
    private int carryVal = 0;

    public final String MESSAGE = "com.example.chucknorris.painter.ShowEnd";
    public final String CONTROL = "control";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_bmi);
        tv = (TextView)findViewById(R.id.tempResult);
    }

    @Override
    public double showResult (int getResult){
        return 0;
    }

    public void bmiCalcBtn(View v){
        getDimVar();
        double radioVal = getRadioVal();

        if(verification(carryVal)){
            res = solveBmi(setHeight, setWeight, radioVal);
            Intent i = new Intent(this, ShowEnd.class);
            i.putExtra(MESSAGE, res);
            i.putExtra(CONTROL, "BMI");
            startActivity(i);
            //tv.setText("" + res);
        }else{
            //do nothing
        }
    }

    private boolean verification(int i){
        if(i!=1){
            return false;
        }else{
            return true;
        }
    }

    private void getDimVar(){
        try{
            EditText getHeight = (EditText) findViewById(R.id.bmiGetHeight);
            EditText getWeight = (EditText) findViewById(R.id.bmiGetWeight);

            String tempHeight = getHeight.getText().toString();
            String tempWeight = getWeight.getText().toString();

            setHeight = Double.parseDouble(tempHeight);
            setWeight = Double.parseDouble(tempWeight);

            carryVal = 1;
        }catch (Exception e){
            //Toast.makeText(this, "Fill all fields " + setHeight+ " " + setWeight, Toast.LENGTH_LONG).show();
            tv.setText("Fill all fields");
            carryVal = 0;
        }
    }

    // get value from radio button
    private double getRadioVal(){
        radioGroup = (RadioGroup) findViewById(R.id.bmiRadio);
        selectedId = radioGroup.getCheckedRadioButtonId();

        switch (selectedId){
            //female
            case 2131492946:
                genderVal = 0.9999;
                break;
            //male
            case 2131492947:
                genderVal = 1;
                break;
            default:
                genderVal = 1;
                break;
        }
        return genderVal;
    }


    private double solveBmi(double height, double weight, double gender){

        double heightMet = height/100;
        double heightMul = heightMet*heightMet;
        double bmi = weight/heightMul*gender;

        return bmi;
    }
}
