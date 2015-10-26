package com.reclamation.woodlands.admin.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.reclamation.woodlands.admin.Adapter.FormAdapter;
import com.reclamation.woodlands.admin.Adapter.SiteFormAdapter;
import com.reclamation.woodlands.admin.DB.Table_SitePrep.SitePrepDAO;
import com.reclamation.woodlands.admin.DB.Table_SitePrep.SitePrepForm;
import com.reclamation.woodlands.admin.Data.Forms.Form;
import com.reclamation.woodlands.admin.Data.Forms.SiteForm;
import com.reclamation.woodlands.admin.Data.Forms.Uploader;
import com.reclamation.woodlands.admin.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy on 6/2/2015.
 */
public class SitePrepActivity extends FormActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mActionBar.setTitle("Site Prep Report");


    }

    @Override
    public List<Form> getAllForms() {
        SitePrepDAO sitePrepDAO = new SitePrepDAO(this);
        sitePrepDAO.open();

        List<SitePrepForm> forms = sitePrepDAO.getAll();
        List<Form> gForms = new ArrayList<Form>();

        for(SitePrepForm spf : forms){
            gForms.add(spf);
        }
        sitePrepDAO.close();
        return gForms;
    }

    @Override
    public List<Form> getReadyForms() {
        SitePrepDAO dao = new SitePrepDAO(mContext);
        dao.open();

        List<SitePrepForm> forms = dao.getAll();

        List<Form> gSiteForms = new ArrayList<Form>();
        if(forms != null && forms.size() > 0){
            for(SitePrepForm sv : forms){
                if(sv.Message == null) {
                    gSiteForms.add(sv);
                }
            }

        }

        dao.close();
        return gSiteForms;
    }

    @Override
    public void createForm() {
        Intent intent = new Intent(mContext, SitePrepDetailActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public synchronized void deleteForm(Form form) {
        SitePrepForm sv = (SitePrepForm) form;
        SitePrepDAO dao = new SitePrepDAO(mContext);
        dao.open();

        dao.delete(sv);

        dao.close();
    }

    @Override
    public Uploader getUploader(List<Form> forms) {
        return null;
    }

    @Override
    public void updateForm(Form form) {

    }

    @Override
    public FormAdapter getFormAdapter() {
        SiteFormAdapter siteFormAdapter = new SiteFormAdapter(this, R.layout.in_gridview_formlist, getAllForms());
        return siteFormAdapter;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        SiteForm f = (SiteForm) formAdapter.getItem(i);
        Intent intent = new Intent(mContext, SitePrepDetailActivity.class);
        intent.putExtra("ID", f.ID);
        startActivityForResult(intent, 1);
    }
}
