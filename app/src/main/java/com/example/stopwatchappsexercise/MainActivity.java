package com.example.stopwatchappsexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView timerTxt, creditTxt;
    private Button startButton, stopButton, resetButton;

    private void init() {
        timerTxt = findViewById(R.id.textView_timer);
        creditTxt = findViewById(R.id.textView_credit);
        startButton = findViewById(R.id.button_start);
        stopButton = findViewById(R.id.button_stop);
        resetButton = findViewById(R.id.button_reset);

        creditTxt.setText("Created by\nKevin Bachtiar Santoso - 2201728566\nFor Mobile Programming Class");
    }

    private int seconds = 0;
    private boolean running;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public void onClickStart(View view) {
        if(running == true) return;
        running = true;
        runTimer();
    }
    public void onClickStop(View view) {
        if(running == false) return;
        running = false;
        stopTimer();
    }
    public void onClickReset(View view) {
        running = false;
        seconds = 0;
        stopTimer();
        time = "0:00:00";
        timerTxt.setText(time);
    }

    Handler handler;
    Runnable run;
    private void runTimer() {
        handler = new Handler();
        run = new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, secs);
                timerTxt.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        };

        handler.post(run);
    }

    private void stopTimer(){
        handler.removeCallbacks(run);
    }

}
