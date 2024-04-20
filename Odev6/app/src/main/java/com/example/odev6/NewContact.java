package com.example.odev6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewContact extends AppCompatActivity {
    EditText nameText, phoneText;
    Button saveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        DatabaseHelper databaseHelper=new DatabaseHelper(this);
        nameText = findViewById(R.id.nameText);
        phoneText = findViewById(R.id.phoneText);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener( v ->
        {
            try{databaseHelper.addContact(nameText.getText().toString(),
                        phoneText.getText().toString());
                Toast.makeText(this, "Contact saved succesfully", Toast.LENGTH_SHORT).show();
                Intent intent =getIntent();
                setResult(Activity.RESULT_OK, intent);
                finish();
            }catch(Exception e){
                Toast.makeText(this,"Error occured while saving", Toast.LENGTH_SHORT).show();
            }

        });

    }


}