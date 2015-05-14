package com.reclamation.woodlands.woodlandsreclamation.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy on 5/8/2015.
 */
public class DR_DataSource extends AbastrctDataSource<DesktopReview>{

    private String[] allColumns = {
            DR_Properties.COLUMN_ID,
            DR_Properties.COLUMN_SITEID,
            DR_Properties.COLUMN_FACILITYTYPENAME,
            DR_Properties.COLUMN_NOTES,
            DR_Properties.COLUMN_CLIENT,
            DR_Properties.COLUMN_APPROVALSTATUS,
            DR_Properties.COLUMN_WORKPHASE,
            DR_Properties.COLUMN_OCCUPANT,
            DR_Properties.COLUMN_OCCUPANTINFO,
            DR_Properties.COLUMN_DISPOSITION,
            DR_Properties.COLUMN_SOILCLASS,
            DR_Properties.COLUMN_SOILGROUP,
            DR_Properties.COLUMN_ERCBLIC,
            DR_Properties.COLUMN_WIDTH,
            DR_Properties.COLUMN_LENGTH,
            DR_Properties.COLUMN_AREAHA,
            DR_Properties.COLUMN_AREAAC,
            DR_Properties.COLUMN_NORTHING,
            DR_Properties.COLUMN_EASTING,
            DR_Properties.COLUMN_LATITUDE,
            DR_Properties.COLUMN_LONGITUDE,
            DR_Properties.COLUMN_ELEVATION,
            DR_Properties.COLUMN_ASPECTNAME,
            DR_Properties.COLUMN_LSD,
            DR_Properties.COLUMN_SURVEYDATE,
            DR_Properties.COLUMN_CONSTRUCTIONDATE,
            DR_Properties.COLUMN_SPUDDATE,
            DR_Properties.COLUMN_ABANDONMENTDATE,
            DR_Properties.COLUMN_RECLAMATIONDATE,
            DR_Properties.COLUMN_RELEVANTCRITERIANAME,
            DR_Properties.COLUMN_LANDSCAPENAME,
            DR_Properties.COLUMN_SOILNAME,
            DR_Properties.COLUMN_VEGETATIONNAME,
            DR_Properties.COLUMN_RCADATE,
            DR_Properties.COLUMN_RCNUMBER,
            DR_Properties.COLUMN_DSACOMMENTS,
            DR_Properties.COLUMN_EXEMPTIONS,
            DR_Properties.COLUMN_AMENDDATE,
            DR_Properties.COLUMN_AMENDDETAIL,
            DR_Properties.COLUMN_REVEGDATE,
            DR_Properties.COLUMN_REVEGDETAIL,
            DR_Properties.COLUMN_FACILITYTYPE,
            DR_Properties.COLUMN_LSDQUARTER,
            DR_Properties.COLUMN_RELEVANTCRITERIA,
            DR_Properties.COLUMN_LANDSCAPE,
            DR_Properties.COLUMN_SOIL,
            DR_Properties.COLUMN_VEGETATION
    };

    public DR_DataSource(Context context){
        super(context);
    }

