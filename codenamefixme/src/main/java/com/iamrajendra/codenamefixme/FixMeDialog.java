package com.iamrajendra.codenamefixme;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public abstract class FixMeDialog {
    private AlertDialog dialog;

    public void  show(final Context context){
if (dialog==null) {
    dialog = new AlertDialog.Builder(context)
            .setTitle("Developer Desk")
            .setMessage("Do you want to help developer")
            .setCancelable(false)

            // Specifying a listener allows you to take an action before dismissing the dialog.
            // The dialog is automatically dismissed when a dialog button is clicked.
            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Continue with delete operation
                    dialog.cancel();
                    pleaseFixMe();
                    FixMeDialog.this.dialog =null;

                }
            })

            // A null listener allows the button to dismiss the dialog and take no further action.
            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    FixMeDialog.this.dialog =null;
                }
            })
            .setIcon(android.R.drawable.ic_menu_help)
            .show();
}
    }
    public abstract void pleaseFixMe();
}
