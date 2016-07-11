package com.example.hyejin.slimtest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by hyejin on 2016-07-04.
 */
public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler(){
            public void handleMessage(Message msg){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        };

        handler.sendEmptyMessageDelayed(0, 2000); // 2초 후 넘어가도록 설정.
    }
}
