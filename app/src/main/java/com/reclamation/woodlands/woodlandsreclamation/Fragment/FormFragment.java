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

import com.reclamation.woodlands.woodlandsreclamation.Activity.FormList;
import com.reclamation.woodlands.woodlandsreclamation.Adapter.FormAdapter;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.FormType;
import com.reclamation.woodlands.woodlandsreclamation.R;

import java.util.ArrayList;

/**
 * Created by Jimmy on 5/12/2015.
 */
public class FormFragment extends android.support.v4.app.Fragment implements AdapterView.OnItemClickListener{

    private Context mContext;
    private GridView gridView;
    private FormAdapter formAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_form, container, false);

        mContext = this.getActivity();
        gridView = (GridView) v.findViewById(R.id.gridview);


        formAdapter = new FormAdapter(mContext, R.layout.in_gridview, getAllFormTypes());

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

        Intent intent = new Intent(mContext, FormList.class);

        FormType f = formAdapter.getItem(position);

        intent.putExtra("type", f.typeID);

        intent.putExtra("name", f.formName);

        startActivity(intent);
    }
}
