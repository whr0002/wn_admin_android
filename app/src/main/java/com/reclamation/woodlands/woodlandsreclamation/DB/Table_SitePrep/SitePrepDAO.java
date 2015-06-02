package com.reclamation.woodlands.woodlandsreclamation.DB.Table_SitePrep;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.reclamation.woodlands.woodlandsreclamation.DB.DAO;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_Photo.Photo;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_Photo.PhotoDAO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy on 6/2/2015.
 */
public class SitePrepDAO extends DAO<SitePrepForm>{
    public SitePrepDAO(Context context) {
        super(context);
    }

    @Override
    public SitePrepForm create(SitePrepForm o) {
        ContentValues cv = new ContentValues();


        cv.put(SitePrepProperties.COLUMN_Date, o.Date);
        cv.put(SitePrepProperties.COLUMN_FACILITYTYPE, o.FacilityType);
        cv.put(SitePrepProperties.COLUMN_SITEID, o.SiteID);
        cv.put(SitePrepProperties.COLUMN_MESSAGE, o.Message);

        cv.put(SitePrepProperties.COLUMN_AgrOther, o.AgrOther);
        cv.put(SitePrepProperties.COLUMN_AgrPipelineCrossing, o.AgrPipelineCrossing);
        cv.put(SitePrepProperties.COLUMN_AgrRoadUse, o.AgrRoadUse);
        cv.put(SitePrepProperties.COLUMN_BackfillDate, o.BackfillDate);
        cv.put(SitePrepProperties.COLUMN_BackfillInspector, o.BackfillInspector);
        cv.put(SitePrepProperties.COLUMN_BackfillIssue, o.BackfillIssue);
        cv.put(SitePrepProperties.COLUMN_Comment, o.Comment);
        cv.put(SitePrepProperties.COLUMN_Contractor, o.Contractor);
        cv.put(SitePrepProperties.COLUMN_HandCompany, o.HandCompany);
        cv.put(SitePrepProperties.COLUMN_HandDamage, o.HandDamage);
        cv.put(SitePrepProperties.COLUMN_HandDate, o.HandDate);
        cv.put(SitePrepProperties.COLUMN_HandEquipment, o.HandEquipment);
        cv.put(SitePrepProperties.COLUMN_HandMethod, o.HandMethod);
        cv.put(SitePrepProperties.COLUMN_LineCompany, o.LineCompany);
        cv.put(SitePrepProperties.COLUMN_LineDate, o.LineDate);
        cv.put(SitePrepProperties.COLUMN_LineMethod, o.LineMethod);

        cv.put(SitePrepProperties.COLUMN_NotiClient, o.NotiClient);
        cv.put(SitePrepProperties.COLUMN_NotiLO, o.NotiLO);
        cv.put(SitePrepProperties.COLUMN_NotiThirdParty, o.NotiThirdParty);
        cv.put(SitePrepProperties.COLUMN_OneCallDate, o.OneCallDate);
        cv.put(SitePrepProperties.COLUMN_OneCallDisp, o.OneCallDisp);
        cv.put(SitePrepProperties.COLUMN_OneCallNo, o.OneCallNo);
        cv.put(SitePrepProperties.COLUMN_Recommendation, o.Recommendation);
        cv.put(SitePrepProperties.COLUMN_TreatedArea, o.TreatedArea);


        long id = db.insert(SitePrepProperties.TABLE_SITEPREP, null, cv);

        Cursor cursor = db.query(SitePrepProperties.TABLE_SITEPREP, null,
                SitePrepProperties.COLUMN_SitePrepID + " = " + id, null, null, null, null);

        cursor.moveToFirst();

        if(!cursor.isAfterLast()){
            SitePrepForm spf = cursorTo(cursor);
            cursor.close();
            return spf;
        }

        cursor.close();
        return null;
    }

