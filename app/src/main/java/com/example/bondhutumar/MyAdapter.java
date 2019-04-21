package com.example.bondhutumar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.bondhutumar.model.QuestionAnswerModel;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    List<QuestionAnswerModel> list;


    public MyAdapter(Context context, List<QuestionAnswerModel> list){
        this.context=context;
        this.list=list;
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

        myViewHolder.question.setText(list.get(i).getQueston());
        myViewHolder.radioButton1.setText(list.get(i).getAnswerA());
        myViewHolder.radioButton2.setText(list.get(i).getAnswerB());
        myViewHolder.radioButton3.setText(list.get(i).getAnswerC());
        myViewHolder.radioButton4.setText(list.get(i).getAnswerD());

    }

    @Override
    public int getItemCount() {

        return list.size();
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
