package com.example.hyejin.slimtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by hyejin on 2016-08-04.
 */
public class adviceNoteActivity_detail extends AppCompatActivity {
    Button advicenote_modify;
    Button advicenote_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advicenote_detail);

        advicenote_modify = (Button) findViewById(R.id.advicenote_modify);
        advicenote_delete = (Button) findViewById(R.id.advicenote_delete);


        advicenote_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), noticeActivity.class);
                startActivity(intent);
            }
        });
        advicenote_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), noticeActivity.class);
                startActivity(intent);
            }
        });
    }
}
