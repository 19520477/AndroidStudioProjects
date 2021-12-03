package com.example.a19520477_sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.RandomAccess;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSimple;
    Button btnFancy;
    TextView tvMyText;
    Boolean myChoice = false;
    View ll_Vertical;
    final int mode = Activity.MODE_PRIVATE;
    final String prefs = "MyPreferences";

    //create an object
    SharedPreferences mySharedPrefs ;
    SharedPreferences.Editor myEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPrefs();
        findViewById();
        setTextForTextView();
        createPrefsFile();
        setOnClickListener();
    }

    private void initPrefs ()
    {
        mySharedPrefs = getSharedPreferences(prefs, mode);
        myEditor = mySharedPrefs.edit();
    }

    private void findViewById()
    {
        ll_Vertical = (View) findViewById(R.id.ll_vertical);
        tvMyText = (TextView) findViewById(R.id.tvMyText);
        btnSimple = (Button) findViewById(R.id.btnSimple);
        btnFancy = (Button) findViewById(R.id.btnFancy);
    }

    @SuppressLint("SetTextI18n")
    private void setTextForTextView()
    {
        tvMyText.setText("This is a sample line \n"
                + "suggesting the way the UI looks \n"
                + "after you choose your preference");
    }

    private void createPrefsFile ()
    {
        if(mySharedPrefs != null && mySharedPrefs.contains("backColor"))
        {
            applySavedPrefs();
        }
        else {
            Toast.makeText(getApplicationContext(), "No Preferences found", Toast.LENGTH_LONG).show();
        }
    }

    private void setOnClickListener()
    {
        ll_Vertical.setOnClickListener(this);
        btnSimple.setOnClickListener(this);
        btnFancy.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        myEditor.clear();
        if(view.getId() == btnSimple.getId())
        {
            myEditor.putInt("layoutColor", Color.DKGRAY);
            myEditor.putInt("backColor", Color.BLACK);
            myEditor.putInt("textSize", 14);
        }
        else if (view.getId() == btnFancy.getId()) {
            myEditor.putInt("backColor", Color.BLUE);
            myEditor.putInt("textSize", 22);
            myEditor.putString("textStyle", "bold");
            myEditor.putInt("layoutColor", Color.GREEN);
        }
        myEditor.commit();
        applySavedPrefs();
    }

    public void applySavedPrefs()
    {
        int backColor = mySharedPrefs.getInt("backColor", Color.BLACK);
        int textSize = mySharedPrefs.getInt("textSize", 16);
        String textStyle = mySharedPrefs.getString("textStyle", "normal");
        int layoutColor = mySharedPrefs.getInt("layoutColor", Color.WHITE);
        String message = "backgroundColor: " + layoutColor + "\n"
                + "backgroundText_Color: " + backColor + "\n"
                + "textSize: " + textSize + "\n"
                + "textStyle: " + textStyle;
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

        tvMyText.setBackgroundColor(backColor);
        tvMyText.setTextSize(textSize);
        if(textStyle.compareTo("normal")==0)
        {
            tvMyText.setTypeface(Typeface.SERIF, Typeface.NORMAL);
        }
        else
        {
            tvMyText.setTypeface(Typeface.SERIF, Typeface.BOLD);
        }
        ll_Vertical.setBackgroundColor(layoutColor);
    }

    @Override
    protected void onPause() {
        myEditor.putString("DateLastExecution", new Date().toString());
        myEditor.commit();
        super.onPause();
    }
}