package com.example.bondhutumar.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.bondhutumar.R;
import com.example.bondhutumar.model.QAModelInsomnia;
import com.example.bondhutumar.model.UserAnswer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QViewInsomniaAdapter extends RecyclerView.Adapter<QViewInsomniaAdapter.QViewHolder> {
    private Context context;
    private List<QAModelInsomnia> list;
    public static Map<Integer, UserAnswer> map_insomnia = new HashMap<>();

    public QViewInsomniaAdapter(Context context, List<QAModelInsomnia> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public QViewInsomniaAdapter.QViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layout_qus_ans_view_insomnia, viewGroup, false);


        return new QViewInsomniaAdapter.QViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QViewInsomniaAdapter.QViewHolder qViewHolder, int i) {
        String ques = list.get(i).getQueston();
        String queswithno = (i + 1) + ". " + ques;
        qViewHolder.question.setText(queswithno);
        qViewHolder.radioButton1.setText(list.get(i).getAnswerA());
        qViewHolder.radioButton2.setText(list.get(i).getAnswerB());
        qViewHolder.radioButton3.setText(list.get(i).getAnswerC());
        qViewHolder.radioGroup.setTag(i);


        if (map_insomnia.get(i) != null && map_insomnia.get(i).isAnswered()) {
            qViewHolder.radioGroup.check(map_insomnia.get(i).getAnswerRBtnID());
        } else {
            qViewHolder.radioGroup.clearCheck();
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class QViewHolder extends RecyclerView.ViewHolder {
        TextView question;
        RadioButton radioButton1, radioButton2, radioButton3;
        RadioGroup radioGroup;

        public QViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.allQuestiontextId);
            radioGroup = itemView.findViewById(R.id.rdbGroup);
            radioButton1 = itemView.findViewById(R.id.rbId1);
            radioButton2 = itemView.findViewById(R.id.rbId2);
            radioButton3 = itemView.findViewById(R.id.rbId3);

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
                    }
                    String answer = "";
                    if (checkedId != -1) {
                        rb.getText().toString();
                    }

                    //Log.d("ok", Integer.toString(pos));
                    //Log.d("id", Integer.toString(aNo));

                    map_insomnia.put(qNo, new UserAnswer(qNo, question, aNo, answer, radioGroup.getCheckedRadioButtonId(), true));

                }
            });

        }
    }
}
