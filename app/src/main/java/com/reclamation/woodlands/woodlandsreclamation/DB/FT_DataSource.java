package com.reclamation.woodlands.woodlandsreclamation.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy on 5/11/2015.
 */
public class FT_DataSource extends AbastrctDataSource<FacilityType> {
    public FT_DataSource(Context context) {
        super(context);
    }

    @Override
    public FacilityType create(FacilityType o) {
        ContentValues values = new ContentValues();
        values.put(FacilityType.COLUMN_FTN, o.FacilityTypeName);
        db.insert(FacilityType.TABLE_FT, null, values);
        return null;
    }

    @Override
    public void delete(FacilityType o) {
        db.delete(FacilityType.TABLE_FT,
                FacilityType.COLUMN_FTN + " = '" + o.FacilityTypeName +"'", null);
    }

    public void deleteAll(){
        db.delete(FacilityType.TABLE_FT, null, null);
    }

    @Override
    public List<FacilityType> getAll() {
        List<FacilityType> facilityTypes = new ArrayList<FacilityType>();

        Cursor cursor = db.query(FacilityType.TABLE_FT, null, null, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            FacilityType f = cursorTo(cursor);
            facilityTypes.add(f);

            cursor.moveToNext();
        }

        return facilityTypes;
    }

    @Override
    public FacilityType cursorTo(Cursor cursor) {
        FacilityType f = new FacilityType();
        f.FacilityTypeName = cursor.getString(0);
        return f;
    }

    @Override
    public FacilityType findFormById(int id) {
        return null;
    }
}
