package com.pam.surveywithfragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConfigurationFragment extends Fragment {

    Button confirmButton;
    Button cancelButton;
    EditText customQuestion;
    EditText answer1;
    EditText answer2;

    private static final String TAG = "CONFIGURATION FRAGMENT";
    private static final String CONFIG_ARGS = "Configuration Arguments";

    public ConfigurationFragment newInstance(){

        final Bundle args = new Bundle();
        final ConfigurationFragment fragment = new ConfigurationFragment();
        customQuestion = (EditText) getView().findViewById(R.id.custom_question_text);
        answer1 = (EditText) getView().findViewById(R.id.possible_answer1);
        answer2 = (EditText) getView().findViewById(R.id.possible_answer2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.configurationfragment, container, false);

        Button confirmButton = (Button) view.findViewById(R.id.confirm_button);
        final EditText customQuestionText = (EditText) view.findViewById(R.id.custom_question_text);
        final EditText answer1tv = (EditText) view.findViewById(R.id.possible_answer1);
        final EditText answer2tv = (EditText) view.findViewById(R.id.possible_answer2);
        Button cancelButton = (Button) view.findViewById(R.id.cancel_button);


        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Validate user has entered some text
                if (customQuestionText.getText().length() > 0||customQuestionText.getText().length() > 0) {
                    String text = customQuestionText.getText().toString();
                    String answer1 = answer1tv.getText().toString();
                    String answer2 = answer2tv.getText().toString();
                    Bundle args = new Bundle();
                    ConfigurationFragment fragment = new ConfigurationFragment();
                    args.putString("QuestionTextView", text);
                    args.putString("answer1", answer1);
                    args.putString("answer2", answer2);
                    fragment.setArguments(args);

                } else {
                    Toast.makeText(getActivity(), "Please enter a question and two answers", Toast.LENGTH_LONG).show();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
            }

        });

        return view;
    }

}
