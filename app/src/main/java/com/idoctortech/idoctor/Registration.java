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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.github.angads25.filepicker.controller.DialogSelectionListener;
import com.github.angads25.filepicker.model.DialogConfigs;
import com.github.angads25.filepicker.model.DialogProperties;
import com.github.angads25.filepicker.view.FilePickerDialog;
import com.idoctortech.idoctor.doctor.Patient;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.Locale;

//import com.bivoiclient.utils.Constants;
//import com.daimajia.easing.linear.Linear;

public class Registration extends Activity {

    private WebView webView;
    private ValueCallback<Uri[]> mUploadMessage;
    private FilePickerDialog dialog;
    private String LOG_TAG = "DREG";
    private Uri[] results;


    LinearLayout home , apps,search,notifications , profile;
    RelativeLayout doctors,centers,pharmces,nurses;
    SharedPreferences sharedPref;
    WebView web;
    String Url = "https://idoctortech.com/api/android/create-profile.php";
    ImageView close;
    //    ProgressBar progressDialog;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

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

        

        close = (ImageView) findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),AuthChoise.class);
                startActivity(i);
            }
        });
        webView = findViewById(R.id.web);
        WebSettings webSettings = webView.getSettings();
        webSettings.setAppCacheEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAllowFileAccess(true);

        webView.setWebViewClient(new PQClient());
        webView.setWebChromeClient(new PQChromeClient());
        if (Build.VERSION.SDK_INT >= 19) {
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }








        home = (LinearLayout) findViewById(R.id.home);
        apps = (LinearLayout) findViewById(R.id.apps);
        search = (LinearLayout) findViewById(R.id.search);
        notifications = (LinearLayout) findViewById(R.id.notification);
        profile = (LinearLayout) findViewById(R.id.profile);




        apps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Registration.this, Apps.class);
                SharedPreferences sharedPreferencesLanguage = getApplicationContext().getSharedPreferences("auth", Context.MODE_PRIVATE);
                String locale_value = sharedPreferencesLanguage.getString("locale","");
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

        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Registration.this, Notifications.class);
                SharedPreferences sharedPreferencesLanguage = getApplicationContext().getSharedPreferences("auth", Context.MODE_PRIVATE);
                String locale_value = sharedPreferencesLanguage.getString("locale","");
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


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Registration.this, Search.class);
                SharedPreferences sharedPreferencesLanguage = getApplicationContext().getSharedPreferences("auth", Context.MODE_PRIVATE);
                String locale_value = sharedPreferencesLanguage.getString("locale","");
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

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Registration.this, Patient.class);
                SharedPreferences sharedPreferencesLanguage = getApplicationContext().getSharedPreferences("auth", Context.MODE_PRIVATE);
                String locale_value = sharedPreferencesLanguage.getString("locale","");
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

//        sharedPref = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
//        String id = sharedPref.getString("id","");
//        Url = Url +id;


        webView.loadUrl(Url);

    }

    private void openFileSelectionDialog() {

        if (null != dialog && dialog.isShowing()) {
            dialog.dismiss();
        }

        //Create a DialogProperties object.
        final DialogProperties properties = new DialogProperties();

        //Instantiate FilePickerDialog with Context and DialogProperties.
        dialog = new FilePickerDialog(Registration.this, properties);
        dialog.setTitle("اختر من ");
        dialog.setPositiveBtnName("اختيار");
        dialog.setNegativeBtnName("الغاء");
        properties.selection_mode = DialogConfigs.MULTI_MODE; // for multiple files
//        properties.selection_mode = DialogConfigs.SINGLE_MODE; // for single file
        properties.selection_type = DialogConfigs.FILE_SELECT;

        //Method handle selected files.
        dialog.setDialogSelectionListener(new DialogSelectionListener() {
            @Override
            public void onSelectedFilePaths(String[] files) {
                results = new Uri[files.length];
                for (int i = 0; i < files.length; i++) {
                    String filePath = new File(files[i]).getAbsolutePath();
                    if (!filePath.startsWith("file://")) {
                        filePath = "file://" + filePath;
                    }
                    results[i] = Uri.parse(filePath);
                    Log.d(LOG_TAG, "file path: " + filePath);
                    Log.d(LOG_TAG, "file uri: " + String.valueOf(results[i]));
                }
                mUploadMessage.onReceiveValue(results);
                mUploadMessage = null;
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                if (null != mUploadMessage) {
                    if (null != results && results.length >= 1) {
                        mUploadMessage.onReceiveValue(results);
                    } else {
                        mUploadMessage.onReceiveValue(null);
                    }
                }
                mUploadMessage = null;
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if (null != mUploadMessage) {
                    if (null != results && results.length >= 1) {
                        mUploadMessage.onReceiveValue(results);
                    } else {
                        mUploadMessage.onReceiveValue(null);
                    }
                }
                mUploadMessage = null;
            }
        });

        dialog.show();

    }

    public class PQChromeClient extends WebChromeClient {

        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
            // Double check that we don't have any existing callbacks
            if (mUploadMessage != null) {
                mUploadMessage.onReceiveValue(null);
            }
            mUploadMessage = filePathCallback;

            openFileSelectionDialog();

            return true;
        }

    }

    //Add this method to show Dialog when the required permission has been granted to the app.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case FilePickerDialog.EXTERNAL_READ_PERMISSION_GRANT: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (dialog != null) {
                        openFileSelectionDialog();
                    }
                } else {
                    //Permission has not been granted. Notify the user.
                    Toast.makeText(Registration.this, "Permission is Required for getting list of files", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)

        return super.onKeyDown(keyCode, event);
    }


    public class PQClient extends WebViewClient {
//        ProgressBar progressDialog;

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // If url contains mailto link then open Mail Intent

            if(url.contains(".html")){
                Toast.makeText(Registration.this, "تم انشاء الحساب", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Registration.this,Login.class);
                startActivity(i);
            }

            if (url.contains("mailto:")) {

                // Could be cleverer and use a regex
                //Open links in new browser
                view.getContext().startActivity(
                        new Intent(Intent.ACTION_VIEW, Uri.parse(url)));

                // Here we can open new activity

                return true;

            } else {
                // Stay within this webview and load url
//                Toast.makeText(Registration.this, "url:"+url, Toast.LENGTH_SHORT).show();
                view.loadUrl(url);
                return true;
            }


        }

        // Show loader on url load
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
//            if(url.contains(".html")){
//                Toast.makeText(Registration.this, "تم انشاء الحساب", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(Registration.this,Login.class);
//                startActivity(i);
//            }
            // Then show progress  Dialog
            // in standard case YourActivity.this
//            if (progressDialog == null) {
//                progressDialog = findViewById(R.id.progressBar);
//                progressDialog.setVisibility(View.VISIBLE);
//            }
        }

        // Called when all page resources loaded
        public void onPageFinished(WebView view, String url) {
//            if(url.contains(".html")){
//                Toast.makeText(Registration.this, "تم انشاء الحساب", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(Registration.this,Login.class);
//                startActivity(i);
//            }


            try {
                // Close progressDialog
                // progressDialog.setVisibility(View.GONE);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

}