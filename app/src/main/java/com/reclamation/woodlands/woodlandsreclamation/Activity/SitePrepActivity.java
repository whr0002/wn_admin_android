package com.reclamation.woodlands.woodlandsreclamation.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.reclamation.woodlands.woodlandsreclamation.Adapter.FormAdapter;
import com.reclamation.woodlands.woodlandsreclamation.Adapter.SiteFormAdapter;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_SitePrep.SitePrepDAO;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_SitePrep.SitePrepForm;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_UserInfo.UI_DataSource;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_UserInfo.UserInfo;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.Form;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.SiteForm;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.SitePrep.SPUploader;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.Uploader;
import com.reclamation.woodlands.woodlandsreclamation.R;

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
    public void submitForms(List<Form> forms) {
        UI_DataSource uiDao = new UI_DataSource(this);
        uiDao.open();
        UserInfo userInfo = uiDao.getUserInfo();
        uiDao.close();
        SitePrepDAO dao = new SitePrepDAO(mContext);

        if(userInfo != null){
            if(userInfo.role != null && !userInfo.role.equalsIgnoreCase("null")){

                if(forms.size() > 0){
                    ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Uploading...");
                    progressDialog.setMax(forms.size());
                    progressDialog.setProgress(0);
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressDialog.show();


                    Uploader uploader = new SPUploader(this, forms.size(), dao, progressDialog);
                    for(Form form : forms){

                        uploader.execute(form);

                    }

                }else{
                    Toast.makeText(this, "There is no form to submit", Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast.makeText(this,
                        "Your account is not authorized to " +
                                "submit forms now, please contact us for details."
                        , Toast.LENGTH_LONG).show();

            }
        }else{
            Toast.makeText(this, "Please login first.", Toast.LENGTH_SHORT).show();
        }

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
