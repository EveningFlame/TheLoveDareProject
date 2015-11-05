package com.coconuts.ariel.thelovedareproject;
/*
 * Ariel McNamara
 * TCSS 450: Mobile Apps
 * Fall 2015
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Activity created at the initial start of the app. Inflates the login in fragment and can
 * transition to the register fragment, where a user can create oneself.
 *
 * @author Ariel McNamara
 * @version Fall 2015
 *
 */
public class MainLoginActivity extends AppCompatActivity implements
        LoginFragment.OnFragmentInteractionListner  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new LoginFragment()).commit();
    }

    public void onFragmentInteraction() {
        RegisterFragment registerFragment = new RegisterFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, registerFragment)
                .addToBackStack(null)
                .commit();
    }

    /**
     * Launch the other activity, bound to the button,
     * finishes this activity.
     * @param v
     */
    public void launchAnother(View v) {
        Intent i = new Intent(v.getContext(), MainMenuScreenActivity.class);
        startActivity(i);
        finish();
    }

} //end Class
