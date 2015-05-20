package com.reclamation.woodlands.woodlandsreclamation.DB.Table_SiteVisit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.reclamation.woodlands.woodlandsreclamation.DB.DAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy on 5/13/2015.
 */
public class SiteVisitDAO extends DAO<SiteVisitForm> {
    public SiteVisitDAO(Context context) {
        super(context);
    }

    @Override
    public SiteVisitForm create(SiteVisitForm o) {
        ContentValues cv = new ContentValues();

        cv.put(SiteVisitProperties.COLUMN_BAREGROUNDCOMMENT, o.BareGroundComment);
        cv.put(SiteVisitProperties.COLUMN_BAREGROUNDPF, o.BareGroundPF);
        cv.put(SiteVisitProperties.COLUMN_CWDCOMMENT, o.CWDComment);
        cv.put(SiteVisitProperties.COLUMN_CWDPF, o.CWDPF);
        cv.put(SiteVisitProperties.COLUMN_CONTOURSCOMMENT, o.ContoursComment);
        cv.put(SiteVisitProperties.COLUMN_CONTOURSPF, o.ContoursPF);

        cv.put(SiteVisitProperties.COLUMN_DATE, o.Date);
        cv.put(SiteVisitProperties.COLUMN_DRAINAGECOMMENT, o.DrainageComment);
        cv.put(SiteVisitProperties.COLUMN_DRAINAGEPF, o.DrainagePF);
        cv.put(SiteVisitProperties.COLUMN_EROSIONCOMMENT, o.ErosionComment);
        cv.put(SiteVisitProperties.COLUMN_EROSIONPF, o.ErosionPF);
        cv.put(SiteVisitProperties.COLUMN_FACILITYTYPE, o.FacilityType);
        cv.put(SiteVisitProperties.COLUMN_LITTERCOMMENT, o.LitterComment);
        cv.put(SiteVisitProperties.COLUMN_LITTERPF, o.LitterPF);

        cv.put(SiteVisitProperties.COLUMN_NSCCOMMENT, o.NSCComment);
        cv.put(SiteVisitProperties.COLUMN_NSCPF, o.NSCPF);
        cv.put(SiteVisitProperties.COLUMN_RECOMMENDATION, o.Recommendation);
        cv.put(SiteVisitProperties.COLUMN_REFUSECOMMENT, o.RefuseComment);
        cv.put(SiteVisitProperties.COLUMN_REFUSEPF, o.RefusePF);
        cv.put(SiteVisitProperties.COLUMN_ROCKGRAVELCOMMENT, o.RockGravelComment);
        cv.put(SiteVisitProperties.COLUMN_ROCKGRAVELPF, o.RockGravelPF);
        cv.put(SiteVisitProperties.COLUMN_ROOTINGCOMMENT, o.RootingComment);
        cv.put(SiteVisitProperties.COLUMN_ROOTINGPF, o.RootingPF);
        cv.put(SiteVisitProperties.COLUMN_SITEID, o.SiteID);
        cv.put(SiteVisitProperties.COLUMN_SOILCHARCOMMENT, o.SoilCharComment);
        cv.put(SiteVisitProperties.COLUMN_SOILCHARPF, o.SoilCharPF);
        cv.put(SiteVisitProperties.COLUMN_SOILSTABILITYCOMMENT, o.SoilStabilityComment);
        cv.put(SiteVisitProperties.COLUMN_SOILSTABILITYPF, o.SoilStabilityPF);
        cv.put(SiteVisitProperties.COLUMN_TOPSOILDEPTHCOMMENT, o.TopsoilDepthComment);
        cv.put(SiteVisitProperties.COLUMN_TOPSOILDEPTHPF, o.TopsoilDepthPF);
        cv.put(SiteVisitProperties.COLUMN_TREEHEALTHCOMMENT, o.TreeHealthComment);
        cv.put(SiteVisitProperties.COLUMN_TREEHEALTHPF, o.TreeHealthPF);
        cv.put(SiteVisitProperties.COLUMN_WSDCOMMENT, o.WSDComment);
        cv.put(SiteVisitProperties.COLUMN_WSDPF, o.WSDPF);
        cv.put(SiteVisitProperties.COLUMN_WEEDSINVASIVESCOMMENT, o.WeedsInvasivesComment);
        cv.put(SiteVisitProperties.COLUMN_WEEDSINVASIVESPF, o.WeedsInvasivesPF);

        long insertID = db.insert(SiteVisitProperties.TABLE_SITEVISIT, null, cv);

        Cursor cursor = db.query(SiteVisitProperties.TABLE_SITEVISIT,
                null,
                SiteVisitProperties.COLUMN_SITEVISITID + " = " + insertID,
                null, null, null, null);

        cursor.moveToFirst();


        if(!cursor.isAfterLast()){
            SiteVisitForm sv = cursorTo(cursor);
            cursor.close();
            return sv;
        }else{
            cursor.close();
            return null;
        }


    }

