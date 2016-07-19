package com.example.hyejin.slimtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by hyejin on 2016-07-04.
 */
public class classActivity extends AppCompatActivity {
    private ListView                m_ListView;
    private CustomAdapter           m_Adapter;
    ArrayList<list_item>             m_List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        // Xml에서 추가한 ListView 연결
        m_ListView = (ListView) findViewById(R.id.listview);
        m_List = new ArrayList<list_item>();


        m_List.add(new list_item("1","C#",new Date(System.currentTimeMillis())));
        m_List.add(new list_item("2","C++",new Date(System.currentTimeMillis())));
        m_List.add(new list_item("3","Android",new Date(System.currentTimeMillis())));


        m_Adapter = new CustomAdapter(classActivity.this,m_List);

        // ListView에 어댑터 연결
        m_ListView.setAdapter(m_Adapter);
        m_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), classMain.class);
                startActivity(intent);
            }
        });




    }
}