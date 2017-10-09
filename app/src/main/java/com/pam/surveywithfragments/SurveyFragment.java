package com.pam.surveywithfragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;

public class SurveyFragment extends Fragment{
    private Button mYesButton;
    private Button mNoButton;
    private Button resultsButton;
    private Button customButton;
    protected TextView QuestionTextView;
    private int yesCount = 0;
    private int noCount = 0;
    private String noCountString;
    private String yesCountString;
    private static final int RESULTS_CODE = 0;
    private static final int CUSTOM_CODE = 0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.surveyfragment, container, false);

        Intent launchIntent = getIntent();

        launchIntent.putExtra("no answers", noCount);
        launchIntent.putExtra("yes answers", yesCount);

        QuestionTextView = (TextView) view.findViewById(R.id.question_text_view);

        mYesButton = (Button) view.findViewById(R.id.yes_button);
        mYesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yesCount++;
                Toast.makeText(SurveyFragment.this, "Thank You!", Toast.LENGTH_SHORT).show();
                yesCountString = "@string/yes_count_text" + yesCount;
            }
        });
        mNoButton = (Button) view.findViewById(R.id.no_button);
        mNoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noCount++;
                Toast.makeText(SurveyFragment.this, "Thank You!", Toast.LENGTH_SHORT).show();
                noCountString = "@string/no_count_text" + noCount;
            }
        });
        resultsButton = (Button) view.findViewById(R.id.results_button);
        resultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start ResultsActivity
                Intent resultsIntent = new Intent(SurveyFragment.this, ResultsFragment.class);
                startActivityForResult(resultsIntent, RESULTS_CODE);
                onActivityResult(CUSTOM_CODE, RESULT_OK, resultsIntent);
            }
        });

        customButton = (Button) view.findViewById(R.id.custom_button);
        customButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start CustomActivity
                Intent customIntent = new Intent(MainActivity.this, ConfigurationFragment.class);
                startActivityForResult(customIntent, CUSTOM_CODE);
                onActivityResultCustom(CUSTOM_CODE, RESULT_OK, customIntent);
            }
        });
    }

    @Override
    protected void onActivityResultCustom(int CUSTOM_CODE, int resultCode, Intent customIntent) {
        // Check which request it is that we're responding to
        if (CUSTOM_CODE == RESULT_OK) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                //get the requested information from CustomQuestionActivity
                //set up question and answers for the survey
                //restart SurveyActivity so new question comes up
            }
        }
    }
    @Override
    protected void onActivityResult(int RESULTS_CODE, int resultCode, Intent resultsIntent) {
        // Check which request it is that we're responding to
        if (RESULTS_CODE == RESULT_OK) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // reset counts for noCount and yesCount
            }
        }
    }

    public Intent getIntent() {
        return mIntent;
    }
}
