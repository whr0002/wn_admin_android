package com.reclamation.woodlands.woodlandsreclamation.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.reclamation.woodlands.woodlandsreclamation.R;

/**
 * Created by Jimmy on 5/12/2015.
 */
public class FormList extends ActionBarActivity{

    private ActionBar mActionBar;
    private static final String DEBUG = "debug";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_formlist);

        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);

        int id = getIntent().getIntExtra("type", -1);
        String name = getIntent().getStringExtra("name");

        mActionBar.setTitle(name);


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

        }
        return super.onOptionsItemSelected(item);
    }


}
