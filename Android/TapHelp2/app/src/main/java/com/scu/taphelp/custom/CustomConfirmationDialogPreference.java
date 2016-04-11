package com.scu.taphelp.custom;

import android.content.Context;
import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.widget.Toast;

public class CustomConfirmationDialogPreference extends DialogPreference implements DialogInterface.OnClickListener {
    public CustomConfirmationDialogPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch(which) {
            case DialogInterface.BUTTON_POSITIVE :
                Toast.makeText(getContext(), "YES for account deletion clicked.", Toast.LENGTH_LONG).show();
                break;
            case DialogInterface.BUTTON_NEGATIVE :
                Toast.makeText(getContext(), "NO for account deletion clicked.", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
