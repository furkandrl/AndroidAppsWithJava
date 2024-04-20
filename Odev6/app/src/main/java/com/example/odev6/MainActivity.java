package com.example.odev6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> contactsList;
    private ArrayAdapter<String> adapter;
    DatabaseHelper dbHelper;

    private static final int  REQUEST_CALL = 100;
    private static final int REQUEST_NEW = 101;
    private static final int REQUEST_UPDATE = 102;
    String selectedName;
    String selectedPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper=new DatabaseHelper(this);
        listView = findViewById(R.id.listView);
        contactsList = dbHelper.getAllContacts();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactsList);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listView.setItemChecked(position, true);
                listView.setSelected(true);
                view.setSelected(true);
                String selectedContact = contactsList.get(position);
                String[] contactInfo = selectedContact.split(" - ");
                selectedName = contactInfo[0];
                selectedPhone = contactInfo[1];
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.new_contact) {// Start NewContactActivity to add a new contact
            Intent newContactIntent = new Intent(MainActivity.this, NewContact.class);
            startActivityForResult(newContactIntent, REQUEST_NEW);
            return true;
        } else if (itemId == R.id.exit) {
            finish();
            return true;
        } else if (itemId == R.id.update_contact) {// Check if any item is selected
            if (listView.getCheckedItemPosition() != ListView.INVALID_POSITION) {
                Intent updateIntent = new Intent(MainActivity.this, UpdateContact.class);
                updateIntent.putExtra("name", selectedName);
                updateIntent.putExtra("phone", selectedPhone);
                startActivityForResult(updateIntent, REQUEST_UPDATE);
            } else {
                Toast.makeText(MainActivity.this, "Please select a contact to update", Toast.LENGTH_SHORT).show();
            }
            return true;
        }else if (itemId == R.id.delete_contact){
            if(dbHelper.deleteContact(
                    ((int) dbHelper.getContactByNameAndPhone(selectedName, selectedPhone).get(0))) == 1){
                Toast.makeText(this, "Contact deleted succesfully", Toast.LENGTH_SHORT).show();
                this.recreate();
            }else Toast.makeText(this, "Error occured", Toast.LENGTH_SHORT).show();
        }else if(itemId == R.id.action_call){
            callContact(selectedPhone);
        }
        return super.onOptionsItemSelected(item);
    }

    private void callContact(String phoneNumber) {
        Intent phoneIntent=new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:"+phoneNumber));
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED)
            startActivity(phoneIntent);
        else {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.CALL_PHONE)){
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
        if(requestCode==REQUEST_CALL){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                if (listView.getCheckedItemPosition() != ListView.INVALID_POSITION) {
                    String selectedContact = contactsList.get(listView.getCheckedItemPosition());
                    String[] contactInfo = selectedContact.split(" - ");
                    String phoneNumber = contactInfo[1];
                    callContact(phoneNumber);
                }
            }
        } else {
            Toast.makeText(this, "User denied the call permission", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_NEW && resultCode == Activity.RESULT_OK){
            System.out.println("activity result");
            contactsList = dbHelper.getAllContacts();
            adapter.notifyDataSetChanged();
            this.recreate();
        }
        if(requestCode == REQUEST_UPDATE && resultCode == Activity.RESULT_OK){
            this.recreate();
        }
    }
}