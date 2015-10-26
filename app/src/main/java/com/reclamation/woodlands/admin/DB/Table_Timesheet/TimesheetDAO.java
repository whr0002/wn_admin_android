package com.reclamation.woodlands.admin.DB.Table_Timesheet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.reclamation.woodlands.admin.DB.DAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy on 6/22/2015.
 */
public class TimesheetDAO extends DAO<TimesheetForm>{
    public TimesheetDAO(Context context) {
        super(context);
    }

    @Override
    public TimesheetForm create(TimesheetForm o) {
        ContentValues cv = new ContentValues();

        cv.put(TimesheetProperties.COLUMN_Name, o.Name);
        cv.put(TimesheetProperties.COLUMN_Date, o.Date);
        cv.put(TimesheetProperties.COLUMN_PPyr, o.PPyr);
        cv.put(TimesheetProperties.COLUMN_PP, o.PP);
        cv.put(TimesheetProperties.COLUMN_Client, o.Client);
        cv.put(TimesheetProperties.COLUMN_Project, o.Project);
        cv.put(TimesheetProperties.COLUMN_ProjectID, o.ProjectID);
        cv.put(TimesheetProperties.COLUMN_Task, o.Task);
        cv.put(TimesheetProperties.COLUMN_Identifier, o.Identifier);
        cv.put(TimesheetProperties.COLUMN_Veh, o.Veh);
        cv.put(TimesheetProperties.COLUMN_Crew, o.Crew);
        cv.put(TimesheetProperties.COLUMN_StartKm, o.StartKm);
        cv.put(TimesheetProperties.COLUMN_EndKm, o.EndKm);
        cv.put(TimesheetProperties.COLUMN_GPS, o.GPS);
        cv.put(TimesheetProperties.COLUMN_Field, o.Field);
        cv.put(TimesheetProperties.COLUMN_PD, o.PD);
        cv.put(TimesheetProperties.COLUMN_JobDescription, o.JobDescription);
        cv.put(TimesheetProperties.COLUMN_Off, o.Off);
        cv.put(TimesheetProperties.COLUMN_Hours, o.Hours);
        cv.put(TimesheetProperties.COLUMN_Bank, o.Bank);
        cv.put(TimesheetProperties.COLUMN_OT, o.OT);
        cv.put(TimesheetProperties.COLUMN_MESSAGE, o.Message);

        long id = db.insert(TimesheetProperties.TABLE_Timesheet, null, cv);

        Cursor cursor = db.query(TimesheetProperties.TABLE_Timesheet,
                null,
                TimesheetProperties.COLUMN_TID + " = " + id,
                null, null, null, null);

        cursor.moveToFirst();

        TimesheetForm timesheetForm = null;
        if(!cursor.isAfterLast()){
            timesheetForm = cursorTo(cursor);
        }

        cursor.close();



        return timesheetForm;
    }

    @Override
    public void update(TimesheetForm o) {
        ContentValues cv = new ContentValues();

        cv.put(TimesheetProperties.COLUMN_Name, o.Name);
        cv.put(TimesheetProperties.COLUMN_Date, o.Date);
        cv.put(TimesheetProperties.COLUMN_PPyr, o.PPyr);
        cv.put(TimesheetProperties.COLUMN_PP, o.PP);
        cv.put(TimesheetProperties.COLUMN_Client, o.Client);
        cv.put(TimesheetProperties.COLUMN_Project, o.Project);
        cv.put(TimesheetProperties.COLUMN_ProjectID, o.ProjectID);
        cv.put(TimesheetProperties.COLUMN_Task, o.Task);
        cv.put(TimesheetProperties.COLUMN_Identifier, o.Identifier);
        cv.put(TimesheetProperties.COLUMN_Veh, o.Veh);
        cv.put(TimesheetProperties.COLUMN_Crew, o.Crew);
        cv.put(TimesheetProperties.COLUMN_StartKm, o.StartKm);
        cv.put(TimesheetProperties.COLUMN_EndKm, o.EndKm);
        cv.put(TimesheetProperties.COLUMN_GPS, o.GPS);
        cv.put(TimesheetProperties.COLUMN_Field, o.Field);
        cv.put(TimesheetProperties.COLUMN_PD, o.PD);
        cv.put(TimesheetProperties.COLUMN_JobDescription, o.JobDescription);
        cv.put(TimesheetProperties.COLUMN_Off, o.Off);
        cv.put(TimesheetProperties.COLUMN_Hours, o.Hours);
        cv.put(TimesheetProperties.COLUMN_Bank, o.Bank);
        cv.put(TimesheetProperties.COLUMN_OT, o.OT);
        cv.put(TimesheetProperties.COLUMN_MESSAGE, o.Message);

        db.update(TimesheetProperties.TABLE_Timesheet, cv,
                TimesheetProperties.COLUMN_TID + " = " + o.ID, null);
    }

