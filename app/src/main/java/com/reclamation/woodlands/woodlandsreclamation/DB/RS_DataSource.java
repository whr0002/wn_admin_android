package com.reclamation.woodlands.woodlandsreclamation.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy on 5/11/2015.
 */
public class RS_DataSource extends AbastrctDataSource<ReviewSite>{

    public RS_DataSource(Context context) {
        super(context);
    }

    @Override
    public ReviewSite create(ReviewSite o) {
        ContentValues values = new ContentValues();
        values.put(RS_Properties.COLUMN_REVIEWSITEID, o.ReviewSiteID);
        values.put(RS_Properties.COLUMN_DATAOWNER, o.DataOwner);
        values.put(RS_Properties.COLUMN_DISPOSITIONNUMBER, o.DispositionNumber);
        values.put(RS_Properties.COLUMN_SWPNUMBER, o.SWPNumber);
        values.put(RS_Properties.COLUMN_AFE, o.AFE);
        values.put(RS_Properties.COLUMN_PROVINCIALAREANAME, o.ProvincialAreaName);
        values.put(RS_Properties.COLUMN_PROVINCIALAREATYPENAME, o.ProvincialAreaTypeName);
        values.put(RS_Properties.COLUMN_OPERATINGAREANAME, o.OperatingAreaName);
        values.put(RS_Properties.COLUMN_COUNTYNAME, o.CountyName);
        values.put(RS_Properties.COLUMN_NATURALREGIONNAME, o.NaturalRegionName);
        values.put(RS_Properties.COLUMN_NATURALSUBREGIONNAME, o.NaturalSubRegionName);
        values.put(RS_Properties.COLUMN_FMAHOLDERNAME, o.FMAHolderName);
        values.put(RS_Properties.COLUMN_SEEDZONE, o.SeedZone);
        values.put(RS_Properties.COLUMN_WELLBOREID, o.WellboreID);
        values.put(RS_Properties.COLUMN_UWI, o.UWI);
        values.put(RS_Properties.COLUMN_WELLSITENAME, o.WellsiteName);
        values.put(RS_Properties.COLUMN_UTMZONE, o.UTMZone);


        db.insert(RS_Properties.TABLE_REVIEWSITE, null, values);

        Cursor c = db.query(RS_Properties.TABLE_REVIEWSITE, null,
                RS_Properties.COLUMN_REVIEWSITEID + " = '" + o.ReviewSiteID + "'",
                null, null, null, null);
        c.moveToFirst();
        ReviewSite r = cursorTo(c);

        return r;
    }

    @Override
    public void delete(ReviewSite o) {
        db.delete(RS_Properties.TABLE_REVIEWSITE,
                RS_Properties.COLUMN_REVIEWSITEID + " = '" + o.ReviewSiteID + "'",
                null);
    }

    public void deleteAll(){
        db.delete(RS_Properties.TABLE_REVIEWSITE, null, null);
    }

    @Override
    public List<ReviewSite> getAll() {
        List<ReviewSite> reviewSites = new ArrayList<ReviewSite>();
        Cursor cursor = db.query(RS_Properties.TABLE_REVIEWSITE,
                null, null, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            ReviewSite reviewSite = cursorTo(cursor);
            reviewSites.add(reviewSite);
            cursor.moveToNext();
        }

        return reviewSites;
    }

    @Override
    public ReviewSite cursorTo(Cursor cursor) {
        ReviewSite o = new ReviewSite();

        o.ReviewSiteID = cursor.getString(0);
        o.DataOwner = cursor.getString(1);
        o.DispositionNumber = cursor.getString(2);
        o.SWPNumber = cursor.getString(3);
        o.AFE = cursor.getString(4);
        o.ProvincialAreaName = cursor.getString(5);
        o.ProvincialAreaTypeName = cursor.getString(6);
        o.OperatingAreaName = cursor.getString(7);
        o.CountyName = cursor.getString(8);
        o.NaturalRegionName = cursor.getString(9);
        o.NaturalSubRegionName = cursor.getString(10);
        o.FMAHolderName = cursor.getString(11);
        o.SeedZone = cursor.getString(12);
        o.WellboreID = cursor.getString(13);
        o.UWI = cursor.getString(14);
        o.WellsiteName = cursor.getString(15);
        o.UTMZone = cursor.getString(16);


        return o;
    }

    @Override
    public ReviewSite findFormById(int id) {
        return null;
    }
}