    @Override
    public void update(SitePrepForm o) {
        ContentValues cv = new ContentValues();

        cv.put(SitePrepProperties.COLUMN_Date, o.Date);
        cv.put(SitePrepProperties.COLUMN_FACILITYTYPE, o.FacilityType);
        cv.put(SitePrepProperties.COLUMN_SITEID, o.SiteID);
        cv.put(SitePrepProperties.COLUMN_MESSAGE, o.Message);

        cv.put(SitePrepProperties.COLUMN_AgrOther, o.AgrOther);
        cv.put(SitePrepProperties.COLUMN_AgrPipelineCrossing, o.AgrPipelineCrossing);
        cv.put(SitePrepProperties.COLUMN_AgrRoadUse, o.AgrRoadUse);
        cv.put(SitePrepProperties.COLUMN_BackfillDate, o.BackfillDate);
        cv.put(SitePrepProperties.COLUMN_BackfillInspector, o.BackfillInspector);
        cv.put(SitePrepProperties.COLUMN_BackfillIssue, o.BackfillIssue);
        cv.put(SitePrepProperties.COLUMN_Comment, o.Comment);
        cv.put(SitePrepProperties.COLUMN_Contractor, o.Contractor);
        cv.put(SitePrepProperties.COLUMN_HandCompany, o.HandCompany);
        cv.put(SitePrepProperties.COLUMN_HandDamage, o.HandDamage);
        cv.put(SitePrepProperties.COLUMN_HandDate, o.HandDate);
        cv.put(SitePrepProperties.COLUMN_HandEquipment, o.HandEquipment);
        cv.put(SitePrepProperties.COLUMN_HandMethod, o.HandMethod);
        cv.put(SitePrepProperties.COLUMN_LineCompany, o.LineCompany);
        cv.put(SitePrepProperties.COLUMN_LineDate, o.LineDate);
        cv.put(SitePrepProperties.COLUMN_LineMethod, o.LineMethod);
        cv.put(SitePrepProperties.COLUMN_NotiClient, o.NotiClient);
        cv.put(SitePrepProperties.COLUMN_NotiLO, o.NotiLO);
        cv.put(SitePrepProperties.COLUMN_NotiThirdParty, o.NotiThirdParty);
        cv.put(SitePrepProperties.COLUMN_OneCallDate, o.OneCallDate);
        cv.put(SitePrepProperties.COLUMN_OneCallDisp, o.OneCallDisp);
        cv.put(SitePrepProperties.COLUMN_OneCallNo, o.OneCallNo);
        cv.put(SitePrepProperties.COLUMN_Recommendation, o.Recommendation);
        cv.put(SitePrepProperties.COLUMN_TreatedArea, o.TreatedArea);

        db.update(SitePrepProperties.TABLE_SITEPREP, cv,
                SitePrepProperties.COLUMN_SitePrepID + " = " + o.ID, null);
    }

    @Override
    public void delete(SitePrepForm o) {
        db.delete(SitePrepProperties.TABLE_SITEPREP,
                SitePrepProperties.COLUMN_SitePrepID + " = " + o.ID, null);

        PhotoDAO photoDAO = new PhotoDAO(mContext);
        photoDAO.open();
        List<Photo> photos = photoDAO.findPhotos(SitePrepProperties.FORM_TYPE, o.ID, null);

        if(photos != null && photos.size() > 0){

            for(Photo p : photos){
                photoDAO.delete(p);

                File f = new File(p.path);
                if(f != null && f.exists()){
                    f.delete();
                }
            }
        }

        photoDAO.close();
    }

    @Override
    public List<SitePrepForm> getAll() {
        Cursor cursor = db.query(SitePrepProperties.TABLE_SITEPREP, null, null, null, null, null, null);
        cursor.moveToFirst();
        List<SitePrepForm> sps = new ArrayList<SitePrepForm>();

        while(!cursor.isAfterLast()){
            SitePrepForm sv = cursorTo(cursor);
            sps.add(sv);

            cursor.moveToNext();
        }

        return sps;
    }

