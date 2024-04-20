package com.example.odev4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE = 100;
    Button startButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        if(v.getId() == R.id.startButton){
            Intent intent = new Intent(this, TextActivity.class);
            //startActivityForResult(intent, REQUEST_CODE);
            startActivityForResult(intent,REQUEST_CODE);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                // Temizlenmiş metin ve kabul edilemeyen karakter sayısı alınıyor
                String cleanedText = data.getStringExtra("cleanedText");
                int invalidCharCount = data.getIntExtra("invalidCharCount", 0);

                // Toast mesajı olarak yayınlanıyor
                Toast.makeText(this, "Temizlenmiş metin: " + cleanedText , Toast.LENGTH_LONG).show();
                Toast.makeText(this, "Kabul edilmeyen sayısı: " + invalidCharCount, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "İşlem iptal edildi", Toast.LENGTH_SHORT).show();
            }
        }
    }



}