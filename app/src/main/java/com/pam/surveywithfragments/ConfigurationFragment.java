package com.pam.surveywithfragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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

    NewQuestionCreatedListener mNewQuestionCreatedListener;

    public ConfigurationFragment newInstance(){
        final Bundle args = new Bundle();
        final ConfigurationFragment fragment = new ConfigurationFragment();
        customQuestion = (EditText) getView().findViewById(R.id.custom_question_text);
        answer1 = (EditText) getView().findViewById(R.id.possible_answer1);
        answer2 = (EditText) getView().findViewById(R.id.possible_answer2);

        return fragment;
    }


    public void onAttach(Context context) {
        super.onAttach(context);

        //must add onAttach context
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.configurationfragment, container, false);

        Button confirmButton = (Button) view.findViewById(R.id.confirm_button);
        final EditText customQuestionText = (EditText) view.findViewById(R.id.custom_question_text);
        final EditText answer1 = (EditText) view.findViewById(R.id.possible_answer1);
        final EditText answer2 = (EditText) view.findViewById(R.id.possible_answer2);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Validate user has entered some text
                if (customQuestionText.getText().length() > 0||customQuestionText.getText().length() > 0) {
                    String text = customQuestionText.getText().toString();

                    //add information to bundle
                } else {
                    Toast.makeText(getActivity(), "Please enter a question and two answers", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mNewQuestionCreatedListener = null;
    }

    interface NewQuestionCreatedListener {
        //TODO add listener;
    }
}
