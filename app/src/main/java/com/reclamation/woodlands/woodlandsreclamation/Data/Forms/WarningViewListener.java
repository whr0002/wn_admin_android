package com.reclamation.woodlands.woodlandsreclamation.Data.Forms;

import com.reclamation.woodlands.woodlandsreclamation.Activity.FormActivity;

/**
 * Created by Jimmy on 5/29/2015.
 */
public class WarningViewListener extends ViewClickListener {
    public WarningViewListener(FormActivity formActivity, int position) {
        super(formActivity, position);
    }

    @Override
    public void onViewClicked() {
        showDialog("Errors", "", "OK", null);
    }

    @Override
    public void onPositiveButtonClicked() {

    }

    @Override
    public void onNegativeButtonClicked() {

    }
}
