package ders.android.second_application;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BMIActivity extends AppCompatActivity {
    EditText etWeight,etHeight;
    Button btnCalculateBMI;
    TextView tvBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        etHeight=findViewById(R.id.et_Height);
        etWeight=findViewById(R.id.et_Weight);
        tvBMI=findViewById(R.id.tv_BMI);
        btnCalculateBMI=findViewById(R.id.btn_CalculateBMI);

        btnCalculateBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double height=Double.parseDouble(etHeight.getText().toString());
                double weight=Double.parseDouble(etWeight.getText().toString());
                double BMI=weight/Math.pow(height,2);
                if(BMI<18.5) {
                    tvBMI.setText("Underweight");
                    tvBMI.setTextColor(Color.BLUE);
                }else if(BMI<25){
                    tvBMI.setText("Normal");
                    tvBMI.setTextColor(Color.GREEN);
                }else if(BMI<30){
                    tvBMI.setText("Overweight");
                    tvBMI.setTextColor(Color.CYAN);
                }else{
                    tvBMI.setText("Obez");
                    tvBMI.setTextColor(Color.RED);
                }
            }
        });
    }
}