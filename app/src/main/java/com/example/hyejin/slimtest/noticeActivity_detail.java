package com.example.hyejin.slimtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by hyejin on 2016-07-21.
 */
public class noticeActivity_detail extends AppCompatActivity {

    TextView textView_notice_title;
    TextView textView_notice_date;
    TextView textView_notice_content;
    Button notice_detail_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);

        //선택한 과목 정보 받아오기
        final list_item_notice item = (list_item_notice) getIntent().getSerializableExtra("notice");

        textView_notice_title = (TextView)findViewById(R.id.textView_notice_title);
        textView_notice_date =(TextView) findViewById(R.id.textView_notice_date);
        textView_notice_content = (TextView)findViewById(R.id.textView_notice_content);
        notice_detail_btn = (Button)findViewById(R.id.notice_detail_btn) ;

        textView_notice_title.setText(item.getTitle());
        textView_notice_date.setText(item.getDate());
        textView_notice_content.setText(item.getContent());


        notice_detail_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
