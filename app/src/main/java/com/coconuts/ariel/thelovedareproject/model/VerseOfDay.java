package com.coconuts.ariel.thelovedareproject.model;

/**
 * Created by Ariel on 11/4/2015.
 */
public class VerseOfDay {

    private String mBookName;
    private String mChapter;
    private String mVerse;
    private String mText;


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
