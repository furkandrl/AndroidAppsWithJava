package ders.android.login_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewUserActivity extends AppCompatActivity {
    ListView lvUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewuser);
        lvUsers=findViewById(R.id.lv_Users);
        DatabaseHelper databaseHelper=new DatabaseHelper(this);
        ArrayList<String> userList=databaseHelper.listAllUsers();

        ArrayAdapter<String> adapter=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,userList);
        lvUsers.setAdapter(adapter);
    }
}