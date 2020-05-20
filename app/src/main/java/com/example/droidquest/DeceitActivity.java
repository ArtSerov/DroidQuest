package com.example.droidquest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DeceitActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE = "com.example.droidquest.answer_is_true";
    private static final String EXTRA_ANSWER_SHOW = "com.example.droidquest.answer_show";
    private  static final String KEY_INDEX = "index";
    private boolean mAnswerIsTrue;
    private boolean mResponseReviewed = false;
    private TextView mAnswerTextView;
    private Button mShowAnswer;

    public static Intent newIntent(Context packageContext, boolean answerIsTrue){
        Intent i = new Intent(packageContext,DeceitActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE,answerIsTrue);
        return i;
    }

    public static boolean wasAnswerShown(Intent result){
        return result.getBooleanExtra(EXTRA_ANSWER_SHOW,false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deceit);
        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);
        if(savedInstanceState!=null)mResponseReviewed = savedInstanceState.getBoolean(KEY_INDEX);
        if(mResponseReviewed){
            if(mAnswerIsTrue)mAnswerTextView.setText(R.string.true_button);
            else mAnswerTextView.setText(R.string.false_button);
            setAnswerShownResult(true);
        }
        mShowAnswer = (Button)findViewById(R.id.show_answer_button);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAnswerIsTrue)mAnswerTextView.setText(R.string.true_button);
                else mAnswerTextView.setText(R.string.false_button);
                setAnswerShownResult(true);
                mResponseReviewed = true;
            }
        });
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOW,isAnswerShown);
        setResult(RESULT_OK,data);
    }

    @Override
    public void onSaveInstanceState(Bundle savdInstanceState) {
        super.onSaveInstanceState(savdInstanceState);
        savdInstanceState.putBoolean(KEY_INDEX,mResponseReviewed);
    }
}