package com.reclamation.woodlands.admin.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.reclamation.woodlands.admin.DB.Table_FacilityType.FT_DataSource;
import com.reclamation.woodlands.admin.DB.Table_FacilityType.FacilityType;
import com.reclamation.woodlands.admin.DB.Table_Photo.Photo;
import com.reclamation.woodlands.admin.DB.Table_Photo.PhotoDAO;
import com.reclamation.woodlands.admin.DB.Table_SiteLatLng.SLL_Datasource;
import com.reclamation.woodlands.admin.DB.Table_SiteLatLng.SiteLatLng;
import com.reclamation.woodlands.admin.DB.Table_SiteVisit.SiteVisitProperties;
import com.reclamation.woodlands.admin.Data.Forms.DecodeImageAsync;
import com.reclamation.woodlands.admin.Data.Forms.Form;
import com.reclamation.woodlands.admin.Data.Forms.ImagePopup;
import com.reclamation.woodlands.admin.Data.Forms.ImageProcessor;
import com.reclamation.woodlands.admin.Data.Forms.PathView;
import com.reclamation.woodlands.admin.Data.Forms.Validator;
import com.reclamation.woodlands.admin.R;
import com.reclamation.woodlands.admin.Services.SiteService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jimmy on 5/13/2015.
 */
public abstract class FormDetailActivity extends ActionBarActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener{

    public GoogleApiClient mGoogleApiClient;
    public Location location;
    protected static final LocationRequest REQUEST = LocationRequest.create()
            .setInterval(5000)
            .setFastestInterval(16)
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    protected Context mContext;
    protected ActionBar mActionBar;
    protected ProgressDialog progressDialog;
    public static final int CAMERA_REQUEST_CODE = 10;
    public static final int DRAWING_CAMERA_REQUEST_CODE = 11;
    protected Photo currentPhoto, curDrawing;
    protected LinearLayout currentLayout;
    protected PhotoDAO photoDAO;
    protected SLL_Datasource siteDAO;
    protected ImageProcessor imageProcessor;
    protected DecodeImageAsync decodeImageAsync;
    public ArrayList<Photo> removedPhotos,newCreatedPhotos, allPhotos;
    public HashMap<String, Boolean> photoMap;
    public int mId;
    private  ArrayAdapter<CharSequence> mSiteAdapter;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        mContext = this;
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        photoDAO = new PhotoDAO(this);
        siteDAO = new SLL_Datasource(this);
        imageProcessor = new ImageProcessor(location);
        decodeImageAsync = new DecodeImageAsync();

        allPhotos = new ArrayList<Photo>();
        removedPhotos = new ArrayList<Photo>();
        newCreatedPhotos = new ArrayList<Photo>();
        photoMap = new HashMap<String, Boolean>();

        setLayout(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.detail_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:

                showExitDialog();

                break;

            case R.id.save:
                Log.i("debug", "save");

                // Get the current form
                Form form = getCurrentForm();

                // Validate the current form
                Validator validator = getValidator();
                validator.validate(form);

                // Saving in background
                progressDialog = ProgressDialog.show(this, "", "Saving...", true);
                SaveAsync saveAsync = new SaveAsync();
                saveAsync.execute(form);


                break;

        }

