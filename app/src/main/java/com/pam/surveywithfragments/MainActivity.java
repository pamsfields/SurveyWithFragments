package com.pam.surveywithfragments;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String CONFIG_FRAG_TAG = "CONFIG FRAGMENT";
    private static final String SURVEY_FRAG_TAG = "SURVEY FRAGMENT";
    private static final String RESULTS_FRAG_TAG = "RESULTS FRAGMENT";
    private static final String TAG = "MAIN ACTIVITY";

    private SurveyFragment mSurveyFragment = new SurveyFragment();
    private ConfigurationFragment mConfigurationFragment= new ConfigurationFragment();
    private ResultsFragment mResultsFragment = new ResultsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        SurveyFragment mSurveyFragment = (SurveyFragment) fm.findFragmentByTag(SURVEY_FRAG_TAG);

        ft.add(R.id.survey_container, mSurveyFragment, SURVEY_FRAG_TAG);
        ft.commit();
    }

    @Override
    public void configureSurvey() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        //Create a new Detail fragment. Add it to the Activity.
        ConfigurationFragment configuNewFragment = ConfigurationFragment.newInstance();
        ft.add(android.R.id.content, configuNewFragment);
        //Add to the back stack, so if user presses back button from the Detail
        //fragment, it will revert this transaction - Activity will go back to the Add+List fragments
        ft.addToBackStack(CONFIG_FRAG_TAG);
        fm.popBackStack();
        ft.commit();
    }

    @Override
    public void viewSurveyResults() {

        //Find List fragment and tell it that the data has changed
        FragmentManager fm = getSupportFragmentManager();
        ResultsFragment mResultsFragment = (ResultsFragment) fm.findFragmentByTag(RESULTS_FRAG_TAG);

        //revert the last fragment transaction on the back stack.
        //This removes the Detail fragment from the Activity, which leaves the Add+List fragments.

        FragmentTransaction ft = fm.beginTransaction();
        fm.popBackStack();
        ft.commit();
    }

}
