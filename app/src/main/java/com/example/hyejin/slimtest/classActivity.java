package com.example.hyejin.slimtest;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
import java.util.ArrayList;

/**
 * Created by hyejin on 2016-07-04.
 */
public class classActivity extends AppCompatActivity {
    private ListView                m_ListView;
    private CustomAdapter           m_Adapter;
    private ArrayList<list_item>    m_List;
    boolean bLog = false; // false : 로그아웃 상태

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        setTitle("수업 목록");

        // Xml에서 추가한 ListView 연결
        m_ListView = (ListView) findViewById(R.id.listview);
        m_List = new ArrayList<list_item>();

        SelectSubjectList selectSubjectList = new SelectSubjectList();
        selectSubjectList.execute("http://14.63.196.146/subject.php", LoginActivity.UserInfo.get(4).toString());

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // 메뉴버튼이 처음 눌러졌을 때 실행되는 콜백메서드
        // 메뉴버튼을 눌렀을 때 보여줄 menu 에 대해서 정의
        getMenuInflater().inflate(R.menu.main, menu);
        Log.d("test", "onCreateOptionsMenu - 최초 메뉴키를 눌렀을 때 호출됨");
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 메뉴의 항목을 선택(클릭)했을 때 호출되는 콜백메서드
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.d("test", "onOptionsItemSelected - 메뉴항목을 클릭했을 때 호출됨");

        int id = item.getItemId();


        switch(id) {
            case R.id.menu_logout:
                new AlertDialog.Builder(this)
                        .setTitle("로그아웃").setMessage("로그아웃 하시겠습니까?")
                        .setPositiveButton("로그아웃", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Intent i = new Intent(classActivity.this, LoginActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(i);
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        })
                        .show();

                return true;

        }
        return super.onOptionsItemSelected(item);
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
                    buffer.append("sub_ids").append("=").append(params[1]);

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
            String sub_id = "";
            String sub_name = "";
            String lecturer_id = "";
            String time_string = "";
            String location = "";
            String lecturer_name = "";


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
                    sub_id = jo.getString("sub_id");
                    sub_name = jo.getString("sub_name");
                    lecturer_id = jo.getString("lecturer_id");
                    time_string = jo.getString("time");
                    location = jo.getString("location");
                    lecturer_name = jo.getString("user_name");

                    m_List.add(new list_item(sub_id, sub_name, time_string, location, lecturer_name));
//                showProgress(false);
                }

                m_Adapter = new CustomAdapter(classActivity.this, m_List);

                // ListView에 어댑터 연결
                m_ListView.setAdapter(m_Adapter);
                m_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getApplicationContext(), classMain.class);
                        intent.putExtra("user",m_List.get(position));
                        intent.putExtra("NAME",m_List.get(position).title);
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