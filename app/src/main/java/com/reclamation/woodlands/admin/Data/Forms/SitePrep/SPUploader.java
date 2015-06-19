package com.reclamation.woodlands.admin.Data.Forms.SitePrep;

import android.app.ProgressDialog;

import com.loopj.android.http.RequestParams;
import com.reclamation.woodlands.admin.Activity.FormActivity;
import com.reclamation.woodlands.admin.DB.DAO;
import com.reclamation.woodlands.admin.Data.Forms.Form;
import com.reclamation.woodlands.admin.Data.Forms.Uploader;

/**
 * Created by Jimmy on 6/2/2015.
 */
public class SPUploader extends Uploader {
    public SPUploader(FormActivity a, int total, DAO dao, ProgressDialog progressDialog) {
        super(a, total, dao, progressDialog);
    }

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public RequestParams getParams(Form form) {
        return null;
    }
}
