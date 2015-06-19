package com.reclamation.woodlands.admin.DB.Table_UserInfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.reclamation.woodlands.admin.DB.DAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy on 5/12/2015.
 */
public class UI_DataSource extends DAO<UserInfo> {
    public UI_DataSource(Context context) {
        super(context);
    }

    @Override
    public UserInfo create(UserInfo o) {
        ContentValues values = new ContentValues();
        values.put(UserInfo.COLUMN_USERNAME, o.username);
        values.put(UserInfo.COLUMN_PASSWORD, o.password);
        values.put(UserInfo.COLUMN_ROLE, o.role);

        db.insert(UserInfo.TABLE_USERINFO, null, values);

        return o;
    }

    @Override
    public void update(UserInfo o) {

    }

    @Override
    public void delete(UserInfo o) {
        if(o != null) {
            db.delete(UserInfo.TABLE_USERINFO,
                    UserInfo.COLUMN_USERNAME + " = '" + o.username + "'",
                    null);
        }
    }

    @Override
    public List<UserInfo> getAll() {
        List<UserInfo> userInfos = new ArrayList<UserInfo>();

        Cursor cursor = db.query(UserInfo.TABLE_USERINFO, null, null, null, null, null, null);
        cursor.moveToFirst();

        if(!cursor.isAfterLast()){

            userInfos.add(cursorTo(cursor));
        }

        return userInfos;
    }

    @Override
    public UserInfo cursorTo(Cursor cursor) {

        UserInfo ui = new UserInfo(cursor.getString(0), cursor.getString(1), cursor.getString(2));
        return ui;

    }

    @Override
    public UserInfo findFormById(int id) {
        return null;
    }

    public UserInfo getUserInfo(){
        Cursor cursor = db.query(UserInfo.TABLE_USERINFO, null, null, null, null, null, null);
        cursor.moveToFirst();

        if(!cursor.isAfterLast()){

            return cursorTo(cursor);
        }

        return null;
    }
}
