package com.coconuts.ariel.thelovedareproject;

import android.util.Log;

import com.coconuts.ariel.thelovedareproject.model.VerseOfDay;

import junit.framework.TestCase;

/**
 * Created by Ariel on 12/5/2015.
 */
public class VerseOfDayTest extends TestCase {

    VerseOfDay mVerse = new VerseOfDay("", "", "", "");

    public void testConstructor(){
        VerseOfDay verse = new VerseOfDay("Matthew", "19", "6", "With God all things are possible.");
        assertNotNull(verse);
    }

    public void testGetBookName(){
        VerseOfDay verse = new VerseOfDay("Matthew", "19", "6", "With God all things are possible.");
        assertEquals("Matthew", verse.getBookName());
    }

    public void testGetChapter(){
        VerseOfDay verse = new VerseOfDay("Matthew", "19", "6", "With God all things are possible.");
        assertEquals("19", verse.getChapter());
    }

    public void testGetVerse(){
        VerseOfDay verse = new VerseOfDay("Matthew", "19", "6", "With God all things are possible.");
        assertEquals("6", verse.getVerse());
    }

    public void testGetText(){
        VerseOfDay verse = new VerseOfDay("Matthew", "19", "6", "With God all things are possible.");
        assertEquals("With God all things are possible.", verse.getText());
    }

    public void testSetBookName(){
        mVerse.setBookName("John");
        String verse = mVerse.getBookName();
        assertEquals("John", verse);
    }

    public void testSetChapter(){
        mVerse.setChapter("3");
        String verse = mVerse.getChapter();
        assertEquals("3", verse);
    }

    public void testSetVerse(){
        mVerse.setVerse("16");
        String verse = mVerse.getVerse();
        assertEquals("16", verse);
    }

    public void testSetText(){
        mVerse.setText("For God so loved the world....");
        String verse = mVerse.getText();
        assertEquals("For God so loved the world....", verse);
    }


    public void testToString() {
        VerseOfDay verse = new VerseOfDay("Matthew", "19", "6", "With God all things are possible.");
        assertEquals("Matthew 19:6\nWith God all things are possible.", verse.toString());
    }
}
//String bookName, String chapter, String verse, String text