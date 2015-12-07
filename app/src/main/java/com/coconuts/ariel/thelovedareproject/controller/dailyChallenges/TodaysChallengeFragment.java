package com.coconuts.ariel.thelovedareproject.controller.dailyChallenges;
/*
 * Ariel McNamara
 * TCSS 450: Mobile Apps
 * Fall 2015
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.coconuts.ariel.thelovedareproject.R;
import com.coconuts.ariel.thelovedareproject.model.DailyChallenges;

import java.util.List;

/**
 * Displays all the information that the user wishes to see concerning the day, that days passage,
 * and that day's dare.
 *
 * @author Ariel McNamara
 * @version Fall 2015
 *
 */
public class TodaysChallengeFragment extends Fragment {
    private int mDayClicked = -1;
    private int mReflectDay = -1;
    private static final String TAG = "TodaysChallengeFragment";

    private onTodaysChallengeReflectionFragmentListenerInteraction mRefelctionListener;


    private List<DailyChallenges.ChallengeDares> mList;

    public TodaysChallengeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();

        Log.d(TAG, "The args is: " + args);

        if (args != null) {
            // Set the student view with the position sent through
            updateStudentView(args.getInt("POSITION"));
        } else if (mDayClicked != -1) {
            // Set article based on saved instance state defined during onCreateView
            updateStudentView(mDayClicked);
        }
    }

    /**
     * Displays the information corresponding to the day that the user pressed on the screen.
     *
     * @param pos the day that the user clicked
     */
    public void updateStudentView(int pos) {
        TextView mDayTextView = (TextView) getActivity().findViewById(R.id.day_text);
        TextView mLessonTextView = (TextView) getActivity().findViewById(R.id.lesson_text);
        TextView mChapterVerseTextView = (TextView) getActivity().findViewById(R.id.chapter_verse_text);
        TextView mDareTextView = (TextView) getActivity().findViewById(R.id.dare_text);

        DailyChallenges.ChallengeDares dayDare = DailyChallenges.DARES.get(pos);

        mDayTextView.setText(dayDare.getDayNumber() + ":");
        mLessonTextView.setText(dayDare.getLesson());
        mChapterVerseTextView.setText(dayDare.getPassage());
        mDareTextView.setText(dayDare.getDare());

        mReflectDay = pos + 1;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_todays_challenge, container, false);

        Button reflection = (Button) v.findViewById(R.id.reflect_button);

        reflection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRefelctionListener = (AllTheDaresActivity) getActivity();
                mRefelctionListener.onRefelectionInteraction(mReflectDay);
            }
        });

        return v;
    }

    /**
     * The interface used when the reflection button is pressed. Sends in the day that the user
     * is on.
     */
    public interface onTodaysChallengeReflectionFragmentListenerInteraction{
        public void onRefelectionInteraction(int dayClicked);
    }

}
