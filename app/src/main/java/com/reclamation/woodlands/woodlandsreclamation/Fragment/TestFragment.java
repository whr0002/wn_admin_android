package com.reclamation.woodlands.woodlandsreclamation.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.reclamation.woodlands.woodlandsreclamation.Adapter.Test2Adapter;
import com.reclamation.woodlands.woodlandsreclamation.Adapter.TestAdapter;
import com.reclamation.woodlands.woodlandsreclamation.DB.DR_DataSource;
import com.reclamation.woodlands.woodlandsreclamation.DB.DesktopReview;
import com.reclamation.woodlands.woodlandsreclamation.DB.RS_DataSource;
import com.reclamation.woodlands.woodlandsreclamation.DB.ReviewSite;
import com.reclamation.woodlands.woodlandsreclamation.DB.SiteVisitForm;
import com.reclamation.woodlands.woodlandsreclamation.R;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;

/**
 * Created by Jimmy on 5/8/2015.
 */
public class TestFragment  extends Fragment implements View.OnClickListener{
    private RS_DataSource dataSource;
    private DR_DataSource dr_dataSource;

    private  ListView listView;
    private  ListView listView1;

    private ArrayAdapter<DesktopReview> adapter_DR;
    private TestAdapter adapter;
    private Test2Adapter adapter1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_test, container, false);

        Button add = (Button) v.findViewById(R.id.add);
        Button delete = (Button) v.findViewById(R.id.delete);
        Button test = (Button) v.findViewById(R.id.test);
        Button add1 = (Button) v.findViewById(R.id.add1);
        Button delete1 = (Button) v.findViewById(R.id.delete1);


        add.setOnClickListener(this);
        delete.setOnClickListener(this);
        add1.setOnClickListener(this);
        delete1.setOnClickListener(this);
        test.setOnClickListener(this);

        dataSource = new RS_DataSource(this.getActivity());
        dataSource.open();

        dr_dataSource = new DR_DataSource(this.getActivity());
        dr_dataSource.open();


        listView = (ListView) v.findViewById(R.id.listview);
        listView1 = (ListView) v.findViewById(R.id.listview1);

        List<ReviewSite> reviewSites = dataSource.getAll();
        List<DesktopReview> desktopReviews = dr_dataSource.getAll();


        adapter = new TestAdapter(this.getActivity(),
                android.R.layout.simple_list_item_1, reviewSites);
        adapter1 = new Test2Adapter(this.getActivity(),
                android.R.layout.simple_list_item_1, desktopReviews);

        listView.setAdapter(adapter);
        listView1.setAdapter(adapter1);


        return v;
    }

    @Override
    public void onClick(View view) {
//        DesktopReview dr = null;
        ReviewSite dr = null;
        switch (view.getId()){
            case R.id.add:
                String[] drs = new String[] {"site-1", "site-2", "site-3"};
                int nextInt = new Random().nextInt(3);
//                DesktopReview temp = new DesktopReview();
                ReviewSite temp = new ReviewSite();
//                temp.SiteID = drs[nextInt];
                temp.ReviewSiteID = drs[nextInt];
                dr = dataSource.create(temp);
                adapter.add(dr);
                break;


            case R.id.delete:
                if(adapter.getCount()>0){
                    dr = adapter.getItem(0);
                    dataSource.delete(dr);
                    adapter.remove(dr);
                    reset();
                }

                break;

            case R.id.test:
                SiteVisitForm sv = new SiteVisitForm();
                for (Field field : sv.getClass().getDeclaredFields()) {
                    field.setAccessible(true); // You might want to set modifier to public first.
                    String name = field.getName();
                    String nameU = "COLUMN_"+name.toUpperCase();
//                    System.out.println("+ COLUMN_"+field.getName().toUpperCase() + " + \" text, \"");
//                    Log.i("debug", "sv.put(SiteVisitProperties.COLUMN_"
//                            + field.getName().toUpperCase() + ", o." + field.getName() + ");");
//                    sv.AD_Photo1 = cursor.getBlob(cursor.getColumnIndex(SiteVisitProperties.COLUMN_AD_PHOTO1));
                    Log.i("debug","sv." + name + " = cursor.getBlob(cursor.getColumnIndex(SiteVisitProperties."+ nameU + "));");
                }

                break;

            case R.id.add1:
                String[] sites1 = new String[]{"site-1", "site-2", "site-3"};
                int nextInt1 = new Random().nextInt(3);
                DesktopReview desktopReview1 = new DesktopReview();
                desktopReview1.SiteID = sites1[nextInt1];
                dr_dataSource.create(desktopReview1);
                adapter1.add(desktopReview1);

                break;

            case R.id.delete1:
                if(adapter1.getCount()>0){
                    DesktopReview temp1 = adapter1.getItem(0);
                    dr_dataSource.delete(temp1);
                    adapter1.remove(temp1);
                }
                break;
        }
        adapter.notifyDataSetChanged();

    }

    private void reset() {
        adapter1.clear();
        adapter1.addAll(dr_dataSource.getAll());
        adapter1.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        dataSource.open();
        super.onResume();
    }

    @Override
    public void onPause() {
        dataSource.close();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        dataSource.close();
        super.onDestroy();
    }
}
