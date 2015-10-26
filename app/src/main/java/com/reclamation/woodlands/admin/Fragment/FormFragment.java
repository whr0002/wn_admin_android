package com.reclamation.woodlands.admin.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.reclamation.woodlands.admin.Activity.SitePrepActivity;
import com.reclamation.woodlands.admin.Activity.SiteVisitActivity;
import com.reclamation.woodlands.admin.Activity.TimesheetsActivity;
import com.reclamation.woodlands.admin.Adapter.FormTypeAdapter;
import com.reclamation.woodlands.admin.Data.Forms.FormType;
import com.reclamation.woodlands.admin.R;

import java.util.ArrayList;

/**
 * Created by Jimmy on 5/12/2015.
 */
public class FormFragment extends android.support.v4.app.Fragment implements AdapterView.OnItemClickListener{

    private Context mContext;
    private GridView gridView;
    private FormTypeAdapter formAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_form, container, false);

        mContext = this.getActivity();
        gridView = (GridView) v.findViewById(R.id.gridview);


        formAdapter = new FormTypeAdapter(mContext, R.layout.in_gridview, getAllFormTypes());

        gridView.setAdapter(formAdapter);
        gridView.setOnItemClickListener(this);


        return v;
    }

    private ArrayList<FormType> getAllFormTypes(){
        ArrayList<FormType> forms = new ArrayList<FormType>();
        FormType f = new FormType();
        f.typeID = FormType.TYPE_SITEVISIT;
        f.formName = "Site Visit Report";
        forms.add(f);

        FormType f1 = new FormType();
        f1.typeID = FormType.TYPE_TIMESHEET;
        f1.formName = "Timesheet";
        forms.add(f1);

        FormType f2 = new FormType();
        f2.typeID = FormType.TYPE_SITEPREP;
        f2.formName = "Site Prep";
        forms.add(f2);

        return forms;


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Toast.makeText(mContext, "" + position ,Toast.LENGTH_LONG).show();



        FormType f = formAdapter.getItem(position);

        switch(f.typeID){
            case FormType.TYPE_SITEVISIT:
                // Enter SiteVisitList Activity
                Intent intent = new Intent(mContext, SiteVisitActivity.class);
                startActivity(intent);

                break;

            case FormType.TYPE_TIMESHEET:

                Intent intent1 = new Intent(mContext, TimesheetsActivity.class);
                startActivity(intent1);
                break;

            case FormType.TYPE_SITEPREP:

                Intent intent2 = new Intent(mContext, SitePrepActivity.class);
                startActivity(intent2);
                break;
        }


    }
}
