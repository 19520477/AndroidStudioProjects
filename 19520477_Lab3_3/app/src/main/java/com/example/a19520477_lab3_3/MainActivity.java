package com.example.a19520477_lab3_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etID;
    private EditText edtPhoneNumber;
    private EditText edtEmail;
    private EditText etNo;
    private Button btnAdd;
    private Button btnUpdate;
    private ListView lvStudent;
    private DBManageStudent dbManager;
    private StudentAdapter studentAdapter;
    private List<Student> studentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DBManageStudent(this);
        findViewById();
        studentList = dbManager.getAllStudent();
        setAdapter();

        btnAdd.setOnClickListener(view -> {
            Student student = createStudent();
            dbManager.addStudent(student);
            updateListStudent();
            setAdapter();
        });
        lvStudent.setOnItemClickListener((adapterView, view, position, l) -> {
            Student student = studentList.get(position);
            etNo.setText(String.valueOf(student.getNo()));
            etName.setText(student.getName());
            etID.setText(student.getID());
            edtEmail.setText(student.getEmail());
            edtPhoneNumber.setText(student.getPhoneNumber());
            btnAdd.setEnabled(false);
            btnUpdate.setEnabled(true);
        });

        btnUpdate.setOnClickListener(view -> {
            Student student = new Student();
            student.setNo(Integer.parseInt(String.valueOf(etNo.getText())));
            student.setName(etName.getText()+"");
            student.setID(etID.getText()+"");
            student.setEmail(edtEmail.getText()+"");
            student.setPhoneNumber(edtPhoneNumber.getText()+"");
            int result = dbManager.updateStudent(student);
            if(result>0){
                updateListStudent();
            }
            btnAdd.setEnabled(true);
            btnUpdate.setEnabled(false);
        });

        deleteStudent();
    }
    private Student createStudent() {
        String name = etName.getText().toString();
        String id = "MSSV: " + String.valueOf(etID.getText());
        String phone ="Phone number: " + edtPhoneNumber.getText() + "";
        String email = "Email: " + edtEmail.getText().toString();

        return new Student(name, id, phone, email);
    }

    private void findViewById() {
        etNo = (EditText) findViewById(R.id.edt_no);
        etName = (EditText) findViewById(R.id.edt_name);
        etID = (EditText) findViewById(R.id.edt_id);
        edtPhoneNumber = (EditText) findViewById(R.id.edt_number);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        btnAdd = (Button) findViewById(R.id.btn_add);
        lvStudent = (ListView) findViewById(R.id.lv_student);
        btnUpdate = (Button) findViewById(R.id.btn_update);
    }

    private void setAdapter() {
        if (studentAdapter == null) {
            studentAdapter = new StudentAdapter(this, R.layout.info_student, studentList);
            lvStudent.setAdapter(studentAdapter);
        }else{
            studentAdapter.notifyDataSetChanged();
            lvStudent.setSelection(studentAdapter.getCount()-1);
        }
    }
    public void updateListStudent(){
        studentList.clear();
        studentList.addAll(dbManager.getAllStudent());
        if(studentAdapter!= null){
            studentAdapter.notifyDataSetChanged();
        }
    }

    public void deleteStudent(){
        lvStudent.setOnItemLongClickListener((adapterView, view, position, l) -> {
            Student student = studentList.get(position);
            int result = dbManager.deleteStudent(student.getNo());
            if(result>0){
                Toast.makeText(MainActivity.this, "Delete successfully", Toast.LENGTH_SHORT).show();
                updateListStudent();
            }else{
                Toast.makeText(MainActivity.this, "Delete fail", Toast.LENGTH_SHORT).show();
            }
            return false;
        });
    }
}