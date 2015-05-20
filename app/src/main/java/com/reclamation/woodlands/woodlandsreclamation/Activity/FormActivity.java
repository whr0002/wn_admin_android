package com.reclamation.woodlands.woodlandsreclamation.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.reclamation.woodlands.woodlandsreclamation.Adapter.FormAdapter;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_ReviewSite.RS_DataSource;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_ReviewSite.ReviewSite;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_SiteVisit.SiteVisitForm;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.SiteForm;
import com.reclamation.woodlands.woodlandsreclamation.R;

import java.util.List;
import java.util.Random;

/**
 * Created by Jimmy on 5/12/2015.
 */
public abstract class FormActivity extends ActionBarActivity implements AdapterView.OnItemClickListener{

    protected Context mContext;
    protected ActionBar mActionBar;
    protected ListView mListview;
    protected FormAdapter formAdapter;

    protected final String DEBUG = "debug";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_formlist);

        mContext = this;

        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);

        mListview = (ListView) findViewById(R.id.listView);

        formAdapter = new FormAdapter(mContext, R.layout.in_listview, getAllForms());

        mListview.setAdapter(formAdapter);
        mListview.setOnItemClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.simple_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case android.R.id.home:
                finish();
                break;

            case R.id.create:
//                SiteForm f = getTestData();
                createForm();
                break;

            case R.id.delete:
                if(formAdapter.getCount() > 0){
                    SiteForm temp = formAdapter.getItem(0);
                    deleteForm(temp);

                    formAdapter.remove(temp);
                }
                break;

        }

        formAdapter.notifyDataSetChanged();

        return super.onOptionsItemSelected(item);
    }


    public SiteForm getTestData(){
        SiteVisitForm sv = new SiteVisitForm();
        RS_DataSource dao = new RS_DataSource(mContext);
        dao.open();
        List<ReviewSite> sites = dao.getAll();
        if(sites != null && sites.size() > 0){
            Random random = new Random();
            int i = random.nextInt(sites.size());

            ReviewSite r = sites.get(i);

            String siteID = r.ReviewSiteID;

            sv.SiteID = siteID;

            return sv;


        }

        dao.close();
        return sv;
    }

    private void onDataSetChanged(){
        formAdapter = new FormAdapter(mContext, R.layout.in_listview, getAllForms());
        mListview.setAdapter(formAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1){
            Log.i("debug", "back");
            onDataSetChanged();
        }
    }

    public abstract List<SiteForm> getAllForms();


    public abstract void createForm();


    public abstract void deleteForm(SiteForm siteForm);


    public abstract void updateForm(SiteForm siteForm);

}
