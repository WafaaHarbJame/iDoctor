package com.idoctortech.idoctor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.idoctortech.idoctor.doctor.Objects;

import java.util.Locale;

public class Splach extends AppCompatActivity {
    SharedPreferences sharedPref;

    Locale myLocale;
    String locale_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);
        Context context = getApplicationContext();

        sharedPref = context.getSharedPreferences("auth", Context.MODE_PRIVATE);


        new CountDownTimer(3000, 1000) {

            public void onTick(long millisUntilFinished) {

                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                Context context = getApplicationContext();
                sharedPref = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
                String id = sharedPref.getString("id","");
                String type = sharedPref.getString("type","");
                if(id!=null&&id.length()!=0&&id!=""){
                    if(type.equalsIgnoreCase("0")){
                        Intent i = new Intent(Splach.this, Home.class);
                        String locale_value = sharedPref.getString("locale","ar");
                        Locale locale = new Locale(locale_value);
                        Locale.setDefault(locale);
                        Configuration config = new Configuration();
                        config.locale = locale;
                        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

                        i.putExtra("orders","all");
                        startActivity(i);

                    }else{
                        Intent i = new Intent(Splach.this, Objects.class);
                        SharedPreferences sharedPreferencesLanguage = getApplicationContext().getSharedPreferences("auth", Context.MODE_PRIVATE);
                        String locale_value = sharedPref.getString("locale","ar");
                        Locale locale = new Locale(locale_value);
                        Locale.setDefault(locale);
                        Configuration config = new Configuration();
                        config.locale = locale;
                        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

                        i.putExtra("orders","all");
                        startActivity(i);
                    }
                }else{
                    Intent i = new Intent(Splach.this,AuthChoise.class);
                    String locale_value = sharedPref.getString("locale","ar");
                    Locale locale = new Locale(locale_value);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

                    i.putExtra("orders","all");
                    startActivity(i);
                }

//                exitAppCLICK();

            }

        }.start();
        Button enBtn , arBtn;
        enBtn = (Button) findViewById(R.id.enBtn);
        arBtn = (Button) findViewById(R.id.arBtn);
        enBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEn("en_");
                Context context = getApplicationContext();
                sharedPref = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
                String id = sharedPref.getString("id","");
                String type = sharedPref.getString("type","");
                if(id!=null&&id.length()!=0&&id!=""){
                    if(type.equalsIgnoreCase("0")){
                        Intent i = new Intent(Splach.this, Home.class);
                        SharedPreferences sharedPreferencesLanguage = getApplicationContext().getSharedPreferences("auth", Context.MODE_PRIVATE);
                         locale_value = sharedPreferencesLanguage.getString("locale","");

                        Locale locale = new Locale("ar");
                        Locale.setDefault(locale);
                        Configuration config = new Configuration();
                        config.locale = locale;
                        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

                        i.putExtra("orders","all");
                        startActivity(i);
                    }else{
                        Intent i = new Intent(Splach.this,Objects.class);
                        i.putExtra("orders","all");
                        SharedPreferences sharedPreferencesLanguage = getApplicationContext().getSharedPreferences("auth", Context.MODE_PRIVATE);
                        String locale_value = sharedPreferencesLanguage.getString("locale","");
                        startActivity(i);
                    }
                }else{
                    Intent i = new Intent(Splach.this,AuthChoise.class);
                    if(locale_value.equalsIgnoreCase("ar")){
//            setEn("ar_");
                        Locale locale = new Locale("ar");
                        Locale.setDefault(locale);
                        Configuration config = new Configuration();
                        config.locale = locale;
                        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());




//            arBtn.setBackgroundResource(R.drawable.whitebutton);
//            arBtn.setTextColor(Color.parseColor("#08da5f"));

                    }else if(locale_value.equalsIgnoreCase("en")) {
//            setEn("en_");
                        Locale locale = new Locale("en");
                        Locale.setDefault(locale);
                        Configuration config = new Configuration();
                        config.locale = locale;
                        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());




//            enBtn.setBackgroundResource(R.drawable.whitebutton);
//            enBtn.setTextColor(Color.parseColor("#08da5f"));

                    }else if(locale_value.length() == 0||locale_value.isEmpty()||locale_value.equalsIgnoreCase("")){
                        setAr("ar_");
                    }
                    startActivity(i);
                }
            }
        });

        arBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAr("ar_");
                Context context = getApplicationContext();
                sharedPref = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
                String id = sharedPref.getString("id","");
                String type = sharedPref.getString("type","");
                if(id!=null&&id.length()!=0&&id!=""){
                    if(type.equalsIgnoreCase("0")){
                        Intent i = new Intent(Splach.this, Home.class);
                        SharedPreferences sharedPreferencesLanguage = getApplicationContext().getSharedPreferences("auth", Context.MODE_PRIVATE);
                        String locale_value = sharedPreferencesLanguage.getString("locale","");

                        Locale locale = new Locale("ar");
                        Locale.setDefault(locale);
                        Configuration config = new Configuration();
                        config.locale = locale;
                        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

                        i.putExtra("orders","all");
                        startActivity(i);
                    }else{
                        Intent i = new Intent(Splach.this,Objects.class);
                        i.putExtra("orders","all");
                        SharedPreferences sharedPreferencesLanguage = getApplicationContext().getSharedPreferences("auth", Context.MODE_PRIVATE);
                        String locale_value = sharedPreferencesLanguage.getString("locale","");
                        startActivity(i);
                    }
                }else{
                    Intent i = new Intent(Splach.this,AuthChoise.class);
                    SharedPreferences sharedPreferencesLanguage = getApplicationContext().getSharedPreferences("auth", Context.MODE_PRIVATE);
                    String locale_value = sharedPreferencesLanguage.getString("locale","");
                    startActivity(i);
                }
            }
        });

    }
    public void exitAppCLICK () {

        System.exit(0);

    }

    public void setAr(String lang) {
        Locale locale = new Locale("ar");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("locale", "ar");
        editor.commit();
    }
    public void setEn(String lang) {
        Locale locale = new Locale("en");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("locale", "en");
        editor.commit();
    }

    @Override
    public void onBackPressed() {

    }
}
