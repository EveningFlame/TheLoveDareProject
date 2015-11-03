package com.coconuts.ariel.thelovedareproject.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ariel on 11/1/2015.
 */
public class Users {
    /**
     * An array of user items.
     */
    public static List<UsersInfo> ITEMS = new ArrayList<UsersInfo>();

    /**
     * A map of user items, by email.
     */
    public static Map<String, UsersInfo> ITEM_MAP = new HashMap<String, UsersInfo>();


    private static void addItem(UsersInfo item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.email, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class UsersInfo {
        public String email;
        public String pwd;

        public UsersInfo(String email, String pwd) {
            this.email = email;
            this.pwd = pwd;
        }

        @Override
        public String toString() {
            return email;
        }
    }
}
