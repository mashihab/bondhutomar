package com.example.bondhutumar;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bondhutumar.adapter.QViewInsomniaAdapter;
import com.example.bondhutumar.dao.QusAnsInsomniaDAO;
import com.example.bondhutumar.dbhelper.QusestionAnswerDBHelper;
import com.example.bondhutumar.model.QAModelInsomnia;
import com.example.bondhutumar.model.UserResultModel;
import com.example.bondhutumar.network.MyNetworkServices;
import com.example.bondhutumar.utils.KeyCollections;
import com.example.bondhutumar.utils.OthersUtils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QAViewInsomniaActivity extends AppCompatActivity {
    private TextView tvTitle;
    private RecyclerView rv;
    private Button btnShowResult;
    private QViewInsomniaAdapter myAdapter;
    private List<QAModelInsomnia> insomniaList;
    private ProgressDialog progressDialog;
    private Retrofit retrofit;
    private QusestionAnswerDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qa_view_insomnia);

        //findvies and init
        findViews();
        init();

        if (new MyNetworkServices(getApplicationContext()).isActiveNetwork()) {
            loadFromWeb();
            Log.d("web", "retro");
        } else {
            loadFromSQLite();
            Log.d("sql", "lite");
        }




        btnShowResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int result = 0;
                for (int i = 0; i < rv.getAdapter().getItemCount(); i++) {

                    if ( QViewInsomniaAdapter.map_insomnia.get(i) != null) {
                        Log.d("check ", QViewInsomniaAdapter.map_insomnia.get(i).getAnswer());
                        result +=  QViewInsomniaAdapter.map_insomnia.get(i).getAnswerNo();
                    }
                }

                showResult(result);

            }
        });
    }


    private void loadFromWeb() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        retrofit = new Retrofit.Builder().baseUrl(KeyCollections.QA_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        QusAnsInsomniaDAO qusAnsDAO = retrofit.create(QusAnsInsomniaDAO.class);
        qusAnsDAO.getInsomniaQA().enqueue(new Callback<List<QAModelInsomnia>>() {
            @Override
            public void onResponse(Call<List<QAModelInsomnia>> call, Response<List<QAModelInsomnia>> response) {
                if (response.isSuccessful()) {
                    insomniaList = response.body();

                    if(dbHelper.getInsomniaDataCount() <= 0){
                        for (int i = 0; i < insomniaList.size(); i++) {
                            dbHelper.insertDataInsomnia(insomniaList.get(i));
                        }

                    }

                    myAdapter = new QViewInsomniaAdapter(getApplicationContext(), insomniaList);

                    rv.setAdapter(myAdapter);
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    progressDialog.dismiss();
                } else {
                    Toast.makeText(getApplicationContext(), "Something went wrong. Try again..", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<List<QAModelInsomnia>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Oops! Check your internet connection.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadFromSQLite() {
        insomniaList = dbHelper.getInsomniaList();
        if(insomniaList.size()==0){
            Toast.makeText(getApplicationContext(), "You need to internet to load questin first.", Toast.LENGTH_SHORT).show();
        }

        myAdapter = new QViewInsomniaAdapter(getApplicationContext(), insomniaList);

        rv.setAdapter(myAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }


    private void init(){
        //clear the map_depression to avoid error
        QViewInsomniaAdapter.map_insomnia.clear();

        dbHelper = new QusestionAnswerDBHelper(this);
    }

    private void findViews() {
        tvTitle = findViewById(R.id.aqvi_tv_qtitle);
        rv = findViewById(R.id.aqvi_rv);
        btnShowResult = findViewById(R.id.aqvi_btn_show_result);
    }

    private void showResult(final int result){
        final Dialog mDialog;
        TextView tvResult, tvCancelButton;
        final EditText etUserEmail;
        final Button btnSubmitEmail;

        mDialog = new Dialog(this);
        View v = getLayoutInflater().inflate(R.layout.layout_user_email, null);
        tvResult = v.findViewById(R.id.tv_show_result);
        tvCancelButton  = v.findViewById(R.id.tv_cancel_button);
        btnSubmitEmail = v.findViewById(R.id.btn_submit_email);
        etUserEmail = v.findViewById(R.id.et_user_email_input);

        tvResult.setText(Integer.toString(result));

        btnSubmitEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etUserEmail.getText().toString();
                if (OthersUtils.isEmailValid(email)) {
                    if (new MyNetworkServices(getApplicationContext()).isActiveNetwork()) {

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss'Z'", Locale.US);
                        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
                        System.out.println(sdf.format(new Date())); //-prints-> 2015-01-22T03:23:26Z
                        String timestamp = sdf.format(new Date());

                        String totalResult = Integer.toString(result);
                        DatabaseReference databaseReference;

                        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

                        String userEmail = etUserEmail.getText().toString();
                        final String id = databaseReference.push().getKey();
                        UserResultModel artist = new UserResultModel(id, userEmail, totalResult, "Insomnia", timestamp);
                        databaseReference.child(id).setValue(artist);

                        new AlertDialog.Builder(QAViewInsomniaActivity.this)
                                .setTitle("Thank you.")
                                .setMessage("You will get a details result soon.")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (mDialog.isShowing()) {
                                            mDialog.dismiss();
                                        }
                                    }
                                })

                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Error in network connection..", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter a valid email.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });

        mDialog.setContentView(v);
        mDialog.setCancelable(false);
        mDialog.show();

    }
}