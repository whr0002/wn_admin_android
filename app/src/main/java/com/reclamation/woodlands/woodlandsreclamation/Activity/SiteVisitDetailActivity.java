package com.reclamation.woodlands.woodlandsreclamation.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.reclamation.woodlands.woodlandsreclamation.Adapter.ImageGridAdapter;
import com.reclamation.woodlands.woodlandsreclamation.DB.DaoFactory;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_FacilityType.FT_DataSource;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_FacilityType.FacilityType;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_Photo.Photo;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_Photo.PhotoDAO;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_ReviewSite.RS_DataSource;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_ReviewSite.ReviewSite;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_SiteVisit.SiteVisitDAO;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_SiteVisit.SiteVisitForm;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_SiteVisit.SiteVisitProperties;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.ImageProcessor;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.SiteForm;
import com.reclamation.woodlands.woodlandsreclamation.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jimmy on 5/13/2015.
 */
public class SiteVisitDetailActivity extends FormDetailActivity implements View.OnClickListener{

    public static final int CAMERA_REQUEST_CODE = 10;
    private SiteVisitDAO svDao;
    private SiteForm sf;

    private Photo curDrawing = null;


    private ImageView drawingView;

    private Spinner facilityTypeSpinner, siteIdSpinner;

    private Button drawerBtn;

    private int mId;

    private Photo oldDrawing = null;

    private PhotoDAO photoDAO;

    private ArrayList<String> drawingUrls;

    private ArrayList<String> tempImagePaths;

    private HashMap<String, String> changedPhotos;

    private GridView nlfGridView;

    private ArrayList<Photo> nlfPhotos;

    private ImageGridAdapter imageGridAdapter;

    private ImageButton NLFImageBtn;

    private String currentPath;

    private ImageProcessor imageProcessor;

    @Override
    public void setLayout(Activity a) {
        a.setContentView(R.layout.activity_form_detail);

        DaoFactory daoFactory = new DaoFactory(this);

        svDao = new SiteVisitDAO(this);

        photoDAO = new PhotoDAO(this);

        drawingUrls = new ArrayList<String>();

        tempImagePaths = new ArrayList<String>();

        changedPhotos = new HashMap<String, String>();

        imageProcessor = new ImageProcessor(null);

        setSpinners(a);

        setButtons();


        setImageViews();

        setGridViews();


        mId = a.getIntent().getIntExtra("ID", -1);
        if(mId != -1){
            setForm(mId);

        }

    }

    private void setButtons(){
        drawerBtn = (Button) findViewById(R.id.open_drawer);
        drawerBtn.setOnClickListener(this);

        NLFImageBtn = (ImageButton) findViewById(R.id.nlf_image_add);
        NLFImageBtn.setOnClickListener(this);

    }

    private void setGridViews(){
        nlfPhotos = new ArrayList<Photo>();
        imageGridAdapter = new ImageGridAdapter(this, R.layout.image_in_gridview, nlfPhotos);

        nlfGridView = (GridView) findViewById(R.id.nlf_grid);
        nlfGridView.setAdapter(imageGridAdapter);

    }

    private void setImageViews(){
        drawingView = (ImageView) findViewById(R.id.drawing);

    }

    private void setSpinners(Activity a){
        facilityTypeSpinner = (Spinner) a.findViewById(R.id.facilityTypeSpinner);
        siteIdSpinner = (Spinner) a.findViewById(R.id.siteIdSpinner);

        setSiteIdSpinner(siteIdSpinner);
        setFTSpinner(facilityTypeSpinner);
    }

    private void setForm(int id) {
        svDao.open();
        sf = (SiteForm) svDao.findFormById(id);
        svDao.close();

        if(sf != null){

            // Set form in View Mode
            siteIdSpinner.setSelection(getSpinnerIndex(siteIdSpinner, sf.SiteID));

            // Get all photos associated with this form
            photoDAO.open();
            ArrayList<Photo> photos = photoDAO.findPhotos(SiteVisitProperties.FORM_TYPE, sf.ID, null);
            photoDAO.close();

            // Set image views
            if(photos != null && photos.size() > 0){

                for(Photo p : photos){
                    if(p.classification.equalsIgnoreCase(SiteVisitProperties.PHOTO_DRAWING)){

                        // temporary save the path of old drawing
                        oldDrawing = new Photo();
                        oldDrawing.path = p.path;

                        // Set the drawing view
                        setDrawing(p);
                        curDrawing = p;

                    }
                }

            }
        }

    }

    private void setDrawing(Photo p){
        Bitmap bm = BitmapFactory.decodeFile(p.path);
        drawingView.setImageBitmap(bm);
        drawingView.setVisibility(View.VISIBLE);
    }

