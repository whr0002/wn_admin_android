package com.reclamation.woodlands.woodlandsreclamation.Data.Forms;

import com.reclamation.woodlands.woodlandsreclamation.Activity.FormActivity;

/**
 * Created by Jimmy on 5/29/2015.
 */
public class DeleteViewListener extends ViewClickListener {
    public DeleteViewListener(FormActivity formActivity, int position) {
        super(formActivity, position);
    }

    @Override
    public void onViewClicked() {
        showDialog("Delete", "Do you want to delete this report?", "Yes", "No");
    }

    @Override
    public void onPositiveButtonClicked() {
        mFormActivity.deleteInView(mPosition);
    }

    @Override
    public void onNegativeButtonClicked() {

    }
}