        return super.onOptionsItemSelected(item);
    }

    public void showExitDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit");
        builder.setMessage("Do you want to exit without saving?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                onFinishWithoutSave();
                finish();

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.show();



    }

    @Override
    public void onBackPressed() {
        showExitDialog();
    }

    public abstract void setLayout(Activity a);

    public abstract void addOrUpdate(Form f);

    public abstract Form getCurrentForm();

    public abstract void onFinishWithoutSave();

    public abstract Validator getValidator();
    public abstract ImageView getDrawingView();
    public abstract String getFormType();
    public abstract TextView getLatitudeView();
    public abstract TextView getLongitudeView();



    class SaveAsync extends AsyncTask<Form, Object, Object> {
        @Override
        protected Object doInBackground(Form[] forms) {

            addOrUpdate(forms[0]);

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            progressDialog.dismiss();
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public int getSpinnerIndex(Spinner spinner, String name){
        if(spinner != null && name != null){
            for(int i=0;i<spinner.getCount();i++){
                if(spinner.getItemAtPosition(i).equals(name)){
                    return i;
                }
            }
        }

        return 0;
    }
    public void openCamera(String formType, String classification, LinearLayout layout, int requestCode){
        imageProcessor = new ImageProcessor(location);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(cameraIntent.resolveActivity(getPackageManager()) != null){
            // image capture app exists
            String path = generateImagePath(formType+"_"+ classification +"_", ".jpg");

            // set up Photo properties and the layout to be added in
            currentPhoto = new Photo();
            currentPhoto.path = path;
            currentPhoto.classification = classification;
            currentPhoto.formType = formType;

            currentLayout = layout;


            if(path != null){

                File file = new File(path);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(cameraIntent, requestCode);
            }else{
                Toast.makeText(this, "Could not generate image path", Toast.LENGTH_SHORT).show();
            }

        }
    }
    public String generateImagePath(String prefix, String ext){

        if(prefix == null){
            prefix = SiteVisitProperties.FORM_TYPE + "_";
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                + File.separator + "picupload" + File.separator;

        File dirFile = new File(dir);
        if(!dirFile.exists()){
            dirFile.mkdir();
        }

        String fullPath = dir + prefix + timeStamp + ext;

        Log.i("debug", "Full image path: "+fullPath);

        return fullPath;


    }

    public void setDate(TextView textView){
        Date date = new Date();
        String cDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);


        textView.setText(cDate);
    }

    public void setFTSpinner(Spinner spinner) {
        ArrayAdapter<CharSequence> adapter;
        FT_DataSource ftDao = new FT_DataSource(this);
        ftDao.open();

        List<FacilityType> facilityTypes = ftDao.getAll();
        ftDao.close();

        ArrayList<CharSequence> values = new ArrayList<CharSequence>();
        values.add("");

        if(facilityTypes != null && facilityTypes.size() > 0){

            for(FacilityType ft : facilityTypes){
                values.add(ft.FacilityTypeName);

            }
        }

        adapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    public void setSiteIdSpinner(Spinner spinner) {



        ArrayAdapter<CharSequence> adapter;

//        RS_DataSource rsDao = new RS_DataSource(this);
//
//        rsDao.open();
//
//        List<ReviewSite> rss = rsDao.getAll();
//
//        rsDao.close();

        // Get sites which are around a given set of coordinates
        SiteService siteService = new SiteService();
        double cLat = 53.498503;
        double cLng = -113.576660;
        List<SiteLatLng> sites = siteService.getSitesAround(this, cLat, cLng, 220);


        ArrayList<CharSequence> values = new ArrayList<CharSequence>();
        values.add("");

        if(sites != null && sites.size() > 0){
            for(SiteLatLng site : sites){
                values.add(site.SiteID);
            }
        }

        adapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        this.mSiteAdapter = adapter;
    }

    // Used for updating site spinner based on a given set of coordinates
    private void updateSiteSpinner(double lat, double lng){
        if(mSiteAdapter != null){
            mSiteAdapter.clear();

            // Get sites which are around a given set of coordinates
            SiteService siteService = new SiteService();
            List<SiteLatLng> sites = siteService.getSitesAround(this, lat, lng, 220);

            mSiteAdapter.add("");

            if(sites != null && sites.size() > 0){
                for(SiteLatLng site : sites){
                    mSiteAdapter.add(site.SiteID);
                }
            }

            mSiteAdapter.notifyDataSetChanged();
        }
    }

    public void clearTempImages(List<Photo> removedPhotos){

        if(removedPhotos != null && removedPhotos.size() > 0){
            photoDAO.open();

            for(Photo photo : removedPhotos){

                if(photo != null) {
                    File file = new File(photo.path);

                    if (file.exists()) {
                        file.delete();
                    }
                    photoDAO.delete(photo);
                }
            }

            photoDAO.close();

        }
    }
    public void setDrawing(Photo p, ImageView drawingView){

        drawingView.setVisibility(View.VISIBLE);

        PathView pv = new PathView();
        pv.imagePath = p.path;
        pv.imageView = drawingView;

        decodeImageAsync = new DecodeImageAsync();
        decodeImageAsync.execute(pv);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1){

            // coming from drawing activity
            if(resultCode == RESULT_OK){

                // saved drawing
                String result = data.getStringExtra("result");
                Log.i("debug", result);

                if(result != null && result.length()>0){
                    if(curDrawing != null){
                        removedPhotos.add(curDrawing);
                    }


                    Photo p = new Photo();
                    p.path = result;
                    p.formType = getFormType();
                    p.classification = "Drawing";
                    curDrawing = p;
                    setDrawing(p, getDrawingView());
                    newCreatedPhotos.add(p);
                    allPhotos.add(p);


                }
            }
        }
        if(requestCode == DRAWING_CAMERA_REQUEST_CODE){
            // back from camera intent
            Log.i("debug", "back from camera for drawing");

            if(currentPhoto != null) {

                if(imageProcessor.isImageFound(currentPhoto.path)) {

                    imageProcessor.shrinkImage(currentPhoto.path);

                    if(curDrawing != null){
                        removedPhotos.add(curDrawing);
                    }

                    Photo p = new Photo();
                    p.path = currentPhoto.path;
                    p.formType = currentPhoto.formType;
                    p.classification = currentPhoto.classification;
                    p.description = currentPhoto.description;

                    curDrawing = p;

                    currentPhoto = null;

                    newCreatedPhotos.add(p);

                    allPhotos.add(p);

                    setDrawing(p, getDrawingView());

                }else{
                    Log.i("debug", "image or layout does not exist");
                }

            }
        }

        if(requestCode == CAMERA_REQUEST_CODE){
            // back from camera intent
            Log.i("debug", "back from camera");

            if(currentPhoto != null && currentLayout != null) {

                if(imageProcessor.isImageFound(currentPhoto.path)) {

                    imageProcessor.shrinkImage(currentPhoto.path);

                    Photo p = new Photo();
                    p.path = currentPhoto.path;
                    p.formType = currentPhoto.formType;
                    p.classification = currentPhoto.classification;
                    p.description = currentPhoto.description;

                    currentPhoto = null;

                    newCreatedPhotos.add(p);

                    allPhotos.add(p);
                    addGalleryItem(allPhotos.size() - 1, p, currentLayout, allPhotos);

                }else{
                    Log.i("debug", "image or layout does not exist");
                }

            }
        }
    }

    public void addGalleryItem(int id, Photo photo, final LinearLayout galleryLayout, final ArrayList<Photo> photos){
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setId(id);
        LinearLayout.LayoutParams layoutParams = new LinearLayout
                .LayoutParams(150
                , 200);
        linearLayout.setPadding(5,5,5,5);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setWeightSum(1.0f);

        linearLayout.setLayoutParams(layoutParams);

        TextView textView = new TextView(this);

        if(photo.description != null){
            textView.setText(photo.description);
        }else {
            textView.setText("Image title goes here");
        }

        textView.setId(R.id.image_desc);
        LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0);
        textViewParams.weight = 0.2f;
        textViewParams.gravity = Gravity.CENTER;
        textView.setLayoutParams(textViewParams);
        textView.setSingleLine(true);


        ImageView imageView = new ImageView(this);
        imageView.setId(R.id.image);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        LinearLayout.LayoutParams imageViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        imageViewParams.weight = 0.8f;
        imageView.setLayoutParams(imageViewParams);




        // Attach views
        linearLayout.addView(imageView);
        linearLayout.addView(textView);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePopup imagePopup = new ImagePopup(mContext, view, photos, removedPhotos, galleryLayout);
                imagePopup.showPopup();
            }
        });

        galleryLayout.addView(linearLayout);



        // Decode bitmap and set it
        PathView pv = new PathView();
        pv.imagePath = photo.path;
        pv.imageView = imageView;
        decodeImageAsync = new DecodeImageAsync();
        decodeImageAsync.execute(pv);

    }

    public static void copyFile(File sourceFile, File destFile) throws IOException {
        if(!destFile.exists()) {
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        }
        finally {
            if(source != null) {
                source.close();
            }
            if(destination != null) {
                destination.close();
            }
        }
    }



    @Override
    public void onConnected(Bundle bundle) {
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient,
                REQUEST,
                this);  // LocationListener
    }

    @Override
    public void onConnectionSuspended(int i) {

    }



    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }


    @Override
    public void onLocationChanged(Location location) {

        Log.i("debug", "In");
//        updateSiteSpinner(location.getLatitude(), location.getLongitude());

        TextView lat = getLatitudeView();
        TextView longi = getLongitudeView();

        if (lat != null && longi != null) {
            if(mId == -1) {

                // Update location for creating form only
                lat.setText("" + location.getLatitude());
                longi.setText("" + location.getLongitude());

            }
        }

        this.location = location;

    }

    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGoogleApiClient.disconnect();
    }
}
