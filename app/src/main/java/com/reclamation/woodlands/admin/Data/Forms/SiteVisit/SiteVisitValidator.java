package com.reclamation.woodlands.admin.Data.Forms.SiteVisit;

import android.content.Context;

import com.reclamation.woodlands.admin.DB.Table_SiteVisit.SiteVisitForm;
import com.reclamation.woodlands.admin.Data.Forms.Form;
import com.reclamation.woodlands.admin.Data.Forms.Validator;

/**
 * Created by Jimmy on 5/29/2015.
 */
public class SiteVisitValidator extends Validator {

    public SiteVisitValidator(Context context) {
        super(context);
    }

    @Override
    public void validate(Form form) {

        SiteVisitForm svf = (SiteVisitForm) form;
        svf.Message = "";

        if(svf.SiteID == null || svf.SiteID.trim().length() == 0){
            svf.Message += "- Site ID is required \n";
        }
        if(svf.FacilityType == null || svf.FacilityType.trim().length() == 0){
            svf.Message += "- Facility Type is required \n";
        }

        // Check number of photos
        if(svf.numberOfPhotos > 35){
            svf.Message += "- Total number of photos must be less than 36 \n";
        }




        if(svf.Message.equals("")){
            svf.Message = null;
        }
    }
}
