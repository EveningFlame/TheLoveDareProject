package com.coconuts.ariel.thelovedareproject;
/*
 * Ariel McNamara
 * TCSS 450: Mobile Apps
 * Fall 2015
 */
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coconuts.ariel.thelovedareproject.model.VerseOfDay;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author Ariel McNamara
 * @version Fall 2015
 *
 */
public class DailyVerseFragment extends Fragment {
    private static final String TAG = "DailyVerseFragment";

    private List<VerseOfDay> verse;
    private TextView mTextView;

    public DailyVerseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_daily_verse, container, false);
        new FetchItemsTask().execute();

//        TextView tv = (TextView) getActivity().findViewById(R.id.VOTD_text);
//        String text = verse.get(0).toString();
//
//        tv.setText(text);

        return v;
    }

    private class FetchItemsTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params){
//            try{
//                String result = new DataVerseRetriever().getUrlString("http://labs.bible.org/api/?passage=votd&type=json");
//                Log.i(TAG, "Fetched contents of URL: " + result);
//            } catch (IOException ioe) {
//                Log.e(TAG, "Failed to fetch URL: ", ioe);
//            }
            verse = new DataVerseRetriever().fetchItems();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //        TextView tv = (TextView) getActivity().findViewById(R.id.VOTD_text);
//        String text = verse.get(0).toString();
//
//        tv.setText(text);
            mTextView = (TextView) getActivity().findViewById(R.id.VOTD_text);

            mTextView.setText(verse.get(0).toString());
        }
    }


}
