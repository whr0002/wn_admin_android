package com.reclamation.woodlands.woodlandsreclamation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.reclamation.woodlands.woodlandsreclamation.DB.ReviewSite;
import com.reclamation.woodlands.woodlandsreclamation.R;

import java.util.List;

/**
 * Created by Jimmy on 5/11/2015.
 */
public class TestAdapter extends ArrayAdapter<ReviewSite>{
    public TestAdapter(Context context, int resource) {
        super(context, resource);
    }

    public TestAdapter(Context context, int resource, List<ReviewSite> reviewSites) {
        super(context, resource, reviewSites);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.test, null);

        }

        ReviewSite r = getItem(position);
        if(r != null){
            TextView title = (TextView) convertView.findViewById(R.id.title);
            if(title != null){
                title.setText(r.ReviewSiteID);
            }

        }

        return convertView;
    }
}
