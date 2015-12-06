package com.coconuts.ariel.thelovedareproject.controller.mainMenu;
/*
 * Ariel McNamara
 * TCSS 450: Mobile Apps
 * Fall 2015
 */
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.coconuts.ariel.thelovedareproject.R;
import com.coconuts.ariel.thelovedareproject.controller.dailyChallenges.AllTheDaresActivity;
import com.coconuts.ariel.thelovedareproject.controller.login.MainLoginActivity;
import com.coconuts.ariel.thelovedareproject.data.ReflectionDB;
import com.coconuts.ariel.thelovedareproject.model.ReflectionInfo;

import java.util.List;

/**
 *
 * @author Ariel McNamara
 * @version Fall 2015
 *
 */
public class MainMenuScreenActivity extends AppCompatActivity implements
        MenuPageFragment.OnMenuPageFragmentInteractionListener,
        MenuPageFragment.OnMenuPageAboutFragInteractionListener {

    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Main Menu");
        setContentView(R.layout.activity_main_menu_screen);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_menu_fragment_container, new MenuPageFragment()).commit();

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        ReflectionDB reflectionDB = new ReflectionDB(this);
        int id = item.getItemId();

        if(id == R.id.action_logout) {
            mSharedPreferences = getSharedPreferences(getString(R.string.SHARED_PREFS),
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putBoolean(getString(R.string.LOGGEDIN), false);
            editor.commit();


            //delete all reflection notes made by user when they logout
            List<ReflectionInfo> list =
                    MainMenuScreenActivity.getReflectionList(this);

            for(ReflectionInfo notes : list){
                reflectionDB.deleteReflection(notes.getDay());
            }

            //Return to login activity
            Intent i = new Intent(this, MainLoginActivity.class);
            startActivity(i);
            finish();
        }


        return true;
    }

    public void onFragmentInteraction() {
        DailyVerseFragment dailyVerseFragment = new DailyVerseFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_menu_fragment_container, dailyVerseFragment)
                .addToBackStack(null).commit();
    }

    public void onAboutFragInteraction() {
        AboutDialogFragment aboutDialogFragment = new AboutDialogFragment();
        aboutDialogFragment.show(getSupportFragmentManager(), getString(R.string.about));

    }

    public static List<ReflectionInfo>getReflectionList(Context c){
        ReflectionDB reflectionDB = new ReflectionDB(c);
        List<ReflectionInfo> list = reflectionDB.selectDayReflection();
        reflectionDB.closeDB();
        return list;
    }
}
