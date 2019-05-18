package com.example.bondhutumar.model;

public class UserAnswer {
    int questionNo;
    String question;
    int answerNo;
    String answer;
    int answerRBtnID;
    boolean isAnswered;


    public UserAnswer(int questionNo, String question, int answerNo, String answer, int answerRBtnID, boolean isAnswered) {
        this.questionNo = questionNo;
        this.question = question;
        this.answerNo = answerNo;
        this.answer = answer;
        this.answerRBtnID = answerRBtnID;
        this.isAnswered = isAnswered;
    }

    public int getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getAnswerNo() {
        return answerNo;
    }

    public void setAnswerNo(int answerNo) {
        this.answerNo = answerNo;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getAnswerRBtnID() {
        return answerRBtnID;
    }

    public void setAnswerRBtnID(int answerRBtnID) {
        this.answerRBtnID = answerRBtnID;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }
}
