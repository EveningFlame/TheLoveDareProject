package com.coconuts.ariel.thelovedareproject.model;

import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ariel on 11/1/2015.
 */
public class DailyChallenges {
    /**
     * An array of DailyChallenges DARES.
     */
    public static List<ChallengeDares> DARES = new ArrayList<ChallengeDares>();

    /**
     * A map of daily dares, by day.
     */
    public static Map<String, ChallengeDares> DARES_MAP = new HashMap<String, ChallengeDares>();


    private static void addItem(ChallengeDares item) {
        DARES.add(item);
        DARES_MAP.put(item.dayNumber, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class ChallengeDares {
        public String dayNumber;
        public String lesson;
        public String passage;
        public String dare;

        public ChallengeDares(String dayNumber, String lesson, String passage, String dare) {
            this.dayNumber = dayNumber;
            this.lesson = lesson;
            this.passage = passage;
            this.dare = dare;
        }

        public String getDayNumber() { return dayNumber; }
        public String getLesson() { return lesson; }
        public String getPassage(){ return passage; }
        public String getDare() { return dare; }

        @Override
        public String toString() {
            return dayNumber;
        }
    }
}
