package ders.android.layouttypes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class FrameActivity extends AppCompatActivity {
    ImageView iv1,iv2;
    Button btnVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        iv1=findViewById(R.id.iv_1);
        iv2=findViewById(R.id.iv_2);
        btnVisible=findViewById(R.id.btn_Visible);
        btnVisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv1.setVisibility(View.INVISIBLE);
                iv2.setVisibility(View.VISIBLE);
            }
        });
    }
}