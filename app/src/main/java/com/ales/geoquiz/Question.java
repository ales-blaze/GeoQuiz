package com.ales.geoquiz;

public class Question {
    private int mID;
//    private int mQuestionStringID;
    private String mQuestionString;
    private Boolean mCorrectAnswer;
    public void setID(int ID) {
        this.mID = ID;
    }

    public int getID() {
        return mID;
    }

//    public int getQuestionStringID() {
//        return mQuestionStringID;
//    }

//    public void setQuestionStringID(int questionStringID) {
//        mQuestionStringID = questionStringID;
//    }


    public void setQuestionString(String questionString) {
        mQuestionString = questionString;
    }

    public String getQuestionString() {
        return mQuestionString;
    }

    public Boolean isCorrectAnswer() {
        return mCorrectAnswer;
    }

    public void setCorrectAnswer(Boolean correctAnswer) {
        mCorrectAnswer = correctAnswer;
    }

    public Question(String questionString, boolean correctAnswer){
//        mQuestionStringID = questionStringID;
        mQuestionString = questionString;
        mCorrectAnswer = correctAnswer;
    }

    public  Question(int id , String questionString , boolean correctAnswer ){
        this.mID = id;
//        this.mQuestionStringID = questionStringID;
        this.mQuestionString = questionString;
        this.mCorrectAnswer = correctAnswer;
    }
}
