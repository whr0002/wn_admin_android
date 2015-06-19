package com.reclamation.woodlands.admin.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.reclamation.woodlands.admin.Activity.FormActivity;
import com.reclamation.woodlands.admin.Data.Forms.Form;
import com.reclamation.woodlands.admin.Data.Forms.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy on 5/13/2015.
 */
public abstract class FormAdapter extends ArrayAdapter<Form> {

    private int mLayoutResource;
    public FormActivity mFormActivity;
    public List<Boolean>isChecked;
    public boolean isEditMode = false;


    public FormAdapter(FormActivity context, int resource, List<Form> forms){
        super(context, resource, forms);
        mLayoutResource = resource;

        mFormActivity = context;
        isChecked = new ArrayList<Boolean>();

        if(forms != null){
            resetCheckTrace(forms.size());
        }
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

    public void resetCheckTrace(int size){
        isChecked = new ArrayList<>();
        for(int i=0;i<size;i++){
            isChecked.add(false);
        }
    }
    public abstract void setView(ViewHolder viewHolder, int position, Form form);
    public abstract ViewHolder getViewHolder();
    public abstract void setViewHolder(View convertView, ViewHolder viewHolder);
}