    @Override
    public void update(SiteVisitForm o) {
        ContentValues cv = new ContentValues();

        cv.put(SiteVisitProperties.COLUMN_BAREGROUNDCOMMENT, o.BareGroundComment);
        cv.put(SiteVisitProperties.COLUMN_BAREGROUNDPF, o.BareGroundPF);
        cv.put(SiteVisitProperties.COLUMN_CWDCOMMENT, o.CWDComment);
        cv.put(SiteVisitProperties.COLUMN_CWDPF, o.CWDPF);
        cv.put(SiteVisitProperties.COLUMN_CONTOURSCOMMENT, o.ContoursComment);
        cv.put(SiteVisitProperties.COLUMN_CONTOURSPF, o.ContoursPF);

        cv.put(SiteVisitProperties.COLUMN_DATE, o.Date);
        cv.put(SiteVisitProperties.COLUMN_DRAINAGECOMMENT, o.DrainageComment);
        cv.put(SiteVisitProperties.COLUMN_DRAINAGEPF, o.DrainagePF);
        cv.put(SiteVisitProperties.COLUMN_EROSIONCOMMENT, o.ErosionComment);
        cv.put(SiteVisitProperties.COLUMN_EROSIONPF, o.ErosionPF);
        cv.put(SiteVisitProperties.COLUMN_FACILITYTYPE, o.FacilityType);
        cv.put(SiteVisitProperties.COLUMN_LITTERCOMMENT, o.LitterComment);
        cv.put(SiteVisitProperties.COLUMN_LITTERPF, o.LitterPF);

        cv.put(SiteVisitProperties.COLUMN_NSCCOMMENT, o.NSCComment);
        cv.put(SiteVisitProperties.COLUMN_NSCPF, o.NSCPF);
        cv.put(SiteVisitProperties.COLUMN_RECOMMENDATION, o.Recommendation);
        cv.put(SiteVisitProperties.COLUMN_REFUSECOMMENT, o.RefuseComment);
        cv.put(SiteVisitProperties.COLUMN_REFUSEPF, o.RefusePF);
        cv.put(SiteVisitProperties.COLUMN_ROCKGRAVELCOMMENT, o.RockGravelComment);
        cv.put(SiteVisitProperties.COLUMN_ROCKGRAVELPF, o.RockGravelPF);
        cv.put(SiteVisitProperties.COLUMN_ROOTINGCOMMENT, o.RootingComment);
        cv.put(SiteVisitProperties.COLUMN_ROOTINGPF, o.RootingPF);
        cv.put(SiteVisitProperties.COLUMN_SITEID, o.SiteID);
        cv.put(SiteVisitProperties.COLUMN_SOILCHARCOMMENT, o.SoilCharComment);
        cv.put(SiteVisitProperties.COLUMN_SOILCHARPF, o.SoilCharPF);
        cv.put(SiteVisitProperties.COLUMN_SOILSTABILITYCOMMENT, o.SoilStabilityComment);
        cv.put(SiteVisitProperties.COLUMN_SOILSTABILITYPF, o.SoilStabilityPF);
        cv.put(SiteVisitProperties.COLUMN_TOPSOILDEPTHCOMMENT, o.TopsoilDepthComment);
        cv.put(SiteVisitProperties.COLUMN_TOPSOILDEPTHPF, o.TopsoilDepthPF);
        cv.put(SiteVisitProperties.COLUMN_TREEHEALTHCOMMENT, o.TreeHealthComment);
        cv.put(SiteVisitProperties.COLUMN_TREEHEALTHPF, o.TreeHealthPF);
        cv.put(SiteVisitProperties.COLUMN_WSDCOMMENT, o.WSDComment);
        cv.put(SiteVisitProperties.COLUMN_WSDPF, o.WSDPF);
        cv.put(SiteVisitProperties.COLUMN_WEEDSINVASIVESCOMMENT, o.WeedsInvasivesComment);
        cv.put(SiteVisitProperties.COLUMN_WEEDSINVASIVESPF, o.WeedsInvasivesPF);

        db.update(SiteVisitProperties.TABLE_SITEVISIT, cv,
                SiteVisitProperties.COLUMN_SITEVISITID + " = " + o.ID, null);

    }

