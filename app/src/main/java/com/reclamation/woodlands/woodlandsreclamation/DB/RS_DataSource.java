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

    private String[] allColumns = {
            RS_Properties.COLUMN_REVIEWSITEID,
            RS_Properties.COLUMN_AFE,
            RS_Properties.COLUMN_PROVINCIALAREANAME,
            RS_Properties.COLUMN_PROVINCIALAREATYPENAME,
            RS_Properties.COLUMN_OPERATINGAREANAME,
            RS_Properties.COLUMN_COUNTYNAME,
            RS_Properties.COLUMN_NATURALREGIONNAME,
            RS_Properties.COLUMN_NATURALSUBREGIONNAME,
            RS_Properties.COLUMN_FMAHOLDERNAME,
            RS_Properties.COLUMN_SEEDZONE,
            RS_Properties.COLUMN_WELLBOREID,
            RS_Properties.COLUMN_UWI,
            RS_Properties.COLUMN_WELLSITENAME,
            RS_Properties.COLUMN_UTMZONE,
            RS_Properties.COLUMN_PROVINCIALAREA,
            RS_Properties.COLUMN_PROVINCIALAREATYPE,
            RS_Properties.COLUMN_OPERATINGAREA,
            RS_Properties.COLUMN_COUNTY,
            RS_Properties.COLUMN_NATURALREGION,
            RS_Properties.COLUMN_NATURALSUBREGION,
            RS_Properties.COLUMN_FMAHOLDER

    };

    public RS_DataSource(Context context) {
        super(context);
    }

    @Override
    public ReviewSite create(ReviewSite o) {
        ContentValues values = new ContentValues();
        values.put(RS_Properties.COLUMN_REVIEWSITEID, o.ReviewSiteID);
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
        values.put(RS_Properties.COLUMN_PROVINCIALAREA, o.ProvincialArea);
        values.put(RS_Properties.COLUMN_PROVINCIALAREATYPE, o.ProvincialAreaType);
        values.put(RS_Properties.COLUMN_OPERATINGAREA, o.OperatingArea);
        values.put(RS_Properties.COLUMN_COUNTY, o.County);
        values.put(RS_Properties.COLUMN_NATURALREGION, o.NaturalRegion);
        values.put(RS_Properties.COLUMN_NATURALSUBREGION, o.NaturalSubRegion);
        values.put(RS_Properties.COLUMN_FMAHOLDER, o.FMAHolder);

        db.insert(RS_Properties.TABLE_REVIEWSITE, null, values);

        Cursor c = db.query(RS_Properties.TABLE_REVIEWSITE, allColumns,
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

    @Override
    public List<ReviewSite> getAll() {
        List<ReviewSite> reviewSites = new ArrayList<ReviewSite>();
        Cursor cursor = db.query(RS_Properties.TABLE_REVIEWSITE,
                allColumns, null, null, null, null, null);
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
        o.AFE = cursor.getString(1);
        o.ProvincialAreaName = cursor.getString(2);
        o.ProvincialAreaTypeName = cursor.getString(3);
        o.OperatingAreaName = cursor.getString(4);
        o.CountyName = cursor.getString(5);
        o.NaturalRegionName = cursor.getString(6);
        o.NaturalSubRegionName = cursor.getString(7);
        o.FMAHolderName = cursor.getString(8);
        o.SeedZone = cursor.getString(9);
        o.WellboreID = cursor.getString(10);
        o.UWI = cursor.getString(11);
        o.WellsiteName = cursor.getString(12);
        o.UTMZone = cursor.getString(13);
        o.ProvincialArea = cursor.getString(14);
        o.ProvincialAreaType = cursor.getString(15);
        o.OperatingArea = cursor.getString(16);
        o.County = cursor.getString(17);
        o.NaturalRegion = cursor.getString(18);
        o.NaturalSubRegion = cursor.getString(19);
        o.FMAHolder = cursor.getString(20);

        return o;
    }
}
