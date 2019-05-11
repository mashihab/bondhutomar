package com.example.bondhutumar;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bondhutumar.model.QuestionAnswerModel;
import com.example.bondhutumar.response.QAResponse;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AllQuestion extends AppCompatActivity {
    public static final String qaBaseUrl = "https://api.myjson.com";
    RecyclerView recyclerView;
    MyAdapter myAdapter;

    List<QuestionAnswerModel> list;
    ProgressDialog progressDialog;
    Button btnSubmit;

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_question);
        recyclerView = findViewById(R.id.recyclerViewId);
        btnSubmit = findViewById(R.id.btnSubmit);

        databaseReference = FirebaseDatabase.getInstance().getReference("artists");



        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        MyAdapter.map.clear();

     final Retrofit retrofit = new Retrofit.Builder().baseUrl(qaBaseUrl).addConverterFactory(GsonConverterFactory.create()).build();

        QAResponse qaResponse = retrofit.create(QAResponse.class);

        qaResponse.getQuestionAnswer().enqueue(new Callback<List<QuestionAnswerModel>>() {
            @Override
            public void onResponse(Call<List<QuestionAnswerModel>> call, Response<List<QuestionAnswerModel>> response) {
                if (response.isSuccessful()) {
                    list = response.body();

                    myAdapter = new MyAdapter(getApplicationContext(), list);

                    recyclerView.setAdapter(myAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    progressDialog.dismiss();
                } else {
                    Toast.makeText(getApplicationContext(), "Something went wrong. Try again..", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<QuestionAnswerModel>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Oops! Check your internet connection.", Toast.LENGTH_SHORT).show();

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                android.support.v7.app.AlertDialog.Builder mBuilder = new AlertDialog.Builder(AllQuestion.this);
                View mview = getLayoutInflater().inflate(R.layout.activity_email_send, null);


                int result = 0;
                for (int i = 0; i < recyclerView.getAdapter().getItemCount(); i++) {

                    if (MyAdapter.map.get(i) != null) {
                        Log.d("check ", MyAdapter.map.get(i).getAnswer());
                        result += MyAdapter.map.get(i).getAnswerNo();
                    }
                }



                String AllResult = Integer.toString(result);

                Button button = mview.findViewById(R.id.sendButton);
                final EditText editText= mview.findViewById(R.id.editTextEmail1);





                    final DatabaseReference databaseReference;

                    databaseReference = FirebaseDatabase.getInstance().getReference("Users");



                    Bundle bundle = getIntent().getExtras();

                    if(bundle!= null){
                        AllResult = bundle.getString("result");
                    }


                    final String finalAllResult = AllResult;


                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            if(editText.getText().toString().isEmpty())
                            {

                               editText.setError("Enter email");
                            }
                            else {

                                String email = editText.getText().toString();
                                String id = databaseReference.push().getKey();
                                Artist artist = new Artist(id, email, finalAllResult);
                                databaseReference.child(id).setValue(artist);



                                Toast.makeText(AllQuestion.this,"You will get your result soon!!!",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(AllQuestion.this,Main2Activity.class);
                                startActivity(intent);
                                finish();

                            }


                        }
                    });


                //Intent intent = new Intent(AllQuestion.this,EmailSendActivity.class);
                //intent.putExtra("result",AllResult);
                //startActivity(intent);

                mBuilder.setView(mview);
                AlertDialog dialog = mBuilder.create();
                dialog.show();

            }
        });





    }

    private void artistAdded() {
        String email = "tt";
        String result = "45";

        String id = databaseReference.push().getKey();
        Artist artist = new Artist(id, email, result);
        databaseReference.child(id).setValue(artist);

            Toast.makeText(this,"Artist added",Toast.LENGTH_LONG).show();
    }



}
