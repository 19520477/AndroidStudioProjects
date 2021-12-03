package com.example.lab02_recyclerview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    private Context context;
    private List<Employee> object;
    public EmployeeAdapter(Context context, List<Employee> objects)
    {
        this.context = context;
        //this.layoutID = layoutID;
        this.object = objects;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tvFullName;
        public TextView tvPosition;
        public ImageView ivManager;
        public LinearLayout llParent;
        public ViewHolder(View convertView)
        {
            super(convertView);
            tvFullName = (TextView) convertView.findViewById(R.id.item_employee_tv_fullname);
            tvPosition = (TextView) convertView.findViewById(R.id.item_employee_tv_position);
            ivManager = (ImageView) convertView.findViewById(R.id.item_employee_iv_manager);
            llParent = (LinearLayout) convertView.findViewById(R.id.item_employee_ll_parent);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View convertView = inflater.inflate(R.layout.item_employee, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Employee employee = object.get(position);

        // Set fullname
        if (employee.getFullName() != null)
        {
            holder.tvFullName.setText(employee.getFullName());
        }
        else holder.tvFullName.setText("");
        // If this is a manager -> show icon manager. Otherwise, show Staff in tvPosition
        if (employee.isManager()==true)
        {
            holder.ivManager.setVisibility(View.VISIBLE);
            holder.tvPosition.setVisibility(View.GONE);
        }
        else
        {
            holder.ivManager.setVisibility(View.GONE);
            holder.tvPosition.setVisibility(View.VISIBLE);
            holder.tvPosition.setText(context.getString(R.string.staff));
        }
        // Show different color backgrounds for 2 continuous employees
        if (position % 2 == 0) {
            holder.llParent.setBackgroundResource(R.color.white);
        } else {
            holder.llParent.setBackgroundResource(R.color.light_blue);
        }

    }

    @Override
    public int getItemCount() {
        return object.size();
    }
}
