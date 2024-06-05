package ders.android.call_appllication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etPhoneNumber;
    ImageButton ibCall;
    String phoneNumber;
    final static int REQUEST_CALL=20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etPhoneNumber=findViewById(R.id.et_PhoneNumber);
        ibCall=findViewById(R.id.ib_Call);
        ibCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneNumber=etPhoneNumber.getText().toString();
                if(!TextUtils.isEmpty(phoneNumber))
                    Call();
                else
                    Toast.makeText(MainActivity.this, "You should enter a phone number", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Call() {
        Intent phoneIntent=new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:"+phoneNumber));
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED)
            startActivity(phoneIntent);
        else {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CALL_PHONE)){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Info");
                builder.setMessage("This application needs CALL permision to call someone");
                builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }

            String permissions[]= new String[]{Manifest.permission.CALL_PHONE};
            ActivityCompat.requestPermissions(this,permissions , REQUEST_CALL);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_CALL)
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
                Call();
            else
                Toast.makeText(this, "User denied the call permission", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int ID=item.getItemId();
        if(ID==R.id.item_Camera)
            Toast.makeText(this, "Camera is opening", Toast.LENGTH_SHORT).show();
        if(ID==R.id.item_Share)
            Toast.makeText(this, "Share activity is opening", Toast.LENGTH_SHORT).show();
        if(ID==R.id.item_Exit)
            System.exit(0);
        return true;
    }
}