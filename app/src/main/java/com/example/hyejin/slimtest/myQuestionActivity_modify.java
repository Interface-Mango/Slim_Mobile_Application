package com.example.hyejin.slimtest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by hyejin on 2016-08-04.
 */
public class myQuestionActivity_modify extends AppCompatActivity {
    phpModify phpModify;

    TextView myque_modify_title;
    EditText myque_modify_content;

    Button modify_myque_enroll;
    Button modify_myque_back;

    String myque_title;
    String myque_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myque_modify);
        setTitle("글수정");
        final list_item_myque item = (list_item_myque) getIntent().getSerializableExtra("myque_modify");
        final list_item item1 = (list_item) getIntent().getSerializableExtra("myque_info");


        myque_modify_content = (EditText)findViewById(R.id.myque_modify_content);
        modify_myque_enroll = (Button)findViewById(R.id.modify_myque_enroll);
        modify_myque_back = (Button)findViewById(R.id.modify_myque_back);

        modify_myque_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        modify_myque_enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myque_content = String.valueOf(myque_modify_content.getText());
                phpModify = new phpModify();
                phpModify.execute("http://14.63.196.146/my_question.php",myque_content,item.getMyque_id());
                Toast.makeText(getApplicationContext(),"수정되었습니다.", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext() ,myQuestionActivity.class);
                intent.putExtra("user_info",item1);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
                finish();
            }
        });

        myque_modify_content.setText(item.getMyque_content());
}

    public class phpModify extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            StringBuilder jsonHtml = new StringBuilder();
            try {
                // 연결 URL 설정
                Log.d("params[0]", params[0]);
                Log.d("params[1]", params[1]);
                Log.d("params[2]", params[2]);

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
                    buffer.append("content").append("=").append(params[1]).append("&").
                            append("id").append("=").append(params[2]).append("&").
                            append("select").append("=").append("500");
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
        protected void onPostExecute(String str){
            if(str.equals(true)){
                Toast.makeText(getApplicationContext(),"질문 저장을 성공하였습니다.",Toast.LENGTH_LONG).show();
                finish();
            }else{
                Toast.makeText(getApplicationContext(),"질문 저장을 실패하였습니다.",Toast.LENGTH_LONG).show();
                finish();
            }

        }

    }
}
