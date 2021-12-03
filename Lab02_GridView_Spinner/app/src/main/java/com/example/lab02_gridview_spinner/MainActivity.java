package com.example.lab02_gridview_spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GridView gvDishMenu;
    Spinner spnThumbnail;
    EditText etName;
    CheckBox cbPromotion;
    List<Dish> dish;
    List<Thumbnail> thumbnail;
    DishAdapter dishAdapter;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewsById();

        initSpnThumbnail();
        initGvMenu();

        handleEvents();
    }

    private void findViewsById()
    {
        // findViewById
        gvDishMenu = (GridView) findViewById(R.id.gv_Dish);
        spnThumbnail = (Spinner) findViewById(R.id.spn_thumbnail);
        etName = (EditText) findViewById(R.id.et_food);
        cbPromotion = (CheckBox) findViewById(R.id.cb_promotion);
        btnAdd = (Button) findViewById(R.id.btn_add);
    }

    private void initSpnThumbnail()
    {
        // Fake thumbnail list
        thumbnail = new ArrayList<>();
        thumbnail.add(Thumbnail.Thumbnail1);
        thumbnail.add(Thumbnail.Thumbnail2);
        thumbnail.add(Thumbnail.Thumbnail3);
        thumbnail.add(Thumbnail.Thumbnail4);

        // Set Adapter for spnThumbnail
        ThumbnailAdapter thumbnailAdapter = new ThumbnailAdapter(MainActivity.this, 1, thumbnail);
        spnThumbnail.setAdapter(thumbnailAdapter);
    }

    private void initGvMenu()
    {
        // fake dishes
        dish = new ArrayList<>();
        //dish.add(new Dish("Dish 01", Thumbnail.Thumbnail1, true));
        //dish.add(new Dish("Dish 02", Thumbnail.Thumbnail2, false));

        // Set adapter gridview
        dishAdapter = new DishAdapter(MainActivity.this, R.layout.item_dish, dish);
        gvDishMenu.setAdapter(dishAdapter);
    }

    private void addDish()
    {
        if (!validateData()) return;

        // Get input
        String name = etName.getText().toString();
        Thumbnail thumnail = (Thumbnail) spnThumbnail.getSelectedItem();
        boolean promotion = cbPromotion.isChecked();

        // Add new dish to dishes list
        Dish dish = new Dish(name, thumnail, promotion);
        this.dish.add(dish);

        // Update UI
        dishAdapter.notifyDataSetChanged();

        // Show success message
        Toast.makeText(MainActivity.this, getString(R.string.message), Toast.LENGTH_SHORT).show();

        // Reset inputs
        etName.setText("");
        spnThumbnail.setSelection(0);
        cbPromotion.setChecked(false);
    }

    private boolean validateData()
    {
        if (etName.getText().length() == 0)
        {
            Toast.makeText(MainActivity.this, getString(R.string.message1), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void handleEvents()
    {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDish();
            }
        });
    }
}