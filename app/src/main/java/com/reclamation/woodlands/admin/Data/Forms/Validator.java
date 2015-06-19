package com.reclamation.woodlands.admin.Data.Forms;

import android.content.Context;

/**
 * Created by Jimmy on 5/29/2015.
 */
public abstract class Validator {

    public Context mContext;

    public Validator(Context context){
        mContext = context;
    }

    public abstract void validate(Form form);
}
