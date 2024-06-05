package ders.android.layouttypes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LinearActivity extends AppCompatActivity {
    EditText etUserName,etPassword;
    Button btnLogin;

    String realUN="yasin";
    String realPass="1234";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);
        etUserName=findViewById(R.id.et_UserName);
        etPassword=findViewById(R.id.et_Password);
        btnLogin=findViewById(R.id.btn_Login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName=etUserName.getText().toString();
                String password=etPassword.getText().toString();
                if((!userName.isEmpty()) || (!password.isEmpty()))
                    if((userName.equals(realUN)) & (password.equals(realPass)))
                        Toast.makeText(LinearActivity.this, "Login is successful...", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(LinearActivity.this, "Username or password is wrong!!!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(LinearActivity.this, "Username and password field can not be left empty", Toast.LENGTH_SHORT).show();
            }
        });
    }
}