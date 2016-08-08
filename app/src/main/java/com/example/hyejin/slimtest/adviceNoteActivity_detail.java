package com.example.hyejin.slimtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by hyejin on 2016-08-04.
 */
public class adviceNoteActivity_detail extends AppCompatActivity {

    TextView textView_advicenote_title;
    TextView textView_advicenote_date;
    TextView textView_advicenote_content;

    Button modify_advice;
    Button delete_advice;
    Button edit_advice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advicenote_detail);
        final list_item_advice_note item = (list_item_advice_note) getIntent().getSerializableExtra("advice_detail");

        textView_advicenote_title = (TextView) findViewById(R.id.textView_advicenote_title);
        textView_advicenote_date = (TextView) findViewById(R.id.textView_advicenote_date);
        textView_advicenote_content = (TextView) findViewById(R.id.textView_advicenote_content);

        modify_advice = (Button) findViewById(R.id.modify_advice);
        delete_advice = (Button) findViewById(R.id.delete_advice);
        edit_advice = (Button) findViewById(R.id.edit_advice);

        textView_advicenote_title.setText(item.getAdvice_title());
        textView_advicenote_date.setText(item.getAdvice_date());
        textView_advicenote_content.setText(item.getAdvice_content());

//        modify_advice.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), adviceNoteActivity.class);
//                startActivity(intent);
//            }
//        });
//        delete_advice.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), adviceNoteActivity.class);
//                startActivity(intent);
//            }
//        });
        edit_advice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), adviceNoteActivity_edit.class);
                startActivity(intent);
            }
        });
    }
}
