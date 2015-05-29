package com.reclamation.woodlands.woodlandsreclamation.Data.Forms;

import android.util.Log;

import com.reclamation.woodlands.woodlandsreclamation.DB.Table_SiteVisit.SiteVisitForm;

/**
 * Created by Jimmy on 5/29/2015.
 */
public class SiteVisitValidator extends Validator{

    @Override
    public void validate(Form form) {

        SiteVisitForm svf = (SiteVisitForm) form;

        svf.Message = "- ### is required \n - $$$ is required \n";
        Log.i("debug", "Message: "+ svf.Message);

    }
}
