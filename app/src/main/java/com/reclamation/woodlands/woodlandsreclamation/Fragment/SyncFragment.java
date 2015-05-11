package com.reclamation.woodlands.woodlandsreclamation.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.loopj.android.http.AsyncHttpClient;
import com.reclamation.woodlands.woodlandsreclamation.R;

/**
 * Created by Jimmy on 5/11/2015.
 */
public class SyncFragment  extends Fragment implements View.OnClickListener{

    private static AsyncHttpClient client = new AsyncHttpClient();
    private static final String url = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sync, container, false);

        Button syncBtn =  (Button) v.findViewById(R.id.sync);
        syncBtn.setOnClickListener(this);


        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sync:
                getData();
                break;
        }
    }

    private void getData(){
        Log.i("debug", "Clicked");
    }
}
