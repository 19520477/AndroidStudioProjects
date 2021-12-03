package com.example.lab02_customadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etId,etName;
    Button btnAdd;
    ListView lvEmployee;
    ArrayList<Employee> arrEmployee = new ArrayList<Employee>();
    EmployeeAdapter adapter = null;
    Employee employee = null; //Declare an employee object
    CheckBox cbIsManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etId = (EditText) findViewById(R.id.etPersonID);
        etName = (EditText) findViewById(R.id.etPersonName);
        cbIsManager = (CheckBox) findViewById(R.id.checkBox);
        btnAdd = (Button) findViewById(R.id.btnInput);
        lvEmployee = (ListView) findViewById(R.id.lv_person);
        //đưa Data Source là các employee vào Adapter
        adapter = new EmployeeAdapter(this, R.layout.item_employee, arrEmployee);
        //đưa adapter vào ListView
        lvEmployee.setAdapter(adapter);
        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0) {
                addNewEmployee();
            }
        });
    }
    //Xử lý sự kiện nhập
    public void addNewEmployee()
    {
        String id = etId.getText()+"";
        String name = etName.getText()+"";

        if(cbIsManager.isChecked())
        {
            employee = new Employee(true);
        }
        else
        {
            employee = new Employee(false);
        }
        employee.setId(id);
        employee.setName(name);
        //add employee to ArrayList
        arrEmployee.add(employee);
        //Update
        adapter.notifyDataSetChanged();
    }
}