package com.mavpokit.transitionsapp.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.mavpokit.transitionsapp.R;

/**
 * Created by Alex on 13.01.2017.
 */

public class GithubOpenDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
//        builder.setTitle(R.string.about);
        builder.setMessage(R.string.github_message);
        builder.setNegativeButton("Cancel",null);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://github.com/mavpokit/animations_and_transitions"));
                //Intent chooser = Intent.createChooser(intent,"open github repo");
                Intent chooser = intent;
                if (chooser.resolveActivity(getActivity().getPackageManager())!=null)
                    startActivity(chooser);
            }
        });
        return builder.create();
    }
}
