package com.reclamation.woodlands.woodlandsreclamation.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;

import com.reclamation.woodlands.woodlandsreclamation.Adapter.FormAdapter;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.Form;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.SubmitAllDialog;
import com.reclamation.woodlands.woodlandsreclamation.R;

import java.util.List;

/**
 * Created by Jimmy on 5/12/2015.
 */
public abstract class FormActivity extends ActionBarActivity implements AdapterView.OnItemClickListener{

    protected Context mContext;
    protected ActionBar mActionBar;
//    protected ListView mListview;
    protected GridView mGridview;
    protected FormAdapter formAdapter;
    protected ProgressDialog progressDialog;

    protected final String DEBUG = "debug";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_formlist);

        mContext = this;

        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);

//        mListview = (ListView) findViewById(R.id.listView);
        mGridview = (GridView)findViewById(R.id.gridview);
        formAdapter = getFormAdapter();
        mGridview.setAdapter(formAdapter);
        mGridview.setOnItemClickListener(this);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.simple_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case android.R.id.home:
                finish();
                break;

            case R.id.create:
//                SiteForm f = getTestData();
                createForm();
                break;

            case R.id.delete:

                deleteInView(0);

                break;

            case R.id.submit_all:
                Log.i("debug", "Submit all");
                SubmitAllDialog submitAllDialog = new SubmitAllDialog(this);
                submitAllDialog.show();

                break;

        }

        formAdapter.notifyDataSetChanged();

        return super.onOptionsItemSelected(item);
    }


    public void onDataSetChanged(){
        formAdapter = getFormAdapter();
        mGridview.setAdapter(formAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1){
            Log.i("debug", "back");
            onDataSetChanged();
        }
    }

    public void deleteInView(int position){
        progressDialog = ProgressDialog.show(this, "", "Deleting...", true);
        DeleteAsync deleteAsync = new DeleteAsync();
        deleteAsync.execute(position);

    }

    public abstract List<Form> getAllForms();


    public abstract void createForm();


    public abstract void deleteForm(Form form);


    public abstract void submitForms(List<Form> forms);

    public abstract void updateForm(Form form);

    public abstract FormAdapter getFormAdapter();

    class DeleteAsync extends AsyncTask<Integer,Object,Form> {


        @Override
        protected Form doInBackground(Integer[] positions) {

            if(formAdapter.getCount() > 0){
                Form temp = formAdapter.getItem(positions[0]);
                deleteForm(temp);
                return temp;

            }

            return null;
        }

        @Override
        protected void onPostExecute(Form form) {

            if(form != null) {
                formAdapter.remove(form);

            }
            progressDialog.dismiss();
        }
    }

}
