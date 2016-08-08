package com.example.hyejin.slimtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by hyejin on 2016-07-21.
 */
public class myQuestionActivity_detail extends AppCompatActivity{

    TextView textView_myque_title;
    TextView textView_myque_date;
    TextView textView_myque_content;

    Button modify_myque;
    Button delete_myque;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myquestion_detail);
        final list_item_myque item = (list_item_myque) getIntent().getSerializableExtra("myque_detail");

        textView_myque_title = (TextView) findViewById(R.id.textView_myque_title);
        textView_myque_date = (TextView) findViewById(R.id.textView_myque_date);
        textView_myque_content = (TextView) findViewById(R.id.textView_myque_content);

        modify_myque = (Button) findViewById(R.id.modify_myque);
        delete_myque = (Button) findViewById(R.id.delete_myque);

        textView_myque_title.setText(item.getMyque_content());
        textView_myque_date.setText(item.getMyque_date());
        textView_myque_content.setText(item.getMyque_content());

//        myque_modify.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), noticeActivity.class);
//                startActivity(intent);
//            }
//        });
//        myque_delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), noticeActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}
