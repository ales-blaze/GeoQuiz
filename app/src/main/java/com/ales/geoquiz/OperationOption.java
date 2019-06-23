package com.ales.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OperationOption extends AppCompatActivity {
    private static final String QUESTION_DATA = "com.ales.geoquiz.alter_question_data";
    private Button mDeleteButton , mCreateButton , mUpdateButton , mReadButton ;
    private static final String USER_ALTER = "com.ales.geoquiz.alter_user_data";
    private static final String USER = " User";
    private static final String QUESTION = " Question";

    private boolean mIsUser = false;

    public static final int CREATE = 1;
    public static final int DELETE = 2;
    public static final int UPDATE = 3;
    public static final int READ   = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation_option);

        mDeleteButton = findViewById(R.id.delete_button);
        mCreateButton = findViewById(R.id.create_Button);
        mReadButton = findViewById(R.id.read_button);
        mUpdateButton = findViewById(R.id.update_button);

        final String isSelectionUser = getIntent().getStringExtra(USER_ALTER);
        final String isSelectionQuestion = getIntent().getStringExtra(QUESTION_DATA);

        if ( isSelectionUser != null && isSelectionUser.compareTo("user") == 0){
            setTextAsUser();
            mIsUser = true;
        }
        else if ( isSelectionQuestion != null && isSelectionQuestion.compareTo("question") == 0 ){
            setTextAsQuestion();
            mIsUser = false;
        }

        //listeners for the buttons
        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIsUser) {
                    intentCreationForUserDataAlter(CREATE);
                }else {
                    intentCreationForQuestionDataAlter(CREATE);
                }
            }
        });
        mDeleteButton.setOnClickListener((view) -> {
            if(mIsUser) {
                intentCreationForUserDataAlter(DELETE);
            }else {
                intentCreationForQuestionDataAlter(DELETE);
            }
        });

        mUpdateButton.setOnClickListener((view) -> {
            if(mIsUser) {
                intentCreationForUserDataAlter(UPDATE);
            }else {
                intentCreationForQuestionDataAlter(DELETE);
            }
        });

        mReadButton.setOnClickListener( (view) -> {
            if(mIsUser) {
                intentCreationForUserDataAlter(READ);
            }else {
                intentCreationForQuestionDataAlter(READ);
            }
        });
    }

    private void intentCreationForQuestionDataAlter(int mode) {
        Intent intent = QuestionDataAlter.createIntent(this , mode);
        startActivity(intent);
    }

    private void intentCreationForUserDataAlter(int mode) {
        Intent intent = UserDataAlter.createIntent(OperationOption.this, mode);
        startActivity(intent);
    }

    private void setTextAsQuestion() {
        mCreateButton.setText(mCreateButton.getText().toString().concat(QUESTION));
        mUpdateButton.setText(mUpdateButton.getText().toString().concat(QUESTION));
        mReadButton.setText(mReadButton.getText().toString().concat(QUESTION));
        mDeleteButton.setText(mDeleteButton.getText().toString().concat(QUESTION));
    }

    private void setTextAsUser() {
        mCreateButton.setText(mCreateButton.getText().toString().concat(USER));
        mDeleteButton.setText(mDeleteButton.getText().toString().concat(USER));
        mUpdateButton.setText(mUpdateButton.getText().toString().concat(USER));
        mReadButton.setText(mReadButton.getText().toString().concat(USER));
    }

    public static Intent createIntent(Context context , String is_user_data ) {
        Intent intent = new Intent(context , OperationOption.class);
        String user = "user";
        String question = "question";
        if ( is_user_data.compareTo(user) == 0) {
            intent = intent.putExtra(USER_ALTER, user);
        }
        else if (is_user_data.compareTo(question) == 0){
            intent = intent.putExtra(QUESTION_DATA , question);
        }
        return intent;
    }
}
