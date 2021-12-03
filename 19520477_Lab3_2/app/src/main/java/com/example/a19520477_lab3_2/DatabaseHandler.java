package com.example.a19520477_lab3_2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "contactsManager";
    // Contacts table name
    private static final String TABLE_CONTACTS = "contacts";
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";

    SQLiteDatabase db;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // Create tables again
        onCreate(db);
    }

    // Adding new contact
    public void addContact(Contact contact) {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if(contact.getID() != -1)
        {
            contentValues.put(KEY_NAME, contact.getName());
            contentValues.put(KEY_PH_NO, contact.getPhoneNumber());
            db.insert(TABLE_CONTACTS, null, contentValues);
        }
        db.close();
    }
    // Getting single contact
    public Contact getContact(int id) {
        Contact contact = null;
        String select = "SELECT " + id + " FROM " + TABLE_CONTACTS;
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        while (cursor.moveToNext())
        {
            contact = new Contact(cursor.getString(1), cursor.getString(2));
        }
        return contact;
    }
    // Getting All Contacts
    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> contactList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_CONTACTS;
        db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Contact contact = new Contact(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2));
            contactList.add(contact);
            cursor.moveToNext();
        }
        db.close();
        return contactList;
    }
    // Updating single contact
    /*public int updateContact(Contact contact) {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, contact.getName());
        contentValues.put(KEY_PH_NO, contact.getPhoneNumber());
        return db.update(TABLE_CONTACTS, contentValues, KEY_ID + "=?", new String[]{String.valueOf(contact.getID())});
    }*/
    // Deleting single contact
    public void deleteContact(Contact contact) {
        db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + "=?", new String[]{String.valueOf(contact.getID())});
        db.close();
    }
}
