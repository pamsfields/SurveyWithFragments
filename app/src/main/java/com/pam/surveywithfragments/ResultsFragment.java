package com.pam.surveywithfragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultsFragment extends Fragment{

    private Button continueButton;
    private Button resetButton;
    private TextView no_Counter;
    private TextView yes_Counter;

    public ConfigurationFragment newInstance(){
        Bundle resultsBundle = getArguments();
        int value = resultsBundle.getInt("resultTally");

        final ConfigurationFragment fragment = new ConfigurationFragment();

        final String noCountString = resultsBundle.getString("noCountString");
        final String yesCountString = resultsBundle.getString("yesCountString");
        yes_Counter= (TextView) getView().findViewById(R.id.yes_Counter);
        yes_Counter.setText( yesCountString);

        no_Counter= (TextView) getView().findViewById(R.id.no_Counter);
        no_Counter.setText(noCountString);

        continueButton = (Button) getView().findViewById(R.id.continue_button);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().setResult(MainActivity.RESULT_CANCELED);
                getActivity().finish();
            }
        });
        resetButton= (Button) getView().findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resetIntent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                startActivity(resetIntent);
                yes_Counter.setText("Yes Counts:0");
                no_Counter.setText("No Counts:0");
                getActivity().finish();

            }
        });

        return fragment;
    }
}
