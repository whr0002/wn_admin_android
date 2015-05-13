package com.reclamation.woodlands.woodlandsreclamation.Activity;

import android.app.Activity;

import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.Form;
import com.reclamation.woodlands.woodlandsreclamation.R;

/**
 * Created by Jimmy on 5/13/2015.
 */
public class SiteVisitDetailActivity extends FormDetailActivity {
    @Override
    public void setLayout(Activity a) {
        a.setContentView(R.layout.activity_sitevisit_detail);
    }

    @Override
    public void addOrUpdate(Form f) {

    }

    @Override
    public Form getCurrentForm() {
        return null;
    }
}
