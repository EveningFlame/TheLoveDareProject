package com.coconuts.ariel.thelovedareproject;
/*
 * Ariel McNamara
 * TCSS 450: Mobile Apps
 * Fall 2015
 */
import android.net.Uri;
import android.util.Log;
import android.widget.TextView;

import com.coconuts.ariel.thelovedareproject.model.VerseOfDay;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * This class uses retrieves the JSON array from an online web service and parses it for the
 * daily verse fragment
 * Created by Ariel on 11/4/2015.
 */
public class DataVerseRetriever {
    private static final String TAG = "DataVerseRetriever";

    public  byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if(connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() + ": within "
                        + urlSpec);
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];

            while ((bytesRead = in.read(buffer)) > 0 ) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }

    } //end of get URL Bytes

    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    public List<VerseOfDay> fetchItems() {

        List<VerseOfDay> items = new ArrayList<>();
        //http://labs.bible.org/api/?passage=votd&type=json
        try {
            String url = Uri.parse("https://labs.bible.org/api/").buildUpon()
                    .appendQueryParameter("passage", "votd")
                    .appendQueryParameter("type", "json")
                    .build().toString();
            Log.i(TAG, "Received URL: " + url);
//            String url = getUrlString("http://labs.bible.org/api/?passage=votd&type=json");
            String jsonString = getUrlString(url);
            Log.i(TAG, "Received JSON: " + jsonString);

            parseItems(items, jsonString);
        } catch(JSONException je) {
            Log.e(TAG, "Failed to parse JSON", je);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        }

        return items;
    }

//    private void parseItems(List<VerseOfDay> items, JSONObject jsonBody)
    private void parseItems(List<VerseOfDay> items, String s)
            throws IOException, JSONException {

        //JSONArray verseJsonArray = jsonBody.getJSONArray(items.toString());
        //JSONArray verseJsonArray = new JSONArray(jsonBody);
        JSONArray verseJsonArray = new JSONArray(s);
        for(int i = 0; i < verseJsonArray.length(); i++) {
            //JSONObject verseJsonObject = verseJsonArray.getJSONObject(i);
            JSONObject verseJsonObject = (JSONObject) verseJsonArray.get(i);
            VerseOfDay item = new VerseOfDay();

            String textWithoutOddCharacters =
                    verseJsonObject.getString("text").replace("</b>", "").replace("<b>", "")
                    .replace("&#8211;", " - ");

            item.setBookName(verseJsonObject.getString("bookname"));
            item.setChapter(verseJsonObject.getString("chapter"));
            item.setVerse(verseJsonObject.getString("verse"));
            item.setText(textWithoutOddCharacters);

//[{"bookname":"Romans","chapter":"11","verse":"33","text":"!"}]
            items.add(item);
        }


    }

}
