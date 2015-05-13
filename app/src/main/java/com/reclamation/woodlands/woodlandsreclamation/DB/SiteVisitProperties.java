package com.reclamation.woodlands.woodlandsreclamation.DB;

/**
 * Created by Jimmy on 5/13/2015.
 */
public class SiteVisitProperties {
    public static final String COLUMN_DRAWING = "Drawing";
    public static final String COLUMN_AD_PHOTO1 = "AD_Photo1";
    public static final String COLUMN_AD_PHOTO10 = "AD_Photo10";
    public static final String COLUMN_AD_PHOTO2 = "AD_Photo2";
    public static final String COLUMN_AD_PHOTO3 = "AD_Photo3";
    public static final String COLUMN_AD_PHOTO4 = "AD_Photo4";
    public static final String COLUMN_AD_PHOTO5 = "AD_Photo5";
    public static final String COLUMN_AD_PHOTO6 = "AD_Photo6";
    public static final String COLUMN_AD_PHOTO7 = "AD_Photo7";
    public static final String COLUMN_AD_PHOTO8 = "AD_Photo8";
    public static final String COLUMN_AD_PHOTO9 = "AD_Photo9";
    public static final String COLUMN_AP_PHOTO1 = "AP_Photo1";
    public static final String COLUMN_AP_PHOTO10 = "AP_Photo10";
    public static final String COLUMN_AP_PHOTO11 = "AP_Photo11";
    public static final String COLUMN_AP_PHOTO12 = "AP_Photo12";
    public static final String COLUMN_AP_PHOTO13 = "AP_Photo13";
    public static final String COLUMN_AP_PHOTO14 = "AP_Photo14";
    public static final String COLUMN_AP_PHOTO15 = "AP_Photo15";
    public static final String COLUMN_AP_PHOTO2 = "AP_Photo2";
    public static final String COLUMN_AP_PHOTO3 = "AP_Photo3";
    public static final String COLUMN_AP_PHOTO4 = "AP_Photo4";
    public static final String COLUMN_AP_PHOTO5 = "AP_Photo5";
    public static final String COLUMN_AP_PHOTO6 = "AP_Photo6";
    public static final String COLUMN_AP_PHOTO7 = "AP_Photo7";
    public static final String COLUMN_AP_PHOTO8 = "AP_Photo8";
    public static final String COLUMN_AP_PHOTO9 = "AP_Photo9";
    public static final String COLUMN_BAREGROUNDCOMMENT = "BareGroundComment";
    public static final String COLUMN_BAREGROUNDPF = "BareGroundPF";
    public static final String COLUMN_CWDCOMMENT = "CWDComment";
    public static final String COLUMN_CWDPF = "CWDPF";
    public static final String COLUMN_CONTOURSCOMMENT = "ContoursComment";
    public static final String COLUMN_CONTOURSPF = "ContoursPF";
    public static final String COLUMN_DATE = "Date";
    public static final String COLUMN_DRAINAGECOMMENT = "DrainageComment";
    public static final String COLUMN_DRAINAGEPF = "DrainagePF";
    public static final String COLUMN_EROSIONCOMMENT = "ErosionComment";
    public static final String COLUMN_EROSIONPF = "ErosionPF";
    public static final String COLUMN_FACILITYTYPE = "FacilityType";
    public static final String COLUMN_SITEVISITID = "SiteVisitID";
    public static final String COLUMN_LITTERCOMMENT = "LitterComment";
    public static final String COLUMN_LITTERPF = "LitterPF";
    public static final String COLUMN_NLF_PHOTO1 = "NLF_Photo1";
    public static final String COLUMN_NLF_PHOTO2 = "NLF_Photo2";
    public static final String COLUMN_NLF_PHOTO3 = "NLF_Photo3";
    public static final String COLUMN_NLF_PHOTO4 = "NLF_Photo4";
    public static final String COLUMN_NLF_PHOTO5 = "NLF_Photo5";
    public static final String COLUMN_NSCCOMMENT = "NSCComment";
    public static final String COLUMN_NSCPF = "NSCPF";
    public static final String COLUMN_RECOMMENDATION = "Recommendation";
    public static final String COLUMN_REFUSECOMMENT = "RefuseComment";
    public static final String COLUMN_REFUSEPF = "RefusePF";
    public static final String COLUMN_ROCKGRAVELCOMMENT = "RockGravelComment";
    public static final String COLUMN_ROCKGRAVELPF = "RockGravelPF";
    public static final String COLUMN_ROOTINGCOMMENT = "RootingComment";
    public static final String COLUMN_ROOTINGPF = "RootingPF";
    public static final String COLUMN_SITEID = "SiteID";
    public static final String COLUMN_SOILCHARCOMMENT = "SoilCharComment";
    public static final String COLUMN_SOILCHARPF = "SoilCharPF";
    public static final String COLUMN_SOILSTABILITYCOMMENT = "SoilStabilityComment";
    public static final String COLUMN_SOILSTABILITYPF = "SoilStabilityPF";
    public static final String COLUMN_TOPSOILDEPTHCOMMENT = "TopsoilDepthComment";
    public static final String COLUMN_TOPSOILDEPTHPF = "TopsoilDepthPF";
    public static final String COLUMN_TREEHEALTHCOMMENT = "TreeHealthComment";
    public static final String COLUMN_TREEHEALTHPF = "TreeHealthPF";
    public static final String COLUMN_WSDCOMMENT = "WSDComment";
    public static final String COLUMN_WSDPF = "WSDPF";
    public static final String COLUMN_WEEDSINVASIVESCOMMENT = "WeedsInvasivesComment";
    public static final String COLUMN_WEEDSINVASIVESPF = "WeedsInvasivesPF";


