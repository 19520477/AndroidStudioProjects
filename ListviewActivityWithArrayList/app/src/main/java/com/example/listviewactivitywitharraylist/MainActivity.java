package com.example.listviewactivitywitharraylist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etInputName;
    Button btnInput;
    TextView tvSelection;
    ListView lvPerson;
    ArrayList<String> names = null;
    ArrayAdapter<String> adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInputName = (EditText) findViewById(R.id.etPersonName);
        lvPerson = (ListView) findViewById(R.id.lv_person);
        tvSelection = (TextView) findViewById(R.id.tvSelection);

        //1. Create ArrayList object
        names = new ArrayList<String>();

        //2. Assign Data Source (ArrayList object) to ArrayAdapter
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);

        //3. Assign Adapter to ListView
        lvPerson.setAdapter(adapter);

        btnInput = (Button) findViewById(R.id.btnInput);
        //4. Handle btnInput event
        btnInput.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View arg0)
            {
                names.add(etInputName.getText()+"");
                adapter.notifyDataSetChanged();
            }
        });

        //5. Handle select event of an element in ListView
        lvPerson.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> arg0,View arg1, int arg2,long arg3)
            {
                tvSelection.setText("position : "+ arg2+ "; value =" + names.get(arg2));
            }
        });

        //6. Handle event Long click
        lvPerson.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            {
                names.remove(arg2);//remove element arg2
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}