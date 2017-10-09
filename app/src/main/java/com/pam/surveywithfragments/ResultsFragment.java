package com.pam.surveywithfragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ResultsFragment extends Fragment{

    private Button continueButton;
    private Button resetButton;
    private TextView no_Counter;
    private TextView yes_Counter;

    public ConfigurationFragment newInstance(){
        final Bundle args = new Bundle();
        final ConfigurationFragment fragment = new ConfigurationFragment();
        Bundle resultsBundle = getIntent().getExtras();
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
                setResult(MainActivity.RESULT_CANCELED);
                ft.commit();
            }
        });
        resetButton= (Button) getView().findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resetIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(resetIntent);
                yes_Counter.setText("Yes Counts:0");
                no_Counter.setText("No Counts:0");
                ft.commit();

            }
        });

        return fragment;
    }
}
