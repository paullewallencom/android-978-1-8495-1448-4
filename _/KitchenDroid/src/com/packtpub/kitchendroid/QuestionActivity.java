/*
 * Copyright Jason Morris 2008. All rights reserved.
 */
package com.packtpub.kitchendroid;

import android.app.Activity;
import android.content.Intent;

import android.content.res.Resources;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 *
 * @author Jason Morris
 */
public class QuestionActivity extends Activity implements OnClickListener {

    private Button[] buttons;

    private static int getQuestionID(
            final Resources res,
            final int index) {

        final String[] questions = res.getStringArray(R.array.questions);

        return res.getIdentifier(
                questions[index],
                "array",
                "com.packtpub.kitchendroid");
    }

    private int getQuestionIndex() {
        return getIntent().getIntExtra("KitchenDroid.Question", 0);
    }

    private void initQuestionScreen() {
        final TextView question = (TextView)findViewById(R.id.question);

        final ViewGroup answers = (ViewGroup)findViewById(R.id.answers);

        final Resources resources = getResources();
        final int questionID = getQuestionID(
                resources,
                getQuestionIndex());

        final String[] quesionData =
                resources.getStringArray(questionID);

        question.setText(quesionData[0]);

        final int answerCount = quesionData.length - 1;

        buttons = new Button[answerCount];

        for(int i = 0; i < answerCount; i++) {
            final String answer = quesionData[i + 1];
            final Button button = new Button(this);

            button.setText(answer);
            button.setOnClickListener(this);

            answers.addView(button);
            buttons[i] = button;
        }
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initQuestionScreen();
    }

    public void onClick(final View clicked) {
        final String[] questions = getResources().getStringArray(
                R.array.questions);

        if(getQuestionIndex() + 1 >= questions.length) {
            Toast.makeText(
                    this,
                    R.string.noMoreQuestions,
                    Toast.LENGTH_SHORT).show();
        } else {
            final Intent intent = new Intent(
                    QuestionActivity.this,
                    QuestionActivity.class);

            intent.putExtra("KitchenDroid.Question", getQuestionIndex() + 1);

            startActivity(intent);
        }
    }

}
