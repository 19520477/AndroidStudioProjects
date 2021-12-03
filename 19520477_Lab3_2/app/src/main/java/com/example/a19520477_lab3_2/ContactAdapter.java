package com.example.a19520477_lab3_2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends BaseAdapter {
    ArrayList<Contact> arrayList;
    private final LayoutInflater layoutInflater;

    public ContactAdapter(Context context, ArrayList<Contact> arList) {
        arrayList = arList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_contact, null);
            holder = new ViewHolder();
            holder.tvName = view.findViewById(R.id.tvName);
            holder.tvPhoneNumber = view.findViewById(R.id.tvPhoneNumber);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Contact contact = this.arrayList.get(i);
        holder.tvName.setText(contact.getName());
        holder.tvPhoneNumber.setText(contact.getPhoneNumber());
        return view;
    }

    static class ViewHolder {
        TextView tvName;
        TextView tvPhoneNumber;
    }
}
