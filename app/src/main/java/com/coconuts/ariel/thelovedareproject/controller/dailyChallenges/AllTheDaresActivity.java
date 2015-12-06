package com.coconuts.ariel.thelovedareproject.controller.dailyChallenges;
/*
 * Ariel McNamara
 * TCSS 450: Mobile Apps
 * Fall 2015
 */
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.coconuts.ariel.thelovedareproject.R;
import com.coconuts.ariel.thelovedareproject.controller.mainMenu.MenuPageFragment;
import com.coconuts.ariel.thelovedareproject.data.ReflectionDB;
import com.coconuts.ariel.thelovedareproject.model.ReflectionInfo;

import java.util.List;

/**
 * Activity that displays and contains a list of all the 40 days in a list through a list fragment.
 *
 * @author Ariel McNamara
 * @version Fall 2015
 *
 */
public class AllTheDaresActivity extends AppCompatActivity
        implements DayListFragment.OnDayListFragmentInteractionListner,
        TodaysChallengeFragment.onTodaysChallengeReflectionFragmentListenerInteraction{
    /**
     * Adds the fragment that will display a list of all 40 days to this activity.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Days of Challenge");
        setContentView(R.layout.activity_all_the_dares);


        //if (savedInstanceState != null) return;

        //if (findViewById(R.id.dare_list_fragment_container) != null) {
            // Create an instance of ExampleFragment
            DayListFragment dayListFragment = new DayListFragment();

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.dare_list_fragment_container, dayListFragment)
                    .commit();
        //}
    }

    /**
     * Transitions to the fragment to display the information of the day that the user clicked on.
     *
     * @param day the day the user clicks
     */
    public void onListFragmentInteraction(int day) {

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

    }

    public void onRefelectionInteraction(int dayClicked){
        ReflectionDialogFragment reflectionDialogFragment = new ReflectionDialogFragment();

        //save day clicked for the purpose of sending to database as key
        Bundle args = new Bundle();
        args.putInt("DAY", dayClicked);
        reflectionDialogFragment.setArguments(args);

        reflectionDialogFragment.show(getSupportFragmentManager(), getString(R.string.reflection));
    }

    public static List<ReflectionInfo>getReflectionList(Context c){
        ReflectionDB reflectionDB = new ReflectionDB(c);
        List<ReflectionInfo> list = reflectionDB.selectDayReflection();
        reflectionDB.closeDB();
        return list;
    }
}
