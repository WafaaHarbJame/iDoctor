package com.idoctortech.idoctor.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.idoctortech.idoctor.Model.AllPlacesModel;
import com.idoctortech.idoctor.R;


public class MainScreenFragment extends FragmentBase {


    Activity activity;

    AllPlacesModel allPlacesModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_screen, container, false);
        activity = getActivity();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = getActivity();

//        rv.setHasFixedSize(true);
//        LinearLayoutManager llm = new LinearLayoutManager(activity);
//        llm.setOrientation(RecyclerView.VERTICAL);
//        rv.setLayoutManager(llm);
//
//        swipeDataContainer.setColorSchemeColors(ContextCompat.getColor(activity, R.color.colorPrimary));
//        swipeDataContainer.setOnRefreshListener(() -> {
//            getPlaces(false);
//        });

//        getPlaces(true);

    }

    @Override
    public void onResume() {
        super.onResume();

        activity = getActivity();

    }

    private void initAdapter() {

//              DoctorsAdapter adapter = new DoctorsAdapter(activity, rv, allPlacesModel.items, allPlacesModel, null);
//        rv.setAdapter(adapter);

    }

//    private void getPlaces(boolean loading) {
//
//        if (loading) {
//            loadingProgressLY.setVisibility(View.VISIBLE);
//            failGetDataLY.setVisibility(View.GONE);
//            dataLY.setVisibility(View.GONE);
//        }
//        failTxt.setText(getString(R.string.fail_to_get_data));
//
//        new DataFeacher(activity, (obj, func, IsSuccess) -> {
//            if (isVisible()) {
//                loadingProgressLY.setVisibility(View.GONE);
//                if (swipeDataContainer.isRefreshing())
//                    swipeDataContainer.setRefreshing(false);
//
//                if (func.equals(Constants.NO_CONNECTION)) {
//                    failGetDataLY.setVisibility(View.VISIBLE);
//                    failTxt.setText(getString(R.string.no_internet_connection));
//                } else {
//                    ResultAPIModel<AllPlacesModel> result = (ResultAPIModel<AllPlacesModel>) obj;
//                    if (IsSuccess) {
//                        allPlacesModel = result.data;
//                        dataLY.setVisibility(View.VISIBLE);
//                        if (allPlacesModel != null && allPlacesModel.items != null && allPlacesModel.items.size() > 0) {
//                            rv.setVisibility(View.VISIBLE);
//                            noDataLY.setVisibility(View.GONE);
//                            failGetDataLY.setVisibility(View.GONE);
//                            initAdapter();
//                        } else {
//                            noDataLY.setVisibility(View.VISIBLE);
//                            rv.setVisibility(View.GONE);
//                        }
//                    } else {
//                        failGetDataLY.setVisibility(View.VISIBLE);
//                        rv.setVisibility(View.GONE);
//                        noDataLY.setVisibility(View.GONE);
//                    }
//
//                }
//            }
//
//        }).getPlaces(null, 1, false);
//    }

//    @OnClick({R.id.refreshBtn, R.id.searchLY})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.refreshBtn:
//                getPlaces(true);
//                break;
//            case R.id.searchLY:
//                Intent intent = new Intent(activity, SearchResultActivity.class);
//                activity.startActivity(intent);
//                break;
//        }
//    }

}
