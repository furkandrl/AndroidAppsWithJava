package ders.android.layouttypes;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class RelativeActivity extends AppCompatActivity {
    Button btnImage,btnColorize;
    ImageView ivImage;
    RelativeLayout relative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative);
        btnImage=findViewById(R.id.btn_Image);
        btnColorize=findViewById(R.id.btn_Colorize);
        ivImage=findViewById(R.id.iv_Image);
        relative=findViewById(R.id.relative);

        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_foreground));
            }
        });

        btnColorize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relative.setBackgroundColor(Color.BLUE);
            }
        });
    }
}