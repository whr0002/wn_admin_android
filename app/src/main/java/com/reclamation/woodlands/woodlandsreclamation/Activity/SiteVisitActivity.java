package com.reclamation.woodlands.woodlandsreclamation.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.reclamation.woodlands.woodlandsreclamation.DB.Table_Photo.Photo;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_Photo.PhotoDAO;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_SiteVisit.SiteVisitDAO;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_SiteVisit.SiteVisitForm;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_SiteVisit.SiteVisitProperties;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.SiteForm;

import java.io.File;
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
    public void deleteForm(SiteForm siteForm) {
        SiteVisitForm sv = (SiteVisitForm) siteForm;
        dao = new SiteVisitDAO(mContext);
        dao.open();

        dao.delete(sv);

        dao.close();

        PhotoDAO photoDAO = new PhotoDAO(mContext);
        photoDAO.open();
        List<Photo> photos = photoDAO.findPhotos(SiteVisitProperties.FORM_TYPE, siteForm.ID, null);

        if(photos != null && photos.size() > 0){
            Log.i("debug", "Size: " + photos.size());
            for(Photo p : photos){
                photoDAO.delete(p);

                File f = new File(p.path);
                if(f != null && f.exists()){
                    f.delete();
                }
            }
        }else{

        }

        photoDAO.close();
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
