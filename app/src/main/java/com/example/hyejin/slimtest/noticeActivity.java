package com.example.hyejin.slimtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by hyejin on 2016-07-04.
 */

public class noticeActivity extends AppCompatActivity {
    private ListView                m_ListView;
    private CustomAdapter_notice           m_Adapter;
    ArrayList<list_item> m_List;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        m_ListView = (ListView) findViewById(R.id.listview_notice);
        m_List = new ArrayList<list_item>();


//        m_List.add(new list_item("1","숙제 공지",new Date(System.currentTimeMillis())));
//        m_List.add(new list_item("2","휴강 공지",new Date(System.currentTimeMillis())));
//        m_List.add(new list_item("3","보강 공지",new Date(System.currentTimeMillis())));


        m_Adapter = new CustomAdapter_notice(noticeActivity.this,m_List);

        // ListView에 어댑터 연결
        m_ListView.setAdapter(m_Adapter);
        m_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), noticeActivity_detail.class);
                startActivity(intent);
            }
        });


    }
}
