package com.idoctortech.idoctor;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.idoctortech.idoctor.Activity.ActivityBase;
import com.idoctortech.idoctor.Activity.LoginActivity;
import com.idoctortech.idoctor.ApiHandler.DataFeacher;
import com.idoctortech.idoctor.Classes.Constants;
import com.idoctortech.idoctor.Classes.GlobalData;
import com.idoctortech.idoctor.Classes.UtilityApp;
import com.idoctortech.idoctor.Dialogs.ConfirmDialog;
import com.idoctortech.idoctor.Dialogs.InfoDialog;
import com.idoctortech.idoctor.Fragment.ChatsFragment;
import com.idoctortech.idoctor.Fragment.MainScreenFragment;
import com.idoctortech.idoctor.Fragment.MyProfileFragment;
import com.idoctortech.idoctor.Fragment.SoonFragment;
import com.idoctortech.idoctor.Model.MemberModel;
import com.idoctortech.idoctor.Model.ResultAPIModel;
import com.idoctortech.idoctor.Utils.ActivityHandler;
import com.idoctortech.idoctor.databinding.ActivityMainBinding;

public class MainActivity extends ActivityBase {

    private CharSequence mTitle;

    FragmentManager fragmentManager;
    FragmentTransaction ft;
    Fragment newFragment;
    FrameLayout container;
    TextView dividerLY;
    private ActivityMainBinding binding;


    final static int DElAY_TIME = 350;
    final static int REQUEST_BOOK_NAV = 10;
    private int nav_position = 0;

    MemberModel user;

    InfoDialog infoDialog;

//    private BroadcastReceiver myReceiver = new BroadcastReceiver() {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            updateChatCount();
//        }
//    };

//    List<NavModel> navModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        mTitle = getString(R.string.app_name);

//        isMainActivity = true;
//        setTitle("");


//        generateMemberData();

        onNavigationDrawerItemSelected(GlobalData.Position);

        binding.navLY.homeBtn.setOnClickListener(v -> {
            Toast("homeBtn");
        });

    }


    @Override
    protected void onResume() {
        super.onResume();

        if (UtilityApp.isLogin())
            user = UtilityApp.getUserData();

//        IntentFilter intentFilter = new IntentFilter(Constants.ACTION_REFRESH_CHAT_STATUS);
//        registerReceiver(myReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();

//        try {
//
//            unregisterReceiver(myReceiver);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }

    }

    public void onNavigationDrawerItemSelected(int position) {


        initGuestFragments(position);

        GlobalData.Position = nav_position;

        new Handler().postDelayed(() -> {
            if (newFragment != null) {
                try {
                    newFragment.setRetainInstance(true);
                    fragmentManager = getSupportFragmentManager();
                    ft = fragmentManager.beginTransaction();
//                    ft.setCustomAnimations(R.anim.fade_in,
//                            R.anim.fade_out);

                    ft.replace(R.id.container, newFragment).commitNowAllowingStateLoss();

                } catch (Exception e) {
                    e.printStackTrace();
                    recreate();
                }

            }
        }, DElAY_TIME);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        Fragment frg = getSupportFragmentManager().findFragmentById(R.id.);
//        if (frg != null) {
        if (newFragment != null)
            newFragment.onActivityResult(requestCode, resultCode, data);

    }

    private void initGuestFragments(int position) {
        switch (position) {

            case 0:
                newFragment = new MainScreenFragment();
                nav_position = 0;
                break;
            case 1:
                newFragment = new SoonFragment();
                nav_position = 1;
                break;
            case 2:

                new Handler().postDelayed(() -> {
                    Intent intent = new Intent(getActiviy(), LoginActivity.class);
                    startActivity(intent);
                }, 350);

                break;
            case 4:
                newFragment = new SoonFragment();
                nav_position = 4;
                break;
            case 5:

                String appPackageName = getPackageName();

//                String shareStr = getString(R.string.share_app_text) + "\n\n" +
//                        Constants.Paly_Link;

//                ActivityHandler.shareTextUrl(getActiviy(), shareStr, null, null);

                break;
            case 6:
                ActivityHandler.OpenGooglePlay(getActiviy());
//                newFragment = new SoonFragment();
//                nav_position = 5;
                break;
            default:
                initGuestFragments(0);
                break;
        }

//            restoreActionBar();

//        mTitle = navModels.get(position).getTitle();

    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {

//        if (GlobalData.LOGIN_TYPE == 1) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (GlobalData.Position == 0)
                onBackPressed();
//                exitByBackKey();
            else {
                onNavigationDrawerItemSelected(0);
//                selectMenu(0);
//                bottomNav.setSelectedItemId(R.id.mHomeBtn);

                return false;
            }//end else

        }
//        }
        return super.onKeyDown(keyCode, event);

    }


    public void signOut() {
        ConfirmDialog.Click click = new ConfirmDialog.Click() {
            @Override
            public void click() {
                new DataFeacher(getActiviy(), (obj, func, IsSuccess) -> {
                    if (func.equals(Constants.ERROR)) {
                        Toast(R.string.error_in_data);
                    } else if (func.equals(Constants.FAIL)) {
                        Toast(R.string.fail_to_get_data);
                    } else {
                        if (IsSuccess) {
                            UtilityApp.logOut();

                            Intent intent = new Intent();
                            intent.setClass(getActiviy(), LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        } else {
                            Toast(R.string.fail_to_sign_out);
                        }
                    }

                }).logOut(true);
            }
        };

        new ConfirmDialog(getActiviy(), R.string.want_to_signout, R.string.ok, R.string.close, click, null);


    }
}
