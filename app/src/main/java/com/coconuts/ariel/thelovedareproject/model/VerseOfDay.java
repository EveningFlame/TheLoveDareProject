package com.coconuts.ariel.thelovedareproject.model;
/*
 * Ariel McNamara
 * TCSS 450: Mobile Apps
 * Fall 2015
 */
/**
 * Constructs an object for the verse of the day.
 * Created by Ariel on 11/4/2015.
 */
public class VerseOfDay {

    private String mBookName;
    private String mChapter;
    private String mVerse;
    private String mText;

    public VerseOfDay(){
        //blank constructor
    }

    public VerseOfDay(String bookName, String chapter, String verse, String text){
        mBookName = bookName;
        mChapter = chapter;
        mVerse = verse;
        mText = text;
    }


    public String getBookName() {    return mBookName;    }
    public String getChapter() {    return mChapter;    }
    public String getVerse() {    return mVerse;   }
    public String getText() {    return mText;   }

    public void setBookName(String bookName) {
        mBookName = bookName;
    }
    public void setChapter(String chapter) {
        mChapter = chapter;
    }
    public void setVerse(String verse) {
        mVerse = verse;
    }
    public void setText(String text) {
        mText = text;
    }

    @Override
    public String toString() {
        return mBookName + " " + mChapter
                + ":" + mVerse + "\n" + mText;
    }
}
