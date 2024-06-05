package ders.android.intent_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SmsActivity extends AppCompatActivity {
    EditText etPhoneNumber,etSMSText;
    Button btnSendSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        etPhoneNumber=findViewById(R.id.et_PhoneNumber);
        etSMSText=findViewById(R.id.et_SMSText);
        btnSendSMS=findViewById(R.id.btn_SendSMS);
        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber=etPhoneNumber.getText().toString();
                String text=etSMSText.getText().toString();
                if(!TextUtils.isEmpty(phoneNumber))
                    if(!TextUtils.isEmpty(text)){
                        Intent smsIntent=new Intent(Intent.ACTION_SENDTO);
                        smsIntent.setData(Uri.parse("smsto:"+phoneNumber));
                        smsIntent.putExtra("sms_body", text);
                        startActivity(smsIntent);
                    }
            }
        });
    }
}