package com.coconuts.ariel.thelovedareproject.controller.dailyChallenges;


import android.app.AlertDialog;
import android.app.Dialog;
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
import android.widget.Toast;

import com.coconuts.ariel.thelovedareproject.R;
import com.coconuts.ariel.thelovedareproject.data.ReflectionDB;

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

        View v = inflater.inflate(R.layout.fragment_reflection_dialog, null);


        Bundle args = getArguments();
        int day = args.getInt("DAY");
        mDay = String.valueOf(day);

        mEditText = (EditText) v.findViewById(R.id.reflection_edit_text_view);
        mReflectionText = mEditText.getText().toString();

        builder.setView(inflater.inflate(R.layout.fragment_reflection_dialog, null))
            //Add the action buttons
            .setNeutralButton(R.string.share, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
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
                    if (mReflectionDB.findDayReflection(mDay)) {
                        Toast.makeText(getActivity(), "SAVED THE DAY " + mDay,
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "UPDATED THE DAY " + mDay,
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

        return builder.create();
    }



}
