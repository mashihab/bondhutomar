package com.example.bondhutumar;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class Insomnia extends AppCompatActivity {
    private LinearLayout test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insomnia);

        test = (LinearLayout) findViewById(R.id.test);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final android.support.v7.app.AlertDialog.Builder mBuilder = new AlertDialog.Builder(Insomnia.this);
                View mview = getLayoutInflater().inflate(R.layout.agreement, null);
                mBuilder.setView(mview);
                final AlertDialog dialog = mBuilder.create();

                Button button = mview.findViewById(R.id.agree);


                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(Insomnia.this, QAViewInsomniaActivity.class);
                        startActivity(intent);
                        if (dialog.isShowing()){
                            dialog.dismiss();
                        }

                    }
                });



                dialog.show();

            }
        });
    }
}
