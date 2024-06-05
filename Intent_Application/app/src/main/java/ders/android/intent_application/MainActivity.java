package ders.android.intent_application;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSMS,btnMail,btnShare,btnLink,btnReturn;
    TextView tvName;
    final static int REQUEST_CODE1=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         btnSMS=findViewById(R.id.btn_SMS);
         btnMail=findViewById(R.id.btn_Mail);
         btnShare=findViewById(R.id.btn_Share);
         btnLink=findViewById(R.id.btn_Link);
         btnReturn=findViewById(R.id.btn_ReturnName);
         tvName=findViewById(R.id.tv_Name);
         btnSMS.setOnClickListener(this);
         btnMail.setOnClickListener(this);
         btnShare.setOnClickListener(this);
         btnLink.setOnClickListener(this);
         btnReturn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
       int ID=view.getId();
       Intent intent;
       if(ID==R.id.btn_SMS){
           intent=new Intent(this,SmsActivity.class);
           startActivity(intent);
       }else if(ID==R.id.btn_Mail){
           intent=new Intent(this,MailActivity.class);
           startActivity(intent);
       }else if(ID==R.id.btn_Share){
           intent=new Intent(this,ShareActivity.class);
           startActivity(intent);
       }else if(ID==R.id.btn_Link){
           intent=new Intent(this,LinkActivity.class);
           startActivity(intent);
       }else if(ID==R.id.btn_ReturnName){
           intent=new Intent(this,ReturnActivity.class);
           startActivityForResult(intent,REQUEST_CODE1);
       }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE1)
            if(resultCode== Activity.RESULT_OK){
                String yourName=data.getStringExtra("YOURNAME");
                tvName.setText("Your name is: "+yourName);
            }

    }
}