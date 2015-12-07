package com.coconuts.ariel.thelovedareproject;

import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;

import com.coconuts.ariel.thelovedareproject.controller.mainMenu.MainMenuScreenActivity;
import com.robotium.solo.Solo;

import java.util.Random;

/**
 * Created by Ariel on 12/6/2015.
 */
public class MainMenuScreenActivityTest  extends ActivityInstrumentationTestCase2<MainMenuScreenActivity> {
    private Solo solo;

    public MainMenuScreenActivityTest() {
        super(MainMenuScreenActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testLoginShowsUp() {
        solo.unlockScreen();
        boolean textFound = solo.searchText("");
        assertTrue("On Main Menu Page", textFound);
    }

    public void testVerseOfTheDayFragment() {
        solo.clickOnText("Verse of the Day");
        boolean textFound = solo.searchText("Share");
        assertTrue("At verse of the day fragment", textFound);
    }

//    public void testVOTDFragmentShare() {
//        solo.clickOnText("Verse of the Day");
//        solo.clickOnButton("Share");
//        boolean textFound = solo.searchText("");
//        solo.goBack();
//        solo.goBack();
//        assertTrue("Share button pressed", textFound);
//    }

    public void testDailyChallengeButton() {
        solo.clickOnText("Daily Challenge");
        boolean textFound = solo.searchText("");
        assertTrue("Went to all the dares activity", textFound);
    }

    public void testAboutDialogFragment() {
        solo.clickOnText("About");
        boolean textFound = solo.searchText("About the Love Dare");
        assertTrue("Went about dialog fragment", textFound);
    }

    @Override
    public void tearDown() throws Exception {
        //tearDown() is run after a test case has finished.
        //finishOpenedActivities() will finish all the activities that have been opened during the test execution.
        solo.finishOpenedActivities();
    }
}
