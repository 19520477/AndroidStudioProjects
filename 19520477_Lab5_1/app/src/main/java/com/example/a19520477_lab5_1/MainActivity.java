package com.example.a19520477_lab5_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ProgressBar pbFirst, pbSecond;
    private TextView tvMsgWorking, tvMsgReturned;
    private boolean isRunning;
    private int MAX_SEC;
    private int intTest;
    private Thread bgThread;
    private Handler handler;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewByIds();
        initVariables();

        btnStart.setOnClickListener(v -> {
            isRunning = true;
            pbFirst.setVisibility(View.VISIBLE);
            pbSecond.setVisibility(View.VISIBLE);
            btnStart.setVisibility(View.GONE);
            bgThread.start();
        });
    }

    private void findViewByIds(){
        pbFirst = findViewById(R.id.pb_first);
        pbSecond= findViewById(R.id.pb_second);
        tvMsgReturned = findViewById(R.id.tv_return);
        tvMsgWorking = findViewById(R.id.tv_working);
        btnStart = findViewById(R.id.btn_start);
    }

    @SuppressLint("HandlerLeak")
    private void initVariables(){
        isRunning = false;
        MAX_SEC = 20;
        intTest = 1;
        pbFirst.setMax(MAX_SEC);
        pbFirst.setProgress(0);
        pbFirst.setVisibility(View.GONE);
        pbSecond.setVisibility(View.GONE);
        handler = new Handler() {
            @SuppressLint("SetTextI18n")
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                String returnedValue = (String) msg.obj;
                tvMsgReturned.setText(getString(R.string.returned_by_bg_thread) + returnedValue);
                pbFirst.incrementProgressBy(2);

                if(pbFirst.getProgress() == MAX_SEC){
                    tvMsgReturned.setText(getString(R.string.done_background_thread_has_been_stopped));
                    tvMsgWorking.setText(getString(R.string.done));
                    pbFirst.setVisibility(View.GONE);
                    pbSecond.setVisibility(View.GONE);
                    btnStart.setVisibility(View.VISIBLE);
                    isRunning = false;
                }else{
                    tvMsgWorking.setText(getString(R.string.working) + pbFirst.getProgress());
                }
            }
        };

    }
    private void initBgThread(){
        bgThread = new Thread(() -> {
            try{
                for(int i=0; i<MAX_SEC && isRunning; i++){
                    Thread.sleep(1000);
                    Random rnd = new Random();
                    String data = "Thread value" + rnd.nextInt(101);
                    data += getString(R.string.global_value_seen) + " " + intTest;
                    intTest++;
                    if(isRunning){
                        Message msg = handler.obtainMessage(1, data);
                        handler.sendMessage(msg);
                    }

                }
            }catch (Throwable ignored){

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        initBgThread();
    }

    @Override
    protected void onStop() {
        super.onStop();
        isRunning = false;
    }
}