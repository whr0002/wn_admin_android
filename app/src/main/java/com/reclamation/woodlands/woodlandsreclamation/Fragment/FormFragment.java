package com.reclamation.woodlands.woodlandsreclamation.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.reclamation.woodlands.woodlandsreclamation.Activity.SiteVisitActivity;
import com.reclamation.woodlands.woodlandsreclamation.Adapter.FormTypeAdapter;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.FormType;
import com.reclamation.woodlands.woodlandsreclamation.R;

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
        f.typeID = 0;
        f.formName = "Site Visit Report";
        forms.add(f);

        FormType f1 = new FormType();
        f1.typeID = 1;
        f1.formName = "Site Preparation Report";
        forms.add(f1);

        FormType f2 = new FormType();
        f2.typeID = 2;
        f2.formName = "Vegetation Control Report";
        forms.add(f2);

        FormType f3 = new FormType();
        f3.typeID = 3;
        f3.formName = "Revegetation Report";
        forms.add(f3);

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

        }


    }
}
