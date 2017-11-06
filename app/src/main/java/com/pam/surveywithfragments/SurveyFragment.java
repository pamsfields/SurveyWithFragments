package com.pam.surveywithfragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;

public class SurveyFragment extends Fragment {
    private Button mAnswer1Button;
    private Button mAnswer2Button;
    private Button resultsButton;
    private Button customButton;
    protected TextView QuestionTextView;
    private int answer1Count = 0;
    private int answer2Count = 0;
    private String answer2CountString;
    private String answer1CountString;
    private static final int RESULTS_CODE = 0;
    private static final int CUSTOM_CODE = 0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.surveyfragment, container, false);

        Intent launchIntent = new Intent(getActivity(), SurveyFragment.class);
        startActivity(launchIntent);
        Bundle resultsBundle = new Bundle();
        String results = getArguments().getString("resultTally");
        resultsBundle.putInt("resultTally", Integer.parseInt(answer1CountString));
        resultsBundle.putInt("resultTally", Integer.parseInt(answer2CountString));

        QuestionTextView = (TextView) view.findViewById(R.id.question_text_view);

        mAnswer1Button = (Button) view.findViewById(R.id.answer1_button);
        mAnswer1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer1Count++;
                Toast.makeText(getActivity(), "Thank You!", Toast.LENGTH_SHORT).show();
                answer1CountString = "@string/answer1_count_text" + answer1Count;
            }
        });
        mAnswer2Button = (Button) view.findViewById(R.id.answer2_button);
        mAnswer2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer2Count++;
                Toast.makeText(getActivity(), "Thank You!", Toast.LENGTH_SHORT).show();
                answer2CountString = "@string/answer2_count_text" + answer2Count;
            }
        });
        resultsButton = (Button) view.findViewById(R.id.results_button);
        resultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start viewSurveyResults
                Intent resultsIntent = new Intent(getActivity(), ResultsFragment.class);
                startActivityForResult(resultsIntent, RESULTS_CODE);
                onActivityResult(CUSTOM_CODE, RESULT_OK, resultsIntent);
            }
        });

        customButton = (Button) view.findViewById(R.id.custom_button);
        customButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start configureSurvey
                Intent customIntent = new Intent(getActivity(), ConfigurationFragment.class);
                startActivityForResult(customIntent, CUSTOM_CODE);
                onActivityResultCustom(CUSTOM_CODE, RESULT_OK, customIntent);
            }
        });
        return view;
    }



        protected void onActivityResultCustom(int CUSTOM_CODE, int resultCode, Intent customIntent){
            // Check which request it is that we're responding to
            if (CUSTOM_CODE == RESULT_OK) {
                // Make sure the request was successful
                if (resultCode == RESULT_OK) {

                    Bundle customBundle = getArguments();
                    customBundle.getString("QuestionTextView");
                    customBundle.getString("answer1");
                    customBundle.getString("answer2");

                    //TODO set up question and answers for the survey


                    Fragment frg = null;
                    frg = getFragmentManager().findFragmentByTag("SURVEY_FRAG_TAG");
                    final FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.detach(frg);
                    ft.attach(frg);
                    ft.commit();
                }
            }
        }
        public void onActivityResult(int RESULTS_CODE, int resultCode, int resultsIntent){
            // Check which request it is that we're responding to
            if (RESULTS_CODE == RESULT_OK) {
                // Make sure the request was successful
                if (resultCode == RESULT_OK) {
                    answer2Count = 0;
                    answer1Count =0;
                }
            }
        }
    }
