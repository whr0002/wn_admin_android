package com.reclamation.woodlands.woodlandsreclamation.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.Drawing.DrawingView;
import com.reclamation.woodlands.woodlandsreclamation.R;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jimmy on 5/19/2015.
 */
public class DrawingActivity extends ActionBarActivity implements View.OnClickListener{

    private DrawingView drawingView;
    private ImageButton currPaint, drawBtn, eraseBtn, newBtn, saveBtn;

    private float smallBrush, mediumBrush, largeBrush;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);

        mContext = this.getApplicationContext();

        drawingView = (DrawingView) findViewById(R.id.drawing);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            String path = bundle.getString("path");
            if (path != null && path.length() > 0) {
                Log.i("debug", "Got: " + path);
                drawingView.setCanvasBitmap(path);

            }
        }

        LinearLayout paintLayout = (LinearLayout) findViewById(R.id.paint_colors);

        currPaint = (ImageButton)paintLayout.getChildAt(0);
        currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));

        smallBrush = getResources().getInteger(R.integer.small_size);
        mediumBrush = getResources().getInteger(R.integer.medium_size);
        largeBrush = getResources().getInteger(R.integer.large_size);

        drawBtn = (ImageButton)findViewById(R.id.draw_btn);
        drawBtn.setOnClickListener(this);

        eraseBtn = (ImageButton)findViewById(R.id.erase_btn);
        eraseBtn.setOnClickListener(this);

        newBtn = (ImageButton)findViewById(R.id.new_btn);
        newBtn.setOnClickListener(this);

        saveBtn = (ImageButton)findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(this);
    }

    public void paintClicked(View view){
        //use chosen color
        if(view != currPaint){
            ImageButton imgView = (ImageButton)view;
            String color = view.getTag().toString();
            drawingView.setErase(false);
            drawingView.setBrushSize(drawingView.getLastBrushSize());
            drawingView.setColor(color);
            imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
            currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
            currPaint = (ImageButton)view;
        }
    }


    @Override
    public void onClick(View view) {

            if(view.getId()== R.id.draw_btn) {

                final Dialog brushDialog = new Dialog(this);
                brushDialog.setTitle("Brush Size: ");
                brushDialog.setContentView(R.layout.brush_chooser);
                ImageButton smallBtn = (ImageButton) brushDialog.findViewById(R.id.small_brush);
                smallBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        drawingView.setBrushSize(smallBrush);
                        drawingView.setLastBrushSize(smallBrush);
                        drawingView.setErase(false);
                        brushDialog.dismiss();
                    }
                });
                ImageButton mediumBtn = (ImageButton) brushDialog.findViewById(R.id.medium_brush);
                mediumBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawingView.setBrushSize(mediumBrush);
                        drawingView.setLastBrushSize(mediumBrush);
                        drawingView.setErase(false);
                        brushDialog.dismiss();
                    }
                });

                ImageButton largeBtn = (ImageButton) brushDialog.findViewById(R.id.large_brush);
                largeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawingView.setBrushSize(largeBrush);
                        drawingView.setLastBrushSize(largeBrush);
                        drawingView.setErase(false);
                        brushDialog.dismiss();
                    }
                });

                brushDialog.show();

            }else if(view.getId() == R.id.erase_btn) {
                final Dialog brushDialog = new Dialog(this);
                brushDialog.setTitle("Eraser size:");
                brushDialog.setContentView(R.layout.brush_chooser);
                ImageButton smallBtn = (ImageButton)brushDialog.findViewById(R.id.small_brush);
                smallBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        drawingView.setErase(true);
                        drawingView.setBrushSize(smallBrush);
                        brushDialog.dismiss();
                    }
                });
                ImageButton mediumBtn = (ImageButton)brushDialog.findViewById(R.id.medium_brush);
                mediumBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        drawingView.setErase(true);
                        drawingView.setBrushSize(mediumBrush);
                        brushDialog.dismiss();
                    }
                });
                ImageButton largeBtn = (ImageButton)brushDialog.findViewById(R.id.large_brush);
                largeBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        drawingView.setErase(true);
                        drawingView.setBrushSize(largeBrush);
                        brushDialog.dismiss();
                    }
                });

                brushDialog.show();
            }else if(view.getId() == R.id.new_btn){
                AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
                newDialog.setTitle("New drawing");
                newDialog.setMessage("Start new drawing (you will lose the current drawing)?");
                newDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        drawingView.startNew();
                        dialog.dismiss();
                    }
                });
                newDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        dialog.cancel();
                    }
                });
                newDialog.show();

            }else if(view.getId() == R.id.save_btn){



                AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);

                saveDialog.setTitle("Save drawing");
                saveDialog.setMessage("Save drawing to device?");

                saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        drawingView.setDrawingCacheEnabled(true);

                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                        String imageName = "DRAWING_" + timeStamp + ".jpg";
                        File dir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "picupload");

                        if(!dir.exists()){
                            dir.mkdir();
                        }

                        File imgFile = new File(dir.getAbsolutePath() + File.separator + imageName);

                        FileOutputStream out = null;
                        Bitmap bmp = drawingView.getDrawingCache();

                        try{

                            out = new FileOutputStream(imgFile);
                            bmp.compress(Bitmap.CompressFormat.JPEG, 100, out);
                            Toast.makeText(mContext, "Drawing saved", Toast.LENGTH_LONG).show();

                            Intent returnIntent = new Intent();
                            returnIntent.putExtra("result", imgFile.getCanonicalPath());
                            setResult(RESULT_OK, returnIntent);
                            finish();

                        }catch (Exception e){

                            Toast.makeText(mContext, "Drawing could not be saved", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }finally {
                            try{
                                if(out != null){
                                    out.close();
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }

                        drawingView.destroyDrawingCache();




//                        String imgPath =

                    }
                });

                saveDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        dialog.cancel();
                    }
                });

                saveDialog.show();

            }


    }


}
