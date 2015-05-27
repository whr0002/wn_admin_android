package com.reclamation.woodlands.woodlandsreclamation.Data.Forms.SiteVisit;

import com.loopj.android.http.RequestParams;
import com.reclamation.woodlands.woodlandsreclamation.DB.DAO;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_SiteVisit.SiteVisitForm;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.Form;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.Uploader;

/**
 * Created by Jimmy on 5/27/2015.
 */
public class SVUploader extends Uploader {


    public SVUploader(int total, DAO dao) {
        super(total, dao);
    }

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public RequestParams getParams(Form form) {
        SiteVisitForm f = (SiteVisitForm) form;
        RequestParams params = new RequestParams();

        params.put("ReviewSiteID", f.SiteID);



        return params;
    }
}
