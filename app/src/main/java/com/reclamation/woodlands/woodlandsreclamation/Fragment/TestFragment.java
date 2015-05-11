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

import com.google.gson.Gson;
import com.reclamation.woodlands.woodlandsreclamation.Adapter.Test2Adapter;
import com.reclamation.woodlands.woodlandsreclamation.Adapter.TestAdapter;
import com.reclamation.woodlands.woodlandsreclamation.DB.DR_DataSource;
import com.reclamation.woodlands.woodlandsreclamation.DB.DesktopReview;
import com.reclamation.woodlands.woodlandsreclamation.DB.RS_DataSource;
import com.reclamation.woodlands.woodlandsreclamation.DB.ReviewSite;
import com.reclamation.woodlands.woodlandsreclamation.R;

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
                Gson gson = new Gson();
                String json = new String("{\"FacilityType\":{\"FacilityTypeName\":\"Prepared Wellsite (Not Drilled)\"},\"Landscape\":{\"LandscapeName\":\"P\"},\"LSDQuarter\":{\"AspectName\":\"SW\"},\"RelevantCriteria\":{\"RelevantCriteriaName\":\"2010 Forested Criteria\"},\"Soil\":{\"SoilName\":\"P (j)\"},\"Vegetation\":{\"VegetationName\":\"P\"},\"DesktopReviewID\":1,\"SiteID\":\"01-03-52-20-W5M\",\"FacilityTypeName\":\"Prepared Wellsite (Not Drilled)\",\"Notes\":\"R.C. pending, no work.\",\"Client\":null,\"ApprovalStatus\":null,\"WorkPhase\":\"R07\",\"Occupant\":null,\"OccupantInfo\":null,\"Disposition\":\"MSL062337\",\"SoilClass\":\"Orthic Gray Luvisol\",\"SoilGroup\":null,\"ERCBLic\":\"0359447\",\"Width\":120,\"Length\":120,\"AreaHA\":1.44,\"AreaAC\":3.558384,\"Northing\":5922965,\"Easting\":509663,\"Latitude\":53.4556117,\"Longitude\":-116.8544743,\"Elevation\":999,\"AspectName\":\"SW\",\"LSD\":\"01-03-52-20-W5M\",\"SurveyDate\":\"\\/Date(1148450400000)\\/\",\"ConstructionDate\":\"\\/Date(1175493600000)\\/\",\"SpudDate\":null,\"AbandonmentDate\":null,\"ReclamationDate\":null,\"RelevantCriteriaName\":\"2010 Forested Criteria\",\"LandscapeName\":\"P\",\"SoilName\":\"P (j)\",\"VegetationName\":\"P\",\"RCADate\":\"\\/Date(1420786800000)\\/\",\"RCNumber\":null,\"DSAComments\":\"RCA submitted, Cert App in progress. Site assessed by WNI in 2014. Soils pass with vegetation override; justifications approved by Kevin Ball on December 19, 2014.\",\"Exemptions\":\"Site is expected to comply with all aspects of the 2010 vegetation criteria; soils are expected to comply but extenuating conditions with topsoil may be justified.\",\"AmendDate\":null,\"AmendDetail\":\"N/A\",\"RevegDate\":\"\\/Date(1308204000000)\\/\",\"RevegDetail\":\"Trees (Stock type: 410A 2+0; Seedlot: SD 29-48-17-5-2003 PL)\"}");
                DesktopReview desktopReview = new DesktopReview();
                desktopReview = gson.fromJson(json, DesktopReview.class);
                if(desktopReview != null) {
                    Log.i("debug", "Result: " + desktopReview.DSAComments);
                }else{
                    Log.i("debug", "failed ");
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
