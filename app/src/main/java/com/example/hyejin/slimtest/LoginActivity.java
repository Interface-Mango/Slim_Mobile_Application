package com.example.hyejin.slimtest;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
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
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    public static List<Object> UserInfo;

    // UI references.
    private AutoCompleteTextView mEditId;
    private EditText mEditPw;
    private View mProgressView;
    private View mLoginFormView;
    private ArrayList<Object> dataList = new ArrayList<Object>();
    private Button mSignInButton;

    // 로그인 정보 저장장
   private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getSharedPreferences("pref", MODE_PRIVATE);
        editor = pref.edit();

        // Set up the login form.
        mEditId = (AutoCompleteTextView) findViewById(R.id.editTextId);

        mEditPw = (EditText) findViewById(R.id.editTextPw);
        mEditPw.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        mSignInButton = (Button) findViewById(R.id.sign_in_button);
        mSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);


    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        // Reset errors.
        mEditId.setError(null);
        mEditPw.setError(null);

        // Store values at the time of the login attempt.
        String id = mEditId.getText().toString();
        String password = mEditPw.getText().toString();

        boolean cancel = false;
        View focusView = null;


        // Check for a valid id.
        if (id.equals("")) {
            mEditId.setError(getString(R.string.error_invalid_email));
            focusView = mEditId;
            cancel = true;
        }
        // Check for a valid password, if the user entered one.
        if (password.equals("")) {
            mEditPw.setError(getString(R.string.error_invalid_password));
            focusView = mEditPw;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            UserLoginTask userLoginTask = new UserLoginTask();
            userLoginTask.execute("http://14.63.196.146/user.php", id, password);
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    private class UserLoginTask extends AsyncTask<String, Integer, String> {

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
            String user_id="";
            String user_name="";
            String user_group="";
            int user_auth = -1;
            ArrayList<Integer> subjectIdList = new ArrayList<Integer>();

            try {
                Log.d("str", str);
                JSONObject root = new JSONObject(str);

                Log.d("rownum", root.getInt("rownum") + "");

                if(root.getInt("rownum") == 0 || root.getInt("rownum") == -1) {
                    Toast.makeText(getApplicationContext(), "로그인 실패!", Toast.LENGTH_LONG).show();
                    showProgress(false);
                    return;
                }
                JSONArray ja = root.getJSONArray("result");//[{}]
                String[] tempList = null;
                for(int i=0;i<ja.length();i++) {
                    JSONObject jo = ja.getJSONObject(i);//{}
                    user_id = jo.getString("user_id");
                    user_name = jo.getString("user_name");
                    user_group = jo.getString("group");
                    user_auth = jo.getInt("auth");
                    tempList = jo.getString("sub_ids").split("_");
                    for(int k=0;k<tempList.length;k++) {    // 과목 ID 넣기
                        subjectIdList.add(Integer.parseInt(tempList[k]));
                    }
                    dataList.add(user_id);
                    dataList.add(user_name);
                    dataList.add(user_group);
                    dataList.add(user_auth);
                    dataList.add(subjectIdList);
                }

                // SharedPreference 저장
                editor.putString("user_id", user_id);
                editor.putString("user_name", user_name);
                editor.putString("user_group", user_group);
                editor.putInt("user_auth", user_auth);
                for(int i=0;i< tempList.length;i++)
                    editor.putInt("user_sub_id"+i, subjectIdList.get(i));
                editor.commit();

                Toast.makeText(getApplicationContext(), dataList.get(1)+"님 환영합니다!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), classActivity.class);

                UserInfo = dataList;    // UserInfo는 LoginActivity에서 static으로 갖고 있기.
                startActivity(intent);
                finish();
            } catch(JSONException ex) {
                ex.printStackTrace();
            }
        }
    }
}

