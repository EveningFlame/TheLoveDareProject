package com.coconuts.ariel.thelovedareproject;
/*
 * Ariel McNamara
 * TCSS 450: Mobile Apps
 * Fall 2015
 */
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

    private static final String TAG = "information";
    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        mSharedPreferences = getSharedPreferences(getString(R.string.SHARED_PREFS),
                MODE_PRIVATE);

        if(savedInstanceState != null) {
            return;
        }

//        boolean loggedIn = mSharedPreferences.getBoolean(getString(R.string.LOGGEDIN),false);
//
//        if(findViewById(R.id.fragment_container) != null) {
//            FragmentTransaction fragmentTransaction =
//                    getSupportFragmentManager().beginTransaction();
//            if(!loggedIn){
//                LoginFragment loginFragment = new LoginFragment();
//                fragmentTransaction.add(R.id.fragment_container, loginFragment).commit();
//            } else {
//                Intent i = new Intent(this, MainMenuScreenActivity.class);
//                startActivity(i);
//                finish();
//            }
//        }
//        setContentView(R.layout.activity_main_login);
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.fragment_container, new LoginFragment()).commit();
    }
    @Override
    protected void onResume(){
        super.onResume();

        if(findViewById(R.id.fragment_container) != null){
            SharedPreferences sharedPreferences =
                    getSharedPreferences(getString(R.string.SHARED_PREFS), MODE_PRIVATE);
            boolean loggedIn =
                    sharedPreferences.getBoolean(getString(R.string.LOGGEDIN), false);
            if(!loggedIn) {
                LoginFragment loginFragment = new LoginFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, loginFragment).commit();
            } else {
                Intent i = new Intent(this, MainMenuScreenActivity.class);
                startActivity(i);
                finish();
            }
        }
    }

    public void onFragmentInteraction() {
        RegisterFragment registerFragment = new RegisterFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, registerFragment)
                .addToBackStack(null)
                .commit();
    }

//    /**
//     * Saving the state as data in a bundle. The keys to values of the instance must be of certain
//     * limited types.
//     *
//     * @param savedInstanceState instance of structure that maps string keys to values
//     */
//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState){
//        super.onSaveInstanceState(savedInstanceState);
//        Log.i(TAG, "onSaveInstanceState");
//        savedInstanceState.putString(PWD,);
//    }

    /**
     * Launch the the main menu activity, bound to the button,
     * finishes this activity.
     * @param v the view of the activity
     */
    public void launchAnother(View v) {
        Intent i = new Intent(v.getContext(), MainMenuScreenActivity.class);
        startActivity(i);
        finish();
    }

} //end Class
