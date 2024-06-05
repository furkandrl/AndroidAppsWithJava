package ders.android.login_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText etRegisterUN,etRegisterName,etRegisterMail,etRegisterPass,etConfirm;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resigter);
        etRegisterUN=findViewById(R.id.et_RegisterUN);
        etRegisterName=findViewById(R.id.et_RegisterName);
        etRegisterMail=findViewById(R.id.et_RegisterMail);
        etRegisterPass=findViewById(R.id.et_RegisterPass);
        etConfirm=findViewById(R.id.et_Confirm);
        btnRegister=findViewById(R.id.btn_Register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName=etRegisterUN.getText().toString();
                String password=etRegisterPass.getText().toString();
                String name=etRegisterName.getText().toString();
                String email=etRegisterMail.getText().toString();
                String confirmPass=etConfirm.getText().toString();

                if(!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(confirmPass)){
                    if(password.equals(confirmPass)) {
                        DatabaseHelper dbHelper = new DatabaseHelper(RegisterActivity.this);
                        if(dbHelper.insertUser(userName, name, password, email))
                                Toast.makeText(RegisterActivity.this, "Registaration is completed succesfully", Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(RegisterActivity.this, "Passwords are not matching.", Toast.LENGTH_SHORT).show();

                }else
                    Toast.makeText(RegisterActivity.this, "All fields must be filled in", Toast.LENGTH_SHORT).show();

            }
        });
    }
}