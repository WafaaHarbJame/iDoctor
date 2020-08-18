package com.idoctortech.idoctor.Activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.idoctortech.idoctor.Classes.Constants;
import com.idoctortech.idoctor.R;


public class PageFragmentActivity extends ActivityBase {

    FragmentManager fragmentManager;
    FragmentTransaction ft;
    Fragment newFragment;

    String type;

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_page_fragment);

        Bundle aBundle = getIntent().getExtras();
        if (aBundle != null) {
            type = aBundle.getString(Constants.KEY_FRAGMENT_TYPE);

        }

        initFragments();

        newFragment.setRetainInstance(true);
        fragmentManager = getSupportFragmentManager();
        ft = fragmentManager.beginTransaction();
        ft.replace(R.id.container, newFragment).commitNowAllowingStateLoss();

    }

    private void initFragments() {

        String title = "";
        switch (type) {
//            case Constants.FRAG_TICKETS:
//                newFragment = new SoonFragment();
//                title = getString(R.string.support);
//                break;
        }
        setTitle(title);
    }

}

