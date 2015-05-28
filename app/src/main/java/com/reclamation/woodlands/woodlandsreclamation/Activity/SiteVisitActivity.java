package com.reclamation.woodlands.woodlandsreclamation.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.reclamation.woodlands.woodlandsreclamation.DB.Table_SiteVisit.SiteVisitDAO;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_SiteVisit.SiteVisitForm;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.Form;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.SiteForm;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.SiteVisit.SVUploader;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.Uploader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy on 5/13/2015.
 */
public class SiteVisitActivity extends FormActivity {

    private SiteVisitDAO dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActionBar.setTitle("Site Visit Report");
    }

    @Override
    public List<SiteForm> getAllForms() {
        dao = new SiteVisitDAO(mContext);
        dao.open();

        List<SiteVisitForm> forms = dao.getAll();

        List<SiteForm> gSiteForms = new ArrayList<SiteForm>();
        if(forms != null && forms.size() > 0){
            for(SiteVisitForm sv : forms){
                gSiteForms.add(sv);
            }

        }

        dao.close();
        return gSiteForms;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public synchronized void deleteForm(SiteForm siteForm) {
        SiteVisitForm sv = (SiteVisitForm) siteForm;
        dao = new SiteVisitDAO(mContext);
        dao.open();

        dao.delete(sv);

        dao.close();


    }

    @Override
    public void submitForms(List<SiteForm> forms) {
        Log.i("debug", "Total number of forms: " + forms.size());

        if(forms.size() > 0){
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Uploading...");
            progressDialog.setMax(forms.size());
            progressDialog.setProgress(0);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.show();


            Uploader uploader = new SVUploader(mContext, forms.size(), dao, progressDialog);
            for(Form form : forms){
                uploader.execute(form);
            }

        }else{
            Toast.makeText(this, "Nothing to submit!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void updateForm(SiteForm siteForm) {

    }


    @Override
    public void createForm() {

        Intent intent = new Intent(mContext, SiteVisitDetailActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        SiteForm f = formAdapter.getItem(position);
        Intent i = new Intent(mContext, SiteVisitDetailActivity.class);
        i.putExtra("ID", f.ID);
        startActivityForResult(i, 1);
    }
}
