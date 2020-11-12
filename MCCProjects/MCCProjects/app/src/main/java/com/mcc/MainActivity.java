package com.mcc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);







    }
    public void cal(View v) {

        EditText editText = findViewById(R.id.principal_et);
        EditText editText1 = findViewById(R.id.interest_et);
        EditText editText2 = findViewById(R.id.n_et);


        double p = Double.parseDouble(editText.getText().toString());
        double i = Double.parseDouble(editText1.getText().toString());
        double n = Double.parseDouble(editText2.getText().toString());

        i =  i / (12*100) ;


        //  100 here
        double emi = (p*i*Math.pow((i+1),n)) / ((Math.pow(i+1,n)) - 1);

        TextView tv = findViewById(R.id.tv_emi);
        tv.setText(emi + "");

    }
}
