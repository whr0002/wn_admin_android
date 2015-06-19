package com.reclamation.woodlands.admin.DB.Table_DesktopReview;

import com.reclamation.woodlands.admin.DB.Table_ReviewSite.RS_Properties;

/**
 * Created by Jimmy on 5/11/2015.
 */
public class DR_Properties {
    /*****************************************************************/
    public static final String TABLE_DESKTOPREVIEWS = "DesktopReviews";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SITEID = "SiteID";
    public static final String COLUMN_FACILITYTYPENAME = "FacilityTypeName";
    public static final String COLUMN_NOTES = "Notes";
    public static final String COLUMN_CLIENT = "Client";
    public static final String COLUMN_APPROVALSTATUS = "ApprovalStatus";
    public static final String COLUMN_WORKPHASE = "WorkPhase";
    public static final String COLUMN_OCCUPANT = "Occupant";
    public static final String COLUMN_OCCUPANTINFO = "OccupantInfo";
    public static final String COLUMN_DISPOSITION = "Disposition";
    public static final String COLUMN_SOILCLASS = "SoilClass";
    public static final String COLUMN_SOILGROUP = "SoilGroup";
    public static final String COLUMN_ERCBLIC = "ERCBLic";
    public static final String COLUMN_WIDTH = "Width";
    public static final String COLUMN_LENGTH = "Length";
    public static final String COLUMN_AREAHA = "AreaHA";
    public static final String COLUMN_AREAAC = "AreaAC";
    public static final String COLUMN_NORTHING = "Northing";
    public static final String COLUMN_EASTING = "Easting";
    public static final String COLUMN_LATITUDE = "Latitude";
    public static final String COLUMN_LONGITUDE = "Longitude";
    public static final String COLUMN_ELEVATION = "Elevation";
    public static final String COLUMN_ASPECTNAME = "AspectName";
    public static final String COLUMN_LSD = "LSD";
    public static final String COLUMN_SURVEYDATE = "SurveyDate";
    public static final String COLUMN_CONSTRUCTIONDATE = "ConstructionDate";
    public static final String COLUMN_SPUDDATE = "SpudDate";
    public static final String COLUMN_ABANDONMENTDATE = "AbandonmentDate";
    public static final String COLUMN_RECLAMATIONDATE = "ReclamationDate";
    public static final String COLUMN_RELEVANTCRITERIANAME = "RelevantCriteriaName";
    public static final String COLUMN_LANDSCAPENAME = "LandscapeName";
    public static final String COLUMN_SOILNAME = "SoilName";
    public static final String COLUMN_VEGETATIONNAME = "VegetationName";
    public static final String COLUMN_RCADATE = "RCADate";
    public static final String COLUMN_RCNUMBER = "RCNumber";
    public static final String COLUMN_DSACOMMENTS = "DSAComments";
    public static final String COLUMN_EXEMPTIONS = "Exemptions";
    public static final String COLUMN_AMENDDATE = "AmendDate";
    public static final String COLUMN_AMENDDETAIL = "AmendDetail";
    public static final String COLUMN_REVEGDATE = "RevegDate";
    public static final String COLUMN_REVEGDETAIL = "RevegDetail";

    public static final String COLUMN_FACILITYTYPE = "FacilityType";
    public static final String COLUMN_LSDQUARTER = "LSDQuarter";
    public static final String COLUMN_RELEVANTCRITERIA = "RelevantCriteria";
    public static final String COLUMN_LANDSCAPE = "Landscape";
    public static final String COLUMN_SOIL = "Soil";
    public static final String COLUMN_VEGETATION = "Vegetation";
    /*****************************************************************/

    // Table creation sql statement
    public static final String DR_CREATE =
            "create table " + TABLE_DESKTOPREVIEWS
                    + "("
                    + COLUMN_ID + " integer primary key autoincrement, "
                    + COLUMN_SITEID + " text not null, "
                    + COLUMN_FACILITYTYPENAME + " text, "
                    + COLUMN_NOTES + " text, "
                    + COLUMN_CLIENT + " text, "
                    + COLUMN_APPROVALSTATUS + " text, "
                    + COLUMN_WORKPHASE + " text, "
                    + COLUMN_OCCUPANT + " text, "
                    + COLUMN_OCCUPANTINFO + " text, "
                    + COLUMN_DISPOSITION + " text, "
                    + COLUMN_SOILCLASS + " text, "
                    + COLUMN_SOILGROUP + " text, "
                    + COLUMN_ERCBLIC + " text, "
                    + COLUMN_WIDTH + " text, "
                    + COLUMN_LENGTH + " text, "
                    + COLUMN_AREAHA + " text, "
                    + COLUMN_AREAAC + " text, "
                    + COLUMN_NORTHING + " text, "
                    + COLUMN_EASTING + " text, "
                    + COLUMN_LATITUDE + " text, "
                    + COLUMN_LONGITUDE + " text, "
                    + COLUMN_ELEVATION + " text, "
                    + COLUMN_ASPECTNAME + " text, "
                    + COLUMN_LSD + " text, "
                    + COLUMN_SURVEYDATE + " text, "
                    + COLUMN_CONSTRUCTIONDATE + " text, "
                    + COLUMN_SPUDDATE + " text, "
                    + COLUMN_ABANDONMENTDATE + " text, "
                    + COLUMN_RECLAMATIONDATE + " text, "
                    + COLUMN_RELEVANTCRITERIANAME + " text, "
                    + COLUMN_LANDSCAPENAME + " text, "
                    + COLUMN_SOILNAME + " text, "
                    + COLUMN_VEGETATIONNAME + " text, "
                    + COLUMN_RCADATE + " text, "
                    + COLUMN_RCNUMBER + " text, "
                    + COLUMN_DSACOMMENTS + " text, "
                    + COLUMN_EXEMPTIONS + " text, "
                    + COLUMN_AMENDDATE + " text, "
                    + COLUMN_AMENDDETAIL + " text, "
                    + COLUMN_REVEGDATE + " text, "
                    + COLUMN_REVEGDETAIL + " text, "

                    + COLUMN_FACILITYTYPE + " text, "
                    + COLUMN_LSDQUARTER + " text, "
                    + COLUMN_RELEVANTCRITERIA + " text, "
                    + COLUMN_LANDSCAPE + " text, "
                    + COLUMN_SOIL + " text, "
                    + COLUMN_VEGETATION + " text, "

                    + " FOREIGN KEY ("+ COLUMN_SITEID +") REFERENCES "
                    + RS_Properties.TABLE_REVIEWSITE + " ("+ RS_Properties.COLUMN_REVIEWSITEID +") "
                    + " ON DELETE CASCADE "


                    + " );";


}
