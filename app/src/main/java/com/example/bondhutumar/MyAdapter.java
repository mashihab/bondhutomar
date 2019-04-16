package com.example.bondhutumar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    String[] question,selectOption1,selectOption2,selectOption3,selectOption4;

    public MyAdapter(Context context, String[] question, String[] selectOption1, String[] selectOption2, String[] selectOption3, String[] selectOption4) {
        this.context = context;
        this.question = question;
        this.selectOption1 = selectOption1;
        this.selectOption2 = selectOption2;
        this.selectOption3 = selectOption3;
        this.selectOption4 = selectOption4;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
       View view = layoutInflater.inflate(R.layout.sample_layout,viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.question.setText(question[i]);
        myViewHolder.radioButton1.setText(selectOption1[i]);
        myViewHolder.radioButton2.setText(selectOption2[i]);
        myViewHolder.radioButton3.setText(selectOption3[i]);
        myViewHolder.radioButton4.setText(selectOption4[i]);

    }

    @Override
    public int getItemCount() {
        return question.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView question;
        RadioButton radioButton1,radioButton2,radioButton3,radioButton4;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.allQuestiontextId);
            radioButton1 = itemView.findViewById(R.id.rbId1);
            radioButton2 = itemView.findViewById(R.id.rbId2);
            radioButton3 = itemView.findViewById(R.id.rbId3);
            radioButton4 = itemView.findViewById(R.id.rbId4);
        }
    }
}
