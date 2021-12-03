package com.example.helloworld;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String STATE = "Status: ";
    String[] status = {"onCreate", "onStart", "onPause", "onResume", "onStop", "onDestroy", "onRestart"};

    public void showAlertDialogState(String str) {

        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Message");
        builder.setMessage(STATE + str);

        // add a button
        builder.setPositiveButton("OK", null);

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(STATE, "onCreate");
        showAlertDialogState(status[0]);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.e(STATE, "onStart");
        showAlertDialogState(status[1]);
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.e(STATE, "onPause");
        showAlertDialogState(status[2]);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.e(STATE, "onResume");
        showAlertDialogState(status[3]);
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.e(STATE, "onStop");
        showAlertDialogState(status[4]);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.e(STATE, "onDestroy");
        showAlertDialogState(status[5]);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.e(STATE, "onRestart");
        showAlertDialogState(status[6]);
    }
}