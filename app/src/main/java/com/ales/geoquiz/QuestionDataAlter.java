package com.ales.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class QuestionDataAlter extends FragmentActivity {
    private static final String MODE  = "com.ales.geoquiz.mode";
//    private DatabaseHelper db = new DatabaseHelper(getApplicationContext());
    private DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_data_alter);

        Integer mode =  getIntent().getIntExtra(MODE , -1);
        FragmentManager fm = getSupportFragmentManager();

        if ( mode == OperationOption.CREATE) {
            Fragment fragment = fm.findFragmentById(R.id.fragment_layout);
            if( fragment == null) {
                fragment = new CreateQuestionFragment();
                fm.beginTransaction().add(R.id.fragment_layout , fragment).commit();
            }

        }
        else if ( mode == OperationOption.READ) {

        }
        else if (mode == OperationOption.DELETE) {

        }
        else if (mode == OperationOption.UPDATE) {

        }
    }

    public static Intent createIntent(Context context , int mode ){
        Intent intent = new Intent(context , QuestionDataAlter.class);
        if(mode == OperationOption.CREATE)
            intent.putExtra(MODE , mode);
        else if ( mode == OperationOption.DELETE)
            intent.putExtra(MODE , mode);
        else if ( mode == OperationOption.READ)
            intent.putExtra(MODE , mode);
        else if ( mode == OperationOption.UPDATE) {
            intent.putExtra(MODE , mode);
        }
        return intent;
    }
}
