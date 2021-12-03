package com.example.a19521219_bai2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private ListView lvContacts;
    private EditText editTextName;
    private EditText editTextPhoneNumber;
    private DatabaseHandler databaseHandler;
    ContactAdapter contactAdapter;
    ArrayList<Contact> allContact;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHandler = new DatabaseHandler(this);

        Button btnAdd = findViewById(R.id.idbtnAdd);
        lvContacts = findViewById(R.id.idlvContact);
        editTextName = findViewById(R.id.idetName);
        editTextPhoneNumber = findViewById(R.id.idetPhoneNumber);

        allContact = databaseHandler.getAllContacts();
        contactAdapter = new ContactAdapter(MainActivity.this, allContact);
        lvContacts.setAdapter(contactAdapter);

        btnAdd.setOnClickListener(view -> addData());
        lvContacts.setOnItemLongClickListener((adapterView, view, i, l) -> {
            Contact contact = (Contact) lvContacts.getItemAtPosition(i);
            allContact.remove(i);
            databaseHandler.deleteContact(contact);
            contactAdapter.notifyDataSetChanged();
            return true;
        });
    }

    public void addData()
    {
        Contact contact = new Contact(editTextName.getText().toString(), editTextPhoneNumber.getText().toString());
        allContact.add(contact);
        databaseHandler.addContact(contact);
        contactAdapter.notifyDataSetChanged();
    }
}