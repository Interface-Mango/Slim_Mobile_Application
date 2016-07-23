package com.example.hyejin.slimtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    EditText edtid;
    EditText edtpw;
    Button loginButton;
    Button signUpButton;

    //private static final String SERVER_ADDRESS = "http://내 컴퓨터 아이피:8080"
    //8080은 apmsetup 설치 시 설정한 포트번호
    //ActivityGroup은 다른 액티비티에서 탭 뷰 상속중이어서 상속한 것
    //Activity만 상속하고 구동해도 문제 없음

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtid = (EditText)findViewById(R.id.idInput);
        edtpw = (EditText)findViewById(R.id.passwordInput);
        loginButton = (Button) findViewById(R.id.loginButton);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtid.getText().toString().equals("")
                        || edtpw.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "입력 오류입니다.", Toast.LENGTH_SHORT).show();
                }
//                runOnUiThread((new Runnable() {
//                    @Override
//                    public void run() {
//                        String id = edtid.getText().toString();
//                        String password = edtpw.getText().toString();
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
                Intent intent = new Intent(getApplicationContext(), classActivity.class);
                startActivity(intent);
            }
        });
    }
}
