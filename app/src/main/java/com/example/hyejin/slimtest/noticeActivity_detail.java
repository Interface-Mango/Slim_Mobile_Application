package com.example.hyejin.slimtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by hyejin on 2016-07-21.
 */
public class noticeActivity_detail extends AppCompatActivity {

    TextView textView_notice_title;
    TextView textView_notice_time;
    TextView textView_notice_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);

        //선택한 과목 정보 받아오기
        final list_item_notice item = (list_item_notice) getIntent().getSerializableExtra("notice");

        textView_notice_title = (TextView)findViewById(R.id.textView_notice_title);
        textView_notice_time =(TextView) findViewById(R.id.textView_notice_time);
        textView_notice_content = (TextView)findViewById(R.id.textView_notice_content);

        textView_notice_title.setText(item.getTitle());
        textView_notice_time.setText(item.getDate());
        textView_notice_content.setText(item.getContent());




    }
}
