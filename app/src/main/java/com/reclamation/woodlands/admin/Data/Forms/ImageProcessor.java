package com.reclamation.woodlands.admin.Data.Forms;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.media.ExifInterface;
import android.widget.ImageView;

import com.google.android.gms.maps.model.LatLng;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Jimmy on 3/16/2015.
 * Used for setting imageviews and compressing images
 */
public class ImageProcessor {



    private Location mLocation;
    public ImageProcessor(Location location){

        mLocation = location;

    }

    public void setImageView(ImageView mImageView, String mPath, Boolean mIsCompress){
        // Get the dimensions of the view
        int targetW = mImageView.getWidth();
        int targetH = mImageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(mPath, bmOptions);

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;
        int scaleFactor = 5;
        // Determine how much to scale down the image
        if(targetW != 0 && targetH != 0) {
            scaleFactor = Math.min(photoW / targetW, photoH / targetH);
        }

        // Decode the image file into a Bitmap sized to fill the view
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;


        Bitmap bitmap = BitmapFactory.decodeFile(mPath, bmOptions);
        mImageView.setImageBitmap(bitmap);
        // Indicate whether to compress the image
        if(mIsCompress) {
            bmOptions.inSampleSize = 4;
            Bitmap b = BitmapFactory.decodeFile(mPath, bmOptions);
            compressImage(b, mPath);


            setGeoTag(mPath);

        }

    }

    public void shrinkImage(String mPath){


        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mPath, options);

        options.inJustDecodeBounds = false;
        options.inSampleSize = 4;
        options.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mPath, options);

        compressImage(bitmap, mPath);
        setGeoTag(mPath);
    }

    public boolean isImageFound(String path){
        if(path != null) {
            File file = new File(path);
            if(file != null && file.exists()){
                return true;
            }
        }

        return false;
    }

    public void compressImage(Bitmap b, String mPath){
        try{
            if(mPath != null) {
//                Log.i("debug", "in: " + mPath);
                File file = new File(mPath);

                if (!file.exists()) {

                    file.createNewFile();

                } else {

                    file.delete();
                    file.createNewFile();
                }

                FileOutputStream fos = null;

                fos = new FileOutputStream(file);
                if (fos != null) {
                    b.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.close();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean setGeoTag(String path) {

            try {
                LatLng geoTag = new LatLng(mLocation.getLatitude(), mLocation.getLongitude());
                ExifInterface exif = new ExifInterface(path);

                double latitude = Math.abs(geoTag.latitude);
                double longitude = Math.abs(geoTag.longitude);

                int num1Lat = (int) Math.floor(latitude);
                int num2Lat = (int) Math.floor((latitude - num1Lat) * 60);
                double num3Lat = (latitude - ((double) num1Lat + ((double) num2Lat / 60))) * 3600000;

                int num1Lon = (int) Math.floor(longitude);
                int num2Lon = (int) Math.floor((longitude - num1Lon) * 60);
                double num3Lon = (longitude - ((double) num1Lon + ((double) num2Lon / 60))) * 3600000;

                String lat = num1Lat + "/1," + num2Lat + "/1," + num3Lat + "/1000";
                String lon = num1Lon + "/1," + num2Lon + "/1," + num3Lon + "/1000";

                if (geoTag.latitude > 0) {
                    exif.setAttribute(ExifInterface.TAG_GPS_LATITUDE_REF, "N");
                } else {
                    exif.setAttribute(ExifInterface.TAG_GPS_LATITUDE_REF, "S");
                }

                if (geoTag.longitude > 0) {
                    exif.setAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF, "E");
                } else {
                    exif.setAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF, "W");
                }

                exif.setAttribute(ExifInterface.TAG_GPS_LATITUDE, lat);
                exif.setAttribute(ExifInterface.TAG_GPS_LONGITUDE, lon);

                exif.saveAttributes();

                return true;

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

    }


}
