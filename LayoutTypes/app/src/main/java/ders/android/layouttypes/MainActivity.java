package ders.android.layouttypes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnLinear,btnRelative,btnFrame,btnTable,btnGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLinear=findViewById(R.id.btn_Linear);
        btnRelative=findViewById(R.id.btn_Relative);
        btnFrame=findViewById(R.id.btn_Frame);
        btnTable=findViewById(R.id.btn_Table);
        btnGrid=findViewById(R.id.btn_Grid);
        btnLinear.setOnClickListener(this);
        btnRelative.setOnClickListener(this);
        btnFrame.setOnClickListener(this);
        btnTable.setOnClickListener(this);
        btnGrid.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int ID=view.getId();
        Intent intent;
        if(ID==R.id.btn_Linear){
            intent=new Intent(this,LinearActivity.class);
            startActivity(intent);
        }else if(ID==R.id.btn_Relative){
            intent=new Intent(this,RelativeActivity.class);
            startActivity(intent);
        }else if(ID==R.id.btn_Frame){
            intent=new Intent(this,FrameActivity.class);
            startActivity(intent);
        }else if(ID==R.id.btn_Table){
            intent=new Intent(this,TableActivity.class);
            startActivity(intent);
        }else if(ID==R.id.btn_Grid){
            intent=new Intent(this,GridActivity.class);
            startActivity(intent);
        }

    }
}