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
public class CustomAdapter_myque extends BaseAdapter{
    // 문자열을 보관 할 ArrayList
    Context context;
    ArrayList<list_item_myque> m_List;

    //생성자
    public CustomAdapter_myque(Context context, ArrayList<list_item_myque> m_List) {
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
            convertView = inflater.inflate(R.layout.activity_custom_list_myque, parent, false);

            // TextView에 현재 position의 문자열 추가

            TextView title = (TextView) convertView.findViewById(R.id.title_myque);
            title.setText(m_List.get(position).getMyque_content());

            TextView date = (TextView) convertView.findViewById(R.id.date_myque);
            date.setText(m_List.get(position).getMyque_date().toString());

            // 버튼을 터치 했을 때 이벤트 발생
//            TextView num_btn = (TextView) convertView.findViewById(R.id.title);
//            num_btn.setOnClickListener(new OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    // 터치 시 해당 아이템 이름 출력
//                    Intent intent = new Intent(context.getApplicationContext(), classMain.class); // 다음 넘어갈 클래스 지정
//                    context.startActivity(intent);
//                }
//            });
//
//            // 리스트 아이템을 터치 했을 때 이벤트 발생
//            convertView.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(getApplicationContext(), classMain.class); // 다음 넘어갈 클래스 지정
//                    startActivity(intent);
//                }
//            });


        }

        return convertView;
    }



   /* // 외부에서 아이템 추가 요청 시 사용
    public void add(String _msg) {
        boolean add = m_List.add(_msg);
    }

    // 외부에서 아이템 삭제 요청 시 사용
    public void remove(int _position) {
        m_List.remove(_position);
    }
*/
}
