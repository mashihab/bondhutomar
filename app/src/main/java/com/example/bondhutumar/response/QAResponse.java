package com.example.bondhutumar.response;

import com.example.bondhutumar.model.QuestionAnswerModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QAResponse {
    @GET("/bins/ilw3s")
    Call<List<QuestionAnswerModel>> getQuestionAnswer();
}
