package com.example.listview_arraylist_object;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    EditText etId,etName;
    Button btnInput;
    RadioGroup radType;
    ListView lvEmployee;
    TextView tvSelection;
    ArrayList<Employee> arrEmployee = new ArrayList<Employee>();
    ArrayAdapter<Employee> adapter = null;
    Employee employee = null; //Declare an employee object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etId = (EditText) findViewById(R.id.etPersonID);
        etName = (EditText) findViewById(R.id.etPersonName);
        btnInput = (Button) findViewById(R.id.btnInput);
        radType = (RadioGroup) findViewById(R.id.radType);
        lvEmployee = (ListView) findViewById(R.id.lv_person);
        //đưa Data Source là các employee vào Adapter
        adapter = new ArrayAdapter<Employee>(this, android.R.layout.simple_list_item_1, arrEmployee);
        //đưa adapter vào ListView
        lvEmployee.setAdapter(adapter);
        btnInput.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View arg0) {
                addNewEmployee();
            }
        });

        /*lvEmployee.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            {
                tvSelection.setText("position :" + arg2 + " ; value =" + arrEmployee.get(arg2).getName());
            }
        });*/
    }
    //Xử lý sự kiện nhập
    public void addNewEmployee()
    {
        //Lấy ra đúng id của Radio Button được checked
        int radId=radType.getCheckedRadioButtonId();
        String id = etId.getText()+"";
        String name = etName.getText()+"";
        if(radId==R.id.radbtnFulltime)
        {
            //Create instance FullTime
            employee = new EmployeeFullTime();
        }
        else
        {
            //Create instance ParTime
            employee = new EmployeePartTime();
        }
        employee.setId(id);
        employee.setName(name);
        //add employee to ArrayList
        arrEmployee.add(employee);
        //Update
        adapter.notifyDataSetChanged();
    }
}