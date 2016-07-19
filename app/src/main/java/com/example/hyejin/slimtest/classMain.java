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
public class classMain extends AppCompatActivity {
    ImageView noticeButton;
    ImageView questionButton;
    ImageView attendButton;
    ImageView qnaButton;
    ImageView advicenoteButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_main);

        noticeButton = (ImageView) findViewById(R.id.notice_btn);
        questionButton = (ImageView) findViewById(R.id.question_btn);
        attendButton = (ImageView) findViewById(R.id.attendence_btn);
        qnaButton = (ImageView) findViewById(R.id.qna_btn);
        advicenoteButton = (ImageView) findViewById(R.id.advicenote_btn);

        //공지사항 페이지로 이동
        noticeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), noticeActivity.class);
                startActivity(intent);
            }
        });

        //나의질문 페이지로 이동
        questionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), myQuestionActivity.class);
                startActivity(intent);
            }
        });

        //출결조회 페이지로 이동
        attendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), attendActivity.class);
                startActivity(intent);
            }
        });

        //Q&A 페이지로 이동
        qnaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), qnaActivity.class);
                startActivity(intent);
            }
        });

        //알림장 페이지로 이동
        advicenoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), adviceNoteActivity.class);
                startActivity(intent);
            }
        });

    }
}
