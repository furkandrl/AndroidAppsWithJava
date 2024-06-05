package ders.android.first_application;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvTitle;
    Button btnChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTitle=findViewById(R.id.tv_title);
        btnChange=findViewById(R.id.btn_Change);
    }

    public void ButtonClick(View v){
        tvTitle.setText("Hello World");
        tvTitle.setTextSize(30);
        tvTitle.setTextColor(Color.GREEN);
    }
}