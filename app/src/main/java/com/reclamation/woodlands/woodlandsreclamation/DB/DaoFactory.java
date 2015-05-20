package com.reclamation.woodlands.woodlandsreclamation.DB;

import android.content.Context;

import com.reclamation.woodlands.woodlandsreclamation.DB.Table_Photo.PhotoDAO;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_SiteVisit.SiteVisitDAO;

/**
 * Created by Jimmy on 5/20/2015.
 */
public class DaoFactory {
    private Context context;
    public DaoFactory(Context c){
        context = c;
    }

    public DAO getDAO(String name){
        if(name != null){

            if(name.equalsIgnoreCase("PhotoDao")){
                return new PhotoDAO(context);
            }

            if(name.equalsIgnoreCase("SiteVisitDao")){
                return new SiteVisitDAO(context);
            }

        }

        return null;
    }
}