    private void setFTSpinner(Spinner spinner) {
        ArrayAdapter<CharSequence> adapter;
        FT_DataSource ftDao = new FT_DataSource(this);
        ftDao.open();

        List<FacilityType> facilityTypes = ftDao.getAll();
        ftDao.close();

        if(facilityTypes != null && facilityTypes.size() > 0){

            ArrayList<CharSequence> values = new ArrayList<CharSequence>();

            for(FacilityType ft : facilityTypes){
                values.add(ft.FacilityTypeName);

            }

            adapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_item, values);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

        }
    }
    private void setSiteIdSpinner(Spinner spinner) {

        ArrayAdapter<CharSequence> adapter;

        RS_DataSource rsDao = new RS_DataSource(this);

        rsDao.open();

        List<ReviewSite> rss = rsDao.getAll();

        rsDao.close();

        if(rss != null && rss.size() > 0){

            ArrayList<CharSequence> values = new ArrayList<CharSequence>();

            for(ReviewSite rs : rss){
                values.add(rs.ReviewSiteID);

            }

            adapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_item, values);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

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

    @Override
    public void addOrUpdate(SiteForm f) {


        if(mId == -1){

            // Creating a form
            Log.i("debug", "creating");
            svDao.open();
            SiteVisitForm svTemp = svDao.create((SiteVisitForm)f);
            svDao.close();

            if(drawingUrls != null && drawingUrls.size()>0) {
                int lastIndex = drawingUrls.size()-1;

                Photo p = new Photo();
                p.formType = "SiteVisit";
                p.formId = svTemp.ID;
                p.path = drawingUrls.get(lastIndex);
                p.classification = "Drawing";

                photoDAO.open();
                photoDAO.create(p);
                photoDAO.close();

                drawingUrls.remove(lastIndex);

            }

        }else{

            // Updating a form
            Log.i("debug", "updating");

            f.ID = mId;
            svDao.open();
            svDao.update((SiteVisitForm)f);
            svDao.close();

            photoDAO.open();
            if(drawingUrls.size()>0) {
                if (oldDrawing != null) {
                    // have photo initially

                    photoDAO.update(oldDrawing, curDrawing);
                    drawingUrls.remove(drawingUrls.size() -1);
                    drawingUrls.add(oldDrawing.path);

                } else {
                    // does not have photo initially

                    Photo p = new Photo();
                    p.formType = "SiteVisit";
                    p.formId = f.ID;
                    p.path = drawingUrls.get(drawingUrls.size() - 1);
                    p.classification = "Drawing";

                    photoDAO.create(p);

                    drawingUrls.remove(drawingUrls.size() -1);
                }
            }
            photoDAO.close();

        }

        clearTempImages();

    }

    private void clearTempImages(){
        if(drawingUrls != null && drawingUrls.size() > 0){

            for(String s : drawingUrls){
                File f = new File(s);

                if(f != null && f.exists()){
                    f.delete();
                }

            }

        }
    }

    @Override
    public SiteForm getCurrentForm() {
        SiteVisitForm svf = new SiteVisitForm();

        svf.SiteID = siteIdSpinner.getSelectedItem().toString();

        return svf;
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

                    if(curDrawing == null){
                        curDrawing = new Photo();
                    }

                    curDrawing.path = result;
                    drawingUrls.add(result);

                    Photo p = new Photo();
                    p.path = result;
                    setDrawing(p);
                }


            }
        }

        if(requestCode == CAMERA_REQUEST_CODE){
            // back from camera intent
            Log.i("debug", "back from camera");


            Photo photo = new Photo();

            if(currentPath != null) {

                imageProcessor.shrinkImage(currentPath);

                photo.path = currentPath;

                imageGridAdapter.add(photo);
                imageGridAdapter.notifyDataSetChanged();


            }
        }


    }

    public void openCamera(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(cameraIntent.resolveActivity(getPackageManager()) != null){
            // image capture app exists
            String path = generateImagePath(SiteVisitProperties.FORM_TYPE+"_NLF_");
            currentPath = path;

            if(path != null){

                File file = new File(path);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
            }else{
                Toast.makeText(this, "Could not generate image path", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public String generateImagePath(String prefix){

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

        String fullPath = dir + prefix + timeStamp + ".jpg";

        Log.i("debug", "Full image path: "+fullPath);

        return fullPath;


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.open_drawer:

                Intent intent = new Intent(mContext, DrawingActivity.class);
                if(curDrawing != null){
                    intent.putExtra("path", curDrawing.path);

                }
                startActivityForResult(intent, 1);

                break;

            case R.id.nlf_image_add:
                openCamera();

                break;

        }
    }
}
