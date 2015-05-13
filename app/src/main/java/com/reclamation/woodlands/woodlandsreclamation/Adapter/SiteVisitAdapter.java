package com.reclamation.woodlands.woodlandsreclamation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.reclamation.woodlands.woodlandsreclamation.DB.SiteVisitForm;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.Form;
import com.reclamation.woodlands.woodlandsreclamation.R;

import java.util.List;

/**
 * Created by Jimmy on 5/13/2015.
 */
public class SiteVisitAdapter extends FormAdapter{

    public SiteVisitAdapter(Context context, int resource, List<Form> forms) {
        super(context, resource, forms);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.in_listview, null);

        }

        SiteVisitForm form = (SiteVisitForm) getItem(position);

        TextView positionView = (TextView) convertView.findViewById(R.id.position);
        TextView siteView = (TextView) convertView.findViewById(R.id.site_id);
        positionView.setText(position + ". ");

        siteView.setText(form.SiteID);


        return convertView;
    }
}
