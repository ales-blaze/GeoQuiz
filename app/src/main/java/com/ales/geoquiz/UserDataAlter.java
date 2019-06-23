package com.ales.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.media.VolumeShaper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UserDataAlter extends AppCompatActivity {
    private static final String MODE  = "com.ales.geoquiz.mode";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_alter);
    }

    public static Intent createIntent(Context context , int mode ){
        Intent intent = new Intent(context , UserDataAlter.class);
        if(mode == OperationOption.CREATE)
            intent.putExtra(MODE , OperationOption.CREATE);
        else if ( mode == OperationOption.DELETE)
            intent.putExtra(MODE , OperationOption.DELETE);
        else if ( mode == OperationOption.READ)
            intent.putExtra(MODE , OperationOption.READ);
        else if ( mode == OperationOption.UPDATE) {
            intent.putExtra(MODE , OperationOption.CREATE);
        }
        return intent;
    }
}
