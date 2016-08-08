package com.example.hyejin.slimtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by hyejin on 2016-08-08.
 */
public class adviceNoteActivity_edit extends AppCompatActivity {
    EditText advice_edit_title;
    EditText advice_edit_content;

    Button edit_advice_enroll;
    Button edit_advice_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice_edit);
        setTitle("글쓰기");

        advice_edit_title = (EditText)findViewById(R.id.advice_edit_title);
        advice_edit_content = (EditText)findViewById(R.id.advice_edit_content);
        edit_advice_enroll = (Button)findViewById(R.id.edit_advice_enroll);
        edit_advice_back = (Button)findViewById(R.id.edit_advice_back);

        edit_advice_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
