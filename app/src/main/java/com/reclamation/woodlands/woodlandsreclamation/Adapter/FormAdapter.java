package com.reclamation.woodlands.woodlandsreclamation.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.reclamation.woodlands.woodlandsreclamation.Activity.FormActivity;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.Form;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.ViewHolder;

import java.util.List;

/**
 * Created by Jimmy on 5/13/2015.
 */
public abstract class FormAdapter extends ArrayAdapter<Form> {

    private int mLayoutResource;
    public FormActivity mFormActivity;


    public FormAdapter(FormActivity context, int resource, List<Form> forms){
        super(context, resource, forms);
        mLayoutResource = resource;

        mFormActivity = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(mLayoutResource, null);

            viewHolder = getViewHolder();
            setViewHolder(convertView, viewHolder);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }


        Form form = getItem(position);
        setView(viewHolder, position, form);


        return convertView;
    }


    public abstract void setView(ViewHolder viewHolder, int position, Form form);
    public abstract ViewHolder getViewHolder();
    public abstract void setViewHolder(View convertView, ViewHolder viewHolder);
}