    public static final String TABLE_SITEVISIT = "SiteVisit";

    public static final String SITEVISIT_CREATE =
            "create table " + TABLE_SITEVISIT
                    + " ( "
                    + COLUMN_SITEVISITID + " integer primary key autoincrement, "
                    + COLUMN_AD_PHOTO1 + " BLOB, "
                    + COLUMN_AD_PHOTO10 + " BLOB, "
                    + COLUMN_AD_PHOTO2 + " BLOB, "
                    + COLUMN_AD_PHOTO3 + " BLOB, "
                    + COLUMN_AD_PHOTO4 + " BLOB, "
                    + COLUMN_AD_PHOTO5 + " BLOB, "
                    + COLUMN_AD_PHOTO6 + " BLOB, "
                    + COLUMN_AD_PHOTO7 + " BLOB, "
                    + COLUMN_AD_PHOTO8 + " BLOB, "
                    + COLUMN_AD_PHOTO9 + " BLOB, "
                    + COLUMN_AP_PHOTO1 + " BLOB, "
                    + COLUMN_AP_PHOTO10 + " BLOB, "
                    + COLUMN_AP_PHOTO11 + " BLOB, "
                    + COLUMN_AP_PHOTO12 + " BLOB, "
                    + COLUMN_AP_PHOTO13 + " BLOB, "
                    + COLUMN_AP_PHOTO14 + " BLOB, "
                    + COLUMN_AP_PHOTO15 + " BLOB, "
                    + COLUMN_AP_PHOTO2 + " BLOB, "
                    + COLUMN_AP_PHOTO3 + " BLOB, "
                    + COLUMN_AP_PHOTO4 + " BLOB, "
                    + COLUMN_AP_PHOTO5 + " BLOB, "
                    + COLUMN_AP_PHOTO6 + " BLOB, "
                    + COLUMN_AP_PHOTO7 + " BLOB, "
                    + COLUMN_AP_PHOTO8 + " BLOB, "
                    + COLUMN_AP_PHOTO9 + " BLOB, "
                    + COLUMN_BAREGROUNDCOMMENT + " text, "
                    + COLUMN_BAREGROUNDPF + " integer, "
                    + COLUMN_CWDCOMMENT + " text, "
                    + COLUMN_CWDPF + " integer, "
                    + COLUMN_CONTOURSCOMMENT + " text, "
                    + COLUMN_CONTOURSPF + " integer, "
                    + COLUMN_DRAWING + " BLOB, "
                    + COLUMN_DATE + " text, "
                    + COLUMN_DRAINAGECOMMENT + " text, "
                    + COLUMN_DRAINAGEPF + " integer, "
                    + COLUMN_EROSIONCOMMENT + " text, "
                    + COLUMN_EROSIONPF + " integer, "
                    + COLUMN_FACILITYTYPE + " text, "
                    + COLUMN_LITTERCOMMENT + " text, "
                    + COLUMN_LITTERPF + " integer, "
                    + COLUMN_NLF_PHOTO1 + " BLOB, "
                    + COLUMN_NLF_PHOTO2 + " BLOB, "
                    + COLUMN_NLF_PHOTO3 + " BLOB, "
                    + COLUMN_NLF_PHOTO4 + " BLOB, "
                    + COLUMN_NLF_PHOTO5 + " BLOB, "
                    + COLUMN_NSCCOMMENT + " text, "
                    + COLUMN_NSCPF + " integer, "
                    + COLUMN_RECOMMENDATION + " text, "
                    + COLUMN_REFUSECOMMENT + " text, "
                    + COLUMN_REFUSEPF + " integer, "
                    + COLUMN_ROCKGRAVELCOMMENT + " text, "
                    + COLUMN_ROCKGRAVELPF + " integer, "
                    + COLUMN_ROOTINGCOMMENT + " text, "
                    + COLUMN_ROOTINGPF + " integer, "
                    + COLUMN_SITEID + " text, "
                    + COLUMN_SOILCHARCOMMENT + " text, "
                    + COLUMN_SOILCHARPF + " integer, "
                    + COLUMN_SOILSTABILITYCOMMENT + " text, "
                    + COLUMN_SOILSTABILITYPF + " integer, "
                    + COLUMN_TOPSOILDEPTHCOMMENT + " text, "
                    + COLUMN_TOPSOILDEPTHPF + " integer, "
                    + COLUMN_TREEHEALTHCOMMENT + " text, "
                    + COLUMN_TREEHEALTHPF + " integer, "
                    + COLUMN_WSDCOMMENT + " text, "
                    + COLUMN_WSDPF + " integer, "
                    + COLUMN_WEEDSINVASIVESCOMMENT + " text, "
                    + COLUMN_WEEDSINVASIVESPF + " integer "
                    + " );";


}
