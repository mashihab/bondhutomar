package com.example.bondhutumar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Result extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textView=(TextView) findViewById(R.id.value);

        Intent intent=getIntent();
        String massage=intent.getStringExtra("result");

          textView.setText(massage);
    }
}
