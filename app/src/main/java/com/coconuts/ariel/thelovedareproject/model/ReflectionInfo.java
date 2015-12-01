package com.coconuts.ariel.thelovedareproject.model;

/**
 * Created by Ariel on 11/30/2015.
 */
public class ReflectionInfo {
    private String mReflection, mDay;


    public ReflectionInfo(String day, String reflection) {
        mDay = day;
        mReflection = reflection;
    }

    public String getDay() {
        return mDay;
    }

    public String getPassword() {
        return mReflection;
    }

    public void setDay(String day) {
        mDay = day;
    }

    public void setReflection(String reflection) {
        mReflection = reflection;
    }

    @Override
    public String toString() {
        return "ReflectionInfo{" +
                "mDay = '" + mDay + '\'' +
                ", mReflection = '" + mReflection + '\'' +
                '}';
    }
}
