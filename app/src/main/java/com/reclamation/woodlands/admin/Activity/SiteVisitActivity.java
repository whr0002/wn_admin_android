package com.reclamation.woodlands.admin.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.reclamation.woodlands.admin.Adapter.FormAdapter;
import com.reclamation.woodlands.admin.Adapter.SiteFormAdapter;
import com.reclamation.woodlands.admin.DB.Table_SiteVisit.SiteVisitDAO;
import com.reclamation.woodlands.admin.DB.Table_SiteVisit.SiteVisitForm;
import com.reclamation.woodlands.admin.Data.Forms.Form;
import com.reclamation.woodlands.admin.Data.Forms.SiteForm;
import com.reclamation.woodlands.admin.Data.Forms.SiteVisit.SVUploader;
import com.reclamation.woodlands.admin.Data.Forms.Uploader;
import com.reclamation.woodlands.admin.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Jimmy on 5/13/2015.
 */
public class SiteVisitActivity extends FormActivity {

    private SiteVisitDAO dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mActionBar.setTitle("Site Visit Report");

//        dao = new SiteVisitDAO(this);
//        addTestData();
    }

    private void addTestData(){

        dao.open();

        for(int i=0;i<20;i++){
            SiteVisitForm form = new SiteVisitForm();

            form.Date = new SimpleDateFormat("yyyy-MM-dd ").format(new Date());
            Random random = new Random();
            form.Date += random.nextInt(24) + ":";
            form.Date += random.nextInt(60) + ":";
            form.Date += random.nextInt(60);
            form.SiteID = "01-03-52-20-W5M";
            form.FacilityType = "Access Road";

            form.RefuseComment = ""+i;


            dao.create(form);


        }

        dao.close();
    }

    @Override
    public List<Form> getAllForms() {
        dao = new SiteVisitDAO(mContext);
        dao.open();

        List<SiteVisitForm> forms = dao.getAll();

        List<Form> gSiteForms = new ArrayList<Form>();
        if(forms != null && forms.size() > 0){
            for(SiteVisitForm sv : forms){
                gSiteForms.add(sv);
            }

        }

        dao.close();
        return gSiteForms;
    }

    @Override
    public List<Form> getReadyForms() {
        dao = new SiteVisitDAO(mContext);
        dao.open();

        List<SiteVisitForm> forms = dao.getAll();

        List<Form> gSiteForms = new ArrayList<Form>();
        if(forms != null && forms.size() > 0){
            for(SiteVisitForm sv : forms){
                if(sv.Message == null) {
                    gSiteForms.add(sv);
                }
            }

        }

        dao.close();
        return gSiteForms;
    }


    @Override
    public synchronized void deleteForm(Form siteForm) {
        SiteVisitForm sv = (SiteVisitForm) siteForm;
        dao = new SiteVisitDAO(mContext);
        dao.open();

        dao.delete(sv);

        dao.close();


    }


    @Override
    public Uploader getUploader(List<Form> forms) {
        Uploader uploader = new SVUploader(this, forms.size(), dao, progressDialog);
        return uploader;
    }

    @Override
    public void updateForm(Form siteForm) {

    }

    @Override
    public FormAdapter getFormAdapter() {
        SiteFormAdapter siteFormAdapter = new SiteFormAdapter(this, R.layout.in_gridview_formlist, getAllForms());
        return siteFormAdapter;
    }


    @Override
    public void createForm() {

        Intent intent = new Intent(mContext, SiteVisitDetailActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        SiteForm f = (SiteForm) formAdapter.getItem(position);
        Intent i = new Intent(mContext, SiteVisitDetailActivity.class);
        i.putExtra("ID", f.ID);
        startActivityForResult(i, 1);
    }

}
