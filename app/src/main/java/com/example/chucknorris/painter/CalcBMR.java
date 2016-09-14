package com.example.chucknorris.painter;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class CalcBMR extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private double setHeight = 0.0;
    private double setWeight = 0.0;
    private double setAge = 0.0;
    private double setActivity = 0.0;
    private TextView uActivity;

    public final String MESSAGE = "com.example.chucknorris.painter.BMR";
    public final String CONTROL = "control";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_bmr);
        setArrayList();
        start();
    }

    // first function, listener for button
    public void start(){
        Button btn = (Button) findViewById(R.id.bmrCalculateBtn);
        assert btn != null;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getDimVar()){
                    try {
                        if(getValueFromRadioGroup().equals("male")){
                            //make calculations for male
                            Intent i = new Intent(CalcBMR.this, ShowEnd.class);
                            i.putExtra(MESSAGE, solveBmrMale(setHeight, setWeight, setAge, setActivity));
                            i.putExtra(CONTROL, "BMR");
                            startActivity(i);
                        }else{
                            //make calculations for female
                            Intent i = new Intent(CalcBMR.this, ShowEnd.class);
                            i.putExtra(MESSAGE, solveBmrFemale(setHeight, setWeight, setAge, setActivity));
                            i.putExtra(CONTROL, "BMR");
                            startActivity(i);
                        }
                    }catch (Exception e){
                        Toast.makeText(CalcBMR.this, "not working", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public double solveBmrMale(double height, double weight, double age, double activity){
        double BMR;

        BMR = 66 + (13.8*weight) + (5*height) - (6.8*age) + activity;
        return BMR;
    }

    public double solveBmrFemale(double height, double weight, double age, double activity){
        double BMR;

        BMR = 655 + (9.6*weight) + (1.8*height) - (4.7*age) + activity;
        return BMR;
    }

    //get dimension from user and check if it is not empty
    private boolean getDimVar(){
        TextView tv = (TextView) findViewById(R.id.bmrAlloy);
        try{
            EditText getHeight = (EditText) findViewById(R.id.bmrGetHeight);
            EditText getWeight = (EditText) findViewById(R.id.bmrGetWeight);
            EditText getAge = (EditText) findViewById(R.id.bmrGetAge);

            String tempHeight = getHeight.getText().toString();
            String tempWeight = getWeight.getText().toString();
            String tempAge = getAge.getText().toString();

            setHeight = Double.parseDouble(tempHeight);
            setWeight = Double.parseDouble(tempWeight);
            setAge = Double.parseDouble(tempAge);

            //Toast.makeText(this, "Fill all fields " + setHeight+ " " + setWeight + " " + setAge, Toast.LENGTH_LONG).show();
            tv.setText("     ");
            return true;
        }catch (Exception e){
            tv.setText("     Fill all fields");
            return false;
        }
    }

    /*
    get val from radio group and return gender
     */
    private String getValueFromRadioGroup(){
        RadioGroup radio = (RadioGroup) findViewById(R.id.bmrRadio);
        int positionId = radio.getCheckedRadioButtonId();
        switch(positionId){
            case 2131558495:
                return "female";
            case 2131558496:
                return "male";
            default:
                return "male";
        }
    }

    //fill activity list
    private void setArrayList(){
        uActivity = (TextView) findViewById(R.id.bmrYourActivityChoose);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getRes());
        listView = (ListView) findViewById(R.id.bmrListView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Object o = listView.getItemAtPosition(position);
                String act = o.toString();

                uActivity.setText(" " + act);
                setActivity = getListElement(position);
            }
        });
    }

    /* set specific value for every activity
    for example moderate activity + 300 kcl ...     */
    private double getListElement(int elem){
        switch (elem){
            case 0:
                return 0;
            case 1:
                return 50;
            case 2:
                return 150;
            case 3:
                return 250;
            case 4:
                return 400;
            default:
                return 1;
        }
    }

    //get data to fill list from res/values/array
    private String[] getRes(){
        Resources res = getResources();
        String[] active = res.getStringArray(R.array.personalActivity);
        return active;
    }
}