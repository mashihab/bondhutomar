package com.example.bondhutumar.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.bondhutumar.model.QAModelDepression;
import com.example.bondhutumar.model.QAModelInsomnia;

import java.util.ArrayList;
import java.util.List;

public class QusestionAnswerDBHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME_DEPRESSTION = "tbl_depression";
    public static final String TABLE_NAME_INSOMNIA = "tbl_insomnia";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_QUESTION = "question";
    public static final String COLUMN_ANSWER_A = "answer_a";
    public static final String COLUMN_ANSWER_B = "answer_b";
    public static final String COLUMN_ANSWER_C = "answer_c";
    public static final String COLUMN_ANSWER_D = "answer_d";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    public static final String CREATE_TABLE_DEPRESSION = "CREATE TABLE "
            + TABLE_NAME_DEPRESSTION
            + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_QUESTION + " TEXT,"
            + COLUMN_ANSWER_A + " TEXT,"
            + COLUMN_ANSWER_B + " TEXT,"
            + COLUMN_ANSWER_C + " TEXT,"
            + COLUMN_ANSWER_D + " TEXT,"
            + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
            + ")";

    public static final String CREATE_TABLE_INSOMNIA = "CREATE TABLE "
            + TABLE_NAME_INSOMNIA
            + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_QUESTION + " TEXT,"
            + COLUMN_ANSWER_A + " TEXT,"
            + COLUMN_ANSWER_B + " TEXT,"
            + COLUMN_ANSWER_C + " TEXT,"
            + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
            + ")";


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_qa";


    public QusestionAnswerDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DEPRESSION);
        db.execSQL(CREATE_TABLE_INSOMNIA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_DEPRESSTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_INSOMNIA);
        onCreate(db);
    }


    public long insertDataDepression(QAModelDepression model) {
        SQLiteDatabase mDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_QUESTION, model.getQueston());
        values.put(COLUMN_ANSWER_A, model.getAnswerA());
        values.put(COLUMN_ANSWER_B, model.getAnswerB());
        values.put(COLUMN_ANSWER_C, model.getAnswerC());
        values.put(COLUMN_ANSWER_D, model.getAnswerD());
        long id = mDB.insert(TABLE_NAME_DEPRESSTION, null, values);
        mDB.close();
        return id;
    }

    public long insertDataInsomnia(QAModelInsomnia model) {
        SQLiteDatabase mDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_QUESTION, model.getQueston());
        values.put(COLUMN_ANSWER_A, model.getAnswerA());
        values.put(COLUMN_ANSWER_B, model.getAnswerB());
        values.put(COLUMN_ANSWER_C, model.getAnswerC());
        long id = mDB.insert(TABLE_NAME_INSOMNIA, null, values);
        mDB.close();
        return id;
    }

    public List<QAModelDepression> getDepressionList() {
        List<QAModelDepression> modelList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME_DEPRESSTION + " ORDER" + COLUMN_TIMESTAMP + "DESC";
        SQLiteDatabase mDB = this.getWritableDatabase();
        Cursor cursor = mDB.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                QAModelDepression modelDB = new QAModelDepression();
                modelDB.setQueston(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION)));
                modelDB.setAnswerA(cursor.getString(cursor.getColumnIndex(COLUMN_ANSWER_A)));
                modelDB.setAnswerB(cursor.getString(cursor.getColumnIndex(COLUMN_ANSWER_B)));
                modelDB.setAnswerC(cursor.getString(cursor.getColumnIndex(COLUMN_ANSWER_C)));
                modelDB.setAnswerD(cursor.getString(cursor.getColumnIndex(COLUMN_ANSWER_D)));

                modelList.add(modelDB);
            } while (cursor.moveToNext());
        }
        mDB.close();
        return modelList;
    }

    public List<QAModelInsomnia> getInsomniaList() {
        List<QAModelInsomnia> modelList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME_INSOMNIA + " ORDER" + COLUMN_TIMESTAMP + "DESC";
        SQLiteDatabase mDB = this.getWritableDatabase();
        Cursor cursor = mDB.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                QAModelInsomnia modelDB = new QAModelInsomnia();
                modelDB.setQueston(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION)));
                modelDB.setAnswerA(cursor.getString(cursor.getColumnIndex(COLUMN_ANSWER_A)));
                modelDB.setAnswerB(cursor.getString(cursor.getColumnIndex(COLUMN_ANSWER_B)));
                modelDB.setAnswerC(cursor.getString(cursor.getColumnIndex(COLUMN_ANSWER_C)));

                modelList.add(modelDB);
            } while (cursor.moveToNext());
        }
        mDB.close();
        return modelList;
    }

    public int getDepreesionDataCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME_DEPRESSTION;
        SQLiteDatabase mDB = this.getReadableDatabase();
        Cursor cursor = mDB.rawQuery(countQuery, null);

        int count = cursor.getCount();
        mDB.close();

        return count;
    }

    public int getInsomniaDataCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME_INSOMNIA;
        SQLiteDatabase mDB = this.getReadableDatabase();
        Cursor cursor = mDB.rawQuery(countQuery, null);

        int count = cursor.getCount();
        mDB.close();

        return count;
    }

}
