package com.coconuts.ariel.thelovedareproject;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


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

} //end Class
