package com.reclamation.woodlands.woodlandsreclamation.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.reclamation.woodlands.woodlandsreclamation.DB.Table_Photo.Photo;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_Photo.PhotoDAO;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_SitePrep.SitePrepDAO;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_SitePrep.SitePrepForm;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_SitePrep.SitePrepProperties;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_SiteVisit.SiteVisitProperties;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.DecodeImageAsync;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.Drawing.DrawingPopup;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.Form;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.SiteForm;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.SiteVisit.LayoutBuilder;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.Validator;
import com.reclamation.woodlands.woodlandsreclamation.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy on 6/2/2015.
 */
public class SitePrepDetailActivity extends FormDetailActivity implements View.OnClickListener{

    private SitePrepDAO sitePrepDAO;

    private List<LinearLayout> foItems;
    private LinearLayout generalLayout, agreementLayout,
            oneCallLayout, handLayout, notiLayout, lineLayout, backFillLayout,ADLayout;
    private TextView dateView;
    private EditText comment, recommendation;

    private ImageView drawingView;
    private int mId;
    private Spinner facilityTypeSpinner, siteIdSpinner;

    private Button drawerBtn;
    private ImageButton ADImageBtn;




    @Override
    public void setLayout(Activity a) {
        a.setContentView(R.layout.activity_siteprep_detail);


        sitePrepDAO = new SitePrepDAO(this);
        photoDAO = new PhotoDAO(this);


        foItems = new ArrayList<LinearLayout>();


        ADLayout = (LinearLayout)findViewById(R.id.ad_image_gallery);
        generalLayout = (LinearLayout)findViewById(R.id.general_section);
        agreementLayout = (LinearLayout)findViewById(R.id.agreement_layout);
        oneCallLayout = (LinearLayout)findViewById(R.id.one_call_layout);
        handLayout = (LinearLayout)findViewById(R.id.hand_exposure_layout);
        notiLayout = (LinearLayout)findViewById(R.id.notification_layout);
        lineLayout = (LinearLayout)findViewById(R.id.line_layout);
        backFillLayout = (LinearLayout)findViewById(R.id.back_fill_layout);


        addFOItems();

        dateView = (TextView)findViewById(R.id.dateView);
        recommendation = (EditText)findViewById(R.id.recommendation);

        decodeImageAsync = new DecodeImageAsync();
        drawingView = (ImageView) findViewById(R.id.drawing);
        drawingView.setOnClickListener(this);


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
            setDate(dateView);
        }
    }

    @Override
    public void addOrUpdate(Form f) {

    }

    @Override
    public SiteForm getCurrentForm() {
        return null;
    }

    @Override
    public void onFinishWithoutSave() {

    }

    @Override
    public Validator getValidator() {
        return null;
    }

    @Override
    public ImageView getDrawingView() {
        return drawingView;
    }

    @Override
    public String getFormType() {
        return SitePrepProperties.FORM_TYPE;
    }

    @Override
    public TextView getLatitudeView() {
        return null;
    }

    @Override
    public TextView getLongitudeView() {
        return null;
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
                DrawingPopup drawingPopup = new DrawingPopup(mContext, (ImageView)view, null,curDrawing, removedPhotos);
                drawingPopup.showPopup();
                break;

            case R.id.ad_image_add:

                openCamera(SitePrepProperties.FORM_TYPE, SitePrepProperties.PHOTO_AD, ADLayout, CAMERA_REQUEST_CODE);
                break;

        }
    }


    private void addFOItems(){

        LayoutBuilder builder = new LayoutBuilder(mContext);

        String[] generalItems = mContext.getResources().getStringArray(R.array.fo_sp_general);
        String[] agreeItems = mContext.getResources().getStringArray(R.array.fo_sp_agree);
        String[] oneCallItems = mContext.getResources().getStringArray(R.array.fo_sp_onecall);
        String[] handItems = mContext.getResources().getStringArray(R.array.fo_sp_hand);
        String[] notiItems = mContext.getResources().getStringArray(R.array.fo_sp_notifications);
        String[] lineItems = mContext.getResources().getStringArray(R.array.fo_sp_line);
        String[] backItems = mContext.getResources().getStringArray(R.array.fo_sp_backfill);

        for(int i=0;i<generalItems.length;i++){
            foItems.add(builder.buildSitePrepLayout(generalLayout, generalItems[i]));
        }
        for(int i=0;i<agreeItems.length;i++){
            foItems.add(builder.buildSitePrepLayout(agreementLayout, agreeItems[i]));
        }
        for(int i=0;i<oneCallItems.length;i++){
            foItems.add(builder.buildSitePrepLayout(oneCallLayout, oneCallItems[i]));
        }
        for(int i=0;i<handItems.length;i++){
            foItems.add(builder.buildSitePrepLayout(handLayout, handItems[i]));
        }
        for(int i=0;i<notiItems.length;i++){
            foItems.add(builder.buildSitePrepLayout(notiLayout, notiItems[i]));
        }
        for(int i=0;i<lineItems.length;i++){
            foItems.add(builder.buildSitePrepLayout(lineLayout, lineItems[i]));
        }
        for(int i=0;i<backItems.length;i++){
            foItems.add(builder.buildSitePrepLayout(backFillLayout, backItems[i]));
        }





    }
    private void setSpinners(Activity a){
        facilityTypeSpinner = (Spinner) a.findViewById(R.id.facilityTypeSpinner);
        siteIdSpinner = (Spinner) a.findViewById(R.id.siteIdSpinner);

        setSiteIdSpinner(siteIdSpinner);
        setFTSpinner(facilityTypeSpinner);
    }

    private void setForm(int id) {
        sitePrepDAO.open();
        SitePrepForm sf = sitePrepDAO.findFormById(id);
        sitePrepDAO.close();

        if(sf != null){

            // Set form in View Mode
            dateView.setText(sf.Date);
            siteIdSpinner.setSelection(getSpinnerIndex(siteIdSpinner, sf.SiteID));
            facilityTypeSpinner.setSelection(getSpinnerIndex(facilityTypeSpinner, sf.FacilityType));
//            getFieldObservations((SitePrepForm)sf);
            recommendation.setText(sf.Recommendation);

        }

    }

    private void setButtons(){
        drawerBtn = (Button) findViewById(R.id.open_drawer);
        drawerBtn.setOnClickListener(this);


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

                if(photo.classification.equals(SiteVisitProperties.PHOTO_AD)){

                    // Photos belong to AD
//                    addGalleryItem(i, allPhotos.get(i), ADLayout, allPhotos);

                }else if(photo.classification.equals(SiteVisitProperties.PHOTO_DRAWING)){

                    // Drawing
                    curDrawing = allPhotos.get(i);
                    setDrawing(curDrawing, drawingView);

                }
            }
        }
    }




}
