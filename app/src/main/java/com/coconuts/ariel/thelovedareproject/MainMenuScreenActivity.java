package com.coconuts.ariel.thelovedareproject;
/*
 * Ariel McNamara
 * TCSS 450: Mobile Apps
 * Fall 2015
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
/**
 *
 * @author Ariel McNamara
 * @version Fall 2015
 *
 */
public class MainMenuScreenActivity extends AppCompatActivity implements
        MenuPageFragment.OnFragmentInteractionListner   {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_screen);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_menu_fragment_container, new MenuPageFragment()).commit();

    }


    public void onFragmentInteraction() {
        DailyVerseFragment dailyVerseFragment = new DailyVerseFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_menu_fragment_container, dailyVerseFragment)
                .addToBackStack(null).commit();
    }

}