    @Override
    public DesktopReview create(DesktopReview desktopReview){

        // Put all values here
        ContentValues values = new ContentValues();
        values.put(DR_Properties.COLUMN_SITEID, desktopReview.SiteID);
        values.put(DR_Properties.COLUMN_FACILITYTYPENAME, desktopReview.FacilityTypeName);
        values.put(DR_Properties.COLUMN_NOTES, desktopReview.Notes);
        values.put(DR_Properties.COLUMN_CLIENT, desktopReview.Client);
        values.put(DR_Properties.COLUMN_APPROVALSTATUS, desktopReview.ApprovalStatus);
        values.put(DR_Properties.COLUMN_WORKPHASE, desktopReview.WorkPhase);
        values.put(DR_Properties.COLUMN_OCCUPANT, desktopReview.Occupant);
        values.put(DR_Properties.COLUMN_OCCUPANTINFO, desktopReview.OccupantInfo);
        values.put(DR_Properties.COLUMN_DISPOSITION, desktopReview.Disposition);
        values.put(DR_Properties.COLUMN_SOILCLASS, desktopReview.SoilClass);
        values.put(DR_Properties.COLUMN_SOILGROUP, desktopReview.SoilGroup);
        values.put(DR_Properties.COLUMN_ERCBLIC, desktopReview.ERCBLic);
        values.put(DR_Properties.COLUMN_WIDTH, desktopReview.Width);
        values.put(DR_Properties.COLUMN_LENGTH, desktopReview.Length);
        values.put(DR_Properties.COLUMN_AREAHA, desktopReview.AreaHA);
        values.put(DR_Properties.COLUMN_AREAAC, desktopReview.AreaAC);
        values.put(DR_Properties.COLUMN_NORTHING, desktopReview.Northing);
        values.put(DR_Properties.COLUMN_EASTING, desktopReview.Easting);
        values.put(DR_Properties.COLUMN_LATITUDE, desktopReview.Latitude);
        values.put(DR_Properties.COLUMN_LONGITUDE, desktopReview.Longitude);
        values.put(DR_Properties.COLUMN_ELEVATION, desktopReview.Elevation);
        values.put(DR_Properties.COLUMN_ASPECTNAME, desktopReview.AspectName);
        values.put(DR_Properties.COLUMN_LSD, desktopReview.LSD);
        values.put(DR_Properties.COLUMN_SURVEYDATE, desktopReview.SurveyDate);
        values.put(DR_Properties.COLUMN_CONSTRUCTIONDATE, desktopReview.ConstructionDate);
        values.put(DR_Properties.COLUMN_SPUDDATE, desktopReview.SpudDate);
        values.put(DR_Properties.COLUMN_ABANDONMENTDATE, desktopReview.AbandonmentDate);
        values.put(DR_Properties.COLUMN_RECLAMATIONDATE, desktopReview.ReclamationDate);
        values.put(DR_Properties.COLUMN_RELEVANTCRITERIANAME, desktopReview.RelevantCriteriaName);
        values.put(DR_Properties.COLUMN_LANDSCAPENAME, desktopReview.LandscapeName);
        values.put(DR_Properties.COLUMN_SOILNAME, desktopReview.SoilName);
        values.put(DR_Properties.COLUMN_VEGETATIONNAME, desktopReview.VegetationName);
        values.put(DR_Properties.COLUMN_RCADATE, desktopReview.RCADate);
        values.put(DR_Properties.COLUMN_RCNUMBER, desktopReview.RCNumber);
        values.put(DR_Properties.COLUMN_DSACOMMENTS, desktopReview.DSAComments);
        values.put(DR_Properties.COLUMN_EXEMPTIONS, desktopReview.Exemptions);
        values.put(DR_Properties.COLUMN_AMENDDATE, desktopReview.AmendDate);
        values.put(DR_Properties.COLUMN_AMENDDETAIL, desktopReview.AmendDetail);
        values.put(DR_Properties.COLUMN_REVEGDATE, desktopReview.RevegDate);
        values.put(DR_Properties.COLUMN_REVEGDETAIL, desktopReview.RevegDetail);
        values.put(DR_Properties.COLUMN_FACILITYTYPE, desktopReview.FacilityType);
        values.put(DR_Properties.COLUMN_LSDQUARTER, desktopReview.LSDQuarter);
        values.put(DR_Properties.COLUMN_RELEVANTCRITERIA, desktopReview.RelevantCriteria);
        values.put(DR_Properties.COLUMN_LANDSCAPE, desktopReview.Landscape);
        values.put(DR_Properties.COLUMN_SOIL, desktopReview.Soil);
        values.put(DR_Properties.COLUMN_VEGETATION, desktopReview.Vegetation);



        long insertId = db.insert(DR_Properties.TABLE_DESKTOPREVIEWS, null, values);
        Cursor cursor = db.query(DR_Properties.TABLE_DESKTOPREVIEWS,
                allColumns, DR_Properties.COLUMN_ID + " = " + insertId, null,
                null, null, null);

        cursor.moveToFirst();

        cursor.close();
        desktopReview.DesktopReviewID = insertId;
        Log.i("debug", "Created with id: " + desktopReview.DesktopReviewID + " | " +desktopReview.SiteID + " | " + desktopReview.Notes);
        return desktopReview;

    }

