package com.idoctortech.idoctor.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.idoctortech.idoctor.R;


public class SoonFragment extends FragmentBase {

    final static int DElAY_TIME = 300;
    Activity activity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_soon, container, false);

        activity = getActivity();


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        activity = getActivity();

    }

    @Override
    public void onResume() {
        super.onResume();

        activity = getActivity();
    }


}
