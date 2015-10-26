package com.reclamation.woodlands.admin.Services;

import android.content.Context;
import android.util.Log;

import com.reclamation.woodlands.admin.DB.Table_SiteLatLng.SLL_Datasource;
import com.reclamation.woodlands.admin.DB.Table_SiteLatLng.SiteLatLng;
import com.reclamation.woodlands.admin.Data.Forms.DistanceCalculator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy on 10/23/2015.
 */
public class SiteService {

    public List<SiteLatLng> getSitesAround(Context context, double currentLat, double currentLng, double kmLimit){
        SLL_Datasource dao = new SLL_Datasource(context);
        List<SiteLatLng> sites = new ArrayList<SiteLatLng>();
        List<SiteLatLng> filteredSites = new ArrayList<SiteLatLng>();
        dao.open();
        sites = dao.getAll();
        dao.close();

        for(int i=0;i<sites.size();i++){
            SiteLatLng sll = sites.get(i);

            if(sll.Lat != null && sll.Lng != null && !sll.Lat.equals("null") && !sll.Lng.equals("null")){
                double dLat = Double.parseDouble(sll.Lat);
                double dLng = Double.parseDouble(sll.Lng);
                double distance = DistanceCalculator.distance(currentLat, currentLng, dLat, dLng, "K");

                if(distance <= kmLimit){
                    Log.i("debug", sll.SiteID + "|" + sll.Lat + "|" + sll.Lng + "|" + distance + " km");
                    filteredSites.add(sll);
                }
            }
        }
        return filteredSites;

    }
}