    @Override
    public void delete(SiteVisitForm o) {
        db.delete(SiteVisitProperties.TABLE_SITEVISIT,
                SiteVisitProperties.COLUMN_SITEVISITID + " = " + o.ID, null);
    }

    @Override
    public List<SiteVisitForm> getAll() {
        Cursor cursor = db.query(SiteVisitProperties.TABLE_SITEVISIT, null, null, null, null, null, null);
        cursor.moveToFirst();
        List<SiteVisitForm> siteVisits = new ArrayList<SiteVisitForm>();

        while(!cursor.isAfterLast()){
            SiteVisitForm sv = cursorTo(cursor);
            siteVisits.add(sv);

            cursor.moveToNext();
        }

        return siteVisits;
    }

    @Override
    public SiteVisitForm cursorTo(Cursor cursor) {
        SiteVisitForm sv = new SiteVisitForm();

        sv.BareGroundComment = cursor.getString(cursor.getColumnIndex(SiteVisitProperties.COLUMN_BAREGROUNDCOMMENT));
        sv.BareGroundPF = cursor.getInt(cursor.getColumnIndex(SiteVisitProperties.COLUMN_BAREGROUNDPF));
        sv.CWDComment = cursor.getString(cursor.getColumnIndex(SiteVisitProperties.COLUMN_CWDCOMMENT));
        sv.CWDPF = cursor.getInt(cursor.getColumnIndex(SiteVisitProperties.COLUMN_CWDPF));
        sv.ContoursComment = cursor.getString(cursor.getColumnIndex(SiteVisitProperties.COLUMN_CONTOURSCOMMENT));
        sv.ContoursPF = cursor.getInt(cursor.getColumnIndex(SiteVisitProperties.COLUMN_CONTOURSPF));

        sv.Date = cursor.getString(cursor.getColumnIndex(SiteVisitProperties.COLUMN_DATE));
        sv.DrainageComment = cursor.getString(cursor.getColumnIndex(SiteVisitProperties.COLUMN_DRAINAGECOMMENT));
        sv.DrainagePF = cursor.getInt(cursor.getColumnIndex(SiteVisitProperties.COLUMN_DRAINAGEPF));
        sv.ErosionComment = cursor.getString(cursor.getColumnIndex(SiteVisitProperties.COLUMN_EROSIONCOMMENT));
        sv.ErosionPF = cursor.getInt(cursor.getColumnIndex(SiteVisitProperties.COLUMN_EROSIONPF));
        sv.FacilityType = cursor.getString(cursor.getColumnIndex(SiteVisitProperties.COLUMN_FACILITYTYPE));
        sv.LitterComment = cursor.getString(cursor.getColumnIndex(SiteVisitProperties.COLUMN_LITTERCOMMENT));
        sv.LitterPF = cursor.getInt(cursor.getColumnIndex(SiteVisitProperties.COLUMN_LITTERPF));


        sv.NSCComment = cursor.getString(cursor.getColumnIndex(SiteVisitProperties.COLUMN_NSCCOMMENT));
        sv.NSCPF = cursor.getInt(cursor.getColumnIndex(SiteVisitProperties.COLUMN_NSCPF));
        sv.Recommendation = cursor.getString(cursor.getColumnIndex(SiteVisitProperties.COLUMN_RECOMMENDATION));
        sv.RefuseComment = cursor.getString(cursor.getColumnIndex(SiteVisitProperties.COLUMN_REFUSECOMMENT));
        sv.RefusePF = cursor.getInt(cursor.getColumnIndex(SiteVisitProperties.COLUMN_REFUSEPF));
        sv.RockGravelComment = cursor.getString(cursor.getColumnIndex(SiteVisitProperties.COLUMN_ROCKGRAVELCOMMENT));
        sv.RockGravelPF = cursor.getInt(cursor.getColumnIndex(SiteVisitProperties.COLUMN_ROCKGRAVELPF));
        sv.RootingComment = cursor.getString(cursor.getColumnIndex(SiteVisitProperties.COLUMN_ROOTINGCOMMENT));
        sv.RootingPF = cursor.getInt(cursor.getColumnIndex(SiteVisitProperties.COLUMN_ROOTINGPF));
        sv.SiteID = cursor.getString(cursor.getColumnIndex(SiteVisitProperties.COLUMN_SITEID));
        sv.ID = cursor.getInt(cursor.getColumnIndex(SiteVisitProperties.COLUMN_SITEVISITID));
        sv.SoilCharComment = cursor.getString(cursor.getColumnIndex(SiteVisitProperties.COLUMN_SOILCHARCOMMENT));
        sv.SoilCharPF = cursor.getInt(cursor.getColumnIndex(SiteVisitProperties.COLUMN_SOILCHARPF));
        sv.SoilStabilityComment = cursor.getString(cursor.getColumnIndex(SiteVisitProperties.COLUMN_SOILSTABILITYCOMMENT));
        sv.SoilStabilityPF = cursor.getInt(cursor.getColumnIndex(SiteVisitProperties.COLUMN_SOILSTABILITYPF));
        sv.TopsoilDepthComment = cursor.getString(cursor.getColumnIndex(SiteVisitProperties.COLUMN_TOPSOILDEPTHCOMMENT));
        sv.TopsoilDepthPF = cursor.getInt(cursor.getColumnIndex(SiteVisitProperties.COLUMN_TOPSOILDEPTHPF));
        sv.TreeHealthComment = cursor.getString(cursor.getColumnIndex(SiteVisitProperties.COLUMN_TREEHEALTHCOMMENT));
        sv.TreeHealthPF = cursor.getInt(cursor.getColumnIndex(SiteVisitProperties.COLUMN_TREEHEALTHPF));
        sv.WSDComment = cursor.getString(cursor.getColumnIndex(SiteVisitProperties.COLUMN_WSDCOMMENT));
        sv.WSDPF = cursor.getInt(cursor.getColumnIndex(SiteVisitProperties.COLUMN_WSDPF));
        sv.WeedsInvasivesComment = cursor.getString(cursor.getColumnIndex(SiteVisitProperties.COLUMN_WEEDSINVASIVESCOMMENT));
        sv.WeedsInvasivesPF = cursor.getInt(cursor.getColumnIndex(SiteVisitProperties.COLUMN_WEEDSINVASIVESPF));


        return sv;
    }

    @Override
    public SiteVisitForm findFormById(int id) {
        Cursor cursor = db.query(SiteVisitProperties.TABLE_SITEVISIT,
                null, SiteVisitProperties.COLUMN_SITEVISITID + " = " + id
                , null, null, null, null);

        cursor.moveToFirst();

        if(!cursor.isAfterLast()) {

            SiteVisitForm svf = cursorTo(cursor);
            cursor.close();
            return svf;

        }else{

            cursor.close();
            return null;

        }





    }
}
