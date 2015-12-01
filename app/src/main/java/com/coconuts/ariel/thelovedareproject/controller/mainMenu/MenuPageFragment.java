package com.coconuts.ariel.thelovedareproject.controller.mainMenu;
/*
 * Ariel McNamara
 * TCSS 450: Mobile Apps
 * Fall 2015
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.coconuts.ariel.thelovedareproject.R;
import com.coconuts.ariel.thelovedareproject.controller.dailyChallenges.AllTheDaresActivity;


/**
 * Displays the main menu for the user.
 * A simple {@link Fragment} subclass.
 *
 * @author Ariel McNamara
 * @version Fall 2015
 *
 */
public class MenuPageFragment extends Fragment {

    private OnMenuPageFragmentInteractionListener mListener;
    private OnMenuPageAboutFragInteractionListener mAboutListener;

    public MenuPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_menu_page, container, false);


        Button dailyVerse = (Button) v.findViewById(R.id.daily_verse_button);
        Button challenge = (Button) v.findViewById(R.id.daily_challenge_button);
        Button about = (Button) v.findViewById(R.id.about_button);

        dailyVerse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mListener = (OnMenuPageFragmentInteractionListener) getActivity();
                mListener.onFragmentInteraction();
            }
        });

        challenge.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(view.getContext(), AllTheDaresActivity.class);
                getActivity().startActivity(i);

            }
        });

        about.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mAboutListener = (OnMenuPageAboutFragInteractionListener) getActivity();
                mAboutListener.onAboutFragInteraction();
            }
        });


        return v;
    }


    public interface OnMenuPageFragmentInteractionListener {
        public void onFragmentInteraction();
    }

    public interface OnMenuPageAboutFragInteractionListener {
        public void onAboutFragInteraction();
    }


}
