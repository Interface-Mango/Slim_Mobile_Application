package com.example.hyejin.slimtest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by hyejin on 2016-08-04.
 */
public class adviceNoteActivity_detail extends AppCompatActivity {
    phpDelete phpDelete;
    public list_item item1;
    TextView textView_advicenote_title;
    TextView textView_advicenote_date;
    TextView textView_advicenote_content;

    Button modify_advice;
    Button delete_advice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advicenote_detail);
        final list_item_advice_note item = (list_item_advice_note) getIntent().getSerializableExtra("advice_detail");
        final list_item item1 = (list_item) getIntent().getSerializableExtra("advice_info");


        textView_advicenote_title = (TextView) findViewById(R.id.textView_advicenote_title);
        textView_advicenote_date = (TextView) findViewById(R.id.textView_advicenote_date);
        textView_advicenote_content = (TextView) findViewById(R.id.textView_advicenote_content);

        modify_advice = (Button) findViewById(R.id.modify_advice);
        delete_advice = (Button) findViewById(R.id.delete_advice);

        textView_advicenote_title.setText(item.getAdvice_title());
        textView_advicenote_date.setText(item.getAdvice_date());
        textView_advicenote_content.setText(item.getAdvice_content());

        modify_advice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), adviceNoteActivity_modify.class);
                intent.putExtra("advice_modify",item);
                intent.putExtra("advice_info",item1);
                startActivity(intent);
            }
        });
        delete_advice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phpDelete = new phpDelete();
                phpDelete.execute("http://14.63.196.146/advice_note.php",item.getAdvice_note_id());
                Toast.makeText(getApplicationContext(),"삭제되었습니다.", Toast.LENGTH_SHORT).show();
                Intent reload = new Intent(getApplicationContext() ,adviceNoteActivity.class);
                reload.putExtra("advice",item1);
                reload.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                reload.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                reload.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                getApplicationContext().startActivity(reload);


            }
        });

    }
    public class phpDelete extends AsyncTask<String, Integer, String> {

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
                    buffer.append("id").append("=").append(params[1]).append("&").
                            append("select").append("=").append("400");
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
    }
}
