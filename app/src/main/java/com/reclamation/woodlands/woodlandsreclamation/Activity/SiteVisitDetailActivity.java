package com.reclamation.woodlands.woodlandsreclamation.Activity;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.reclamation.woodlands.woodlandsreclamation.DB.FT_DataSource;
import com.reclamation.woodlands.woodlandsreclamation.DB.FacilityType;
import com.reclamation.woodlands.woodlandsreclamation.DB.SiteVisitDAO;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.SiteForm;
import com.reclamation.woodlands.woodlandsreclamation.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy on 5/13/2015.
 */
public class SiteVisitDetailActivity extends FormDetailActivity {

    private SiteVisitDAO dao;
    private SiteForm sf;

    private TextView siteIDView;

    private Spinner facilityTypeSpinner;


    @Override
    public void setLayout(Activity a) {
        a.setContentView(R.layout.activity_form_detail);

        dao = new SiteVisitDAO(a);

        siteIDView = (TextView) a.findViewById(R.id.siteIDView);
        facilityTypeSpinner = (Spinner) a.findViewById(R.id.facilityTypeSpinner);

        int id = a.getIntent().getIntExtra("ID", -1);
        if(id != -1){
            setForm(id);
        }

    }

    private void setForm(int id) {
        dao.open();
        sf = dao.findFormById(id);
        dao.close();

        if(sf != null){
            siteIDView.setText(sf.SiteID);
            setSpinner(facilityTypeSpinner);
        }

    }

    private void setSpinner(Spinner spinner) {
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


    @Override
    public void addOrUpdate(SiteForm f) {

    }


    @Override
    public SiteForm getCurrentForm() {
        return null;
    }
}
