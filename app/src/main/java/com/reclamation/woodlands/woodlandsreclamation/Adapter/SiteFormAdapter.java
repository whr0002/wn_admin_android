package com.reclamation.woodlands.woodlandsreclamation.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.reclamation.woodlands.woodlandsreclamation.Activity.FormActivity;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.DeleteViewListener;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.Form;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.FormViewHolder;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.SiteForm;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.ViewHolder;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.WarningViewListener;
import com.reclamation.woodlands.woodlandsreclamation.R;

import java.util.List;

/**
 * Created by Jimmy on 5/29/2015.
 */
public class SiteFormAdapter extends FormAdapter {



    public SiteFormAdapter(FormActivity context, int resource, List<Form> forms) {
        super(context, resource, forms);
    }

    @Override
    public void setView(ViewHolder viewHolder, int position, Form form) {

        SiteForm siteForm = (SiteForm) form;
        FormViewHolder formViewHolder = (FormViewHolder) viewHolder;

        formViewHolder.positionView.setText(position + 1 + "");
        formViewHolder.titleView.setText(siteForm.SiteID);
        formViewHolder.singleDeleteView.setOnClickListener(new DeleteViewListener(mFormActivity, position));
        formViewHolder.singleWarningView.setOnClickListener(new WarningViewListener(mFormActivity, position));

        if(siteForm.Message != null){
            formViewHolder.singleSubmitView.setVisibility(View.GONE);
            formViewHolder.singleWarningView.setVisibility(View.VISIBLE);
        }else{

            formViewHolder.singleSubmitView.setVisibility(View.VISIBLE);
            formViewHolder.singleWarningView.setVisibility(View.GONE);

        }


    }

    @Override
    public ViewHolder getViewHolder() {

        return new FormViewHolder();
    }

    @Override
    public void setViewHolder(View convertView, ViewHolder viewHolder) {
        FormViewHolder formViewHolder = (FormViewHolder) viewHolder;

        formViewHolder.positionView = (TextView) convertView.findViewById(R.id.position);
        formViewHolder.titleView = (TextView) convertView.findViewById(R.id.site_id);
        formViewHolder.singleSubmitView = (ImageView) convertView.findViewById(R.id.single_submit_view);
        formViewHolder.singleWarningView = (ImageView) convertView.findViewById(R.id.single_warning_view);
        formViewHolder.singleDeleteView = (ImageView) convertView.findViewById(R.id.single_delete_view);
    }
}
