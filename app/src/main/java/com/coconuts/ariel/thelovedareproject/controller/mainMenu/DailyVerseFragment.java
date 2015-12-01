package com.coconuts.ariel.thelovedareproject.controller.mainMenu;
/*
 * Ariel McNamara
 * TCSS 450: Mobile Apps
 * Fall 2015
 */
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.coconuts.ariel.thelovedareproject.DataVerseRetriever;
import com.coconuts.ariel.thelovedareproject.R;
import com.coconuts.ariel.thelovedareproject.model.VerseOfDay;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;

/**
 * Displays the Verse of the day for the user using a TextView
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

        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new FetchItemsTask().execute();
        } else {
            Toast.makeText(getActivity()
                    , "Apologies, a network connection there is not.", Toast.LENGTH_LONG)
                    .show();
        }

        Button share = (Button) v.findViewById(R.id.share_button);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(networkInfo != null && networkInfo.isConnected()){
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, verse.get(0).toString());
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);
                } else {
                    Toast.makeText(getActivity()
                            , "Sadly due to lack of a connection," +
                            " verse to share there is not.", Toast.LENGTH_LONG)
                            .show();
                }

            }
        });

        return v;
    }

    /**
     * Retrieves the actual verse by sending it to the parser.
     */
    private class FetchItemsTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params){

            verse = new DataVerseRetriever().fetchItems();

            return null;
        }

        /**
         * Displays the verse in the text view
         * @param aVoid
         */
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            mTextView = (TextView) getActivity().findViewById(R.id.VOTD_text);

            mTextView.setText(verse.get(0).toString());
        }
    }


}
