package com.example.bondhutumar;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.bondhutumar.model.QuestionAnswerModel;
import com.example.bondhutumar.response.QAResponse;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_question);
        recyclerView = findViewById(R.id.recyclerViewId);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(qaBaseUrl).addConverterFactory(GsonConverterFactory.create()).build();

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


    }
}
