package com.reclamation.woodlands.admin.Activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.reclamation.woodlands.admin.DB.Table_Timesheet.TimesheetDAO;
import com.reclamation.woodlands.admin.DB.Table_Timesheet.TimesheetForm;
import com.reclamation.woodlands.admin.Data.Forms.Form;
import com.reclamation.woodlands.admin.Data.Forms.Timesheet.TSValidator;
import com.reclamation.woodlands.admin.Data.Forms.Validator;
import com.reclamation.woodlands.admin.R;

import java.util.Calendar;

/**
 * Created by Jimmy on 6/19/2015.
 */
public class TimesheetDetailActivity extends FormDetailActivity {

    private static final int DATE_DIALOG_ID = 999;

    private TextView nameView, dateView, ppyView, ppView, projectIdView;
    private EditText identifierEdit, crewEdit, startKmEdit, endKmEdit, jobEdit, hoursEdit, bankEdit,overtimeEdit;
    private Spinner clientSpinner, projectSpinner, taskSpinner, vehicleSpinner, fieldSpinner, offSpinner;
    private CheckBox gpsCheckbox, pdCheckbox;
    private ImageButton dateButton;

    private TimesheetDAO tsDao;

    @Override
    public void setLayout(Activity a) {
        a.setContentView(R.layout.activity_timesheet);

        tsDao = new TimesheetDAO(this);

        // Find TextViews
        nameView = (TextView) findViewById(R.id.nameView);
        dateView = (TextView) findViewById(R.id.dateView);
        ppyView = (TextView) findViewById(R.id.ppyView);
        ppView = (TextView) findViewById(R.id.ppView);
        projectIdView = (TextView) findViewById(R.id.project_id);

        // Find EditTexts
        identifierEdit = (EditText) findViewById(R.id.identifier_field);
        crewEdit = (EditText) findViewById(R.id.crew_edittext);
        startKmEdit = (EditText) findViewById(R.id.startkm_edittext);
        endKmEdit = (EditText) findViewById(R.id.endkm_edittext);
        jobEdit = (EditText) findViewById(R.id.job_desc_edittext);
        hoursEdit = (EditText) findViewById(R.id.hours_edittext);
        bankEdit = (EditText) findViewById(R.id.bank_edittext);
        overtimeEdit = (EditText) findViewById(R.id.ot_edittext);

        // Find Spinners
        clientSpinner = (Spinner) findViewById(R.id.client_spinner);
        projectSpinner = (Spinner) findViewById(R.id.project_spinner);
        taskSpinner = (Spinner) findViewById(R.id.task_spinner);
        vehicleSpinner = (Spinner) findViewById(R.id.vehicle_spinner);
        fieldSpinner = (Spinner) findViewById(R.id.field_spinner);
        offSpinner = (Spinner) findViewById(R.id.off_spinner);

        // Find Checkboxes
        gpsCheckbox = (CheckBox) findViewById(R.id.gps_checkbox);
        pdCheckbox = (CheckBox) findViewById(R.id.pd_checkbox);

        // Find Buttons
        dateButton = (ImageButton) findViewById(R.id.date_picker);

        setSpinners();

        mId = a.getIntent().getIntExtra("ID", -1);

        if(mId == -1){
            mActionBar.setTitle("Create");
            setDate(dateView);
            setDatePicker();

        }else{
            mActionBar.setTitle("Edit");
            setForm(mId);
        }


    }

    private void setDatePicker() {
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_DIALOG_ID);
                final Calendar calendar = Calendar.getInstance();

                new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        dateView.setText(year+"-"+month+"-"+day);
                    }
                }, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
            }
        });
    }

    /**
     * Used in Edit Mode to set up the form
     */
    private void setForm(int id) {
        tsDao.open();
        TimesheetForm t = tsDao.findFormById(id);
        tsDao.close();

        if(t != null) {
            crewEdit.setText(t.Crew);
        }



    }

    /*
    * This method is used for getting dropdown items from DB
    * */
    private void setSpinners() {

    }

    @Override
    public void addOrUpdate(Form f) {
        TimesheetForm tForm = (TimesheetForm) f;

        if(mId == -1){
            // This is a new form
            tsDao.open();
            tsDao.create(tForm);
            tsDao.close();
        }else {
            // It's in Edit Mode, update the form
            tForm.ID = mId;
            tsDao.open();
            tsDao.update(tForm);
            tsDao.close();

        }
    }

    @Override
    public Form getCurrentForm() {
        TimesheetForm t = new TimesheetForm();
        t.Crew = crewEdit.getText().toString();
        return t;
    }

    @Override
    public void onFinishWithoutSave() {

    }

    @Override
    public Validator getValidator() {
        return new TSValidator(mContext);
    }

    @Override
    public ImageView getDrawingView() {
        return null;
    }

    @Override
    public String getFormType() {
        return null;
    }

    @Override
    public TextView getLatitudeView() {
        return null;
    }

    @Override
    public TextView getLongitudeView() {
        return null;
    }
}
