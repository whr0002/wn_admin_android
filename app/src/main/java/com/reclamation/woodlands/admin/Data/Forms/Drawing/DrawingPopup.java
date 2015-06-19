package com.reclamation.woodlands.admin.Data.Forms.Drawing;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.reclamation.woodlands.admin.DB.Table_Photo.Photo;
import com.reclamation.woodlands.admin.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy on 5/26/2015.
 */
public class DrawingPopup{

    private Context mContext;
    private TextView mTextView;
    private ImageView mImageView;
    private Photo mPhoto;
    private List<Photo> mRemovedPhotos;
    private Photo temp;

    public DrawingPopup(Context context, ImageView imageView, TextView desc, Photo currentPhoto, ArrayList<Photo> removedPhotos){

        mContext = context;
        mTextView = desc;
        mImageView = imageView;
        mPhoto = currentPhoto;
        mRemovedPhotos = removedPhotos;

        temp = new Photo();
        temp.path = currentPhoto.path;



    }

    public void showPopup(){

        PopupMenu popupMenu = new PopupMenu(mContext, mImageView);
        popupMenu.getMenuInflater().inflate(R.menu.image_popup_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.add_desc:
                        showDialog();
                        break;

                    case R.id.delete:
                        mRemovedPhotos.add(temp);
                        mImageView.setVisibility(View.GONE);
                        mTextView.setVisibility(View.GONE);
                        break;
                }

                return true;
            }
        });

        popupMenu.show();


    }

    private void showDialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Description");

        final EditText editText = new EditText(mContext);
        LinearLayout.LayoutParams params = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);

        editText.setLayoutParams(params);
        if(mPhoto.description != null) {
            editText.setText(mPhoto.description);
        }

        builder.setView(editText);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mPhoto.description = editText.getText().toString();
                Toast.makeText(mContext, mPhoto.description, Toast.LENGTH_SHORT).show();
                mTextView.setText(mPhoto.description);
                mTextView.setVisibility(View.VISIBLE);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.show();
    }

}
