package com.reclamation.woodlands.woodlandsreclamation.Adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.Form;

import java.util.List;

/**
 * Created by Jimmy on 5/13/2015.
 */
public abstract class FormAdapter extends ArrayAdapter<Form> {


    public FormAdapter(Context context, int resource, List<Form> forms){
        super(context, resource, forms);
    }

}
