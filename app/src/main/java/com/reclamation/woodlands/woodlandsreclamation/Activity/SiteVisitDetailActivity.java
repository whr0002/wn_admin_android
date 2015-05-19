package com.reclamation.woodlands.woodlandsreclamation.Activity;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.reclamation.woodlands.woodlandsreclamation.DB.Table_FacilityType.FT_DataSource;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_FacilityType.FacilityType;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_ReviewSite.RS_DataSource;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_ReviewSite.ReviewSite;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_SiteVisit.SiteVisitDAO;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_SiteVisit.SiteVisitForm;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.SiteForm;
import com.reclamation.woodlands.woodlandsreclamation.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy on 5/13/2015.
 */
public class SiteVisitDetailActivity extends FormDetailActivity implements View.OnClickListener{

    private SiteVisitDAO svDao;
    private SiteForm sf;

//    private TextView siteIDView;

    private Spinner facilityTypeSpinner, siteIdSpinner;

    private Button drawerBtn;


    @Override
    public void setLayout(Activity a) {
        a.setContentView(R.layout.activity_form_detail);

        svDao = new SiteVisitDAO(a);

        setSpinners(a);

        drawerBtn = (Button) a.findViewById(R.id.open_drawer);
        drawerBtn.setOnClickListener(this);


        int id = a.getIntent().getIntExtra("ID", -1);
        if(id != -1){
            setForm(id);
        }

    }

    private void setSpinners(Activity a){
        facilityTypeSpinner = (Spinner) a.findViewById(R.id.facilityTypeSpinner);
        siteIdSpinner = (Spinner) a.findViewById(R.id.siteIdSpinner);

        setSiteIdSpinner(siteIdSpinner);
        setFTSpinner(facilityTypeSpinner);
    }

    private void setForm(int id) {
        svDao.open();
        sf = svDao.findFormById(id);
        svDao.close();

        if(sf != null){
            // Set form in View Mode

            siteIdSpinner.setSelection(getSpinnerIndex(siteIdSpinner, sf.SiteID));
        }

    }

    private void setFTSpinner(Spinner spinner) {
        ArrayAdapter<CharSequence> adapter;
        FT_DataSource ftDao = new FT_DataSource(this);
        ftDao.open();

        List<FacilityType> facilityTypes = ftDao.getAll();
        ftDao.close();

        if(facilityTypes != null && facilityTypes.size() > 0){

            ArrayList<CharSequence> values = new ArrayList<CharSequence>();

            for(FacilityType ft : facilityTypes){
                values.add(ft.FacilityTypeName);

            }

            adapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_item, values);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

        }
    }
    private void setSiteIdSpinner(Spinner spinner) {

        ArrayAdapter<CharSequence> adapter;

        RS_DataSource rsDao = new RS_DataSource(this);

        rsDao.open();

        List<ReviewSite> rss = rsDao.getAll();

        rsDao.close();

        if(rss != null && rss.size() > 0){

            ArrayList<CharSequence> values = new ArrayList<CharSequence>();

            for(ReviewSite rs : rss){
                values.add(rs.ReviewSiteID);

            }

            adapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_item, values);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

        }
    }

    public int getSpinnerIndex(Spinner spinner, String name){
        if(spinner != null && name != null){
            for(int i=0;i<spinner.getCount();i++){
                if(spinner.getItemAtPosition(i).equals(name)){
                    return i;
                }
            }
        }

        return 0;
    }

    @Override
    public void addOrUpdate(SiteForm f) {
        svDao.open();

        svDao.create((SiteVisitForm)f);

        svDao.close();
    }


    @Override
    public SiteForm getCurrentForm() {
        SiteVisitForm svf = new SiteVisitForm();

        svf.SiteID = siteIdSpinner.getSelectedItem().toString();

        return svf;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1){
            // coming from drawing activity
            if(resultCode == RESULT_OK){
                // saved drawing
                String result = data.getStringExtra("result");
                Log.i("debug", result);

            }
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.open_drawer:

                Intent intent = new Intent(mContext, DrawingActivity.class);
                startActivityForResult(intent, 1);

                break;

        }
    }
}
