package com.example.a19521219_bai3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText edtName;
    private EditText editAddress;
    private EditText edtPhoneNumber;
    private EditText edtEmail;
    private EditText edtId;
    private Button btnSave;
    private Button btnUpdate;
    private ListView lvStudent;
    private DBManager dbManager;
    private CustomAdapter customAdapter;
    private List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DBManager(this);
        initWidget();
        studentList = dbManager.getAllStudent();
        setAdapter();
        btnSave.setOnClickListener(view -> {
            Student student = createStudent();
            dbManager.addStudent(student);
            updateListStudent();
            setAdapter();
        });
        lvStudent.setOnItemClickListener((adapterView, view, position, l) -> {
            Student student = studentList.get(position);
            edtId.setText(String.valueOf(student.getmID()));
            edtName.setText(student.getmName());
            editAddress.setText(student.getmAddress());
            edtEmail.setText(student.getmEmail());
            edtPhoneNumber.setText(student.getmPhoneNumber());
            btnSave.setEnabled(false);
            btnUpdate.setEnabled(true);
        });
        btnUpdate.setOnClickListener(view -> {
            Student student = new Student();
            student.setmID(Integer.parseInt(String.valueOf(edtId.getText())));
            student.setmName(edtName.getText()+"");
            student.setmAddress(editAddress.getText()+"");
            student.setmEmail(edtEmail.getText()+"");
            student.setmPhoneNumber(edtPhoneNumber.getText()+"");
            int result = dbManager.updateStudent(student);
            if(result>0){
                updateListStudent();
            }
            btnSave.setEnabled(true);
            btnUpdate.setEnabled(false);
        });
        lvStudent.setOnItemLongClickListener((adapterView, view, position, l) -> {
            Student student = studentList.get(position);
            int result = dbManager.deleteStudent(student.getmID());
            if(result>0){
                Toast.makeText(MainActivity.this, "Delete successfully", Toast.LENGTH_SHORT).show();
                updateListStudent();
            }else{
                Toast.makeText(MainActivity.this, "Delete fail", Toast.LENGTH_SHORT).show();
            }
            return false;
        });
    }

    private Student createStudent() {
        String name = edtName.getText().toString();
        String address = String.valueOf(editAddress.getText());
        String phoneNumber = edtPhoneNumber.getText() + "";
        String email = edtEmail.getText().toString();

        return new Student(name, address, phoneNumber, email);
    }

    private void initWidget() {
        edtName = (EditText) findViewById(R.id.edt_name);
        editAddress = (EditText) findViewById(R.id.edt_address);
        edtPhoneNumber = (EditText) findViewById(R.id.edt_number);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        btnSave = (Button) findViewById(R.id.btn_save);
        lvStudent = (ListView) findViewById(R.id.lv_student);
        edtId = (EditText) findViewById(R.id.edt_id);
        btnUpdate = (Button) findViewById(R.id.btn_update);
    }

    private void setAdapter() {
        if (customAdapter == null) {
            customAdapter = new CustomAdapter(this, R.layout.item_list_student, studentList);
            lvStudent.setAdapter(customAdapter);
        }else{
            customAdapter.notifyDataSetChanged();
            lvStudent.setSelection(customAdapter.getCount()-1);
        }
    }
    public void updateListStudent(){
        studentList.clear();
        studentList.addAll(dbManager.getAllStudent());
        if(customAdapter!= null){
            customAdapter.notifyDataSetChanged();
        }
    }

}
