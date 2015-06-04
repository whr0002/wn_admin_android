package com.reclamation.woodlands.woodlandsreclamation.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.reclamation.woodlands.woodlandsreclamation.Adapter.FormAdapter;
import com.reclamation.woodlands.woodlandsreclamation.Adapter.SiteFormAdapter;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_SiteVisit.SiteVisitDAO;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_SiteVisit.SiteVisitForm;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_UserInfo.UI_DataSource;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_UserInfo.UserInfo;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.Form;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.SiteForm;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.SiteVisit.SVUploader;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.Uploader;
import com.reclamation.woodlands.woodlandsreclamation.R;

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
    public void submitForms(List<Form> forms) {
        UI_DataSource uiDao = new UI_DataSource(this);
        uiDao.open();
        UserInfo userInfo = uiDao.getUserInfo();
        uiDao.close();

        if(userInfo != null){
            if(userInfo.role != null && !userInfo.role.equalsIgnoreCase("null")){

                if(forms.size() > 0){
                    ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Uploading...");
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMax(forms.size());
                    progressDialog.setProgress(0);
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressDialog.show();


                    Uploader uploader = new SVUploader(this, forms.size(), dao, progressDialog);
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
