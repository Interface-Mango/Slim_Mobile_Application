package com.example.hyejin.slimtest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editid;
    EditText editpw;
    Button loginButton;

    ArrayList<GetDataList> dataList = new ArrayList<GetDataList>();
    SendPost sendPost;

    String strId;
    String strPw;
    //private static final String SERVER_ADDRESS = "http://내 컴퓨터 아이피:8080"
    //8080은 apmsetup 설치 시 설정한 포트번호
    //ActivityGroup은 다른 액티비티에서 탭 뷰 상속중이어서 상속한 것
    //Activity만 상속하고 구동해도 문제 없음

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendPost = new SendPost();
        editid = (EditText)findViewById(R.id.idInput);
        editpw = (EditText)findViewById(R.id.passwordInput);
        loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editid.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                } else if(editpw.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                strId = editid.getText().toString();
                strPw = editpw.getText().toString();
                sendPost.execute("http://14.63.196.146/user.php", strId, strPw);
//                runOnUiThread((new Runnable() {
//                    @Override
//                    public void run() {
//                        String id = editid.getText().toString();
//                        String password = editpw.getText().toString();
//
//                        try {
//                            URL url = new URL(SERVER_ADDRESS + "/sos.php?"
//                                    + "id=" + URLEncoder.encode(id,"UTF-8")
//                                    + "&password=" + URLEncoder.encode(password, "UTF-8"));
//                            url.openStream();
//
//                        }
//                    }
//                }));
            }
        });
    }

    private class SendPost extends AsyncTask<String, Integer, String> {

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
                    buffer.append("user_id").append("=").append(params[1]).append("&");
                    buffer.append("pw").append("=").append(params[2]);

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
            String user_id;
            String user_name;
            try {
                Log.d("str", str);
                JSONObject root = new JSONObject(str);

                Log.d("rownum", root.getInt("rownum") + "");

                if(root.getInt("rownum") == 0) {
                    Toast.makeText(getApplicationContext(), "로그인 실패!", Toast.LENGTH_LONG).show();
                    strId = "";
                    strPw = "";
                    return;
                }
                JSONArray ja = root.getJSONArray("result");//[{}]

                for(int i=0;i<ja.length();i++) {
                    JSONObject jo = ja.getJSONObject(i);//{}
                    user_id = jo.getString("user_id");
                    user_name = jo.getString("user_name");
                    dataList.add(new GetDataList(user_id));
                    dataList.add(new GetDataList(user_name));
                }
                Toast.makeText(getApplicationContext(), dataList.get(1).getData(0)+"님 환영합니다!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), classActivity.class);
                startActivity(intent);
            } catch(JSONException ex) {
                ex.printStackTrace();
            }
//            textViewId.setText("user_id: "+listItem.get(0).getData(0));
//            textViewName.setText("user_name: "+listItem.get(1).getData(0));
        }
    }
}
