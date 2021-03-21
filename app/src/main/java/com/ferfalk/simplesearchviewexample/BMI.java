package com.ferfalk.simplesearchviewexample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BMI extends AppCompatActivity {

    Button button;
    //    66.4730 + (13.7516 x weight in kg) + (5.0033 x height in cm) – (6.7550 x age in years)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_m_i);

        button = (Button)findViewById(R.id.BMIResultButtonId);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                boolean calculate = true;
                double height = 0.0;
                double weight = 0.0;
                double age = 0.0;
                String weightCategories = null;

                EditText yourHeight = (EditText) findViewById(R.id.heightId);
                try {
                    String height_S = yourHeight.getText().toString();
                    height = Double.parseDouble(height_S);
                }catch(Exception e) {
                    Toast.makeText(getApplicationContext(),"Please enter your height correctly.",Toast.LENGTH_LONG).show();
                    calculate = false;
                }
                if(height <0.0 || height >3.5)
                {
                    Toast.makeText(getApplicationContext(),"Please enter your height correctly.",Toast.LENGTH_LONG).show();
                    calculate = false;
                }

                EditText yourWeight = (EditText) findViewById(R.id.weightId);
                try{
                    String weight_S = yourWeight.getText().toString();
                    weight = Double.parseDouble(weight_S);
                }catch(Exception e) {

                    Toast.makeText(getApplicationContext(),"Please enter your weight correctly.",Toast.LENGTH_LONG).show();
                    calculate = false;
                }
                if(weight < 0.0)
                {
                    Toast.makeText(getApplicationContext(),"Please enter your weight correctly.",Toast.LENGTH_LONG).show();
                    calculate = false;
                }

                EditText yourAge = (EditText) findViewById(R.id.AgeEditText);
                try{
                    String yourAge_S = yourAge.getText().toString();
                    age = Double.parseDouble(yourAge_S);
                }catch(Exception e) {

                    Toast.makeText(getApplicationContext(),"Please enter your age correctly.",Toast.LENGTH_LONG).show();
                    calculate = false;
                }
                if(age < 0.0)
                {
                    Toast.makeText(getApplicationContext(),"Please enter your age correctly.",Toast.LENGTH_LONG).show();
                    calculate = false;
                }


                if(calculate == true)
                {
                    double BMI = weight / Math.pow(height,2);

                    if(BMI <18.5)
                    {
                        weightCategories = "Underweight";
                    }
                    else if(BMI >=18.5 && BMI <= 24.9)
                    {
                        weightCategories = "Normal weight";
                    }
                    else if(BMI == 25 && BMI <=29.9)
                    {
                        weightCategories = "Overweight";
                    }
                    else if(BMI >= 30)
                    {
                        weightCategories = "Obesity";
                    }

                    AlertDialog.Builder bmiBuilder = new AlertDialog.Builder(BMI.this);
                    bmiBuilder.setCancelable(true);
                    bmiBuilder.setTitle("Calculated BMI Result & Your Category of Weight");
                    bmiBuilder.setMessage("BMI = "+ BMI + "\n" + "Your Category of Weight = " + weightCategories+ "\n"+
                            "Your daily calorie intake to maintain your weight is :" + "\n" + (66.4730 + (13.7516*weight) + (5.0033*height*100) - (6.7550 *age)));

                    bmiBuilder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            finish();
                            Intent i = new Intent(BMI.this, MainActivity.class);
                            startActivity(i); //go to next next activity
                        }
                    });
                    bmiBuilder.show();
                }

            }

        });
    }
}