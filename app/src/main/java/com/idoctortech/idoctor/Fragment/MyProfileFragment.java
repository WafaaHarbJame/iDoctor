package com.idoctortech.idoctor.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.idoctortech.idoctor.ApiHandler.DataFeacher;
import com.idoctortech.idoctor.Classes.Constants;
import com.idoctortech.idoctor.Classes.GlobalData;
import com.idoctortech.idoctor.Classes.UtilityApp;
import com.idoctortech.idoctor.Dialogs.ConfirmDialog;
import com.idoctortech.idoctor.MainActivity;
import com.idoctortech.idoctor.Model.MemberModel;
import com.idoctortech.idoctor.R;


public class MyProfileFragment extends FragmentBase {


    Activity activity;

    MemberModel user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);
        activity = getActivity();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = getActivity();


        initData();
    }

    @Override
    public void onResume() {
        super.onResume();

        activity = getActivity();

    }

    private void initData() {

        user = UtilityApp.getUserData();

//        usernameLabelTxt.setText(user.getName());
//        nameTxt.setText(user.getName());
//        emailLabelTxt.setText(user.email);
//        emailTxt.setText(user.email);
//        mobileTxt.setText(user.mobile);
//        cityTxt.setText(user.cityName != null ? user.cityName : "-");
//
//        Glide.with(activity)
//                .asBitmap()
//                .load(user.logo)
//                .placeholder(R.drawable.error_logo)
//                .into(userImg);

    }

    public void signOut() {
        ConfirmDialog.Click click = new ConfirmDialog.Click() {
            @Override
            public void click() {
                new DataFeacher(activity, (obj, func, IsSuccess) -> {
                    if (func.equals(Constants.ERROR)) {
                        Toast(R.string.error_in_data);
                    } else if (func.equals(Constants.FAIL)) {
                        Toast(R.string.fail_to_get_data);
                    } else {
                        if (IsSuccess) {
                            UtilityApp.logOut();
                            GlobalData.Position = 0;

                            Intent intent = new Intent();
                            intent.setClass(activity, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        } else {
                            Toast(R.string.fail_to_sign_out);
                        }
                    }

                }).logOut(true);
            }
        };

        new ConfirmDialog(activity, R.string.want_to_signout, R.string.ok, R.string.close, click, null);

    }

//    @OnClick({R.id.editBtn, R.id.changePasswordLY, R.id.logoutLY})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.editBtn:
//                Intent editIntent = new Intent(activity, EditProfileActivity.class);
//                startActivity(editIntent);
//                break;
//            case R.id.changePasswordLY:
//                Intent changePassIntent = new Intent(activity, ChangePasswordActivity.class);
//                startActivity(changePassIntent);
//                break;
//            case R.id.logoutLY:
//                signOut();
//                break;
//        }
//    }

}
