package com.example.lab02_gridview_spinner;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class DishAdapter extends ArrayAdapter<Dish> {
    private Activity context;
    private int layoutID;
    public DishAdapter(Activity context, int layoutID, List<Dish> objects) {
        super(context, layoutID, objects);
        this.context = context;
        this.layoutID = layoutID;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutID, null, false);
        }

        // Get item
        Dish dish = getItem(position);

        // Get view
        TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
        ImageView ivThumbnail = (ImageView) convertView.findViewById(R.id.iv_thumbnail);
        ImageView ivPromotion = (ImageView) convertView.findViewById(R.id.iv_promotion);

        // Show thumbnail
        if (dish.getThumbnail()!=null) {
            ivThumbnail.setImageResource(dish.getThumbnail().getImg());
        }

        // Show name
        if (dish.getDishName()!=null)
        {
            tvName.setText(dish.getDishName());
            tvName.setHorizontallyScrolling(true);
            tvName.setSelected(true);
        }

        // Show promotion
        if (dish.isPromotion())
        {
            ivPromotion.setVisibility(View.VISIBLE);
        }
        else
        {
            ivPromotion.setVisibility(View.GONE);
        }

        return convertView;
    }
}
