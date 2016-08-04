package com.example.hyejin.slimtest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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

/**
 * Created by hyejin on 2016-07-04.
 */
public class noticeActivity extends AppCompatActivity {
    private ListView                m_ListView;
    private CustomAdapter_notice           m_Adapter;
    private ArrayList<list_item_notice>    m_List;
    TextView title_notice;
    TextView date_notice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        setTitle("공지사항");
        // Xml에서 추가한 ListView 연결
        m_ListView = (ListView) findViewById(R.id.listview_notice);
        m_List = new ArrayList<list_item_notice>();

        title_notice = (TextView)findViewById(R.id.title_notice);
        date_notice = (TextView)findViewById(R.id.date_notice);


        //선택한 과목 정보 받아오기
        final list_item item = (list_item) getIntent().getSerializableExtra("notice");


                //연결
        SelectSubjectList selectSubjectList = new SelectSubjectList();
        selectSubjectList.execute("http://14.63.196.146/announcement.php", item.num);


    }

    private class SelectSubjectList extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            StringBuilder jsonHtml = new StringBuilder();
            try {
                // 연결 URL 설정
                Log.d("params[0]", params[0]);
                Log.d("params[1]", params[1]);
                URL url = new URL(params[0]);  // URL 설정
                // 커넥션 객체 생성
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();// 접속
                if(conn != null) {// 연결되었으면..
                    //--------------------------
                    //   전송 모드 설정 - 기본적인 설정이다
                    //--------------------------
                    conn.setConnectTimeout(10000);
                    conn.setUseCaches(false);
                    conn.setDoInput(true);                         // 서버에서 읽기 모드 지정
                    conn.setDoOutput(true);                        // 서버로 쓰기 모드 지정
                    conn.setRequestMethod("POST");                // 전송 방식은 POST
                    // 서버에게 웹에서 <Form>으로 값이 넘어온 것과 같은 방식으로 처리하라는 걸 알려준다
                    conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");

                    //--------------------------
                    //   서버로 값 전송
                    //--------------------------
                    StringBuffer buffer = new StringBuffer();
                    buffer.append("sub_id").append("=").append(params[1]).append("&").append("select").append("=").append("100");

                    OutputStreamWriter outStream = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
                    PrintWriter writer = new PrintWriter(outStream);
                    writer.write(buffer.toString());
                    writer.flush();

                    Log.d("buffer", buffer.toString());
                    // 연결 되었다는 코드가 리턴되면..
                    if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) {

                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                        Log.d("conn.getInputStream()", ""+conn.getInputStream());
                        for(;;){
                            // 웹상에 보여지는 텍스트를 라인단위로 읽어 저장.
                            String line = br.readLine();
                            if(line == null) break;
                            // 저장된 텍스트 라인을 jsonHtml에 붙여넣음
                            jsonHtml.append(line+"\n");
                        }
                        br.close();
                    }
                    conn.disconnect();
                }
            }catch (Exception ex) {
                ex.printStackTrace();
            }
            return jsonHtml.toString();
        }

        @Override
        protected void onPostExecute(String str) {
            String id = "";
            String title = "";
            String content = "";
            String sub_id = "";
            String date = "";


            try {
                Log.d("str", str);
                JSONObject root = new JSONObject(str); //str 전체 긁어온 값

                Log.d("rownum", root.getInt("rownum") + "");

                if(root.getInt("rownum") == 0) {
                    Toast.makeText(getApplicationContext(), "데이터를 불러오는데 실패하였습니다.", Toast.LENGTH_LONG).show();
//                    showProgress(false);
                    return;
                }
                JSONArray ja = root.getJSONArray("result");//[{}]
                String[] tempList = null;
                for(int i=0;i<ja.length();i++) {
                    JSONObject jo = ja.getJSONObject(i);//{}
                    id = jo.getString("id");
                    title = jo.getString("title");
                    content = jo.getString("content");
                    sub_id = jo.getString("sub_id");
                    date = jo.getString("date");


                    m_List.add(new list_item_notice(id, title, content, sub_id, date));
//                showProgress(false);
                }

                m_Adapter = new CustomAdapter_notice(noticeActivity.this, m_List);

                // ListView에 어댑터 연결
                m_ListView.setAdapter(m_Adapter);
                m_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getApplicationContext(), noticeActivity_detail.class);
                        intent.putExtra("notice",m_List.get(position));
                        startActivity(intent);
                    }
                });
            } catch(JSONException ex) {
                Toast.makeText(getApplicationContext(), "데이터를 불러오는데 실패하였습니다.", Toast.LENGTH_LONG).show();
                ex.printStackTrace();
            }
        }
    }
}