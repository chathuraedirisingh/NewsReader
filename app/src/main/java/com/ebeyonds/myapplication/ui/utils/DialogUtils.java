package com.ebeyonds.myapplication.ui.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.ebeyonds.myapplication.R;

public class DialogUtils {

    public static void showAlert(Context context, String message) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog);
        TextView messageTextView = (TextView) dialog.findViewById(R.id.message);
        messageTextView.setText(message);
        Button okButton = (Button) dialog.findViewById(R.id.dialog_button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
