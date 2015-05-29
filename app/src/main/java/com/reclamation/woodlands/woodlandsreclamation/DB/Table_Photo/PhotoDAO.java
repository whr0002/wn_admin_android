package com.reclamation.woodlands.woodlandsreclamation.DB.Table_Photo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.reclamation.woodlands.woodlandsreclamation.DB.DAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy on 5/19/2015.
 */
public class PhotoDAO extends DAO<Photo> {


    public PhotoDAO(Context context) {
        super(context);
    }

    @Override
    public Photo create(Photo o) {
        ContentValues cv = new ContentValues();
        cv.put(Photo.COLUMN_FORMTYPE, o.formType);
        cv.put(Photo.COLUMN_FORMID, o.formId);
        cv.put(Photo.COLUMN_PATH, o.path);
        cv.put(Photo.COLUMN_DESC, o.description);
        cv.put(Photo.COLUMN_CLASS, o.classification);

        db.insert(Photo.TABLE_NAME, null, cv);


        return null;
    }

    @Override
    public void update(Photo o) {
        ContentValues cv = new ContentValues();
        cv.put(Photo.COLUMN_FORMTYPE, o.formType);
        cv.put(Photo.COLUMN_FORMID, o.formId);
        cv.put(Photo.COLUMN_PATH, o.path);
        cv.put(Photo.COLUMN_DESC, o.description);
        cv.put(Photo.COLUMN_CLASS, o.classification);

    }

    public void update(Photo old, Photo newPhoto){
        ContentValues cv = new ContentValues();
        cv.put(Photo.COLUMN_FORMTYPE, newPhoto.formType);
        cv.put(Photo.COLUMN_FORMID, newPhoto.formId);
        cv.put(Photo.COLUMN_PATH, newPhoto.path);
        cv.put(Photo.COLUMN_DESC, newPhoto.description);
        cv.put(Photo.COLUMN_CLASS, newPhoto.classification);

        db.update(Photo.TABLE_NAME, cv, Photo.COLUMN_PATH + " = '" + old.path +"'", null);
    }

    @Override
    public void delete(Photo o) {
        db.delete(Photo.TABLE_NAME, Photo.COLUMN_PATH + " = '" + o.path + "'", null);
    }


    @Override
    public List<Photo> getAll() {

        List<Photo> photos = new ArrayList<Photo>();
        Cursor cursor = db.query(Photo.TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){

            photos.add(cursorTo(cursor));
            cursor.moveToNext();
        }

        cursor.close();

        return photos;
    }

    @Override
    public Photo cursorTo(Cursor cursor) {
        Photo photo = new Photo();

        photo.formType = cursor.getString(cursor.getColumnIndex(Photo.COLUMN_FORMTYPE));
        photo.formId = cursor.getInt(cursor.getColumnIndex(Photo.COLUMN_FORMID));
        photo.path = cursor.getString(cursor.getColumnIndex(Photo.COLUMN_PATH));
        photo.description = cursor.getString(cursor.getColumnIndex(Photo.COLUMN_DESC));
        photo.classification = cursor.getString(cursor.getColumnIndex(Photo.COLUMN_CLASS));


        return photo;
    }

    @Override
    public Photo findFormById(int id) {
        return null;
    }

    public ArrayList<Photo> findPhotos(String formType, int formId, String classification){

        ArrayList<Photo> photos = new ArrayList<Photo>();
        Cursor cursor = null;
        if(classification == null) {
            cursor = db.query(Photo.TABLE_NAME, null,
                    Photo.COLUMN_FORMTYPE + " = '" + formType + "' AND "
                            + Photo.COLUMN_FORMID + " = " + formId,
                    null, null, null, Photo.COLUMN_PATH, null);
        }else{
            cursor = db.query(Photo.TABLE_NAME, null,
                    Photo.COLUMN_FORMTYPE + " = '"
                            + formType + "' AND "
                            + Photo.COLUMN_FORMID + " = " + formId + " AND "
                            + Photo.COLUMN_CLASS + " = '" + classification + "'",
                    null, null, null, Photo.COLUMN_PATH, null);
        }
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {

                photos.add(cursorTo(cursor));
                cursor.moveToNext();
            }

            cursor.close();

        return photos;
    }


}
