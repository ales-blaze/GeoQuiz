package com.ales.geoquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static final String ANSWER_IS_TRUE = "com.ales.geoquiz.answer_is_true";
    private static final String ANSWER_SHOWN = " com.ales.geoquiz.answer_shown";
    private boolean mAnswerIsTrue ;
    private Button mShowAnswer;
    private TextView mAnswerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(ANSWER_IS_TRUE  , false );
        mShowAnswer = findViewById(R.id.show_answer);
        mAnswerTextView = findViewById(R.id.answer_text_view);

        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( mAnswerIsTrue ){
                    mAnswerTextView.setText(R.string.true_button);
                }
                else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                answerShown(true);
            }
        });
    }

    public static boolean isAnswerShown( Intent intent) {
        return intent.getBooleanExtra(ANSWER_SHOWN , false );
    }

    private void answerShown(boolean data) {
        Intent backIntent = new Intent();
        backIntent.putExtra(ANSWER_SHOWN , data);
        setResult(RESULT_OK , backIntent);
    }

    public static Intent createIntent(Context context , boolean answer) {
        Intent intent = new Intent(context , CheatActivity.class );
        intent.putExtra(ANSWER_IS_TRUE , answer);
        return intent;
    }
}
