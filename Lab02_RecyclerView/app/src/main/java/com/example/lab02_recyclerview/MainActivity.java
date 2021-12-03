package com.example.lab02_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    RecyclerView rvEmployee;
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
        rvEmployee = (RecyclerView) findViewById(R.id.rv_person);
        arrEmployee = new ArrayList<>();
        btnAdd.setOnClickListener(view -> addNewEmployee());
    }
    //Xử lý sự kiện nhập
    public void addNewEmployee()
    {
        String id = etId.getText()+"".toString();
        String name = etName.getText()+"".toString();

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

        //đưa Data Source là các employee vào Adapter
        adapter = new EmployeeAdapter(this, arrEmployee);
        //đưa adapter vào ListView
        rvEmployee.setAdapter(adapter);
        rvEmployee.setLayoutManager(new LinearLayoutManager(this));

        //Update
        //adapter.notifyDataSetChanged();
    }
}