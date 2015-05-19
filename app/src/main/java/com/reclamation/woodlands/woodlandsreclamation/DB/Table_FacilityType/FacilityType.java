package com.reclamation.woodlands.woodlandsreclamation.DB.Table_FacilityType;

/**
 * Created by Jimmy on 5/11/2015.
 */
public class FacilityType {
    public String FacilityTypeName;

    public static final String COLUMN_FTN = "FacilityTypeName";
    public static final String TABLE_FT = "FacilityType";
    public static final String FT_CREATE = "CREATE TABLE " + TABLE_FT
            + " ( "
            + COLUMN_FTN + " text primary key "
            + " ); ";
}
