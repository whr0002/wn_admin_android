package com.reclamation.woodlands.admin.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;

import com.reclamation.woodlands.admin.DB.Table_Photo.Photo;
import com.reclamation.woodlands.admin.Data.Forms.ImageProcessor;
import com.reclamation.woodlands.admin.R;

import java.util.ArrayList;

/**
 * Created by Jimmy on 5/20/2015.
 */
public class ImageGridAdapter extends ArrayAdapter<Photo>{

    private static ImageProcessor imageProcessor = new ImageProcessor(null);


    public ImageGridAdapter(Context context, int resource) {
        super(context, resource);
    }

    public ImageGridAdapter(Context context, int resource, ArrayList<Photo> photos){
        super(context, resource, photos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        boolean isRecycled = true;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());

            convertView = inflater.inflate(R.layout.image_in_gridview, null);

            isRecycled = false;

        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.one_image);


        Log.i("debug", "decoding");
        Bitmap bm = BitmapFactory.decodeFile(getItem(position).path);
        imageView.setImageBitmap(bm);


//        imageProcessor.setImageView(imageView, getItem(position).path, isRecycled);




        return convertView;
    }

    private static class ViewHolder{
        ImageView imageView;
        EditText descText;
    }
}
