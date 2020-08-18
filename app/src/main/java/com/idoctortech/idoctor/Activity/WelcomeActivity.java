package com.idoctortech.idoctor.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.franmontiel.localechanger.LocaleChanger;
import com.idoctortech.idoctor.Classes.Constants;
import com.idoctortech.idoctor.Classes.UtilityApp;
import com.idoctortech.idoctor.R;
import com.idoctortech.idoctor.Utils.LocaleUtils;
import com.idoctortech.idoctor.Utils.SharedPManger;
import com.idoctortech.idoctor.databinding.ActivityRegisterBinding;
import com.idoctortech.idoctor.databinding.ActivityWelcomBinding;

import java.util.Locale;

public class WelcomeActivity extends ActivityBase {
    private ActivityWelcomBinding binding;
    private String appLanguage;
    SharedPManger sharedPManger;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPManger = new SharedPManger(this);
         appLanguage = UtilityApp.getLanguage();



        if(appLanguage.equals("en")){
             binding.videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.envideo));

         }
         else {
             binding.videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.arv));

         }
        binding.videoView.start();


        binding.loginBtn.setOnClickListener(view -> {
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
        });

        binding.registration.setOnClickListener(view -> {
            Intent intent=new Intent(this,RegisterActivity.class);
            startActivity(intent);
        });

        binding.arBtn.setOnClickListener(view -> {
            appLanguage = Constants.Arabic;
            UtilityApp.setLanguage(appLanguage);
            LocaleUtils.setLocale(new Locale(appLanguage));
            recreate();



        });

        binding.enBtn.setOnClickListener(view -> {
            appLanguage = Constants.English;
            UtilityApp.setLanguage(appLanguage);
            LocaleUtils.setLocale(new Locale(appLanguage));
            recreate();


        });
    }
}