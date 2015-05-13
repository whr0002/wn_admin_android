package com.reclamation.woodlands.woodlandsreclamation.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.reclamation.woodlands.woodlandsreclamation.Data.Forms.Form;
import com.reclamation.woodlands.woodlandsreclamation.R;

/**
 * Created by Jimmy on 5/13/2015.
 */
public abstract class FormDetailActivity extends ActionBarActivity implements MenuItem.OnMenuItemClickListener{

    protected Context mContext;
    protected ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);

        setLayout(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.detail_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;

            case R.id.save:
                addOrUpdate(getCurrentForm());
                break;

        }
        return true;
    }

    public abstract void setLayout(Activity a);

    public abstract void addOrUpdate(Form f);

    public abstract Form getCurrentForm();

}
