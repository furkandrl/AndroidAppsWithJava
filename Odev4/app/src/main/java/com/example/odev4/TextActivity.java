package com.example.odev4;

import android.app.Activity;
import android.content.Intent;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class TextActivity extends AppCompatActivity {

    EditText editText;
    Button buttonDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        editText = findViewById(R.id.edit_text);
        buttonDone = findViewById(R.id.button_done);

        // EditText'e metin girildiğinde değişiklikleri dinlemek için dinleyici ekle
        editText.addTextChangedListener(textWatcher);

        // "Done" butonuna tıklama dinleyicisi ekle
        buttonDone.setOnClickListener(v -> {
            String text = editText.getText().toString();
            int invalidCharCount = countInvalidCharacters(text);

            // Temizlenmiş metni ve kabul edilemeyen karakter sayısını ana aktiviteye gönder
            Intent resultIntent = new Intent();
            resultIntent.putExtra("cleanedText", text.replaceAll("[^a-zA-Z]", ""));
            resultIntent.putExtra("invalidCharCount", invalidCharCount);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }

    // EditText'e girilen metindeki kabul edilmeyen karakterlerin sayısını bulan metod
    private int countInvalidCharacters(String text) {
        int count = 0;
        for (char c : text.toCharArray()) {
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
                count++;
            }
        }
        return count;
    }

    // Metin değişikliklerini dinlemek için TextWatcher kullanılıyor
    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            // EditText'te herhangi bir değişiklik olduğunda, giriş uzunluğuna bağlı olarak "Done" butonunun etkinleştirilmesi veya devre dışı bırakılması
            buttonDone.setEnabled(s.length() > 0);
        }
    };






}
