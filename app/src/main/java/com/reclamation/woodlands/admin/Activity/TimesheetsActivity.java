package com.reclamation.woodlands.admin.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.reclamation.woodlands.admin.Adapter.FormAdapter;
import com.reclamation.woodlands.admin.Adapter.TimesheetAdapter;
import com.reclamation.woodlands.admin.DB.Table_Timesheet.TimesheetDAO;
import com.reclamation.woodlands.admin.DB.Table_Timesheet.TimesheetForm;
import com.reclamation.woodlands.admin.Data.Forms.Form;
import com.reclamation.woodlands.admin.Data.Forms.Timesheet.TSUploader;
import com.reclamation.woodlands.admin.Data.Forms.Uploader;
import com.reclamation.woodlands.admin.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy on 6/19/2015.
 */
public class TimesheetsActivity extends FormActivity {

    private TimesheetDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mActionBar.setTitle("Timesheets");


    }

    @Override
    public List<Form> getAllForms() {
        dao = new TimesheetDAO(mContext);
        dao.open();
        List<Form> forms = new ArrayList<Form>();
        List<TimesheetForm> timesheetForms = dao.getAll();
        dao.close();

        for(TimesheetForm t : timesheetForms){
            forms.add(t);
        }
        return forms;
    }

    @Override
    public List<Form> getReadyForms() {

        dao = new TimesheetDAO(mContext);
        dao.open();
        List<Form> forms = new ArrayList<Form>();
        List<TimesheetForm> timesheetForms = dao.getAll();


        for(TimesheetForm t : timesheetForms){
            if(t.Message == null) {
                forms.add(t);
            }
        }

        dao.close();
        return forms;

    }

    @Override
    public void createForm() {
        Intent intent = new Intent(this, TimesheetDetailActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public synchronized void deleteForm(Form form) {
        TimesheetForm t = (TimesheetForm) form;
        dao.open();
        dao.delete(t);
        dao.close();

    }

    @Override
    public Uploader getUploader(List<Form> forms) {
        return new TSUploader(this, forms.size(), dao, null);
    }

    @Override
    public void updateForm(Form form) {
        TimesheetForm t = (TimesheetForm) form;
        dao.open();
        dao.update(t);
        dao.close();
    }

    @Override
    public FormAdapter getFormAdapter() {
        return new TimesheetAdapter(this, R.layout.in_gridview_formlist, getAllForms());
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Form f = formAdapter.getItem(i);
        Intent intent = new Intent(mContext, TimesheetDetailActivity.class);
        intent.putExtra("ID", f.ID);

        startActivityForResult(intent, 1);
    }
}
