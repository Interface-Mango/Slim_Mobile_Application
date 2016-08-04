package com.example.hyejin.slimtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by hyejin on 2016-08-04.
 */
public class myQuestionActivity_modify extends AppCompatActivity {
    Button button_myque_detail_modify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myque_modify);

        button_myque_detail_modify = (Button)findViewById(R.id.button_myque_detail_modify);

        button_myque_detail_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), myQuestionActivity.class);
                startActivity(intent);
            }
        });
}

}
