package com.reclamation.woodlands.woodlandsreclamation.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Jimmy on 5/8/2015.
 */
public class SQLHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "wn_reclamation.db";
    private static final int DATABASE_VERSION = 15;


    public SQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if(!db.isReadOnly()){
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(RS_Properties.RS_CREATE);
        sqLiteDatabase.execSQL(DR_Properties.DR_CREATE);
        sqLiteDatabase.execSQL(FacilityType.FT_CREATE);
        sqLiteDatabase.execSQL(UserInfo.USERINFO_CREATE);
        sqLiteDatabase.execSQL(SiteVisitProperties.SITEVISIT_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.i("debug", "Upgrading database from version "+ oldVersion +" to "
                + newVersion);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DR_Properties.TABLE_DESKTOPREVIEWS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RS_Properties.TABLE_REVIEWSITE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FacilityType.TABLE_FT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + UserInfo.TABLE_USERINFO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SiteVisitProperties.TABLE_SITEVISIT);

        onCreate(sqLiteDatabase);
    }
}
