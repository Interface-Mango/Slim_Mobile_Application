package com.example.hyejin.slimtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by hyejin on 2016-07-04.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class myQuestionActivity extends AppCompatActivity {
    private ListView                m_ListView;
    private CustomAdapter_myque           m_Adapter;
    ArrayList<list_item> m_List;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myquestion);
        m_ListView = (ListView) findViewById(R.id.listview_myque);
        m_List = new ArrayList<list_item>();


        m_List.add(new list_item("1","오버로딩 ",new Date(System.currentTimeMillis())));
        m_List.add(new list_item("2","C# 질문",new Date(System.currentTimeMillis())));
        m_List.add(new list_item("3","안드로이드 질문",new Date(System.currentTimeMillis())));


        m_Adapter = new CustomAdapter_myque(myQuestionActivity.this,m_List);

        // ListView에 어댑터 연결
        m_ListView.setAdapter(m_Adapter);
        m_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), myQuestionActivity_detail.class);
                startActivity(intent);
            }
        });


    }
}
