package com.reclamation.woodlands.woodlandsreclamation.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.reclamation.woodlands.woodlandsreclamation.DB.Table_Photo.Photo;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_SiteVisit.SiteVisitDAO;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_SiteVisit.SiteVisitForm;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_SiteVisit.SiteVisitProperties;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.DirectoryChooserDialog;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.Drawing.DrawingPopup;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.Form;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.SiteForm;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.SiteVisit.LayoutBuilder;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.SiteVisitValidator;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.Validator;
import com.reclamation.woodlands.woodlandsreclamation.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jimmy on 5/13/2015.
 */
public class SiteVisitDetailActivity extends FormDetailActivity implements View.OnClickListener{

    public static final int CAMERA_REQUEST_CODE = 10;
    private SiteVisitDAO svDao;
    private SiteForm sf;
    private TextView dateView, drawingDesc;
    private EditText recommendation;
    private ImageView drawingView;
    private Spinner facilityTypeSpinner, siteIdSpinner;

    private ImageButton drawerBtn, drawingCameraBtn, drawingAttcBtn, NLFImageBtn, APImageBtn, ADImageBtn;
    private LinearLayout NLFLayout,APLayout,ADLayout, landscapeLayout, soilLayout, vegeLayout;
    private List<LinearLayout> foItems;


    @Override
    public void setLayout(Activity a) {
        a.setContentView(R.layout.activity_form_detail);


        svDao = new SiteVisitDAO(this);

        foItems = new ArrayList<LinearLayout>();

        NLFLayout = (LinearLayout)findViewById(R.id.nlf_image_gallery);
        APLayout = (LinearLayout)findViewById(R.id.ap_image_gallery);
        ADLayout = (LinearLayout)findViewById(R.id.ad_image_gallery);
        landscapeLayout = (LinearLayout)findViewById(R.id.landscape);
        soilLayout = (LinearLayout)findViewById(R.id.soil);
        vegeLayout = (LinearLayout)findViewById(R.id.vegetation);


        addFOItems();

        dateView = (TextView)findViewById(R.id.dateView);
        recommendation = (EditText)findViewById(R.id.recommendation);
        drawingView = (ImageView) findViewById(R.id.drawing);
        drawingView.setOnClickListener(this);

        drawingDesc = (TextView) findViewById(R.id.drawing_desc);


        setSpinners(a);
        setButtons();

        mId = a.getIntent().getIntExtra("ID", -1);
        Log.i("debug", "Form id: " + mId);
        ActionBar actionBar = getSupportActionBar();
        if(mId != -1){
            actionBar.setTitle("View");
            setGallery();
            setForm(mId);

        }else{
            actionBar.setTitle("Create");
            setDate();
        }

    }

    private void setDate(){
        Date date = new Date();
        String cDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);


        dateView.setText(cDate);
    }

    private void setButtons(){
        drawerBtn = (ImageButton) findViewById(R.id.open_drawer);
        drawerBtn.setOnClickListener(this);

        drawingCameraBtn = (ImageButton) findViewById(R.id.drawing_camera);
        drawingCameraBtn.setOnClickListener(this);

        drawingAttcBtn = (ImageButton) findViewById(R.id.drawing_attachment);
        drawingAttcBtn.setOnClickListener(this);

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
                    drawingDesc.setText(curDrawing.description);
                    drawingDesc.setVisibility(View.VISIBLE);
                    setDrawing(curDrawing, drawingView);

                }
            }

        }
    }


    private void setSpinners(Activity a){
        facilityTypeSpinner = (Spinner) a.findViewById(R.id.facilityTypeSpinner);
        siteIdSpinner = (Spinner) a.findViewById(R.id.siteIdSpinner);

        setSiteIdSpinner(siteIdSpinner);
        setFTSpinner(facilityTypeSpinner);
    }

    private void setForm(int id) {
        svDao.open();
        sf = svDao.findFormById(id);
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






    @Override
    public SiteForm getCurrentForm() {

        // Binding form data

        SiteVisitForm sf  = new SiteVisitForm();

        sf.SiteID = siteIdSpinner.getSelectedItem().toString();
        sf.Date = dateView.getText().toString();

        sf.FacilityType = facilityTypeSpinner.getSelectedItem().toString();
        setFieldObservations(sf);
        sf.Recommendation = recommendation.getText().toString();

        sf.numberOfPhotos = allPhotos.size() - removedPhotos.size();

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
    public Validator getValidator() {
        return new SiteVisitValidator(mContext);
    }

    @Override
    public ImageView getDrawingView() {
        return drawingView;
    }

    @Override
    public String getFormType() {
        return SiteVisitProperties.FORM_TYPE;
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

            case R.id.drawing_camera:
                openCamera(SiteVisitProperties.FORM_TYPE,
                        SiteVisitProperties.PHOTO_DRAWING, null, DRAWING_CAMERA_REQUEST_CODE);
                break;

            case R.id.drawing_attachment:
                DirectoryChooserDialog directoryChooserDialog = new DirectoryChooserDialog(this, new DirectoryChooserDialog.ChosenDirectoryListener() {
                    @Override
                    public void onChosenDir(String chosenDir) {
                        if(curDrawing != null){
                            removedPhotos.add(curDrawing);
                        }

                        File file = new File(chosenDir);
                        if(file.exists()){
                            long fileSizeInBytes = file.length();
                            if(fileSizeInBytes > 10000000){
                                Toast.makeText(SiteVisitDetailActivity.this,
                                        "File size must be less than 10MB",
                                        Toast.LENGTH_SHORT).show();
                            }else{

                                String extension = chosenDir
                                        .substring(chosenDir
                                                .lastIndexOf("."));

                                String newPath = generateImagePath(getFormType(), extension);
                                File newFile = new File(newPath);
                                try {
                                    copyFile(file, newFile);

                                    Photo p = new Photo();
                                    p.path = newPath;
                                    p.formType = getFormType();
                                    p.classification = "Drawing";
                                    curDrawing = p;
                                    setDrawing(p, getDrawingView());
                                    newCreatedPhotos.add(p);
                                    allPhotos.add(p);

                                }catch (Exception e){
                                    Toast.makeText(mContext, "Fail to copy file", Toast.LENGTH_SHORT).show();
                                }


                            }
                        }


                    }
                });

                directoryChooserDialog.setNewFolderEnabled(false);
                directoryChooserDialog.chooseDirectory("");

                break;

            case R.id.drawing:
                DrawingPopup drawingPopup = new DrawingPopup(mContext, drawingView,drawingDesc, curDrawing, removedPhotos);
                drawingPopup.showPopup();
                break;

            case R.id.nlf_image_add:

               openCamera(SiteVisitProperties.FORM_TYPE, SiteVisitProperties.PHOTO_NLF, NLFLayout, CAMERA_REQUEST_CODE);

                break;

            case R.id.ap_image_add:

                openCamera(SiteVisitProperties.FORM_TYPE, SiteVisitProperties.PHOTO_AP, APLayout,CAMERA_REQUEST_CODE);
                break;

            case R.id.ad_image_add:

                openCamera(SiteVisitProperties.FORM_TYPE, SiteVisitProperties.PHOTO_AD, ADLayout,CAMERA_REQUEST_CODE);
                break;

        }
    }

    @Override
    public void addOrUpdate(Form form) {
        SiteVisitForm f = (SiteVisitForm) form;

        if(mId == -1){

            // Creating a form
            Log.i("debug", "creating");
            svDao.open();

            SiteVisitForm svTemp = svDao.create(f);

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
            svDao.update(f);
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

        clearTempImages(removedPhotos);

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
