package com.reclamation.woodlands.woodlandsreclamation.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.reclamation.woodlands.woodlandsreclamation.Adapter.FormAdapter;
import com.reclamation.woodlands.woodlandsreclamation.Adapter.SiteVisitAdapter;
import com.reclamation.woodlands.woodlandsreclamation.DB.SiteVisitDAO;
import com.reclamation.woodlands.woodlandsreclamation.DB.SiteVisitForm;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.Form;
import com.reclamation.woodlands.woodlandsreclamation.R;

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
    public List<Form> getAllForms() {
        dao = new SiteVisitDAO(mContext);
        dao.open();

        List<SiteVisitForm> forms = dao.getAll();

        List<Form> gForms = new ArrayList<Form>();
        if(forms != null && forms.size() > 0){
            for(SiteVisitForm sv : forms){
                gForms.add(sv);
            }

        }

        dao.close();
        return gForms;
    }

    @Override
    public void deleteForm(Form form) {
        SiteVisitForm sv = (SiteVisitForm) form;
        dao = new SiteVisitDAO(mContext);
        dao.open();

        dao.delete(sv);

        dao.close();
    }

    @Override
    public void updateForm(Form form) {

    }


    @Override
    public void createForm(Form form) {
        SiteVisitForm sv = (SiteVisitForm) form;
        dao = new SiteVisitDAO(mContext);
        dao.open();

        SiteVisitForm result = dao.create(sv);



        dao.close();
    }


    @Override
    public FormAdapter getAdapter() {
        FormAdapter adapter = new SiteVisitAdapter(mContext, R.layout.in_listview, getAllForms());
        return adapter;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent i = new Intent(mContext, SiteVisitDetailActivity.class);

        startActivity(i);
    }
}
