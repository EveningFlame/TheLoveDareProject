package com.coconuts.ariel.thelovedareproject;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.coconuts.ariel.thelovedareproject.model.DailyChallenges;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DayListFragment extends Fragment {

    private List<DailyChallenges.ChallengeDares> mList;

    private OnDayListFragmentInteractionListner mListener;

    private static final String
            url = "http://cssgate.insttech.washington.edu/~arielm3/dailyChallenges.php";
    private ListView mListView;
    private ArrayAdapter<DailyChallenges.ChallengeDares> mArrayAdapter;

    public DayListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_day_list, container, false);


//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position,
//                                    long id) {
//                Toast.makeText(getActivity(), "Clicked: " + position, Toast.LENGTH_SHORT).show();
//            }
//        });
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnDayListFragmentInteractionListner {
        public void onFragmentInteraction(int day);
    }

    @Override
    public void onStart() {
        super.onStart();
        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new UserWebTask().execute(url);
        } else {
            Toast.makeText(getActivity()
                    , "No network connection available.", Toast.LENGTH_SHORT)
                    .show();
        }

        mListView = (ListView) getActivity().findViewById(R.id.day_list);


        mList = DailyChallenges.DARES;
        mArrayAdapter = new ArrayAdapter<DailyChallenges.ChallengeDares>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, mList);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Toast.makeText(getActivity(), "Clicked: " + position, Toast.LENGTH_SHORT).show();
                //mListner = (OnFragmentInteractionListner) getActivity();
                mListener.onFragmentInteraction(position);
            }
        });
    }


    private class UserWebTask extends AsyncTask<String, Void, String> {

        private static final String TAG = "DayListWebTask";

        @Override
        protected String doInBackground(String...urls) {
            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

        // Given a URL, establishes an HttpUrlConnection and retrieves
// the web page content as a InputStream, which it returns as
// a string.
        private String downloadUrl(String myurl) throws IOException {
            InputStream is = null;
            // Only display the first 500 characters of the retrieved
            // web page content.
            int len = 200000;

            try {
                URL url = new URL(myurl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                // Starts the query
                conn.connect();
                int response = conn.getResponseCode();
                Log.d(TAG, "The response is: " + response);
                is = conn.getInputStream();

                // Convert the InputStream into a string
                String contentAsString = readIt(is, len);
                Log.d(TAG, "The string is: " + contentAsString);
                return contentAsString;

                // Makes sure that the InputStream is closed after the app is
                // finished using it.
            } catch(Exception e ) {
                Log.d(TAG, "Something happened" + e.getMessage());
            }
            finally {
                if (is != null) {
                    is.close();
                }
            }
            return null;
        }

        // Reads an InputStream and converts it to a String.
        public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
            Reader reader = null;
            reader = new InputStreamReader(stream, "UTF-8");
            char[] buffer = new char[len];
            reader.read(buffer);
            return new String(buffer);
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            // Parse JSON
            try {
                mList.clear();
                DailyChallenges.DARES.clear();

                JSONArray jsonarray = new JSONArray(s);
                for (int i=0; i<jsonarray.length(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonarray.get(i);
                    String dayNumber = (String) jsonObject.get("dayNumber");
                    String lesson = (String) jsonObject.get("lesson");
                    String passage = (String) jsonObject.get("passage");
                    String dare = (String) jsonObject.get("dare");
                    DailyChallenges.DARES.add(new DailyChallenges.ChallengeDares
                            (dayNumber, lesson, passage, dare));

                }

                mList = DailyChallenges.DARES;
                mListView.setAdapter(mArrayAdapter);

            }
            catch(Exception e) {
                Log.d(TAG, "Parsing JSON Exception " + e.getMessage());
            }
        }
    }


}
