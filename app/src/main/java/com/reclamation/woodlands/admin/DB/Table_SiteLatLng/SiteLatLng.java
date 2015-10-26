package com.reclamation.woodlands.admin.DB.Table_SiteLatLng;

/**
 * Created by Jimmy on 10/22/2015.
 */
public class SiteLatLng {
    public String SiteID;
    public String Lat;
    public String Lng;

    public static final String COLUMN_SITEID = "SiteID";
    public static final String COLUMN_LAT = "Lat";
    public static final String COLUMN_LNG = "Lng";
    public static final String TABLE_NAME = "SiteLatLng";
    public static final String SLL_CREATE = "CREATE TABLE " + TABLE_NAME
            + " ( "
            + COLUMN_SITEID + " text primary key, "
            + COLUMN_LAT + " text, "
            + COLUMN_LNG + " text"
            + " ); ";
}
