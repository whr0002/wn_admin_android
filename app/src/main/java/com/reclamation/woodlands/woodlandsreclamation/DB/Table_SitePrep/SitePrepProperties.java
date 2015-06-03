package com.reclamation.woodlands.woodlandsreclamation.DB.Table_SitePrep;

/**
 * Created by Jimmy on 6/2/2015.
 */
public class SitePrepProperties {

    public static final String COLUMN_SitePrepID = "SitePrepID";
    public static final String COLUMN_Date = "Date";
    public static final String FORM_TYPE = "SitePrep";
    public static final String COLUMN_FACILITYTYPE = "FacilityType";
    public static final String COLUMN_SITEID = "SiteID";

    public static final String COLUMN_Contractor = "Contractor";
    public static final String COLUMN_TreatedArea = "TreatedArea";


    public static final String COLUMN_AgrRoadUse = "AgrRoadUse";
    public static final String COLUMN_AgrPipelineCrossing = "AgrPipeLineCrossing";
    public static final String COLUMN_AgrOther = "AgrOther";

    public static final String COLUMN_NotiLO = "NotiLO";
    public static final String COLUMN_NotiClient = "NotiClient";
    public static final String COLUMN_NotiThirdParty = "NotiThirdParty";

    public static final String COLUMN_OneCallNo = "OneCallNo";
    public static final String COLUMN_OneCallDate = "OneCallDate";
    public static final String COLUMN_OneCallDisp = "OneCallDisp";

    public static final String COLUMN_LineCompany = "LineCompany";
    public static final String COLUMN_LineDate = "LineDate";
    public static final String COLUMN_LineMethod = "LineMethod";

    public static final String COLUMN_HandCompany = "HandCompany";
    public static final String COLUMN_HandDate = "HandDate";
    public static final String COLUMN_HandDamage  = "HandDamage";
    public static final String COLUMN_HandMethod = "HandMethod";
    public static final String COLUMN_HandEquipment = "HandEquipment";

    public static final String COLUMN_BackfillInspector = "BackfillInspector";
    public static final String COLUMN_BackfillDate = "BackfillDate";
    public static final String COLUMN_BackfillIssue = "BackfillIssue";

    public static final String COLUMN_Comment = "Comment";
    public static final String COLUMN_Recommendation = "Recommendation";
    public static final String COLUMN_MESSAGE = "Message";


    public static final String PHOTO_AD = "Additional";
    public static final String TABLE_SITEPREP = "SitePrep";
    public static final String SITEPREP_CREATE =
            "create table " + TABLE_SITEPREP
                    + " ( "
                    + COLUMN_SitePrepID + " integer primary key autoincrement, "
                    + COLUMN_Date + " text, "
                    + COLUMN_FACILITYTYPE + " text, "
                    + COLUMN_SITEID + " text, "
                    + COLUMN_Contractor + " text, "
                    + COLUMN_TreatedArea + " text, "
                    + COLUMN_AgrRoadUse + " text, "
                    + COLUMN_AgrPipelineCrossing + " text, "
                    + COLUMN_AgrOther + " text, "
                    + COLUMN_NotiLO + " text, "
                    + COLUMN_NotiClient + " text, "
                    + COLUMN_NotiThirdParty + " text, "
                    + COLUMN_OneCallNo + " text, "
                    + COLUMN_OneCallDate + " text, "
                    + COLUMN_OneCallDisp + " text, "
                    + COLUMN_LineCompany + " text, "
                    + COLUMN_LineDate + " text, "
                    + COLUMN_LineMethod + " text, "
                    + COLUMN_HandCompany + " text, "
                    + COLUMN_HandDate + " text, "
                    + COLUMN_HandDamage + " integer, "
                    + COLUMN_HandMethod + " text, "
                    + COLUMN_HandEquipment + " text, "
                    + COLUMN_BackfillInspector + " text, "
                    + COLUMN_BackfillDate + " text, "
                    + COLUMN_BackfillIssue + " integer, "
                    + COLUMN_Comment + " text, "
                    + COLUMN_Recommendation + " text, "
                    + COLUMN_MESSAGE + " text "
                    + " );";
}
