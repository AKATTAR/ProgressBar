package com.example.progressbar;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.InterruptedIOException;

public class MainActivity extends AppCompatActivity {
    private ProgressBar bar1;
    private int progressStatus = 0;
    private TextView textView;
    private Handler handler = new Handler();
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bar1 = findViewById(R.id.progressBar1);
        textView = findViewById(R.id.textView);
        button=findViewById(R.id.button);
        //start long running operation in a background thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    //update the progress bar and display the
                    //current value in the next view
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            bar1.setProgress(progressStatus);
                            textView.setText(progressStatus + "/" + bar1.getMax());
                        }
                    });
                    try {
                        //sleep for 200 miliseconds
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // startActivity(new Intent(this.class));
                }
            }
        }).start();

    }
}