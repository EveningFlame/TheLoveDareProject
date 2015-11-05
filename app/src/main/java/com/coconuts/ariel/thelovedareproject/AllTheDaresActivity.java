package com.coconuts.ariel.thelovedareproject;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.coconuts.ariel.thelovedareproject.model.DailyChallenges;

import java.util.List;

public class AllTheDaresActivity extends AppCompatActivity
        implements DayListFragment.OnDayListFragmentInteractionListner{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_the_dares);


        if (savedInstanceState != null) return;

        if (findViewById(R.id.dare_list_fragment_container) != null) {
            // Create an instance of ExampleFragment
            DayListFragment dayListFragment = new DayListFragment();

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.dare_list_fragment_container, dayListFragment).commit();
        }
    }

    public void onFragmentInteraction(int day) {
//        TodaysChallengeFragment todaysChallengeFragment = new TodaysChallengeFragment();
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.dare_list_fragment_container, todaysChallengeFragment)
//                .addToBackStack(null)
//                .commit();

        Intent intent = new Intent(this, ChallengeActivity.class);
        intent.putExtra("day", day);
        this.startActivity(intent);
    }
}
