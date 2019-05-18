package com.example.bondhutumar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EmailSendActivity extends AppCompatActivity {

  /*  private EditText editText;
    private Button button;
    String result="";
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_send);

        editText = findViewById(R.id.editTextEmail1);
        button = findViewById(R.id.sendButton);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");



        Bundle bundle = getIntent().getExtras();

        if(bundle!= null){
            result = bundle.getString("result");
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editText.getText().toString();
                String id = databaseReference.push().getKey();
                UserResultModel artist = new UserResultModel(id, email, result);
                databaseReference.child(id).setValue(artist);



                Toast.makeText(EmailSendActivity.this,"You will get your result soon!!!",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(EmailSendActivity.this,Main2Activity.class);
                startActivity(intent);
                finish();
            }
        });


    }*/
}
