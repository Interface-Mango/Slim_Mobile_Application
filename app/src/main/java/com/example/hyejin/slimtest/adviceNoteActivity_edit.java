package com.example.hyejin.slimtest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hyejin on 2016-08-08.
 */
public class adviceNoteActivity_edit extends AppCompatActivity {
    EditText advice_edit_title;
    EditText advice_edit_content;

    Button edit_advice_enroll;
    Button edit_advice_back;
    phpInsert phpInsert;
    String advice_title;
    String advice_content;


    @Override
    protected   void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice_edit);
        setTitle("글쓰기");
        final list_item item = (list_item) getIntent().getSerializableExtra("advice_edit");

        advice_edit_title = (EditText) findViewById(R.id.advice_edit_title);
        advice_edit_content = (EditText) findViewById(R.id.advice_edit_content);
        edit_advice_enroll = (Button) findViewById(R.id.edit_advice_enroll);
        edit_advice_back = (Button) findViewById(R.id.edit_advice_back);


        edit_advice_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edit_advice_enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                advice_title = String.valueOf(advice_edit_title.getText());
                advice_content = String.valueOf(advice_edit_content.getText());
                phpInsert = new phpInsert();
                phpInsert.execute("http://14.63.196.146/advice_note.php",LoginActivity.UserInfo.get(0).toString(),item.num,
                        advice_title, advice_content, getLastExecTime());
                                    //페이지, user_id, sub_id,
                                    //title, content, date
                Toast.makeText(getApplicationContext(),"등록되었습니다.", Toast.LENGTH_SHORT).show();
                Intent reload = new Intent(getApplicationContext() ,adviceNoteActivity.class);
                reload.putExtra("advice",item);
                reload.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                reload.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                reload.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                getApplicationContext().startActivity(reload);

            }
        });
    }
   public String getLastExecTime()
   {
       Date date = new Date();
       SimpleDateFormat sdfCurTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       String strCurTime = sdfCurTime.format(date.getTime()).toString();

       return  strCurTime;
   }



   public class phpInsert extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            StringBuilder jsonHtml = new StringBuilder();
            try {
                // 연결 URL 설정
                Log.d("params[0]", params[0]);
//                Log.d("params[1]", params[1]);
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
                            append("title").append("=").append(params[3]).append("&").
                            append("content").append("=").append(params[4]).append("&").
                            append("date").append("=").append(params[5]).append("&").
                            append("select").append("=").append("300");
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
//       protected void onPostExecute(String str){
//           if(str.equals(true)){
//               Toast.makeText(getApplicationContext(),"알림장 저장을 성공하였습니다.",Toast.LENGTH_LONG).show();
//               finish();
//           }else{
//               Toast.makeText(getApplicationContext(),"알림장 저장을 실패하였습니다.",Toast.LENGTH_LONG).show();
//               finish();
//           }
//
//       }

   }
}
