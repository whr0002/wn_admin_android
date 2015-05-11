package com.reclamation.woodlands.woodlandsreclamation.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reclamation.woodlands.woodlandsreclamation.R;

/**
 * Created by Jimmy on 5/8/2015.
 */
public class LoginFragment extends Fragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        return rootView;
    }
}
