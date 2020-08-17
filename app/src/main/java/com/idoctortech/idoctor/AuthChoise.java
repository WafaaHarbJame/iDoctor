package com.idoctortech.idoctor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.idoctortech.idoctor.doctor.Objects;

import java.util.Locale;

public class AuthChoise extends AppCompatActivity {

    Button loginBtn,registration,langBtn;
    SharedPreferences sharedPref;
    String locale_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_choise);
        loginBtn = (Button) findViewById(R.id.loginBtn);



        Context context = getApplicationContext();
        SharedPreferences sharedPreferencesLanguage = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
         locale_value = sharedPreferencesLanguage.getString("locale","");
        sharedPref = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AuthChoise.this,Login.class);
                SharedPreferences sharedPreferencesLanguage = getApplicationContext().getSharedPreferences("auth", Context.MODE_PRIVATE);
                String locale_value = sharedPreferencesLanguage.getString("locale","ar");
                if(locale_value.equals("ar")){
                    Locale locale = new Locale("ar");
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                }else if(locale_value.equals("en")){
                    Locale locale = new Locale("en");
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                }
                startActivity(i);
            }
        });

        registration = (Button) findViewById(R.id.registration);
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AuthChoise.this,NewRegistration.class);
                SharedPreferences sharedPreferencesLanguage = getApplicationContext().getSharedPreferences("auth", Context.MODE_PRIVATE);
                String locale_value = sharedPreferencesLanguage.getString("locale","ar");
                assert locale_value != null;
                if(locale_value.equalsIgnoreCase("ar")){
                    Locale locale = new Locale("ar");
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                }else if(locale_value.equalsIgnoreCase("en")){
                    Locale locale = new Locale("en");
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                }
                startActivity(i);
            }
        });

//        setEn("en_");
        Button enBtn , arBtn;
        enBtn = (Button) findViewById(R.id.enBtn);
        arBtn = (Button) findViewById(R.id.arBtn);


        VideoView  videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.arv));
        if(locale_value.equalsIgnoreCase("ar")){
//            setEn("ar_");
            Locale locale = new Locale("ar");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());



            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.arv));

//            arBtn.setBackgroundResource(R.drawable.whitebutton);
//            arBtn.setTextColor(Color.parseColor("#08da5f"));

        }else if(locale_value.equalsIgnoreCase("en")) {
//            setEn("en_");
            Locale locale = new Locale("en");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());


            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.envideo));


//            enBtn.setBackgroundResource(R.drawable.whitebutton);
//            enBtn.setTextColor(Color.parseColor("#08da5f"));

        }else if(locale_value.length() == 0||locale_value.isEmpty()||locale_value.equalsIgnoreCase("")){
            setAr("ar_");
        }
            videoView.start();

        enBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEn("en_");
            }
        });
        arBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAr("ar_");

            }
        });

    }

    public void setAr(String lang) {

        Locale locale = new Locale("ar");


        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.putString("locale", "ar");
        editor.apply();
        editor.commit();


        Intent refresh = new Intent(this, AuthChoise.class);
        startActivity(refresh);
    }


    public void setEn(String lang) {

//        myLocale = new Locale(lang);
//        Resources res = getResources();
//        DisplayMetrics dm = res.getDisplayMetrics();
//        Configuration conf = res.getConfiguration();
//        conf.locale = myLocale;
//        res.updateConfiguration(conf, dm);

        Locale locale = new Locale("en");


        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("locale", "en");
        editor.apply();
        editor.commit();
        Intent refresh = new Intent(this, AuthChoise.class);
        startActivity(refresh);
    }

    public void setIntie() {

//        myLocale = new Locale(lang);
//        Resources res = getResources();
//        DisplayMetrics dm = res.getDisplayMetrics();
//        Configuration conf = res.getConfiguration();
//        conf.locale = myLocale;
//        res.updateConfiguration(conf, dm);

        Locale locale = new Locale("en");


        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("locale", "en");

        editor.commit();

//        Intent refresh = new Intent(this, AuthChoise.class);
//        startActivity(refresh);
    }

    @Override
    public void onBackPressed()
    {


    }






}
