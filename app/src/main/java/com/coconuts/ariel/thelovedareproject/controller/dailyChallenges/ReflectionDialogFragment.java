package com.coconuts.ariel.thelovedareproject.controller.dailyChallenges;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.coconuts.ariel.thelovedareproject.R;
import com.coconuts.ariel.thelovedareproject.data.ReflectionDB;
import com.coconuts.ariel.thelovedareproject.model.ReflectionInfo;

import java.io.OutputStreamWriter;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReflectionDialogFragment extends DialogFragment {
    private ReflectionDB mReflectionDB;
    private String mDay;
    private EditText mEditText;
    private String mReflectionText;

    public ReflectionDialogFragment() {
        // Required empty public constructor

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setTitle("Time to Reflect");

        mReflectionDB = new ReflectionDB(getActivity());

//        mEditText = (EditText) v.findViewById(R.id.reflection_edit_text_view);
//        mReflectionText = mEditText.getText().toString();


        builder.setView(inflater.inflate(R.layout.fragment_reflection_dialog, null))
            //Add the action buttons
            .setNeutralButton(R.string.share, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    retrieveReflectionText();
                    Log.e("HERE I AM ", mReflectionText);
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, mReflectionText);
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);
                }
            })
            .setPositiveButton(R.string.save_text, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    retrieveReflectionText();
                    if (!mReflectionDB.findDayReflection(mDay)) {
                        if (mReflectionDB.insertReflection(mDay, mReflectionText)) {
                            Toast.makeText(getActivity(), "Saved your thoughts have been.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Day " + mDay + " failed to save.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        mReflectionDB.saveReflection(mDay, mReflectionText);

                        Toast.makeText(getActivity(), "Updated your day " + mDay + " is.",
                                    Toast.LENGTH_SHORT).show();

                    }
                }
            })
            .setNegativeButton(R.string.cancel_text, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dismiss();
                }
            });
//        mReflectionDB.closeDB();
        return builder.create();
    }

    private void retrieveReflectionText() {
        Dialog dialogView = getDialog();
        mEditText = (EditText) dialogView.findViewById(R.id.reflection_edit_text_view);
        mReflectionText = mEditText.getText().toString();

    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialogView = getDialog();
        Bundle args = getArguments();
        int day = args.getInt("DAY");
        mDay = String.valueOf(day);

        List<ReflectionInfo> list =
                AllTheDaresActivity.getReflectionList(dialogView.getContext());
        mEditText = (EditText) dialogView.findViewById(R.id.reflection_edit_text_view);

        for(int i = 0; i < list.size(); i++){
            Log.e("HERE I AM: info", list.get(i).toString());

            if(list.get(i).getDay().equals(mDay)){
                String reflection = list.get(i).getReflection();

                mEditText.setText(reflection);
            }
        }
    }

}
