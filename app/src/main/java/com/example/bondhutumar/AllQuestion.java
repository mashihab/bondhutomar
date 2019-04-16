package com.example.bondhutumar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class AllQuestion extends AppCompatActivity {
    RecyclerView recyclerView;
    MyAdapter myAdapter;

    String[] question,selectOption1,selectOption2,selectOption3,selectOption4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_question);
        recyclerView = findViewById(R.id.recyclerViewId);

        question = getResources().getStringArray(R.array.question);
        selectOption1 = getResources().getStringArray(R.array.questionSelectOption1);
        selectOption2 = getResources().getStringArray(R.array.questionSelectOption2);
        selectOption3 = getResources().getStringArray(R.array.questionSelectOption3);
        selectOption4 = getResources().getStringArray(R.array.questionSelectOption4);

        myAdapter = new MyAdapter(this,question,selectOption1,selectOption2,selectOption3,selectOption4);

        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