    @Override
    public void delete(DesktopReview dr){

        long id = dr.DesktopReviewID;
        Log.i("debug", "Deleted with id: "+id);
        db.delete(DR_Properties.TABLE_DESKTOPREVIEWS, DR_Properties.COLUMN_ID
        + " = " + id, null);

    }

    @Override
    public List<DesktopReview> getAll(){
        List<DesktopReview> desktopReviews = new ArrayList<DesktopReview>();

        Cursor cursor = db.query(DR_Properties.TABLE_DESKTOPREVIEWS,
                allColumns, null, null,null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            DesktopReview dr = cursorTo(cursor);
            desktopReviews.add(dr);
            cursor.moveToNext();
        }

        // close the cursor
        cursor.close();
        return desktopReviews;
    }

    @Override
    public DesktopReview cursorTo(Cursor cursor){
        DesktopReview dr = new DesktopReview();
        dr.DesktopReviewID = cursor.getLong(0);
        dr.SiteID = cursor.getString(1);
        dr.FacilityTypeName = cursor.getString(2);
        dr.Notes = cursor.getString(3);
        dr.Client = cursor.getString(4);
        dr.ApprovalStatus = cursor.getString(5);
        dr.WorkPhase = cursor.getString(6);
        dr.Occupant = cursor.getString(7);
        dr.OccupantInfo = cursor.getString(8);
        dr.Disposition = cursor.getString(9);
        dr.SoilClass = cursor.getString(10);
        dr.SoilGroup = cursor.getString(11);
        dr.ERCBLic = cursor.getString(12);
        dr.Width = cursor.getString(13);
        dr.Length = cursor.getString(14);
        dr.AreaHA = cursor.getString(15);
        dr.AreaAC = cursor.getString(16);
        dr.Northing = cursor.getString(17);
        dr.Easting = cursor.getString(18);
        dr.Latitude = cursor.getString(19);
        dr.Longitude = cursor.getString(20);
        dr.Elevation = cursor.getString(21);
        dr.AspectName = cursor.getString(22);
        dr.LSD = cursor.getString(23);
        dr.SurveyDate = cursor.getString(24);
        dr.ConstructionDate = cursor.getString(25);
        dr.SpudDate = cursor.getString(26);
        dr.AbandonmentDate = cursor.getString(27);
        dr.ReclamationDate = cursor.getString(28);
        dr.RelevantCriteriaName = cursor.getString(29);
        dr.LandscapeName = cursor.getString(30);
        dr.SoilName = cursor.getString(31);
        dr.VegetationName = cursor.getString(32);
        dr.RCADate = cursor.getString(33);
        dr.RCNumber = cursor.getString(34);
        dr.DSAComments = cursor.getString(35);
        dr.Exemptions = cursor.getString(36);
        dr.AmendDate = cursor.getString(37);
        dr.AmendDetail = cursor.getString(38);
        dr.RevegDate = cursor.getString(39);
        dr.RevegDetail = cursor.getString(40);

        dr.FacilityType = cursor.getString(41);
        dr.LSDQuarter = cursor.getString(42);
        dr.RelevantCriteria = cursor.getString(43);
        dr.Landscape = cursor.getString(44);
        dr.Soil = cursor.getString(45);
        dr.Vegetation = cursor.getString(46);

        return dr;
    }

    @Override
    public DesktopReview findFormById(int id) {
        return null;
    }


}
