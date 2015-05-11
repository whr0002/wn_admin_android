package com.reclamation.woodlands.woodlandsreclamation.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

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

    @Override
    public List<FacilityType> getAll() {

        return null;
    }

    @Override
    public FacilityType cursorTo(Cursor cursor) {
        return null;
    }
}
