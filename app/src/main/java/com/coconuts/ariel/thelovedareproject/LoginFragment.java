package com.coconuts.ariel.thelovedareproject;
/*
 * Ariel McNamara
 * TCSS 450: Mobile Apps
 * Fall 2015
 */
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author Ariel McNamara
 * @version Fall 2015
 *
 */
public class LoginFragment extends Fragment {

    private String url =
            "http://cssgate.insttech.washington.edu/~arielm3/login.php";
    private OnFragmentInteractionListner mListner;
    EditText mEmailText;
    EditText mPwdText;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        mEmailText = (EditText) v.findViewById(R.id.email_text);
        mPwdText = (EditText) v.findViewById(R.id.pwd_text);

        Button loginButton = (Button) v.findViewById(R.id.login_button);
        Button registerButton = (Button) v.findViewById(R.id.register_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mEmailText.getText().length() != 0 && mPwdText.getText().length() != 0) {
                    url += "?email=" + mEmailText.getText().toString()
                            + "&password=" +mPwdText.getText().toString();
                    new  UsersWebTask().execute(url);
                } else {
                    Toast.makeText(getActivity(), "Email and Password must not be left blank.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                Toast.makeText(getActivity(), "Clicked Register", Toast.LENGTH_SHORT).show();
                mListner = (OnFragmentInteractionListner) getActivity();
                mListner.onFragmentInteraction();
            }
        });
        return v;
    }

    public interface OnFragmentInteractionListner {
        public void onFragmentInteraction();
    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        ConnectivityManager connMgr = (ConnectivityManager)
//                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//        if(networkInfo != null && networkInfo.isConnected()){
//            new UsersWebTask().execute(url);
//        } else {
//            Toast.makeText(getActivity(),
//                    "No network connection available.", Toast.LENGTH_SHORT).show();
//        }
//
//        //mListView = (ListView) getActivity().findViewById(R.id.users_list);
//    }

    private class UsersWebTask extends AsyncTask<String, Void, String> {

        private static final String TAG = "UsersWebTask";

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
            int len = 500;

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
                JSONObject jsonObject = new JSONObject(s);
                String status = jsonObject.getString("result");
                if (status.equalsIgnoreCase("success")) {


                    Intent i = new Intent(getView().getContext(), MainMenuScreenActivity.class);
                    getActivity().startActivity(i);
                    getActivity().finish();
//                    Toast.makeText(getActivity(), "Should enter main menu now!!!",
//                            Toast.LENGTH_SHORT)
//                            .show();
                } else {
                    String reason = jsonObject.getString("error");
                    Toast.makeText(getActivity(), "Failed :" + reason,
                            Toast.LENGTH_SHORT)
                            .show();
                }
            }
            catch(Exception e) {
                Log.d(TAG, "Parsing JSON Exception " + e.getMessage());
            }
        }
    }




}

