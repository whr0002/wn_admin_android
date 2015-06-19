package com.reclamation.woodlands.admin.Data.Forms;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.reclamation.woodlands.admin.Activity.FormActivity;

import java.util.List;

/**
 * Created by Jimmy on 6/1/2015.
 */
public class DeleteDialog {

    private FormActivity mActivity;
    private List<Form> mForms;
    private String mMessage;

    public DeleteDialog(FormActivity formActivity, List<Form> forms, String message){
        mActivity = formActivity;
        mForms = forms;
        mMessage = message;

    }

    public void show(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);

        builder.setTitle("Delete");
        builder.setMessage(mMessage);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                for (Form f : mForms) {
                    mActivity.deleteForm(f);
                    mActivity.formAdapter.remove(f);
                }
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
