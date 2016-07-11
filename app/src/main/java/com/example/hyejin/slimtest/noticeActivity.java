package com.example.hyejin.slimtest;

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

public class noticeActivity extends AppCompatActivity {

    private ListView                m_ListView;
    private ArrayAdapter<String>    m_Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        // Android에서 제공하는 string 문자열 하나를 출력 가능한 layout으로 어댑터 생성
        m_Adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);

        // Xml에서 추가한 ListView 연결
        m_ListView = (ListView) findViewById(R.id.listview);

        // ListView에 어댑터 연결
        m_ListView.setAdapter(m_Adapter);

        // ListView 아이템 터치 시 이벤트 추가
        m_ListView.setOnItemClickListener(onClickListItem);

        // ListView에 아이템 추가
        m_Adapter.add("하스스톤");
        m_Adapter.add("몬스터 헌터");
        m_Adapter.add("디아블로");
        m_Adapter.add("와우");
        m_Adapter.add("리니지");
        m_Adapter.add("안드로이드");
        m_Adapter.add("아이폰");
    }

    // 아이템 터치 이벤트
    private OnItemClickListener onClickListItem = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            // 이벤트 발생 시 해당 아이템 위치의 텍스트를 출력
            Toast.makeText(getApplicationContext(), m_Adapter.getItem(arg2), Toast.LENGTH_SHORT).show();
        }
    };
}
