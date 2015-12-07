package com.coconuts.ariel.thelovedareproject;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import com.coconuts.ariel.thelovedareproject.controller.login.MainLoginActivity;
import com.robotium.solo.Solo;

import java.util.Random;

/**
 * Tests the functionality of the Login pages
 *
 * Created by Ariel on 12/6/2015.
 *
 */
public class MainLoginActivityTest extends ActivityInstrumentationTestCase2<MainLoginActivity> {
    private Solo solo;

    public MainLoginActivityTest() {
        super(MainLoginActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testLoginShowsUp() {
        solo.unlockScreen();
        boolean textFound = solo.searchText("");
        assertTrue("On Login Page", textFound);
    }

    public void testInvalidEmail() {
        solo.enterText(0, "Gajeel@Redfox.com");
        solo.enterText(1, "fairytail");
        solo.clickOnText("Login");
        boolean textFound = solo.searchText("Failed :");
        assertTrue("User add failed", textFound);
    }

    public void testInvalidPassword() {
        solo.enterText(0, "Natsu@Dragneel.com");
        solo.enterText(1, "Igneel");
        solo.clickOnText("Login");
        boolean textFound = solo.searchText("Failed :");
        assertTrue("User add failed", textFound);
    }

    public void testBlankEmail() {

        solo.clickOnText("Login");
        boolean textFound = solo.searchText("Email and Password must not be left blank.");
        assertTrue("Email was left blank", textFound);
    }

    public void testBlankPassword() {
        solo.enterText(0, "Erza@Scarlet.com");
        solo.clickOnText("Login");
        boolean textFound = solo.searchText("Email and Password must not be left blank.");
        assertTrue("Password was left blank", textFound);
    }

    public void testValidEmail() {
        solo.enterText(0, "ErzaScarlet.com");
        solo.enterText(1, "fairytail");
        solo.clickOnText("Login");
        boolean textFound = solo.searchText("Failed :");
        assertTrue("Email was invalid", textFound);
    }

    public void testValidPassword() {
        solo.enterText(0, "Erza@Scarlet.com");
        solo.enterText(1, "fairy");
        solo.clickOnText("Login");
        boolean textFound = solo.searchText("Failed :");
        assertTrue("Password was invalid", textFound);
    }

    public void testRegisterUser() {

        solo.clickOnText("Register");
        boolean textFound = solo.searchText("Enter Your Email Address");
        assertTrue("Went to the register fragment", textFound);
    }

    public void testAddDuplicateUserFragment() {

        solo.clickOnText("Register");
        solo.enterText(0, "Natsu@Dragneel.com");
        solo.enterText(1, "fairytail");
        solo.clickOnButton("Sign Up");
        boolean textFound = solo.searchText("Failed :");
        assertTrue("User add failed", textFound);
    }

    public void testRegisterUserFragment() {

        solo.clickOnText("Register");
        Random random = new Random();
        int number = random.nextInt(10000);
        solo.enterText(0, "FairyTail@test"+number + ".com");
        solo.enterText(1, "fairytail");
        solo.clickOnButton("Sign Up");
        boolean textFound = solo.searchText("Congragulations! You can now sign in!!");
        assertTrue("User register passed", textFound);
    }

    public void testRegisterFragmentBlankEmail() {

        solo.clickOnText("Register");
        solo.clickOnButton("Sign Up");
        boolean textFound = solo.searchText("Be a blank space, there cannot");
        assertTrue("Email was left blank", textFound);
    }

    public void testRegisterFragmentBlankPassword() {

        solo.clickOnText("Register");
        solo.enterText(0, "Erza@Scarlet.com");
        solo.clickOnButton("Sign Up");
        boolean textFound = solo.searchText("Be a blank space, there cannot");
        assertTrue("Password was left blank", textFound);
    }

    public void testRegisterFragmentValidEmail() {

        solo.clickOnText("Register");
        solo.enterText(0, "ErzaScarlet.com");
        solo.enterText(1, "fairytail");
        solo.clickOnButton("Sign Up");
        boolean textFound = solo.searchText("Failed :");
        assertTrue("Email was invalid", textFound);
    }

    public void testRegisterFragmentValidPassword() {

        solo.clickOnText("Register");
        solo.enterText(0, "Erza@Scarlet.com");
        solo.enterText(1, "fairy");
        solo.clickOnButton("Sign Up");
        boolean textFound = solo.searchText("Failed :");
        assertTrue("Password was invalid", textFound);
    }

    public void testLogin() {
        solo.enterText(0, "Natsu@Dragneel.com");
        solo.enterText(1, "fairytail");
        solo.clickOnButton("Login");
        boolean textFound = solo.searchText("Successfully logged in, you have.");
        Log.i("HERE", Boolean.toString(textFound));
        assertTrue("Went to the register fragment", textFound);
        solo.pressMenuItem(0);
    }

    @Override
    public void tearDown() throws Exception {
        //tearDown() is run after a test case has finished.
        //finishOpenedActivities() will finish all the activities that have been opened during the test execution.
        solo.finishOpenedActivities();
    }
}
