package com.reclamation.woodlands.admin.Data.Forms.SitePrep;

import android.content.Context;

import com.reclamation.woodlands.admin.Data.Forms.Form;
import com.reclamation.woodlands.admin.Data.Forms.Validator;

/**
 * Created by Jimmy on 10/15/2015.
 */
public class SitePrepValidator extends Validator {
    public SitePrepValidator(Context context) {
        super(context);
    }

    @Override
    public void validate(Form form) {
        form.Message = null;
    }
}
