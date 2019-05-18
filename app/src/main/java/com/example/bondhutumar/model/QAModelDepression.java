package com.example.bondhutumar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QAModelDepression {
    @SerializedName("queston")
    @Expose
    private String queston;
    @SerializedName("answer_a")
    @Expose
    private String answerA;
    @SerializedName("answer_b")
    @Expose
    private String answerB;
    @SerializedName("answer_c")
    @Expose
    private String answerC;
    @SerializedName("answer_d")
    @Expose
    private String answerD;

    public String getQueston() {
        return queston;
    }

    public void setQueston(String queston) {
        this.queston = queston;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }
}
