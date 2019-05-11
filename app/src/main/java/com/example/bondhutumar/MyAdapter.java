package com.example.bondhutumar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.bondhutumar.model.QuestionAnswerModel;
import com.example.bondhutumar.model.UserAnswer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    List<QuestionAnswerModel> list;
    public static Map<Integer, UserAnswer> map = new HashMap<>();

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
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {

        String ques = list.get(i).getQueston();
        String queswithno = (i + 1) + ". " + ques;
        myViewHolder.question.setText(queswithno);
        myViewHolder.radioButton1.setText(list.get(i).getAnswerA());
        myViewHolder.radioButton2.setText(list.get(i).getAnswerB());
        myViewHolder.radioButton3.setText(list.get(i).getAnswerC());
        myViewHolder.radioButton4.setText(list.get(i).getAnswerD());

        myViewHolder.radioGroup.setTag(i);


        if (MyAdapter.map.get(i) != null && map.get(i).isAnswered()) {
            myViewHolder.radioGroup.check(map.get(i).getAnswerRBtnID());
        } else {
            myViewHolder.radioGroup.clearCheck();
        }


    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView question;
        RadioButton radioButton1,radioButton2,radioButton3,radioButton4;
        RadioGroup radioGroup;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.allQuestiontextId);
            radioGroup = itemView.findViewById(R.id.rdbGroup);
            radioButton1 = itemView.findViewById(R.id.rbId1);
            radioButton2 = itemView.findViewById(R.id.rbId2);
            radioButton3 = itemView.findViewById(R.id.rbId3);
            radioButton4 = itemView.findViewById(R.id.rbId4);


            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                    RadioButton rb = group.findViewById(checkedId);

                    int qNo = (int) group.getTag();
                    String question = list.get(qNo).getQueston();
                    int aNo = 0;
                    if (rb == radioButton1) {
                        aNo = 0;
                    } else if (rb == radioButton2) {
                        aNo = 1;
                    } else if (rb == radioButton3) {
                        aNo = 2;
                    } else if (rb == radioButton4) {
                        aNo = 3;
                    }
                    String answer = "";
                    if (checkedId != -1) {
                        rb.getText().toString();
                    }



                    map.put(qNo, new UserAnswer(qNo, question, aNo, answer, radioGroup.getCheckedRadioButtonId(), true));

                }
            });

        }
    }
}
