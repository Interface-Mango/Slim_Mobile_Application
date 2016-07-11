package com.example.hyejin.slimtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by hyejin on 2016-07-04.
 */
public class Main extends AppCompatActivity {
    ImageView classlist;
    ImageView hwcheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        classlist = (ImageView) findViewById(R.id.classlist);
        hwcheck = (ImageView) findViewById(R.id.hwcheck);

        classlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), classActivity.class);
                startActivity(intent);
            }
        });
        hwcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), hwActivity.class);
                startActivity(intent);
            }
        });
    }
}
