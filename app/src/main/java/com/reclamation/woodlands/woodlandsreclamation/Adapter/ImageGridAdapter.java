package com.reclamation.woodlands.woodlandsreclamation.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.reclamation.woodlands.woodlandsreclamation.DB.Table_Photo.Photo;
import com.reclamation.woodlands.woodlandsreclamation.R;

import java.util.ArrayList;

/**
 * Created by Jimmy on 5/20/2015.
 */
public class ImageGridAdapter extends ArrayAdapter<Photo>{
    public ImageGridAdapter(Context context, int resource) {
        super(context, resource);
    }

    public ImageGridAdapter(Context context, int resource, ArrayList<Photo> photos){
        super(context, resource, photos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());

            convertView = inflater.inflate(R.layout.image_in_gridview, null);


        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.one_image);

        if(imageView != null){
            Bitmap bm = BitmapFactory.decodeFile(getItem(position).path);
            imageView.setImageBitmap(bm);
        }


        return convertView;
    }
}
