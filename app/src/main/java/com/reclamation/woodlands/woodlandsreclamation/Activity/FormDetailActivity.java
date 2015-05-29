package com.reclamation.woodlands.woodlandsreclamation.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.Form;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.SiteForm;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.Validator;
import com.reclamation.woodlands.woodlandsreclamation.R;

/**
 * Created by Jimmy on 5/13/2015.
 */
public abstract class FormDetailActivity extends ActionBarActivity{


    protected Context mContext;
    protected ActionBar mActionBar;
    protected ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setLayout(this);

        mContext = this.getApplicationContext();
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);


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

    public abstract SiteForm getCurrentForm();

    public abstract void onFinishWithoutSave();

    public abstract Validator getValidator();


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
}
