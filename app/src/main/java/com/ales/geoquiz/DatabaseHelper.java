package com.ales.geoquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.jetbrains.annotations.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

//    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
    public DatabaseHelper(@Nullable Context context) {
        super(context , DataBaseConst.DATABASE_NAME , null , DataBaseConst.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createAnswerTable = " CREATE TABLE IF NOT EXISTS " + DataBaseConst.TABLE_ANSWERS + " (" +
                                    DataBaseConst.ANSWER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                    DataBaseConst.ANSWER_CONTENT + " BOOLEAN NOT NULL " + ");";

        db.execSQL(createAnswerTable);
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseConst.ANSWER_TRUE , true );
        contentValues.put(DataBaseConst.ANSWER_FALSE , false);

        db.insert(DataBaseConst.TABLE_ANSWERS , null , contentValues );
        contentValues.clear();

        String createQuestionTable = "CREATE TABLE IF NOT EXISTS " + DataBaseConst.TABLE_QUESTION + " (" +
                                DataBaseConst.QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                DataBaseConst.QUESTION_CONTENT + " TEXT NOT NULL, " +
                                DataBaseConst.QUESTION_ANSWER_KEY + " INTEGER NOT NULL, " +
                                "FOREIGN KEY " + "(" + DataBaseConst.QUESTION_ID + ") " + " REFERENCES " + DataBaseConst.TABLE_ANSWERS + "(" + DataBaseConst.ANSWER_ID + ") " +
                ");";
        db.execSQL(createQuestionTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop = " DROP TABLE IF EXISTS ";
        db.execSQL(drop + DataBaseConst.TABLE_QUESTION);
        db.execSQL(drop + DataBaseConst.TABLE_ANSWERS);

        onCreate(db);
    }

    public boolean addQuestion(Question question ){
        try (SQLiteDatabase sqlDB = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();

            contentValues.put(DataBaseConst.QUESTION_ID, question.getID());
            contentValues.put(DataBaseConst.QUESTION_CONTENT, question.getQuestionString());
            if ( question.isCorrectAnswer()) {
                contentValues.put(DataBaseConst.QUESTION_ANSWER_KEY,0);
            }else {
                contentValues.put(DataBaseConst.QUESTION_ANSWER_KEY, 1);
            }
            sqlDB.insert(DataBaseConst.TABLE_QUESTION, null, contentValues);
            contentValues.clear();
            return true;
        }
    }

    public Question getQuestion(int id) {
        try (SQLiteDatabase sqlDB = this.getReadableDatabase()) {
            Cursor cursor = sqlDB.query(DataBaseConst.TABLE_QUESTION , new String[] {DataBaseConst.QUESTION_ID + DataBaseConst.QUESTION_CONTENT + DataBaseConst.QUESTION_ANSWER_KEY} , DataBaseConst.QUESTION_ID + "=?", new String[]{String.valueOf(id)} , null , null , null);

            if ( cursor != null ){
                cursor.moveToFirst();
            }
            Question question1 = new Question( Integer.parseInt(cursor.getString(0)) ,cursor.getString(1) , Boolean.parseBoolean(cursor.getString(2)));
            return question1;
        }
    }

    public void updateQuestion(Question question) {
        try ( SQLiteDatabase sqlDB = this.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DataBaseConst.QUESTION_ID, question.getID());
            contentValues.put(DataBaseConst.QUESTION_CONTENT, question.getQuestionString());
            if (question.isCorrectAnswer()) {
                contentValues.put(DataBaseConst.QUESTION_ANSWER_KEY, 0);
            } else {
                contentValues.put(DataBaseConst.QUESTION_ANSWER_KEY, 1);
            }
            sqlDB.update(DataBaseConst.TABLE_QUESTION , contentValues , DataBaseConst.QUESTION_ID + "=?" , new String[]{String.valueOf(question.getID())});
        }
    }

    public void deleteQuestion(int id) {
        try( SQLiteDatabase sqlDB = this.getWritableDatabase()) {
            sqlDB.delete(DataBaseConst.TABLE_QUESTION , DataBaseConst.QUESTION_ID + "=?" , new String[]{String.valueOf(id)});
        }
    }

    public int questionCount() {
        String countCommand = "SELECT * FROM " + DataBaseConst.TABLE_QUESTION;
        try ( SQLiteDatabase sqlDB = this.getReadableDatabase() ) {
            Cursor counter = sqlDB.rawQuery(countCommand , null);
            int numberOfRows = counter.getCount();
            counter.close();
            return numberOfRows;
        }
    }
}

class DataBaseConst {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "quiz";
    public static final String TABLE_QUESTION = "questions";
    public static final String TABLE_ANSWERS = "answers";
    public static final String QUESTION_ID = "question_id";
    public static final String QUESTION_CONTENT = "question_content";
    public static final String QUESTION_ANSWER_KEY = "answer";
    public static final String ANSWER_ID = "answer_id";
    public static final String ANSWER_CONTENT = "answer_content";
    public static final String ANSWER_TRUE = "True";
    public static final String ANSWER_FALSE = "False";
}