package ders.android.second_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnAdd,btnSubtract,btnMultiply,btnDivision;
    EditText etFirst,etSecond;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        btnAdd=findViewById(R.id.btn_Add);
        btnSubtract=findViewById(R.id.btn_Subtract);
        btnMultiply=findViewById(R.id.btn_Multiply);
        btnDivision=findViewById(R.id.btn_Division);
        etFirst=findViewById(R.id.et_First);
        etSecond=findViewById(R.id.et_Second);
        tvResult=findViewById(R.id.tv_Result);
        btnAdd.setOnClickListener(this);
        btnSubtract.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnDivision.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        double firstNumber=Double.parseDouble(etFirst.getText().toString());
        double secondNumber=Double.parseDouble(etSecond.getText().toString());
        double result=0.0;
        if(id==R.id.btn_Add)
            result=firstNumber+secondNumber;
        else if(id==R.id.btn_Subtract)
            result=firstNumber-secondNumber;
        else if(id==R.id.btn_Multiply)
            result=firstNumber*secondNumber;
        else if(id==R.id.btn_Division){
            if(secondNumber!=0)
                result=firstNumber/secondNumber;
            else
                Toast.makeText(this, "Divided by Zero Error", Toast.LENGTH_SHORT).show();
        }
        tvResult.setText(String.valueOf(result));

    }
}