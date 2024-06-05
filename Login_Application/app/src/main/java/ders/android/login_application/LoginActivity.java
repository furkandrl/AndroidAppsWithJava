package ders.android.login_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText etLoginUN,etLoginPass;
    Button btnSignIn,btnViewUsers;
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etLoginUN=findViewById(R.id.et_LoginUN);
        etLoginPass=findViewById(R.id.et_LoginPass);
        btnSignIn=findViewById(R.id.btn_SignIn);
        btnViewUsers=findViewById(R.id.btn_AllUsers);
        tvRegister=findViewById(R.id.tv_Register);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName=etLoginUN.getText().toString();
                String password=etLoginPass.getText().toString();
                if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)) {
                    DatabaseHelper dbHelper=new DatabaseHelper(LoginActivity.this);
                    if(dbHelper.searchUser(userName,password)){
                        Intent  welcome=new Intent(LoginActivity.this,WelcomeActivity.class);
                        welcome.putExtra("USERNAME",userName);
                        startActivity(welcome);
                    }else
                        Toast.makeText(LoginActivity.this, "Username is not defined or password is wrong", Toast.LENGTH_SHORT).show();


                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(register);
            }
        });

        btnViewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewUser=new Intent(LoginActivity.this,ViewUserActivity.class);
                startActivity(viewUser);

            }
        });
    }
}