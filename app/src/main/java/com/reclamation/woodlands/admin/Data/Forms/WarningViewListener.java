package com.reclamation.woodlands.admin.Data.Forms;

import com.reclamation.woodlands.admin.Activity.FormActivity;

/**
 * Created by Jimmy on 5/29/2015.
 */
public class WarningViewListener extends ViewClickListener {

    public WarningViewListener(FormActivity formActivity, int position, String message) {
        super(formActivity, position, message);

    }

    @Override
    public void onViewClicked() {

        showDialog("Errors", mMessage, "OK", null);
    }

    @Override
    public void onPositiveButtonClicked() {

    }

    @Override
    public void onNegativeButtonClicked() {

    }
}
