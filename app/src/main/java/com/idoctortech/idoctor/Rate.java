package com.idoctortech.idoctor;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.File;
import java.util.Arrays;
import java.util.Locale;

import static android.content.ContentValues.TAG;

//import com.bivoiclient.utils.Constants;
//import com.daimajia.easing.linear.Linear;

public class Rate extends Activity {
    ImageView s1,s2,s3,s4,s5;
    int rateValue = 0;
    EditText rateText;
    Button rate;
    ImageView close;
    RequestQueue queue;
    String URL = "https://idoctortech.com/api/android/rate.php";
    //    ProgressBar progressDialog;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rate);

        close = (ImageView) findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Rate.this,Splach.class);
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
//                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
//                finishAffinity();
            }
        });

        s1 = (ImageView) findViewById(R.id.s1);
        s2 = (ImageView) findViewById(R.id.s2);
        s3 = (ImageView) findViewById(R.id.s3);
        s4 = (ImageView) findViewById(R.id.s4);
        s5 = (ImageView) findViewById(R.id.s5);
        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateValue = 1;
                s1.setImageResource(R.drawable.star);
                s2.setImageResource(R.drawable.unstar);
                s3.setImageResource(R.drawable.unstar);
                s4.setImageResource(R.drawable.unstar);
                s5.setImageResource(R.drawable.unstar);
            }
        });
        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateValue = 2;
                s1.setImageResource(R.drawable.star);
                s2.setImageResource(R.drawable.star);
                s3.setImageResource(R.drawable.unstar);
                s4.setImageResource(R.drawable.unstar);
                s5.setImageResource(R.drawable.unstar);
            }
        });
        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateValue = 3;
                s1.setImageResource(R.drawable.star);
                s2.setImageResource(R.drawable.star);
                s3.setImageResource(R.drawable.star);
                s4.setImageResource(R.drawable.unstar);
                s5.setImageResource(R.drawable.unstar);
            }
        });
        s4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateValue = 4;
                s1.setImageResource(R.drawable.star);
                s2.setImageResource(R.drawable.star);
                s3.setImageResource(R.drawable.star);
                s4.setImageResource(R.drawable.star);
                s5.setImageResource(R.drawable.unstar);
            }
        });
        s5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateValue = 5;
                s1.setImageResource(R.drawable.star);
                s2.setImageResource(R.drawable.star);
                s3.setImageResource(R.drawable.star);
                s4.setImageResource(R.drawable.star);
                s5.setImageResource(R.drawable.star);
            }
        });

        rateText = (EditText) findViewById(R.id.rateText);
        rate = (Button) findViewById(R.id.rate);
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queue = Volley.newRequestQueue(getApplicationContext());
                SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("auth", Context.MODE_PRIVATE);
                String id = sharedPref.getString("id","");
                URL = URL + "?rate="+rateValue+"&comment="+rateText.getText()+"&id="+id;
                StringRequest request = new StringRequest(Request.Method.GET,URL , new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        String string =response;

                        Intent i = new Intent(Rate.this,Home.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        finishAffinity();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error",error.toString());
                        Intent i = new Intent(Rate.this,Home.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        finishAffinity();
                    }
                });
                queue.add(request);
            }
        });


//        showDialog();
    }



}