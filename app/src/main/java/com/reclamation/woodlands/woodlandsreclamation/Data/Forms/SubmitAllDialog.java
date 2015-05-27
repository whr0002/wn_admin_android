package com.reclamation.woodlands.woodlandsreclamation.Data.Forms;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.reclamation.woodlands.woodlandsreclamation.Activity.FormActivity;

/**
 * Created by Jimmy on 5/27/2015.
 */
public class SubmitAllDialog{

    private FormActivity mActivity;


    public SubmitAllDialog(FormActivity formActivity){
        mActivity = formActivity;

    }

    public void show(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);

        builder.setTitle("Submit");
        builder.setMessage("Do you want to submit all forms?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                mActivity.submitForms(mActivity.getAllForms());

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();

            }
        });

        builder.show();
    }

}
