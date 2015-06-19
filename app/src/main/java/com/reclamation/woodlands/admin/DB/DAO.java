package com.reclamation.woodlands.admin.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Jimmy on 5/11/2015.
 */
public abstract class DAO<T> {

    public SQLiteDatabase db;
    public SQLHelper dbHelper;
    public Context mContext;


    public DAO(Context context){
        dbHelper = new SQLHelper(context);
        mContext = context;
    }

    public void open(){
        try {
            db = dbHelper.getWritableDatabase();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void close(){
        dbHelper.close();
    }

    public abstract T create(T o);

    public abstract void update(T o);

    public abstract void delete(T o);

    public abstract List<T> getAll();

    public abstract T cursorTo(Cursor cursor);

    public abstract T findFormById(int id);
}
