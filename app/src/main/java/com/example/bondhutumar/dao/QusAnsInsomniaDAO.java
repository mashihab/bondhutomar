package com.example.bondhutumar.dao;

import com.example.bondhutumar.model.QAModelInsomnia;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QusAnsInsomniaDAO {
    @GET("/bins/17farm")
    Call<List<QAModelInsomnia>> getInsomniaQA();


}
