package com.reclamation.woodlands.woodlandsreclamation.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.GridView;

import com.reclamation.woodlands.woodlandsreclamation.Adapter.FormAdapter;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.DeleteDialog;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.Form;
import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.SubmitDialog;
import com.reclamation.woodlands.woodlandsreclamation.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy on 5/12/2015.
 */
public abstract class FormActivity extends ActionBarActivity implements AdapterView.OnItemClickListener{

    protected Context mContext;
    protected ActionBar mActionBar;

    protected GridView mGridview;
    public FormAdapter formAdapter;
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

        mGridview.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE_MODAL);
        mGridview.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int position, long id, boolean checked) {

                actionMode.setTitle("Selected " + mGridview.getCheckedItemCount());

                View v = mGridview.getChildAt(position);
                CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkbox);
                checkBox.setChecked(checked);



            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {

                MenuInflater inflater = actionMode.getMenuInflater();
                inflater.inflate(R.menu.edit_mode_menu, menu);

                for(int i=0;i<formAdapter.getCount();i++){
                    View v = mGridview.getChildAt(i);
                    CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkbox);
                    checkBox.setVisibility(View.VISIBLE);


                }

                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                // Respond to clicks on the actions in the CAB
                switch (menuItem.getItemId()) {
                    case R.id.delete:
                        deleteSelectedItems();
                        actionMode.finish(); // Action picked, so close the CAB
                        return true;

                    case R.id.submit_selected:

                        submitSelectedItems();
                        actionMode.finish();

                        return true;


                    default:
                        return false;


                }
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

                formAdapter.notifyDataSetChanged();

                mGridview.clearChoices();

                for(int i=0;i<formAdapter.getCount();i++){
                    View v = mGridview.getChildAt(i);
                    CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkbox);
                    checkBox.setChecked(false);
                    checkBox.setVisibility(View.GONE);


                }
            }
        });





    }

    /**
     * Delete selected items in action mode
     */
    public void deleteSelectedItems(){
        SparseBooleanArray sparseBooleanArray = mGridview.getCheckedItemPositions();
        List<Form> forms = new ArrayList<Form>();

        for(int i=0;i<formAdapter.getCount();i++){
            if(sparseBooleanArray.get(i)){
                forms.add(formAdapter.getItem(i));
            }

        }

        DeleteDialog deleteDialog = new DeleteDialog(this, forms, "Do you want to delete selected forms?");
        deleteDialog.show();

    }

    /**
     * Submit selected items in action mode
     */
    public void submitSelectedItems(){
        SparseBooleanArray sparseBooleanArray = mGridview.getCheckedItemPositions();

        List<Form> forms = new ArrayList<Form>();

        for(int i=0;i<formAdapter.getCount();i++){
            if(sparseBooleanArray.get(i)){
                forms.add(formAdapter.getItem(i));
            }
        }

        SubmitDialog dialog = new SubmitDialog(this, forms, "Do you want to submit selected forms?");
        dialog.show();
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

            case R.id.submit_all:

                SubmitDialog submitDialog = new SubmitDialog(this, getAllForms(), "Do you want to submit all forms?");
                submitDialog.show();

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
