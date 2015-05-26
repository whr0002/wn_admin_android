package com.reclamation.woodlands.woodlandsreclamation.Data.Forms;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * Created by Jimmy on 5/26/2015.
 */
public class DecodeImageAsync extends AsyncTask<PathView, Void, DecodeImageAsync.BitmapView>{


    @Override
    protected BitmapView doInBackground(PathView... pathViews) {

        if(pathViews != null && pathViews[0] != null) {
            BitmapView bitmapView = new BitmapView();
            Bitmap bitmap = BitmapFactory.decodeFile(pathViews[0].imagePath);

            bitmapView.bitmap = bitmap;
            bitmapView.imageView = pathViews[0].imageView;

            return bitmapView;
        }

        return null;
    }



    @Override
    protected void onPostExecute(BitmapView bv) {

        if(bv != null){

            bv.imageView.setImageBitmap(bv.bitmap);
        }

    }

    class BitmapView {
        public Bitmap bitmap;
        public ImageView imageView;
    }
}
