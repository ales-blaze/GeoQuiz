package com.ales.geoquiz;

public class Question {
    private int mQuestionStringID;
    private boolean mCorrectAnswer;

    public int getQuestionStringID() {
        return mQuestionStringID;
    }

    public void setQuestionStringID(int questionStringID) {
        mQuestionStringID = questionStringID;
    }

    public boolean isCorrectAnswer() {
        return mCorrectAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        mCorrectAnswer = correctAnswer;
    }

    public Question(int questionStringID, boolean correctAnswer){
        mQuestionStringID = questionStringID;
        mCorrectAnswer = correctAnswer;
    }
}
