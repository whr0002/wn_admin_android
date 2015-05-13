package com.reclamation.woodlands.woodlandsreclamation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.FormType;
import com.reclamation.woodlands.woodlandsreclamation.R;

import java.util.ArrayList;

/**
 * Created by Jimmy on 5/12/2015.
 */
public class FormTypeAdapter extends ArrayAdapter<FormType>{


    public FormTypeAdapter(Context context, int resource) {
        super(context, resource);
    }

    public FormTypeAdapter(Context context, int resource, ArrayList<FormType> list){
        super(context, resource, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater =  LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.in_gridview, null);


        }

        TextView titleView = (TextView) convertView.findViewById(R.id.title);

        titleView.setText(getItem(position).formName);



        return convertView;
    }
}
