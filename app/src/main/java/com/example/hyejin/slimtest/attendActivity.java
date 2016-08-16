package com.example.hyejin.slimtest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
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
        setTitle("출석 확인");
        // Xml에서 추가한 ListView 연결
        m_ListView = (ListView) findViewById(R.id.listview_attend);
        m_List = new ArrayList<list_item_attend>();

//
        //선택한 과목 정보 받아오기
        final list_item item = (list_item) getIntent().getSerializableExtra("attend");


        //연결
        SelectSubjectList selectSubjectList = new SelectSubjectList();
        selectSubjectList.execute("http://14.63.196.146/attendance.php", LoginActivity.UserInfo.get(0).toString(), item.num);

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
                        buffer.append("std_id").append("=").append(params[1]).append("&").
                                append("sub_id").append("=").append(params[2]).append("&").
                                append("select").append("=").append("100");


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
                String attend_id;
                String sub_num;
                String std_num;
                String attend_time;
                String attend_check;


                try {
                    Log.d("str", str);
                    JSONObject root = new JSONObject(str); //str 전체 긁어온 값

                    Log.d("rownum", root.getInt("rownum") + "");

                    if(root.getInt("rownum") == 0) {
                        Toast.makeText(getApplicationContext(), "데이터를 불러오는데 실패하였습니다.", Toast.LENGTH_LONG).show();
                        return;
                    }
                    JSONArray ja = root.getJSONArray("result");//[{}]
                    String[] tempList = null;
                    for(int i=0;i<ja.length();i++) {
                        JSONObject jo = ja.getJSONObject(i);//{}
                        attend_id = jo.getString("id");
                        sub_num = jo.getString("sub_id");
                        std_num = jo.getString("std_id");
                        attend_time = jo.getString("date");
                        attend_check = jo.getString("check");

                        m_List.add(new list_item_attend(attend_id, sub_num, std_num, attend_time, attend_check));
                    }

                    m_Adapter = new CustomAdapter_attend(attendActivity.this, m_List);

                    // ListView에 어댑터 연결
                    m_ListView.setAdapter(m_Adapter);


                } catch(JSONException ex) {
                    Toast.makeText(getApplicationContext(), "데이터를 불러오는데 실패하였습니다.", Toast.LENGTH_LONG).show();
                    ex.printStackTrace();
                }
            }
        }
    }
