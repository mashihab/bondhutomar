package com.example.bondhutumar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bondhutumar.utils.MySharedPref;
import com.example.bondhutumar.utils.OthersUtils;

public class LoginActivity extends AppCompatActivity {
    private Button login, signup;
    private EditText etEmail, etPass;
    private MySharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //findviews
        findViews();

        //initialization
        init();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String pass = etPass.getText().toString().trim();

                if (OthersUtils.isEmailValid(email)) {
                    sharedPref.saveEmail(email);
                    sharedPref.savePassword(pass);
                    sharedPref.saveName("Mr. " + email);
                    sharedPref.saveLogStatus(true);

                    startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), "Email in not valid", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignupActivity.class));
                finish();
            }
        });
    }

    private void init() {
        sharedPref = new MySharedPref(getApplicationContext());
    }

    private void findViews() {
        login = findViewById(R.id.log_btn_login);
        signup = findViewById(R.id.log_btn_signup);

        etEmail = findViewById(R.id.log_et_email);
        etPass = findViewById(R.id.log_et_pass);
    }
}