    @Override
    public SitePrepForm cursorTo(Cursor cursor) {
        SitePrepForm f = new SitePrepForm();

        f.ID = cursor.getInt(cursor.getColumnIndex(SitePrepProperties.COLUMN_SitePrepID));
        f.Date = cursor.getString(cursor.getColumnIndex(SitePrepProperties.COLUMN_Date));
        f.FacilityType = cursor.getString(cursor.getColumnIndex(SitePrepProperties.COLUMN_FACILITYTYPE));
        f.Message = cursor.getString(cursor.getColumnIndex(SitePrepProperties.COLUMN_MESSAGE));
        f.SiteID = cursor.getString(cursor.getColumnIndex(SitePrepProperties.COLUMN_SITEID));

        f.AgrOther = cursor.getString(cursor.getColumnIndex(SitePrepProperties.COLUMN_AgrOther));
        f.AgrPipelineCrossing = cursor.getString(cursor.getColumnIndex(SitePrepProperties.COLUMN_AgrPipelineCrossing));
        f.AgrRoadUse = cursor.getString(cursor.getColumnIndex(SitePrepProperties.COLUMN_AgrRoadUse));
        f.BackfillDate = cursor.getString(cursor.getColumnIndex(SitePrepProperties.COLUMN_BackfillDate));
        f.BackfillInspector = cursor.getString(cursor.getColumnIndex(SitePrepProperties.COLUMN_BackfillInspector));
        f.BackfillIssue = cursor.getInt(cursor.getColumnIndex(SitePrepProperties.COLUMN_BackfillIssue));
        f.Comment = cursor.getString(cursor.getColumnIndex(SitePrepProperties.COLUMN_Comment));
        f.Contractor = cursor.getString(cursor.getColumnIndex(SitePrepProperties.COLUMN_Contractor));
        f.HandCompany = cursor.getString(cursor.getColumnIndex(SitePrepProperties.COLUMN_HandCompany));
        f.HandDamage = cursor.getInt(cursor.getColumnIndex(SitePrepProperties.COLUMN_HandDamage));
        f.HandDate = cursor.getString(cursor.getColumnIndex(SitePrepProperties.COLUMN_HandDate));
        f.HandEquipment = cursor.getString(cursor.getColumnIndex(SitePrepProperties.COLUMN_HandEquipment));
        f.HandMethod = cursor.getString(cursor.getColumnIndex(SitePrepProperties.COLUMN_HandMethod));
        f.LineCompany = cursor.getString(cursor.getColumnIndex(SitePrepProperties.COLUMN_LineCompany));
        f.LineDate = cursor.getString(cursor.getColumnIndex(SitePrepProperties.COLUMN_LineDate));
        f.LineMethod = cursor.getString(cursor.getColumnIndex(SitePrepProperties.COLUMN_LineMethod));
        f.NotiClient = cursor.getString(cursor.getColumnIndex(SitePrepProperties.COLUMN_NotiClient));
        f.NotiLO = cursor.getString(cursor.getColumnIndex(SitePrepProperties.COLUMN_NotiLO));
        f.NotiThirdParty = cursor.getString(cursor.getColumnIndex(SitePrepProperties.COLUMN_NotiThirdParty));
        f.OneCallDate = cursor.getString(cursor.getColumnIndex(SitePrepProperties.COLUMN_OneCallDate));
        f.OneCallDisp = cursor.getString(cursor.getColumnIndex(SitePrepProperties.COLUMN_OneCallDisp));
        f.OneCallNo = cursor.getString(cursor.getColumnIndex(SitePrepProperties.COLUMN_OneCallNo));
        f.Recommendation = cursor.getString(cursor.getColumnIndex(SitePrepProperties.COLUMN_Recommendation));
        f.TreatedArea = cursor.getString(cursor.getColumnIndex(SitePrepProperties.COLUMN_TreatedArea));


        return f;
    }

    @Override
    public SitePrepForm findFormById(int id) {
        Cursor cursor = db.query(SitePrepProperties.TABLE_SITEPREP,
                null, SitePrepProperties.COLUMN_SitePrepID + " = " + id
                , null, null, null, null);

        cursor.moveToFirst();

        if(!cursor.isAfterLast()) {

            SitePrepForm svf = cursorTo(cursor);
            cursor.close();
            return svf;

        }else{

            cursor.close();
            return null;

        }
    }
}
