package com.ales.geoquiz;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "MainActivity";
    private Button mTrueButton , mFalseButton , mCheatButton ;
    private ImageButton mNextButton , mPreviousButton;
    private TextView mQuestionTextView;
    private boolean mCheatArray[];
    private final static boolean TRUE = true;
    private final static boolean FALSE = false;
    private static final String INDEX = "index";
    private static final int CHEAT_REQUEST_CODE = 0;
    private static final String IS_CHEATER = "cheater";
    private static final String USER_DATA = "user";
    private static final String QUESTION_DATA = "question";

    private Button mAlterQuestionButton , mAlterUserButton;
    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question1, true),
            new Question(R.string.question2, true),
            new Question(R.string.question3, true),
            new Question(R.string.question4, false),
    };
    private int mCurrentIndex = 0;
    private boolean mIsCheater;

    private void checkAnswer(boolean answer){
        boolean result = mQuestionBank[mCurrentIndex].isCorrectAnswer();
        int messageID = 0;

        if (mCheatArray[mCurrentIndex]) {
            messageID = R.string.judgement_statement;
        }
        else if ( result == answer) {
            messageID = R.string.correct_toast;
        }
        else {
            messageID = R.string.incorrect_toast;
        }
        Toast.makeText(getApplicationContext() , messageID , Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState()");
        outState.putInt(INDEX, mCurrentIndex);
//        outState.putBoolean(IS_CHEATER , mIsCheater);
        outState.putBooleanArray(IS_CHEATER , mCheatArray);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        setContentView(R.layout.activity_main);

        mAlterQuestionButton = findViewById(R.id.alter_question_data);
        mAlterUserButton = findViewById(R.id.alter_data_button);

        mCheatButton = findViewById(R.id.cheat_button);
        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mNextButton = findViewById(R.id.next_button);
        mQuestionTextView = findViewById(R.id.question_text_view);
        mPreviousButton = findViewById(R.id.previous_button);

//        mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getQuestionStringID());

        mQuestionTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                updateQuestion();
            }
        });

        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this , CheatActivity.class );
                Intent intent = CheatActivity.createIntent(MainActivity.this , mQuestionBank[mCurrentIndex].isCorrectAnswer());
                //starting cheat activity
//                startActivity(intent);
                //starting activity for result
                startActivityForResult(intent , CHEAT_REQUEST_CODE);
            }
        });
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(TRUE);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(FALSE);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuestion();
            }
        });
        mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getQuestionStringID());

        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( mCurrentIndex == 0) {
                    mCurrentIndex = mQuestionBank.length;
                }
                mCurrentIndex--;
                mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getQuestionStringID());
            }
        });
        mCheatArray = new boolean[mQuestionBank.length];

        if(savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(INDEX);
            mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getQuestionStringID());
            mCheatArray = savedInstanceState.getBooleanArray(IS_CHEATER);
            assert mCheatArray != null;
            if ( mCheatArray[mCurrentIndex]) {
                checkAnswer(mCheatArray[mCurrentIndex]);
            }
        }

        mAlterUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = OperationOption.createIntent(getApplicationContext() , USER_DATA);
                startActivity(intent);
            }
        });

        mAlterQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = OperationOption.createIntent(getApplicationContext() , QUESTION_DATA);
                startActivity(intent);
            }
        });
    }

    private void updateQuestion() {
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
//        mIsCheater = false;
        mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getQuestionStringID());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( resultCode == RESULT_OK ) {
            if ( requestCode == CHEAT_REQUEST_CODE ) {
                if ( data != null ) {
//                    boolean result = mCheatArray[mCurrentIndex] = CheatActivity.isAnswerShown(data);
                    boolean result = false;
                    if ( !mCheatArray[mCurrentIndex] ) {
                        result = mCheatArray[mCurrentIndex] = CheatActivity.isAnswerShown(data);
                    }
                    if(result) {
                        checkAnswer(result);
                    }
                }else {
                    return;
                }
            }else {
                return;
            }
        }else {
            return;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }
}
