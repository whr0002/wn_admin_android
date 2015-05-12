package com.reclamation.woodlands.woodlandsreclamation.DB;

/**
 * Created by Jimmy on 5/11/2015.
 */
public class RS_Properties {
    public static final String TABLE_REVIEWSITE = "ReviewSites";
    public static final String COLUMN_REVIEWSITEID = "ReviewSiteID";
    public static final String COLUMN_DATAOWNER = "DataOwner";
    public static final String COLUMN_DISPOSITIONNUMBER = "DispositionNumber";
    public static final String COLUMN_SWPNUMBER = "SWPNumber";
    public static final String COLUMN_AFE = "AFE";
    public static final String COLUMN_PROVINCIALAREANAME = "ProvincialAreaName";
    public static final String COLUMN_PROVINCIALAREATYPENAME = "ProvincialAreaTypeName";
    public static final String COLUMN_OPERATINGAREANAME = "OperatingAreaName";
    public static final String COLUMN_COUNTYNAME = "CountyName";
    public static final String COLUMN_NATURALREGIONNAME = "NaturalRegionName";
    public static final String COLUMN_NATURALSUBREGIONNAME = "NaturalSubRegionName";
    public static final String COLUMN_FMAHOLDERNAME = "FMAHolderName";
    public static final String COLUMN_SEEDZONE = "SeedZone";
    public static final String COLUMN_WELLBOREID = "WellboreID";
    public static final String COLUMN_UWI = "UWI";
    public static final String COLUMN_WELLSITENAME = "WellsiteName";
    public static final String COLUMN_UTMZONE = "UTMZone";
    public static final String COLUMN_PROVINCIALAREA = "ProvincialArea";
    public static final String COLUMN_PROVINCIALAREATYPE = "ProvincialAreaType";
    public static final String COLUMN_OPERATINGAREA = "OperatingArea";
    public static final String COLUMN_COUNTY = "County";
    public static final String COLUMN_NATURALREGION = "NaturalRegion";
    public static final String COLUMN_NATURALSUBREGION = "NaturalSubRegion";
    public static final String COLUMN_FMAHOLDER = "FMAHolder";

    public static final String RS_CREATE =
            "create table " + TABLE_REVIEWSITE
                    + "("
                    + COLUMN_REVIEWSITEID + " text primary key, "
                    + COLUMN_DATAOWNER + " text, "
                    + COLUMN_DISPOSITIONNUMBER + " text, "
                    + COLUMN_SWPNUMBER + " text, "
                    + COLUMN_AFE + " text, "
                    + COLUMN_PROVINCIALAREANAME + " text, "
                    + COLUMN_PROVINCIALAREATYPENAME + " text, "
                    + COLUMN_OPERATINGAREANAME + " text, "
                    + COLUMN_COUNTYNAME + " text, "
                    + COLUMN_NATURALREGIONNAME + " text, "
                    + COLUMN_NATURALSUBREGIONNAME + " text, "
                    + COLUMN_FMAHOLDERNAME + " text, "
                    + COLUMN_SEEDZONE + " text, "
                    + COLUMN_WELLBOREID + " text, "
                    + COLUMN_UWI + " text, "
                    + COLUMN_WELLSITENAME + " text, "
                    + COLUMN_UTMZONE + " text, "
                    + COLUMN_PROVINCIALAREA + " text, "
                    + COLUMN_PROVINCIALAREATYPE + " text, "
                    + COLUMN_OPERATINGAREA + " text, "
                    + COLUMN_COUNTY + " text, "
                    + COLUMN_NATURALREGION + " text, "
                    + COLUMN_NATURALSUBREGION + " text, "
                    + COLUMN_FMAHOLDER + " text "
                    + " );";

}
