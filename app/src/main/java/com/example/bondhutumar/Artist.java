package com.example.bondhutumar;

public class Artist {

    String id;
    String email;
    String result;

    private Artist(){

    }

    public Artist(String id, String email, String result) {
        this.id = id;
        this.email = email;
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getResultt() {
        return result;
    }
}
