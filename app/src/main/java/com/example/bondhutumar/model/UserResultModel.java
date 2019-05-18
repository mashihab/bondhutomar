package com.example.bondhutumar.model;

public class UserResultModel {
    String id;
    String email;
    String result;
    String testType;
    String timestamp;

    private UserResultModel(){

    }

    public UserResultModel(String id, String email, String result, String testType, String timestamp) {
        this.id = id;
        this.email = email;
        this.result = result;
        this.testType = testType;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
