package com.reclamation.woodlands.woodlandsreclamation.Data.Forms.Drawing;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.reclamation.woodlands.woodlandsreclamation.DB.Table_Photo.Photo;
import com.reclamation.woodlands.woodlandsreclamation.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy on 5/26/2015.
 */
public class DrawingPopup{

    private Context mContext;
    private View mView;
    private Photo mPhoto;
    private List<Photo> mRemovedPhotos;
    private Photo temp;

    public DrawingPopup(Context context, View view, Photo currentPhoto, ArrayList<Photo> removedPhotos){

        mContext = context;
        mView = view;
        mPhoto = currentPhoto;
        mRemovedPhotos = removedPhotos;

        temp = new Photo();
        temp.path = currentPhoto.path;



    }

    public void showPopup(){

        PopupMenu popupMenu = new PopupMenu(mContext, mView);
        popupMenu.getMenuInflater().inflate(R.menu.drawing_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.delete:
                        mRemovedPhotos.add(temp);
                        mView.setVisibility(View.GONE);
                        break;
                }

                return true;
            }
        });

        popupMenu.show();


    }

}
