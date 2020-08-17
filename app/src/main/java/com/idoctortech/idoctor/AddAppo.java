package com.idoctortech.idoctor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.Locale;

public class AddAppo extends AppCompatActivity {
    LinearLayout home , apps,search,notifications , profile;
    RelativeLayout doctors,centers,pharmces,nurses;
    SharedPreferences sharedPref;
    WebView web;
    Button go_to_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_appo);
        Context context = getApplicationContext();

        SharedPreferences sharedPreferencesLanguage = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
        String locale_value = sharedPreferencesLanguage.getString("locale","");
        if(locale_value.equals("ar")){
            Locale locale = new Locale("ar");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }else{
            Locale locale = new Locale("en");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }


        SharedPreferences sharedPref;

        sharedPref = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
        String id = sharedPref.getString("id","");
//        Toast.makeText(context, "id="+id, Toast.LENGTH_SHORT).show();
        home = (LinearLayout) findViewById(R.id.home);
        apps = (LinearLayout) findViewById(R.id.apps);
        search = (LinearLayout) findViewById(R.id.search);
        notifications = (LinearLayout) findViewById(R.id.notification);
        profile = (LinearLayout) findViewById(R.id.profile);
        doctors = (RelativeLayout) findViewById(R.id.doctors);
        centers = (RelativeLayout) findViewById(R.id.centers);
        pharmces = (RelativeLayout) findViewById(R.id.pharmces);
        nurses = (RelativeLayout) findViewById(R.id.nurses);
        web = (WebView) findViewById(R.id.web);
        go_to_back = (Button) findViewById(R.id.go_to_back);
        Log.d("tag","getObject_id"+getIntent().getStringExtra("getObject_id")+"&p="+id);

        web.loadUrl("https://idoctortech.com/api/android/add-appo-html.php?id="+getIntent().getStringExtra("getObject_id")+"&p="+id);

//

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddAppo.this,Home.class);

                //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                //finishAffinity()();
            }
        });

        apps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddAppo.this,Apps.class);
                //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                //finishAffinity()();
            }
        });

        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddAppo.this,Notifications.class);
                //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                //finishAffinity()();
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddAppo.this,Search.class);
                //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                //finishAffinity()();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddAppo.this,Profile.class);
                //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                //finishAffinity()();
            }
        });



        TextView name = (TextView) findViewById(R.id.name);
        name.setText(getIntent().getStringExtra("getObject_ar_name"));

        TextView spicalization = (TextView) findViewById(R.id.spicalization);
        spicalization.setText(getIntent().getStringExtra("getObject_ar_spic"));

        TextView location = (TextView) findViewById(R.id.location);
        location.setText(getIntent().getStringExtra("getObject_ar_address"));

        ImageView image = (ImageView) findViewById(R.id.image);
        Picasso.get().load(getIntent().getStringExtra("getObject_image")).into(image);
        go_to_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddAppo.this,Home.class);
                startActivity(i);
            }
        });
        WebViewClient mWebViewClient = new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
               if(url.contains("add-appo-o.php")){
                   go_to_back.setVisibility(View.VISIBLE);
               }
            };
        };
        web.setWebViewClient(mWebViewClient);
    }




}

