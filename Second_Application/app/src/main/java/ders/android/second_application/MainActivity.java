package ders.android.second_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ButtonClick(View v){
        int ID=v.getId();
        if (ID == R.id.btn_Calculator) {
            Intent calculatorIntent = new Intent(MainActivity.this, CalculatorActivity.class);
            startActivity(calculatorIntent);
        } else if (ID == R.id.btn_BMI) {
            Intent BMIIntent = new Intent(MainActivity.this, BMIActivity.class);
            startActivity(BMIIntent);
        } else if (ID == R.id.btn_Age) {
            Intent ageIntent = new Intent(MainActivity.this, AgeActivity.class);
            startActivity(ageIntent);
        }

    }
}