    @Override
    public void delete(TimesheetForm o) {
        db.delete(TimesheetProperties.TABLE_Timesheet,
                TimesheetProperties.COLUMN_TID + " = " + o.ID, null);
    }

    @Override
    public List<TimesheetForm> getAll() {
        List<TimesheetForm> timesheetForms = new ArrayList<TimesheetForm>();
        Cursor cursor = db.query(TimesheetProperties.TABLE_Timesheet, null, null, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            timesheetForms.add(cursorTo(cursor));
            cursor.moveToNext();
        }

        cursor.close();
        return timesheetForms;
    }

    @Override
    public TimesheetForm cursorTo(Cursor cursor) {
        TimesheetForm t = new TimesheetForm();

        t.ID = cursor.getInt(cursor.getColumnIndex(TimesheetProperties.COLUMN_TID));
        t.Name = cursor.getString(cursor.getColumnIndex(TimesheetProperties.COLUMN_Name));
        t.Date = cursor.getString(cursor.getColumnIndex(TimesheetProperties.COLUMN_Date));
        t.PPyr = cursor.getString(cursor.getColumnIndex(TimesheetProperties.COLUMN_PPyr));
        t.PP = cursor.getString(cursor.getColumnIndex(TimesheetProperties.COLUMN_PP));
        t.Client = cursor.getString(cursor.getColumnIndex(TimesheetProperties.COLUMN_Client));
        t.Project = cursor.getString(cursor.getColumnIndex(TimesheetProperties.COLUMN_Project));
        t.ProjectID = cursor.getString(cursor.getColumnIndex(TimesheetProperties.COLUMN_ProjectID));
        t.Task = cursor.getString(cursor.getColumnIndex(TimesheetProperties.COLUMN_Task));
        t.Identifier = cursor.getString(cursor.getColumnIndex(TimesheetProperties.COLUMN_Identifier));
        t.Veh = cursor.getString(cursor.getColumnIndex(TimesheetProperties.COLUMN_Veh));
        t.Crew = cursor.getString(cursor.getColumnIndex(TimesheetProperties.COLUMN_Crew));
        t.StartKm = cursor.getString(cursor.getColumnIndex(TimesheetProperties.COLUMN_StartKm));
        t.EndKm = cursor.getString(cursor.getColumnIndex(TimesheetProperties.COLUMN_EndKm));
        t.GPS = cursor.getString(cursor.getColumnIndex(TimesheetProperties.COLUMN_GPS));
        t.Field = cursor.getString(cursor.getColumnIndex(TimesheetProperties.COLUMN_Field));
        t.PD = cursor.getString(cursor.getColumnIndex(TimesheetProperties.COLUMN_PD));
        t.JobDescription = cursor.getString(cursor.getColumnIndex(TimesheetProperties.COLUMN_JobDescription));
        t.Off = cursor.getString(cursor.getColumnIndex(TimesheetProperties.COLUMN_Off));
        t.Hours = cursor.getString(cursor.getColumnIndex(TimesheetProperties.COLUMN_Hours));
        t.Bank = cursor.getString(cursor.getColumnIndex(TimesheetProperties.COLUMN_Bank));
        t.OT = cursor.getString(cursor.getColumnIndex(TimesheetProperties.COLUMN_OT));
        t.Message = cursor.getString(cursor.getColumnIndex(TimesheetProperties.COLUMN_MESSAGE));

        return t;
    }

    @Override
    public TimesheetForm findFormById(int id) {
        Cursor cursor = db.query(TimesheetProperties.TABLE_Timesheet,
                null,
                TimesheetProperties.COLUMN_TID + " = " + id,
                null, null, null, null);
        cursor.moveToFirst();

        TimesheetForm timesheetForm = null;
        if(!cursor.isAfterLast()){
            timesheetForm = cursorTo(cursor);
        }

        cursor.close();
        return timesheetForm;
    }
}
