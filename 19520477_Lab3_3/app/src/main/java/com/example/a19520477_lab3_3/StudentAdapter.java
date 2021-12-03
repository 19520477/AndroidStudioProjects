package com.example.a19520477_lab3_3;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {

    private final Context context;
    private final List<Student> listStudent;

    public StudentAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Student> objects) {
        super(context, resource, objects);
        this.context= context;
        this.listStudent=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.info_student,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvNo = (TextView)convertView.findViewById(R.id.tv_no);
            viewHolder.tvName = (TextView)convertView.findViewById(R.id.tv_name);
            viewHolder.tvPhoneNumber = (TextView)convertView.findViewById(R.id.tv_phone_number);
            viewHolder.tvEmail  = (TextView)convertView.findViewById(R.id.tv_email);
            viewHolder.tvID = (TextView)convertView.findViewById(R.id.tv_id);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Student student = listStudent.get(position);
        viewHolder.tvNo.setText(String.valueOf(student.getNo()));
        viewHolder.tvName.setText(student.getName());
        viewHolder.tvID.setText(student.getID());
        viewHolder.tvPhoneNumber.setText(student.getPhoneNumber());
        viewHolder.tvEmail.setText(student.getEmail());

        return convertView;
    }

    public static class ViewHolder{

        private TextView tvNo;
        private TextView tvName;
        private TextView tvID;
        private TextView tvEmail;
        private TextView tvPhoneNumber;
    }
}

