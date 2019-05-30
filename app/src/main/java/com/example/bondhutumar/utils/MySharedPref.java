package com.example.bondhutumar.utils;

import android.content.Context;
import android.content.SharedPreferences;


public class MySharedPref {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public MySharedPref(Context context) {
        mSharedPreferences = context.getSharedPreferences(KeyCollections.sharedPreferencesDB, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public void saveEmail(String email) {
        mEditor.putString(KeyCollections.spEmail, email);
        mEditor.apply();
    }

    public void savePassword(String pass) {
        mEditor.putString(KeyCollections.spPassword, pass);
        mEditor.apply();
    }

    public void saveName(String name) {
        mEditor.putString(KeyCollections.spName, name);
        mEditor.apply();
    }

    public void saveLogStatus(boolean status){
        mEditor.putBoolean(KeyCollections.spIsLoggedIn, status);
        mEditor.apply();
    }

    public String retrieveEmail() {
        return mSharedPreferences.getString(KeyCollections.spEmail, "");
    }

    public String retrievePassword() {
        return mSharedPreferences.getString(KeyCollections.spPassword, "");
    }

    public String retrieveName() {
        return mSharedPreferences.getString(KeyCollections.spName, "");
    }

    public boolean retreiveLoggedStatus(){
        return mSharedPreferences.getBoolean(KeyCollections.spIsLoggedIn, false);
    }
}
