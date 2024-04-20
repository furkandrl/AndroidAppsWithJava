package com.example.odev6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateContact extends AppCompatActivity {
    EditText nameText, phoneText;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);
        DatabaseHelper databaseHelper=new DatabaseHelper(this);
        nameText = findViewById(R.id.nameText);
        phoneText = findViewById(R.id.phoneText);
        saveButton = findViewById(R.id.saveButton);

        Intent intent = getIntent();
        nameText.setText(intent.getStringExtra("name"));
        phoneText.setText(intent.getStringExtra("phone"));

        ArrayList<Object> foundContact = databaseHelper
                .getContactByNameAndPhone(intent.getStringExtra("name"), intent.getStringExtra("phone"));

        saveButton.setOnClickListener(v -> {

            if(!foundContact.isEmpty()){
                databaseHelper.updateContact(((int) foundContact.get(0)), nameText.getText().toString(), phoneText.getText().toString());
                Toast.makeText(this, "Contact updated succesfully", Toast.LENGTH_SHORT).show();
                setResult(Activity.RESULT_OK, intent);
                finish();
            }else{
                Toast.makeText(this,"Error occured while saving", Toast.LENGTH_SHORT).show();
            }
        });
    }
}