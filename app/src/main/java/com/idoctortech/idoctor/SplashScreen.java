package com.idoctortech.idoctor;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.idoctortech.idoctor.Activity.ActivityBase;
import com.idoctortech.idoctor.ApiHandler.DataFeacher;
import com.idoctortech.idoctor.Classes.Constants;
import com.idoctortech.idoctor.Classes.UtilityApp;
import com.idoctortech.idoctor.Dialogs.ConfirmDialog;
import com.idoctortech.idoctor.Model.MemberModel;
import com.idoctortech.idoctor.Model.ResultAPIModel;


public class SplashScreen extends ActivityBase {
    private static final int SPLASH_TIMER = 3000;

    boolean isNotify;
    private ImageView daleniLogo;

    String notifyType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        startSplash();

    }

    private void startSplash() {

        setContentView(R.layout.splash_screen);

        initData();

    }

    private void initData() {


//        new DataFeacher(getActiviy()).getCities(false);
//        new DataFeacher(getActiviy()).getPlaceOperation(false);
//        new DataFeacher(getActiviy()).getPlaceType(false);
//        new DataFeacher(getActiviy()).getTerms(false);

//        Log.i(getClass().getSimpleName(), "Log fcm token " + UtilityApp.getFCMToken());

        new Handler().postDelayed(() -> {

                    if (UtilityApp.isLogin()) {

//                        new DataFeacher(getActiviy(), (obj, func, IsSuccess) -> {
//                            if (func.equals(Constants.UNAUTHENTICATED)) {
//                                signOut();
//                            } else {
//                                ResultAPIModel<MemberModel> result = (ResultAPIModel<MemberModel>) obj;
//                                if (IsSuccess) {
//
//                                    MemberModel profileModel = result.data;
//                                    UtilityApp.setUserData(profileModel);
//
//                                }
//
//                                Intent intent = new Intent(getActiviy(), MainActivity.class);
//                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                                startActivity(intent);
//                                finish();
//                            }
//
//
//                        }).getMyProfile(false);

                    } else {

                        Intent intent = new Intent(getActiviy(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();


                    }

                }
                , SPLASH_TIMER);
    }

    public void signOut() {

        UtilityApp.logOut();

        Intent intent = new Intent(getActiviy(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }

    private void getWorngDialog() {
        ConfirmDialog.Click okClick = new ConfirmDialog.Click() {
            @Override
            public void click() {
                recreate();
            }
        };
        ConfirmDialog.Click cancelClick = new ConfirmDialog.Click() {
            @Override
            public void click() {
                finish();
            }
        };
        new ConfirmDialog(getActiviy(),
                R.string.fail_to_get_data, R.string.retry
                , R.string.cancel, okClick, cancelClick);
    }


}
