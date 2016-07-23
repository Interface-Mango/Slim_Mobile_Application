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
public class attendActivity extends AppCompatActivity {
    private ListView                       m_ListView;
    private CustomAdapter_attend           m_Adapter;
    ArrayList<list_item_attend>             m_List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attend);
        // Xml에서 추가한 ListView 연결
        m_ListView = (ListView) findViewById(R.id.listview_attend);
        m_List = new ArrayList<list_item_attend>();


        m_List.add(new list_item_attend("2016-07-25 09:05","출석"));
        m_List.add(new list_item_attend("2016-07-23","결석"));
        m_List.add(new list_item_attend("2016-07-21 09:35","지각"));


        m_Adapter = new CustomAdapter_attend(attendActivity.this,m_List);

           // ListView에 어댑터 연결
        m_ListView.setAdapter(m_Adapter);
//        m_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getApplicationContext(), classMain.class);
//                startActivity(intent);
//            }
//        });




    }
}