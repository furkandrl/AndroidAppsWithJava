package com.example.odev6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public final static String DATABASE_NAME="USER.DB";
    public final static String TABLE_NAME="USERS";
    public final static String COL_ID="ID";
    public final static String COL_NAME="NAME";
    public final static String COL_PHONE="PHONE";

    SQLiteDatabase database;
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null,  1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT, " +
                COL_PHONE + " TEXT" +
                ")";
        db.execSQL(createTableQuery);
        this.database = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public ArrayList<String> getAllContacts(){
        database=this.getReadableDatabase();
        ArrayList<String> contactsList = new ArrayList<>();
        String query="SELECT * FROM "+TABLE_NAME;
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                String phoneNumber = cursor.getString(2);
                contactsList.add(name + " - " + phoneNumber);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return contactsList;
    }

    public long addContact(String name, String phone) {
        database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_PHONE, phone);
        long result = database.insert(TABLE_NAME, null, contentValues);
        return result;
    }

    public int updateContact(int id, String newName, String newPhone) {
        database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, newName);
        contentValues.put(COL_PHONE, newPhone);
        int result = database.update(TABLE_NAME, contentValues, COL_ID + "=?", new String[]{String.valueOf(id)});
        database.close();
        return result;
    }

    public ArrayList<Object> getContactByNameAndPhone(String name, String phoneNumber){
        database = this.getReadableDatabase();
        ArrayList<Object> foundContact = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE NAME=? AND PHONE=?";
        Cursor cursor = database.rawQuery(query, new String[]{name, phoneNumber});
        if(cursor.moveToFirst()){
            Integer contactId = cursor.getInt(0);
            String contactName = cursor.getString(1);
            String contactPhone = cursor.getString(2);
            foundContact.add(contactId);
            foundContact.add(contactName);
            foundContact.add(contactPhone);
        }
        cursor.close();
        return foundContact;
    }

    public int deleteContact(int id) {
        database = this.getWritableDatabase();
        int result = database.delete(TABLE_NAME, COL_ID + "=?", new String[]{String.valueOf(id)});
        database.close();
        return result;
    }
}
