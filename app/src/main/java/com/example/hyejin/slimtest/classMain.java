package com.example.hyejin.slimtest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyejin on 2016-07-04.
 */
public class classMain extends AppCompatActivity {
    ImageView noticeButton;
    ImageView questionButton;
    ImageView attendButton;
    ImageView advicenoteButton;
    ImageView backButton;
    TextView sub_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_main);

        noticeButton = (ImageView) findViewById(R.id.notice_btn);
        questionButton = (ImageView) findViewById(R.id.question_btn);
        attendButton = (ImageView) findViewById(R.id.attendence_btn);
        advicenoteButton = (ImageView) findViewById(R.id.advicenote_btn);
        backButton = (ImageView) findViewById(R.id.back_btn);

        final list_item item = (list_item) getIntent().getSerializableExtra("user");
        setTitle(item.title);


        //공지사항 페이지로 이동
        noticeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), noticeActivity.class);
                intent.putExtra("notice",item);
                startActivity(intent);
            }
        });

        //나의질문 페이지로 이동
        questionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), myQuestionActivity.class);
                intent.putExtra("user_info",item);
                startActivity(intent);
            }
        });

        //출결조회 페이지로 이동
        attendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), attendActivity.class);
                intent.putExtra("attend",item);
                startActivity(intent);
            }
        });

        //알림장 페이지로 이동
        advicenoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), adviceNoteActivity.class);
                intent.putExtra("advice",item);
                startActivity(intent);
            }
        });

        //과목선택 페이지로 이동
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), classActivity.class);
                // 생명주기 이전에 있던 액티비티 스택 제거//
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                  startActivity(intent);


            }
        });
    }

}
