package com.reclamation.woodlands.admin.DB.Table_Timesheet;

/**
 * Created by Jimmy on 6/22/2015.
 */
public class TimesheetProperties {
    public static final String COLUMN_TID = "TID";
    public static final String COLUMN_Name = "Name";
    public static final String COLUMN_Date = "Date";
    public static final String COLUMN_PPyr = "PPyr";
    public static final String COLUMN_PP = "PP";
    public static final String COLUMN_Client = "Client";
    public static final String COLUMN_Project = "Project";
    public static final String COLUMN_ProjectID = "ProjectID";
    public static final String COLUMN_Task = "Task";
    public static final String COLUMN_Identifier = "Identifier";
    public static final String COLUMN_Veh = "Veh";
    public static final String COLUMN_Crew = "Crew";
    public static final String COLUMN_StartKm = "StartKm";
    public static final String COLUMN_EndKm = "EndKm";
    public static final String COLUMN_GPS = "GPS";
    public static final String COLUMN_Field = "Field";
    public static final String COLUMN_PD = "PD";
    public static final String COLUMN_JobDescription = "JobDescription";
    public static final String COLUMN_Off = "Off";
    public static final String COLUMN_Hours = "Hours";
    public static final String COLUMN_Bank = "Bank";
    public static final String COLUMN_OT = "OT";
    public static final String COLUMN_MESSAGE = "Message";


    public static final String TABLE_Timesheet = "Timesheet";
    public static final String TABLE_Create =
            "create table "+ TABLE_Timesheet
                    + " ( "
                    + COLUMN_TID + " integer primary key autoincrement, "
                    + COLUMN_Name + " text, "
                    + COLUMN_Date + " text, "
                    + COLUMN_PPyr + " text, "
                    + COLUMN_PP + " text, "
                    + COLUMN_Client + " text, "
                    + COLUMN_Project + " text, "
                    + COLUMN_ProjectID + " text, "
                    + COLUMN_Task + " text, "
                    + COLUMN_Identifier + " text, "
                    + COLUMN_Veh + " text, "
                    + COLUMN_Crew + " text, "
                    + COLUMN_StartKm + " text, "
                    + COLUMN_EndKm + " text, "
                    + COLUMN_GPS + " text, "
                    + COLUMN_Field + " text, "
                    + COLUMN_PD + " text, "
                    + COLUMN_JobDescription + " text, "
                    + COLUMN_Off + " text, "
                    + COLUMN_Hours + " text, "
                    + COLUMN_Bank + " text, "
                    + COLUMN_OT + " text, "
                    + COLUMN_MESSAGE + " text "
                    + " ); ";
}
