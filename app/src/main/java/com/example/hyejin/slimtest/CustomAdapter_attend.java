package com.example.hyejin.slimtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hyejin on 2016-07-21.
 */
public class CustomAdapter_attend extends BaseAdapter{
    // 문자열을 보관 할 ArrayList
    Context context;
    ArrayList<list_item_attend> m_List;
    private String check="";

    //생성자
    public CustomAdapter_attend(Context context, ArrayList<list_item_attend> m_List) {
        this.context = context;
        this.m_List = m_List;
    }


    // 현재 아이템의 수를 리턴
    @Override
    public int getCount() {
        return m_List.size();
    }

    // 현재 아이템의 오브젝트를 리턴, Object를 상황에 맞게 변경하거나 리턴받은 오브젝트를 캐스팅해서 사용
    @Override
    public Object getItem(int position) {
        return m_List.get(position);
    }

    // 아이템 position의 ID 값 리턴
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 출력 될 아이템 관리
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // 리스트가 길어지면서 현재 화면에 보이지 않는 아이템은 converView가 null인 상태로 들어 옴
        if ( convertView == null ) {
            // view가 null일 경우 커스텀 레이아웃을 얻어 옴
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_custom_list_attend, parent, false);

            // TextView에 현재 position의 문자열 추가
            TextView num = (TextView) convertView.findViewById(R.id.time_attend);
            num.setText(m_List.get(position).getAttend_time().toString());

            TextView title = (TextView) convertView.findViewById(R.id.check_attend);


                if (m_List.get(position).getAttend_check().toString() == "1") {
                    check = "출석";
                    title.setText(check);
                } else if (m_List.get(position).getAttend_check().toString() == "2") {
                    check = "지각";
                    title.setText(check);
                } else if(m_List.get(position).getAttend_check().toString() == "3"){
                    check = "결석";
                    title.setText(check);
                }

        }



        return convertView;
    }



}
