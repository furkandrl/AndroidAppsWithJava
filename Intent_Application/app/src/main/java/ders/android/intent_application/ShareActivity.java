package ders.android.intent_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class ShareActivity extends AppCompatActivity {
    EditText etShare;
    ImageButton ibShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        etShare=findViewById(R.id.et_Share);
        ibShare=findViewById(R.id.ib_Share);
        ibShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=etShare.getText().toString();
                if(!TextUtils.isEmpty(text)){
                    Intent share=new Intent(Intent.ACTION_SEND);
                    share.setType("plain/text");
                    share.putExtra(Intent.EXTRA_TEXT,text);
                    startActivity(share);
                }
            }
        });
    }
}