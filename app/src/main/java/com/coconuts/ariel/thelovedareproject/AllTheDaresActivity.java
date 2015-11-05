package com.coconuts.ariel.thelovedareproject;

import android.app.FragmentTransaction;
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

//        Bundle bundle = new Bundle();
//        bundle.put;
//
//        Intent intent = new Intent(this, ChallengeActivity.class);
//        intent.put
//        intent.putExtra("day", day);
//        this.startActivity(intent);
// Capture the student fragment from the activity layout
//        TodaysChallengeFragment todaysChallengeFragment = (TodaysChallengeFragment)
//                getSupportFragmentManager().findFragmentById(R.id.dare_list_fragment_container);
//
//        if (todaysChallengeFragment != null) {
//            // If article frag is available, we're in two-pane layout...
//
//            // Call a method in the student fragment to update its content
//            todaysChallengeFragment.updateStudentView(day);
//
//        } else {
            // Create fragment and give it an argument for the selected student
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            TodaysChallengeFragment newFragment = new TodaysChallengeFragment();
            Bundle args = new Bundle();
            args.putInt("POSITION", day);
            newFragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.dare_list_fragment_container, newFragment)
                    .addToBackStack(null)
                    .commit();
//        }

//
//
//        FragmentTransaction transaction = getFragmentManager()
//                .beginTransaction()
//                .replace(R.id.dare_list_fragment_container, todaysChallengeFragment)
//                .addToBackStack(null);
//
//        // Commit the transaction
//        transaction.commit();
    }
}
