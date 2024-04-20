package com.example.odev_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView textView;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
    Button btnAdd, btnSubtract, btnMultiply, btnDivide, btnEqual,btnClear;
    StringBuilder numberBuilder = new StringBuilder();
    char operator;
    double num1, num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.editText);
        btn0 = findViewById(R.id.button0);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
        btn7 = findViewById(R.id.button7);
        btn8 = findViewById(R.id.button8);
        btn9 = findViewById(R.id.button9);
        btnAdd = findViewById(R.id.buttonPlus);
        btnSubtract = findViewById(R.id.buttonMinus);
        btnMultiply = findViewById(R.id.buttonMultiply);
        btnDivide = findViewById(R.id.buttonDivide);
        btnEqual = findViewById(R.id.buttonEqual);
        btnClear = findViewById(R.id.buttonClear);


        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnSubtract.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btnEqual.setOnClickListener(this);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear text in TextView
                textView.setText("");
                num1 = 0;
                num2 = 0;
                numberBuilder.delete(0,numberBuilder.length());
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button0) {
            numberBuilder.append("0");
            textView.setText(numberBuilder.toString());
            num2 = Double.parseDouble(numberBuilder.toString());
        } else if (id == R.id.button1) {
            numberBuilder.append("1");
            textView.setText(numberBuilder.toString());
            num2 = Double.parseDouble(numberBuilder.toString());
        } else if (id == R.id.button2) {
            numberBuilder.append("2");
            textView.setText(numberBuilder.toString());
            num2 = Double.parseDouble(numberBuilder.toString());
        } else if (id == R.id.button3) {
            numberBuilder.append("3");
            textView.setText(numberBuilder.toString());
            num2 = Double.parseDouble(numberBuilder.toString());
        } else if (id == R.id.button4) {
            numberBuilder.append("4");
            textView.setText(numberBuilder.toString());
            num2 = Double.parseDouble(numberBuilder.toString());
        } else if (id == R.id.button5) {
            numberBuilder.append("5");
            textView.setText(numberBuilder.toString());
            num2 = Double.parseDouble(numberBuilder.toString());
        } else if (id == R.id.button6) {
            numberBuilder.append("6");
            textView.setText(numberBuilder.toString());
            num2 = Double.parseDouble(numberBuilder.toString());
        } else if (id == R.id.button7) {
            numberBuilder.append("7");
            textView.setText(numberBuilder.toString());
            num2 = Double.parseDouble(numberBuilder.toString());
        } else if (id == R.id.button8) {
            numberBuilder.append("8");
            textView.setText(numberBuilder.toString());
            num2 = Double.parseDouble(numberBuilder.toString());
        } else if (id == R.id.button9) {
            numberBuilder.append("9");
            textView.setText(numberBuilder.toString());
            num2 = Double.parseDouble(numberBuilder.toString());
        } else if (id == R.id.buttonPlus) {
            numberBuilder.delete(0,numberBuilder.length());
            num1 = Double.parseDouble(textView.getText().toString());
            operator = '+';
            textView.setText("");
        } else if (id == R.id.buttonMinus) {
            numberBuilder.delete(0,numberBuilder.length());
            num1 = Double.parseDouble(textView.getText().toString());
            operator = '-';
            textView.setText("");
        } else if (id == R.id.buttonMultiply) {
            numberBuilder.delete(0,numberBuilder.length());
            num1 = Double.parseDouble(textView.getText().toString());
            operator = '*';
            textView.setText("");
        } else if (id == R.id.buttonDivide) {
            numberBuilder.delete(0,numberBuilder.length());
            num1 = Double.parseDouble(textView.getText().toString());
            operator = '/';
            textView.setText("");
        } else if (id == R.id.buttonEqual) {
            num2 = Double.parseDouble(textView.getText().toString());
            double result = performOperation(num1, num2, operator);
            textView.setText(String.valueOf(result));
        } else {
            textView.setText(0);
        }
    }

    private double performOperation(double num1, double num2, char operator) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 != 0)
                    return num1 / num2;
                else
                    Toast.makeText(this, "Division by 0 error!", Toast.LENGTH_LONG).show();
                    return Double.NaN; // Division by zero error
            default:
                return Double.NaN; // Invalid operator
        }
    }
}