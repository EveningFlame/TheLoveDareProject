package com.coconuts.ariel.thelovedareproject;
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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
        MenuPageFragment.OnMenuPageFragmentInteractionListner   {

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

        int id = item.getItemId();

        if(id == R.id.action_logout) {
            mSharedPreferences = getSharedPreferences(getString(R.string.SHARED_PREFS),
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putBoolean(getString(R.string.LOGGEDIN), false);
            editor.commit();

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

}
