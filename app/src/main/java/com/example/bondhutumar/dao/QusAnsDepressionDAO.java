package com.example.bondhutumar.dao;


import com.example.bondhutumar.model.QAModelDepression;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QusAnsDepressionDAO {
    @GET("/bins/162xpk")
    Call<List<QAModelDepression>> getDepressionQA();
}
