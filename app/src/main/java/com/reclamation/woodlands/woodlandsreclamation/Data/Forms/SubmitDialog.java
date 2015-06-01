package com.reclamation.woodlands.woodlandsreclamation.Data.Forms;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.reclamation.woodlands.woodlandsreclamation.Activity.FormActivity;

import java.util.List;

/**
 * Created by Jimmy on 5/27/2015.
 */
public class SubmitDialog {

    private FormActivity mActivity;
    private List<Form> mForms;
    private String mMessage;

    public SubmitDialog(FormActivity formActivity, List<Form> forms, String message){
        mActivity = formActivity;
        mForms = forms;
        mMessage = message;

    }

    public void show(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);

        builder.setTitle("Submit");
        builder.setMessage(mMessage);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                mActivity.submitForms(mForms);

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
