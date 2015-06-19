package com.reclamation.woodlands.admin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.reclamation.woodlands.admin.DB.Table_DesktopReview.DesktopReview;
import com.reclamation.woodlands.admin.R;

import java.util.List;

/**
 * Created by Jimmy on 5/11/2015.
 */
public class Test2Adapter extends ArrayAdapter<DesktopReview>{
    public Test2Adapter(Context context, int resource) {
        super(context, resource);
    }

    public Test2Adapter(Context context, int resource, List<DesktopReview> reviewSites) {
        super(context, resource, reviewSites);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.test, null);

        }

        DesktopReview r = getItem(position);
        if(r != null){
            TextView title = (TextView) convertView.findViewById(R.id.title);
            if(title != null){
                title.setText(r.SiteID);
            }

        }

        return convertView;
    }
}
