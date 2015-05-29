package com.reclamation.woodlands.woodlandsreclamation.Data.Forms;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

import com.reclamation.woodlands.woodlandsreclamation.Activity.FormActivity;

/**
 * Created by Jimmy on 5/29/2015.
 */
public abstract class ViewClickListener implements View.OnClickListener {

    public FormActivity mFormActivity;
    public int mPosition;

    public ViewClickListener(FormActivity formActivity, int position){

        mFormActivity = formActivity;
        mPosition = position;

    }

    public void showDialog(String title, String message, String positiveName, String negativeName){

        AlertDialog.Builder builder = new AlertDialog.Builder(mFormActivity);

        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveName, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                onPositiveButtonClicked();

            }
        });

        if(negativeName != null){
            builder.setNegativeButton(negativeName, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    onNegativeButtonClicked();
                    dialogInterface.dismiss();
                }
            });
        }


        builder.show();

    }

    @Override
    public void onClick(View view) {
       onViewClicked();
    }

    public abstract void onViewClicked();
    public abstract void onPositiveButtonClicked();
    public abstract void onNegativeButtonClicked();
}
