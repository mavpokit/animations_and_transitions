package com.mavpokit.transitionsapp.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.mavpokit.transitionsapp.R;

/**
 * Created by Alex on 13.01.2017.
 */

public class CreateMarkerDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.about);
        builder.setMessage(R.string.about_message);
        builder.setPositiveButton("OK", null);
        return builder.create();
    }
}
