package com.reclamation.woodlands.woodlandsreclamation.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.reclamation.woodlands.woodlandsreclamation.DB.Table_FacilityType.FT_DataSource;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_FacilityType.FacilityType;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_Photo.Photo;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_Photo.PhotoDAO;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_ReviewSite.RS_DataSource;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_ReviewSite.ReviewSite;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_SiteVisit.SiteVisitDAO;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_SiteVisit.SiteVisitForm;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_SiteVisit.SiteVisitProperties;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.DecodeImageAsync;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.Drawing.DrawingPopup;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.PathView;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.ImagePopup;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.ImageProcessor;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.SiteForm;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.SiteVisit.LayoutBuilder;
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

    private Photo curDrawing;

    private TextView dateView;
    private String timeStamp;
    private EditText recommendation;

    private ImageView drawingView;
    private Spinner facilityTypeSpinner, siteIdSpinner;
    private Button drawerBtn;
//    private HorizontalScrollView NLFScrollView;

    private int mId;
    private PhotoDAO photoDAO;
    private HashMap<String, Boolean> photoMap;
    public ArrayList<Photo> removedPhotos,newCreatedPhotos, allPhotos;

    private ImageButton NLFImageBtn, APImageBtn, ADImageBtn;

    private Photo currentPhoto;
    private LinearLayout currentLayout,NLFLayout,APLayout,ADLayout, landscapeLayout, soilLayout, vegeLayout;
    private List<LinearLayout> foItems;
    private ImageProcessor imageProcessor;


    private DecodeImageAsync decodeImageAsync;

    private Context mContext;

    @Override
    public void setLayout(Activity a) {
        a.setContentView(R.layout.activity_form_detail);

        mContext = this;
        svDao = new SiteVisitDAO(this);
        photoDAO = new PhotoDAO(this);
        imageProcessor = new ImageProcessor(null);

        foItems = new ArrayList<LinearLayout>();

        NLFLayout = (LinearLayout)findViewById(R.id.nlf_image_gallery);
        APLayout = (LinearLayout)findViewById(R.id.ap_image_gallery);
        ADLayout = (LinearLayout)findViewById(R.id.ad_image_gallery);
        landscapeLayout = (LinearLayout)findViewById(R.id.landscape);
        soilLayout = (LinearLayout)findViewById(R.id.soil);
        vegeLayout = (LinearLayout)findViewById(R.id.vegetation);


        addFOItems();


//        NLFScrollView = (HorizontalScrollView) findViewById(R.id.nlf_scroll_view);

        dateView = (TextView)findViewById(R.id.dateView);
        recommendation = (EditText)findViewById(R.id.recommendation);

        allPhotos = new ArrayList<Photo>();
        removedPhotos = new ArrayList<Photo>();
        newCreatedPhotos = new ArrayList<Photo>();
        photoMap = new HashMap<String, Boolean>();

        decodeImageAsync = new DecodeImageAsync();
        drawingView = (ImageView) findViewById(R.id.drawing);
        drawingView.setOnClickListener(this);


        setSpinners(a);
        setButtons();

        mId = a.getIntent().getIntExtra("ID", -1);
        Log.i("debug", "Form id: " + mId);
        if(mId != -1){
            setGallery();
            setForm(mId);

        }else{
            setDate();
        }

    }

    private void setDate(){
        Date date = new Date();
        String cDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        timeStamp = new SimpleDateFormat("HH:mm:ss").format(date);

        dateView.setText(cDate);
    }

    private void setButtons(){
        drawerBtn = (Button) findViewById(R.id.open_drawer);
        drawerBtn.setOnClickListener(this);

        NLFImageBtn = (ImageButton) findViewById(R.id.nlf_image_add);
        NLFImageBtn.setOnClickListener(this);

        APImageBtn = (ImageButton) findViewById(R.id.ap_image_add);
        APImageBtn.setOnClickListener(this);

        ADImageBtn = (ImageButton) findViewById(R.id.ad_image_add);
        ADImageBtn.setOnClickListener(this);

    }


    private void setGallery(){


        photoDAO.open();

        allPhotos = photoDAO.findPhotos(SiteVisitProperties.FORM_TYPE, mId, null);

        Log.i("debug", "Size All Photo Get: " + allPhotos.size());

        photoDAO.close();

        if(allPhotos != null && allPhotos.size()>0){

            for(int i=0;i< allPhotos.size();i++){
                Photo photo = allPhotos.get(i);
                photoMap.put(photo.path, true);

                if(photo.classification.equals(SiteVisitProperties.PHOTO_NLF)) {

                    // Photos belong to NLF
                    addGalleryItem(i, allPhotos.get(i), NLFLayout, allPhotos);


                }else if(photo.classification.equals(SiteVisitProperties.PHOTO_AP)){

                    // Photos belong to AP
                    addGalleryItem(i, allPhotos.get(i), APLayout, allPhotos);

                }else if(photo.classification.equals(SiteVisitProperties.PHOTO_AD)){

                    // Photos belong to AD
                    addGalleryItem(i, allPhotos.get(i), ADLayout, allPhotos);

                }else if(photo.classification.equals(SiteVisitProperties.PHOTO_DRAWING)){

                    // Drawing
                    curDrawing = allPhotos.get(i);
                    setDrawing(curDrawing);

                }
            }

        }



    }

    private void addGalleryItem(int id, Photo photo, final LinearLayout galleryLayout, final ArrayList<Photo> photos){
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


    private void setImageViews(){


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
            dateView.setText(sf.Date);
            siteIdSpinner.setSelection(getSpinnerIndex(siteIdSpinner, sf.SiteID));
            facilityTypeSpinner.setSelection(getSpinnerIndex(facilityTypeSpinner, sf.FacilityType));
            getFieldObservations((SiteVisitForm)sf);
            recommendation.setText(((SiteVisitForm) sf).Recommendation);

        }

    }

    private void setDrawing(Photo p){

        drawingView.setVisibility(View.VISIBLE);

        PathView pv = new PathView();
        pv.imagePath = p.path;
        pv.imageView = drawingView;

        decodeImageAsync = new DecodeImageAsync();
        decodeImageAsync.execute(pv);

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



    private void clearTempImages(){

        if(removedPhotos != null && removedPhotos.size() > 0){
            photoDAO.open();

            for(Photo photo : removedPhotos){

                if(photo != null) {
                    File file = new File(photo.path);

                    if (file != null && file.exists()) {
                        file.delete();
                    }
                    photoDAO.delete(photo);
                }
            }

            photoDAO.close();

        }
    }

    @Override
    public SiteForm getCurrentForm() {

        // Binding form data

        SiteVisitForm sf  = new SiteVisitForm();

        sf.SiteID = siteIdSpinner.getSelectedItem().toString();
        sf.Date = dateView.getText().toString();
        sf.TimeStamp = timeStamp;
        sf.FacilityType = facilityTypeSpinner.getSelectedItem().toString();
        setFieldObservations(sf);
        sf.Recommendation = recommendation.getText().toString();

        return sf;
    }

    @Override
    public void onFinishWithoutSave() {
        // Clear new created images
//        clearTempImages();
        Log.i("debug", "Created size: " + newCreatedPhotos.size());
        if(newCreatedPhotos != null){
            for(Photo photo : newCreatedPhotos){
                File file = new File(photo.path);

                if(file != null && file.exists()){
                    file.delete();
                }
            }
        }

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
                    p.formType = "SiteVisit";
                    p.classification = "Drawing";
                    curDrawing = p;
                    setDrawing(p);
                    newCreatedPhotos.add(p);
                    allPhotos.add(p);


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

    public void openCamera(String classification, LinearLayout layout){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(cameraIntent.resolveActivity(getPackageManager()) != null){
            // image capture app exists
            String path = generateImagePath(SiteVisitProperties.FORM_TYPE+"_"+ classification +"_");

            // set up Photo properties and the layout to be added in
            currentPhoto = new Photo();
            currentPhoto.path = path;
            currentPhoto.classification = classification;
            currentPhoto.formType = SiteVisitProperties.FORM_TYPE;

            currentLayout = layout;


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

            case R.id.drawing:
                DrawingPopup drawingPopup = new DrawingPopup(mContext, view, curDrawing, removedPhotos);
                drawingPopup.showPopup();
                break;

            case R.id.nlf_image_add:

               openCamera(SiteVisitProperties.PHOTO_NLF, NLFLayout);

                break;

            case R.id.ap_image_add:

                openCamera(SiteVisitProperties.PHOTO_AP, APLayout);
                break;

            case R.id.ad_image_add:

                openCamera(SiteVisitProperties.PHOTO_AD, ADLayout);
                break;

        }
    }

    @Override
    public void addOrUpdate(SiteForm f) {


        if(mId == -1){

            // Creating a form
            Log.i("debug", "creating");
            svDao.open();

            SiteVisitForm svTemp = svDao.create((SiteVisitForm)f);
            svDao.close();

            photoDAO.open();

            if(allPhotos != null && allPhotos.size()>0){
                for(Photo photo : allPhotos){
                    photo.formId = svTemp.ID;

                    photoDAO.create(photo);

                }
            }

            photoDAO.close();

        }else{

            // Updating a form
            Log.i("debug", "updating");

            f.ID = mId;
            svDao.open();
            svDao.update((SiteVisitForm) f);
            svDao.close();

            photoDAO.open();

            // update all photos
            Log.i("debug", "Size All Photos: " + allPhotos.size());


            // update NLF photos
            for(Photo p : allPhotos){
                Log.i("debug", "Photo Path: " + p.path);
                Boolean hasPhoto = photoMap.get(p.path);

                if(hasPhoto != null){
                    // The photo exists in db
                    Log.i("debug", "Found Path ------- Updating");
                    photoDAO.update(p, p);

                }else{
                    // Photo does not exist in database, create a new photo
                    Log.i("debug", "Path Not Found ------- Creating");
                    p.formId = f.ID;
                    photoDAO.create(p);
                }

            }

            photoDAO.close();

        }

        clearTempImages();

    }

    private void setFieldObservations(SiteVisitForm sf){
        for(LinearLayout linearLayout : foItems){
            Spinner passFail = (Spinner) linearLayout.getChildAt(0);
            EditText description = (EditText) linearLayout.getChildAt(1);
            String fieldName = linearLayout.getTag().toString();

            String pf = passFail.getSelectedItem().toString();


            if(fieldName.equalsIgnoreCase("Refuse")){

                sf.RefusePF = pf.equals("Pass") ? 1 : (pf.equals("Fail") ? 2 : 0);
                sf.RefuseComment = description.getText().toString();

            }else if(fieldName.equalsIgnoreCase("Drainage")){

                sf.DrainagePF = pf.equals("Pass") ? 1 : (pf.equals("Fail") ? 2 : 0);
                sf.DrainageComment = description.getText().toString();

            }else if(fieldName.equalsIgnoreCase("Rock/Gravel Content")){

                sf.RockGravelPF = pf.equals("Pass") ? 1 : (pf.equals("Fail") ? 2 : 0);
                sf.RockGravelComment = description.getText().toString();

            }else if(fieldName.equalsIgnoreCase("Bare Ground")){

                sf.BareGroundPF = pf.equals("Pass") ? 1 : (pf.equals("Fail") ? 2 : 0);
                sf.BareGroundComment = description.getText().toString();

            }else if(fieldName.equalsIgnoreCase("Soil Stability")){

                sf.SoilStabilityPF = pf.equals("Pass") ? 1 : (pf.equals("Fail") ? 2 : 0);
                sf.SoilStabilityComment = description.getText().toString();

            }else if(fieldName.equalsIgnoreCase("Contours")){

                sf.ContoursPF = pf.equals("Pass") ? 1 : (pf.equals("Fail") ? 2 : 0);
                sf.ContoursComment = description.getText().toString();

            }else if(fieldName.equalsIgnoreCase("Coarse Woody Debris")){

                sf.CWDPF = pf.equals("Pass") ? 1 : (pf.equals("Fail") ? 2 : 0);
                sf.CWDComment = description.getText().toString();

            }else if(fieldName.equalsIgnoreCase("Erosion")){

                sf.ErosionPF = pf.equals("Pass") ? 1 : (pf.equals("Fail") ? 2 : 0);
                sf.ErosionComment = description.getText().toString();

            }else if(fieldName.equalsIgnoreCase("Soil Characteristics")){

                sf.SoilCharPF = pf.equals("Pass") ? 1 : (pf.equals("Fail") ? 2 : 0);
                sf.SoilCharComment = description.getText().toString();

            }else if(fieldName.equalsIgnoreCase("Topsoil Depth")){

                sf.TopsoilDepthPF = pf.equals("Pass") ? 1 : (pf.equals("Fail") ? 2 : 0);
                sf.TopsoilDepthComment = description.getText().toString();

            }else if(fieldName.equalsIgnoreCase("Rooting Restrictions")){

                sf.RootingPF = pf.equals("Pass") ? 1 : (pf.equals("Fail") ? 2 : 0);
                sf.RootingComment = description.getText().toString();

            }else if(fieldName.equalsIgnoreCase("Woody Stem Density")){

                sf.WSDPF = pf.equals("Pass") ? 1 : (pf.equals("Fail") ? 2 : 0);
                sf.WSDComment = description.getText().toString();

            }else if(fieldName.equalsIgnoreCase("Tree Health")){

                sf.TreeHealthPF = pf.equals("Pass") ? 1 : (pf.equals("Fail") ? 2 : 0);
                sf.TreeHealthComment = description.getText().toString();

            }else if(fieldName.equalsIgnoreCase("Weeds and Invasives")){

                sf.WeedsInvasivesPF = pf.equals("Pass") ? 1 : (pf.equals("Fail") ? 2 : 0);
                sf.WeedsInvasivesComment = description.getText().toString();

            }else if(fieldName.equalsIgnoreCase("Native Species Cover")){

                sf.NSCPF = pf.equals("Pass") ? 1 : (pf.equals("Fail") ? 2 : 0);
                sf.NSCComment = description.getText().toString();

            }else if(fieldName.equalsIgnoreCase("Litter/LFH")){

                sf.LitterPF = pf.equals("Pass") ? 1 : (pf.equals("Fail") ? 2 : 0);
                sf.LitterComment = description.getText().toString();

            }

        }
    }

    private void getFieldObservations(SiteVisitForm sf){

        for(LinearLayout linearLayout : foItems){

            Spinner passFail = (Spinner) linearLayout.getChildAt(0);
            EditText description = (EditText) linearLayout.getChildAt(1);
            String fieldName = linearLayout.getTag().toString();

            if(fieldName.equalsIgnoreCase("Refuse")){

                passFail.setSelection(sf.RefusePF);
                description.setText(sf.RefuseComment);

            }else if(fieldName.equalsIgnoreCase("Drainage")){

                passFail.setSelection(sf.DrainagePF);
                description.setText(sf.DrainageComment);

            }else if(fieldName.equalsIgnoreCase("Rock/Gravel Content")){

                passFail.setSelection(sf.RockGravelPF);
                description.setText(sf.RockGravelComment);

            }else if(fieldName.equalsIgnoreCase("Bare Ground")){

                passFail.setSelection(sf.BareGroundPF);
                description.setText(sf.BareGroundComment);

            }else if(fieldName.equalsIgnoreCase("Soil Stability")){

                passFail.setSelection(sf.SoilStabilityPF);
                description.setText(sf.SoilStabilityComment);

            }else if(fieldName.equalsIgnoreCase("Contours")){

                passFail.setSelection(sf.ContoursPF);
                description.setText(sf.ContoursComment);

            }else if(fieldName.equalsIgnoreCase("Coarse Woody Debris")){

                passFail.setSelection(sf.CWDPF);
                description.setText(sf.CWDComment);

            }else if(fieldName.equalsIgnoreCase("Erosion")){

                passFail.setSelection(sf.ErosionPF);
                description.setText(sf.ErosionComment);

            }else if(fieldName.equalsIgnoreCase("Soil Characteristics")){

                passFail.setSelection(sf.SoilCharPF);
                description.setText(sf.SoilCharComment);

            }else if(fieldName.equalsIgnoreCase("Topsoil Depth")){

                passFail.setSelection(sf.TopsoilDepthPF);
                description.setText(sf.TopsoilDepthComment);

            }else if(fieldName.equalsIgnoreCase("Rooting Restrictions")){

                passFail.setSelection(sf.RootingPF);
                description.setText(sf.RootingComment);

            }else if(fieldName.equalsIgnoreCase("Woody Stem Density")){

                passFail.setSelection(sf.WSDPF);
                description.setText(sf.WSDComment);

            }else if(fieldName.equalsIgnoreCase("Tree Health")){

                passFail.setSelection(sf.TreeHealthPF);
                description.setText(sf.TreeHealthComment);

            }else if(fieldName.equalsIgnoreCase("Weeds and Invasives")){

                passFail.setSelection(sf.WeedsInvasivesPF);
                description.setText(sf.WeedsInvasivesComment);

            }else if(fieldName.equalsIgnoreCase("Native Species Cover")){

                passFail.setSelection(sf.NSCPF);
                description.setText(sf.NSCComment);

            }else if(fieldName.equalsIgnoreCase("Litter/LFH")){

                passFail.setSelection(sf.LitterPF);
                description.setText(sf.LitterComment);

            }
        }
    }

    private void setFOItem(Spinner spinner, int number){

        spinner.setSelection(number);
//
//        if(number == 1){
//            spinner.setSelection(getSpinnerIndex(spinner, "Pass"));
//        }else if(number == 2){
//            spinner.setSelection(getSpinnerIndex(spinner, "Fail"));
//        }else{
//            spinner.setSelection(0);
//        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void addFOItems(){

        LayoutBuilder builder = new LayoutBuilder(mContext);

        String[] landscapeItems = mContext.getResources().getStringArray(R.array.fo_landscape_items);
        String[] soilItems = mContext.getResources().getStringArray(R.array.fo_soil_items);
        String[] vegeItems = mContext.getResources().getStringArray(R.array.fo_vegetation_items);

        for(int i=0;i<landscapeItems.length;i++){
            foItems.add(builder.buildLayout(landscapeLayout, landscapeItems[i]));
        }

        for(int i=0;i<soilItems.length;i++){
            foItems.add(builder.buildLayout(soilLayout, soilItems[i]));
        }

        for(int i=0;i<vegeItems.length;i++){
            foItems.add(builder.buildLayout(vegeLayout, vegeItems[i]));
        }


    }
}
