package com.example.a19520477_lab3_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvContacts;
    private EditText etName;
    private EditText etPhoneNumber;
    private Button btnAdd;
    private Button btnUpdate;
    private DatabaseHandler databaseHandler;
    ContactAdapter contactAdapter;
    ArrayList<Contact> allContact;
    Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHandler = new DatabaseHandler(this);

        findViewById();

        allContact = databaseHandler.getAllContacts();
        contactAdapter = new ContactAdapter(MainActivity.this, allContact);
        lvContacts.setAdapter(contactAdapter);

        /*lvContacts.setOnItemClickListener((adapterView, view, position, id) -> {
            Contact selectItem = allContact.get(position);
            etName.setText(selectItem.getName());
            etPhoneNumber.setText(selectItem.getPhoneNumber());
            btnAdd.setEnabled(false);
            btnUpdate.setEnabled(true);

        });
        //event click button Update
        btnUpdate.setOnClickListener(view -> {
            Contact updateContact = new Contact();
            updateContact.setName(etName.getText()+"");
            updateContact.setPhoneNumber(etPhoneNumber.getText()+"");
            int update = databaseHandler.updateContact(updateContact);
            if(update>0)
            {
                updateData();
            }
            btnUpdate.setEnabled(false);
            btnAdd.setEnabled(true);
        });*/

        //event click button Add
        btnAdd.setOnClickListener(view -> {
            addData();
        });

        lvContacts.setOnItemLongClickListener((adapterView, view, i, l) -> {
            contact = (Contact) lvContacts.getItemAtPosition(i);
            allContact.remove(i);
            databaseHandler.deleteContact(contact);
            contactAdapter.notifyDataSetChanged();
            return true;
        });
    }

    public void findViewById()
    {
        btnAdd = findViewById(R.id.btnAdd);
        //btnUpdate = findViewById(R.id.btnUpdate);
        lvContacts = findViewById(R.id.lvContact);
        etName = findViewById(R.id.etName);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
    }

    public void addData()
    {
        contact = new Contact(etName.getText().toString(), etPhoneNumber.getText().toString());
        allContact.add(contact);
        databaseHandler.addContact(contact);
        contactAdapter.notifyDataSetChanged();
    }

    public void updateData()
    {
        allContact.clear();
        allContact.addAll(databaseHandler.getAllContacts());
        if(contactAdapter!=null) {
            contactAdapter.notifyDataSetChanged();
        }
    }
}