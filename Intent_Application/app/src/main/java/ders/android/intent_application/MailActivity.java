package ders.android.intent_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class MailActivity extends AppCompatActivity {
    EditText etMailAddress,etSubject,etMailText;
    ImageButton ibSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        etMailAddress=findViewById(R.id.et_MailAddress);
        etSubject=findViewById(R.id.et_Subject);
        etMailText=findViewById(R.id.et_MailText);
        ibSend=findViewById(R.id.ib_Send);
        ibSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mailAddress=etMailAddress.getText().toString();
                String subject=etSubject.getText().toString();
                String text=etMailText.getText().toString();
                if(!TextUtils.isEmpty(mailAddress) && !TextUtils.isEmpty(text)) {
                    Intent mail = new Intent(Intent.ACTION_SEND);
                    mail.setType("message/rfc822");
                    mail.putExtra(Intent.EXTRA_EMAIL, new String[]{mailAddress});
                    mail.putExtra(Intent.EXTRA_SUBJECT,subject);
                    mail.putExtra(Intent.EXTRA_TEXT,text);
                    startActivity(mail);
                }
            }
        });
    }
}