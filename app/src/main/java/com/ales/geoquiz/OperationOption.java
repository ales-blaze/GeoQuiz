package com.ales.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class OperationOption extends AppCompatActivity {
    private static final String QUESTION_DATA = "com.ales.geoquiz.alter_question_data";
    private Button mDeleteButton , mCreateButton , mUpdateButton , mReadButton ;
    private static final String USER_ALTER = "com.ales.geoquiz.alter_user_data";
    private static final String USER = " User";
    private static final String QUESTION = " Question";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation_option);

        mDeleteButton = findViewById(R.id.delete_button);
        mCreateButton = findViewById(R.id.create_Button);
        mReadButton = findViewById(R.id.read_button);
        mUpdateButton = findViewById(R.id.update_button);

        String isSelectionUser = getIntent().getStringExtra(USER_ALTER);
        String isSelectionQuestion = getIntent().getStringExtra(QUESTION_DATA);

        if ( isSelectionUser != null && isSelectionUser.compareTo("user") == 0){
            setTextAsUser();
        }
        else if ( isSelectionQuestion != null && isSelectionQuestion.compareTo("question") == 0 ){
            setTextAsQuestion();
        }

        //listeners for the buttons

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
