package com.coconuts.ariel.thelovedareproject.controller.login;
/*
 * Ariel McNamara
 * TCSS 450: Mobile Apps
 * Fall 2015
 */
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.coconuts.ariel.thelovedareproject.R;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * This fragment displays edit texts for the user to register for an account in order to
 * use this app. It uses a php to check the data base to either add in a valid input or not.
 * A simple {@link Fragment} subclass.
 *
 * @author Ariel McNamara
 * @version Fall 2015
 *
 */
public class RegisterFragment extends Fragment {

    private String url = "http://cssgate.insttech.washington.edu/~arielm3/registerUser.php";
    EditText mRegisterEmailText;
    EditText mRegisterPwdText;


    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Displays the edit text boxes and button in order to allow for user input and submission
     *
     * @param inflater
     * @param container the container that holds the fragment
     * @param savedInstanceState
     * @return view the user is seeing
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_register, container, false);

        mRegisterEmailText = (EditText) v.findViewById(R.id.register_email_text);
        mRegisterPwdText = (EditText) v.findViewById(R.id.register_pwd_text);

        mRegisterPwdText.setTransformationMethod(new PasswordTransformationMethod());

        Button signUpButton = (Button) v.findViewById(R.id.sign_up_button);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRegisterEmailText.getText().length() != 0 && mRegisterPwdText.getText().length() != 0) {
                    url += "?email=" + mRegisterEmailText.getText().toString()
                            + "&password=" +mRegisterPwdText.getText().toString();
                    new  RegisterWebTask().execute(url);
                } else {
                    Toast.makeText(getActivity(), "Be a blank space, there cannot!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }


    /**
     * Checks with the php file that the input is an appropriate email and password.
     * It will let the user know if either is incorrect or if they were successfully added to
     * the database.
     */
    private class RegisterWebTask extends AsyncTask<String, Void, String> {
        private static final String TAG = "RegisterWebTask";

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
                    Toast.makeText(getActivity(), "Congragulations! You can now sign in!!",
                            Toast.LENGTH_SHORT)
                            .show();
                    getFragmentManager().popBackStackImmediate();
                } else {
                    String reason = jsonObject.getString("error");
                    Toast.makeText(getActivity(), "Failed :" + reason,
                            Toast.LENGTH_SHORT)
                            .show();
                    url = "http://cssgate.insttech.washington.edu/~arielm3/registerUser.php";
                }
                //Pop back to other screen immediately
                //getFragmentManager().popBackStackImmediate();
                mRegisterEmailText.setText("");
                mRegisterPwdText.setText("");
            }
            catch(Exception e) {
                Log.d(TAG, "Parsing JSON Exception " + e.getMessage());
            }
        }
    }
}
