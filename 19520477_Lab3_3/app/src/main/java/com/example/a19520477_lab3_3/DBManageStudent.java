package com.example.a19520477_lab3_3;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBManageStudent extends SQLiteOpenHelper {

    private final String TAG = "DBManageStudent";
    private static final String DATABASE_NAME = "students_manager";
    private static final String TABLE_NAME = "students";
    private static final String NO = "STT";
    private static final String NAME = "name";
    private static final String ID = "ID";
    private static final String PHONE_NUMBER = "phone";
    private static final String EMAIL = "email";
    private static final int VERSION = 1;

    public DBManageStudent(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        Log.d(TAG, "DB Manager: ");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Query = "CREATE TABLE " + TABLE_NAME + " (" +
                NO + " integer primary key, " +
                NAME + " TEXT, " +
                ID + " TEXT, " +
                PHONE_NUMBER + " TEXT, " +
                EMAIL + " TEXT)";
        db.execSQL(Query);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade: ");
    }

    public void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, student.getName());
        values.put(ID, student.getID());
        values.put(PHONE_NUMBER, student.getPhoneNumber());
        values.put(EMAIL, student.getEmail());

        db.insert(TABLE_NAME, null, values);
        db.close();
        Log.d(TAG, "addStudent Successfully");
    }

    public List<Student> getAllStudent() {
        List<Student> listStudent = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setNo(cursor.getInt(0));
                student.setName(cursor.getString(1)+"");
                student.setID(cursor.getString(2));
                student.setPhoneNumber(cursor.getString(3));
                student.setEmail(cursor.getString(4));
                listStudent.add(student);

            } while (cursor.moveToNext());
        }
        db.close();
        return listStudent;
    }
    public int updateStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,student.getName());
        contentValues.put(ID,student.getID());
        contentValues.put(PHONE_NUMBER,student.getPhoneNumber());
        contentValues.put(EMAIL,student.getEmail());
        return db.update(TABLE_NAME,contentValues,NO+"=?",new String[]{String.valueOf(student.getNo())});
    }

    public int deleteStudent(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,NO+"=?",new String[] {String.valueOf(id)});
    }
}
