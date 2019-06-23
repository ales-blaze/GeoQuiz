package com.ales.geoquiz;


import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateQuestionFragment extends Fragment {
    private EditText mQuestionEditText;
    private RadioButton mTrueRadioButton , mFalseRadioButton;
    private RadioGroup mAnswerRadioGroup;
    private Button mSubmitButton;
    private Question mQuestion;
    public CreateQuestionFragment() {
        // Required empty public constructor
//        mQuestion = new Question();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_question, container, false);
        mQuestionEditText = view.findViewById(R.id.create_question_field);
//        mAnswerEditText = view.findViewById(R.id.create_answer_field);
        mTrueRadioButton = view.findViewById(R.id.true_button);
        mFalseRadioButton = view.findViewById(R.id.false_radio_button);
        mSubmitButton = view.findViewById(R.id.submit_created_question);

        mAnswerRadioGroup = view.findViewById(R.id.answer_radio_group);
        mTrueRadioButton = view.findViewById(R.id.true_radio_button);
        mFalseRadioButton = view.findViewById(R.id.false_radio_button);

        mQuestionEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mQuestion.setQuestionString(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mAnswerRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if ( checkedId == mTrueRadioButton.getId() ) {
                    mQuestion.setCorrectAnswer(Boolean.TRUE);
                }
                else if ( checkedId == mFalseRadioButton.getId() ){
                    mQuestion.setCorrectAnswer(Boolean.FALSE);
                }
            }
        });

        mSubmitButton.setOnClickListener(v -> {
            if(mQuestion.getQuestionString() != null && mQuestion.isCorrectAnswer() != null) {
                DatabaseHelper DBHealper = new DatabaseHelper(getContext());
                if (DBHealper.addQuestion(mQuestion)) {
                    Toast.makeText( this.getActivity().getApplicationContext() , "Question Added Successfully!" ,Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText( this.getActivity().getApplicationContext() , "Question Not Added!" ,Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

}
