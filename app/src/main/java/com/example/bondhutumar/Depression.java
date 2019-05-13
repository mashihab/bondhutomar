package com.example.bondhutumar;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class Depression extends AppCompatActivity {

    private LinearLayout test;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depression);

        test=(LinearLayout) findViewById(R.id.test);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                android.support.v7.app.AlertDialog.Builder mBuilder = new AlertDialog.Builder(Depression.this);
                View mview = getLayoutInflater().inflate(R.layout.agreement, null);


                Button button = mview.findViewById(R.id.agree);


                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                        Intent intent = new Intent(Depression.this,AllQuestion.class);
                        startActivity(intent);



                    }
                });





                mBuilder.setView(mview);
                AlertDialog dialog = mBuilder.create();
                dialog.show();

            }
        });



    }
}
