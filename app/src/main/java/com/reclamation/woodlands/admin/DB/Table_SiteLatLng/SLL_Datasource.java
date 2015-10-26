package com.reclamation.woodlands.admin.DB.Table_SiteLatLng;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.reclamation.woodlands.admin.DB.DAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy on 10/22/2015.
 */
public class SLL_Datasource extends DAO<SiteLatLng> {
    public SLL_Datasource(Context context) {
        super(context);
    }

    @Override
    public SiteLatLng create(SiteLatLng o) {
        ContentValues cv = new ContentValues();
        cv.put(SiteLatLng.COLUMN_SITEID, o.SiteID);
        cv.put(SiteLatLng.COLUMN_LAT, o.Lat);
        cv.put(SiteLatLng.COLUMN_LNG, o.Lng);
        Cursor c = db.query(SiteLatLng.TABLE_NAME, null, SiteLatLng.COLUMN_SITEID + " = '" + o.SiteID + "'", null, null, null, null);
        c.moveToFirst();
        if(c.isAfterLast()){
            db.insert(SiteLatLng.TABLE_NAME, null, cv);
            Log.i("debug", o.SiteID + "|" + o.Lat + "|" + o.Lng);
        }


        return null;
    }

    @Override
    public void update(SiteLatLng o) {

    }

    @Override
    public void delete(SiteLatLng o) {
        db.delete(SiteLatLng.TABLE_NAME, SiteLatLng.COLUMN_SITEID + " = '" + o.SiteID + "'", null);
    }

    @Override
    public List<SiteLatLng> getAll() {
        List<SiteLatLng> sites = new ArrayList<SiteLatLng>();

        Cursor cursor = db.query(SiteLatLng.TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            SiteLatLng sll = cursorTo(cursor);
            sites.add(sll);
            cursor.moveToNext();
        }

        return sites;
    }

    @Override
    public SiteLatLng cursorTo(Cursor cursor) {
        SiteLatLng sll = new SiteLatLng();
        sll.SiteID = cursor.getString(0);
        sll.Lat = cursor.getString(1);
        sll.Lng = cursor.getString(2);
        return sll;
    }

    @Override
    public SiteLatLng findFormById(int id) {
        return null;
    }

    public void deleteAll(){
        db.delete(SiteLatLng.TABLE_NAME, null, null);
    }
}
