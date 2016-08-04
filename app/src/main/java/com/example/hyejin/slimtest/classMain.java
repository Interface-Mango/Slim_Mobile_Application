package com.example.hyejin.slimtest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
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
import java.util.List;

/**
 * Created by hyejin on 2016-07-04.
 */
public class classMain extends AppCompatActivity {
    ImageView noticeButton;
    ImageView questionButton;
    ImageView attendButton;
    ImageView qnaButton;
    ImageView advicenoteButton;
    TextView sub_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_main);

        sub_name = (TextView) findViewById(R.id.bar);
        noticeButton = (ImageView) findViewById(R.id.notice_btn);
        questionButton = (ImageView) findViewById(R.id.question_btn);
        attendButton = (ImageView) findViewById(R.id.attendence_btn);
        qnaButton = (ImageView) findViewById(R.id.qna_btn);
        advicenoteButton = (ImageView) findViewById(R.id.advicenote_btn);


//        sub_name.setText(intent.getStringExtra("NAME"));

        final list_item item = (list_item) getIntent().getSerializableExtra("user");
        sub_name.setText(item.title);


        //공지사항 페이지로 이동
        noticeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), noticeActivity.class);
                intent.putExtra("notice",item);
                startActivity(intent);
            }
        });

        //나의질문 페이지로 이동
        questionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), myQuestionActivity.class);
                startActivity(intent);
            }
        });

        //출결조회 페이지로 이동
        attendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), attendActivity.class);
                startActivity(intent);
            }
        });

        //Q&A 페이지로 이동
        qnaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), qnaActivity.class);
                startActivity(intent);
            }
        });

        //알림장 페이지로 이동
        advicenoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), adviceNoteActivity.class);
                startActivity(intent);
            }
        });

    }

//    private class SelectSubjectList extends AsyncTask<String, Integer, String> {
//        @Override
//        protected String doInBackground(String... params) {
//            StringBuilder jsonHtml = new StringBuilder();
//            try {
//                // 연결 URL 설정
//                Log.d("params[0]", params[0]);
//                Log.d("params[1]", params[1]);
//                URL url = new URL(params[0]);  // URL 설정
//                // 커넥션 객체 생성
//                HttpURLConnection conn = (HttpURLConnection)url.openConnection();// 접속
//                if(conn != null) {// 연결되었으면..
//                    //--------------------------
//                    //   전송 모드 설정 - 기본적인 설정이다
//                    //--------------------------
//                    conn.setConnectTimeout(10000);
//                    conn.setUseCaches(false);
//                    conn.setDoInput(true);                         // 서버에서 읽기 모드 지정
//                    conn.setDoOutput(true);                        // 서버로 쓰기 모드 지정
//                    conn.setRequestMethod("POST");                // 전송 방식은 POST
//                    // 서버에게 웹에서 <Form>으로 값이 넘어온 것과 같은 방식으로 처리하라는 걸 알려준다
//                    conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
//
//                    //--------------------------
//                    //   서버로 값 전송
//                    //--------------------------
//                    StringBuffer buffer = new StringBuffer();
//                    buffer.append("sub_ids").append("=").append(params[1]);
//
//                    OutputStreamWriter outStream = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
//                    PrintWriter writer = new PrintWriter(outStream);
//                    writer.write(buffer.toString());
//                    writer.flush();
//
//                    Log.d("buffer", buffer.toString());
//                    // 연결 되었다는 코드가 리턴되면..
//                    if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
//
//                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
//                        Log.d("conn.getInputStream()", ""+conn.getInputStream());
//                        for(;;){
//                            // 웹상에 보여지는 텍스트를 라인단위로 읽어 저장.
//                            String line = br.readLine();
//                            if(line == null) break;
//                            // 저장된 텍스트 라인을 jsonHtml에 붙여넣음
//                            jsonHtml.append(line+"\n");
//                        }
//                        br.close();
//                    }
//                    conn.disconnect();
//                }
//            }catch (Exception ex) {
//                ex.printStackTrace();
//            }
//            return jsonHtml.toString();
//        }
//        @Override
//        protected void onPostExecute(String str) {
//            String sub_id = "";
//            String sub_name = "";
//            String lecturer_id = "";
//            String time_string = "";
//            String location = "";
//            String lecturer_name = "";
//
//            try {
//                Log.d("str", str);
//                JSONObject root = new JSONObject(str); //str 전체 긁어온 값
//
//                Log.d("rownum", root.getInt("rownum") + "");
//
//                if(root.getInt("rownum") == 0) {
//                    Toast.makeText(getApplicationContext(), "데이터를 불러오는데 실패하였습니다.", Toast.LENGTH_LONG).show();
////                    showProgress(false);
//                    return;
//                }
//                JSONArray ja = root.getJSONArray("result");//[{}]
////                String[] tempList = null;
//                for(int i=0;i<ja.length();i++) {
//                    JSONObject jo = ja.getJSONObject(i);//{}
//                    sub_id = jo.getString("sub_id");
//                    sub_name = jo.getString("sub_name");
//                    lecturer_id = jo.getString("lecturer_id");
//                    time_string = jo.getString("time");
//                    location = jo.getString("location");
//                    lecturer_name = jo.getString("user_name");
//
//
////                showProgress(false);
//                }
//
//
//            } catch(JSONException ex) {
//                Toast.makeText(getApplicationContext(), "데이터를 불러오는데 실패하였습니다.", Toast.LENGTH_LONG).show();
//                ex.printStackTrace();
//            }
//        }
//        }

}
