package com.idoctortech.idoctor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.idoctortech.idoctor.doctor.P;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Profile extends Activity {

    WebView mWebView;
    ImageView backBtn;
    private static final int INPUT_FILE_REQUEST_CODE = 1;
    private static final int FILECHOOSER_RESULTCODE = 1;
    private static final String TAG = NewRegistration.class.getSimpleName();
    private ValueCallback<Uri> mUploadMessage;
    private Uri mCapturedImageURI = null;
    private ValueCallback<Uri[]> mFilePathCallback;
    private String mCameraPhotoPath;
    SharedPreferences sharedPref;
String Url = "";
TextView profile_text;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (requestCode != INPUT_FILE_REQUEST_CODE || mFilePathCallback == null) {
                super.onActivityResult(requestCode, resultCode, data);
                return;
            }
            Uri[] results = null;
            // Check that the response is a good one
            if (resultCode == Activity.RESULT_OK) {
                if (data == null) {
                    // If there is not data, then we may have taken a photo
                    if (mCameraPhotoPath != null) {
                        results = new Uri[]{Uri.parse(mCameraPhotoPath)};
                    }
                } else {
                    String dataString = data.getDataString();
                    if (dataString != null) {
                        results = new Uri[]{Uri.parse(dataString)};
                    }
                }
            }
            mFilePathCallback.onReceiveValue(results);
            mFilePathCallback = null;
        } else if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            if (requestCode != FILECHOOSER_RESULTCODE || mUploadMessage == null) {
                super.onActivityResult(requestCode, resultCode, data);
                return;
            }
            if (requestCode == FILECHOOSER_RESULTCODE) {
                if (null == this.mUploadMessage) {
                    return;
                }
                Uri result = null;
                try {
                    if (resultCode != RESULT_OK) {
                        result = null;
                    } else {
                        // retrieve from the private variable if the intent is null
                        result = data == null ? mCapturedImageURI : data.getData();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "activity :" + e,
                            Toast.LENGTH_LONG).show();
                }
                mUploadMessage.onReceiveValue(result);
                mUploadMessage = null;
            }
        }
        return;
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File imageFile = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        return imageFile;
    }


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.profile);

        Context context = getApplicationContext();

        SharedPreferences sharedPreferencesLanguage = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
        String locale_value = sharedPreferencesLanguage.getString("locale","");
        profile_text = (TextView) findViewById(R.id.profile_text);
        if(locale_value.equals("ar")){
            profile_text.setText("حسابي");
            Locale locale = new Locale("ar");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }else if(locale_value.equals("en")){
            profile_text.setText("Profile");
            Locale locale = new Locale("en");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }


        backBtn = (ImageView) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mWebView = (WebView) findViewById(R.id.web);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mWebView.getSettings().setAllowFileAccess(true);
        sharedPref = getSharedPreferences("auth", Context.MODE_PRIVATE);
        String id = sharedPref.getString("id","");
        Url = getResources().getString(R.string.url_profile) +id+"&hows=0";
        mWebView.loadUrl(Url);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url.contains(".html")){
                    Toast.makeText(Profile.this, getResources().getString(R.string.create_account_done), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Profile.this,Login.class);
                    startActivity(i);
                }
                view.loadUrl(url);
                return false;
            }
        });


        mWebView.setWebChromeClient(new WebChromeClient() {


            // For Android 5.0
            public boolean onShowFileChooser(WebView view, ValueCallback<Uri[]> filePath, WebChromeClient.FileChooserParams fileChooserParams) {
                // Double check that we don't have any existing callbacks
                if (mFilePathCallback != null) {
                    mFilePathCallback.onReceiveValue(null);
                }
                mFilePathCallback = filePath;
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    // Create the File where the photo should go
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                        takePictureIntent.putExtra("PhotoPath", mCameraPhotoPath);
                    } catch (IOException ex) {
                        // Error occurred while creating the File
                        Log.e(TAG, "Unable to create Image File", ex);
                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        mCameraPhotoPath = "file:" + photoFile.getAbsolutePath();
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                                Uri.fromFile(photoFile));
                    } else {
                        takePictureIntent = null;
                    }
                }
                Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
                contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
                contentSelectionIntent.setType("image/*");
                Intent[] intentArray;
                if (takePictureIntent != null) {
                    intentArray = new Intent[]{takePictureIntent};
                } else {
                    intentArray = new Intent[0];
                }
                Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
                chooserIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
                chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
                startActivityForResult(chooserIntent, INPUT_FILE_REQUEST_CODE);
                return true;
            }

            // openFileChooser for Android 3.0+
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
                mUploadMessage = uploadMsg;
                // Create AndroidExampleFolder at sdcard
                // Create AndroidExampleFolder at sdcard
                File imageStorageDir = new File(
                        Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES)
                        , "AndroidExampleFolder");
                if (!imageStorageDir.exists()) {
                    // Create AndroidExampleFolder at sdcard
                    imageStorageDir.mkdirs();
                }
                // Create camera captured image file path and name
                File file = new File(
                        imageStorageDir + File.separator + "IMG_"
                                + String.valueOf(System.currentTimeMillis())
                                + ".jpg");
                mCapturedImageURI = Uri.fromFile(file);
                // Camera capture image intent
                final Intent captureIntent = new Intent(
                        android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCapturedImageURI);
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                // Create file chooser intent
                Intent chooserIntent = Intent.createChooser(i, "Image Chooser");
                // Set camera intent to file chooser
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS
                        , new Parcelable[]{captureIntent});
                // On select image call onActivityResult method of activity
                startActivityForResult(chooserIntent, FILECHOOSER_RESULTCODE);
            }

            // openFileChooser for Android < 3.0
            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                openFileChooser(uploadMsg, "");
            }

            //openFileChooser for other Android versions
            public void openFileChooser(ValueCallback<Uri> uploadMsg,
                                        String acceptType,
                                        String capture) {
                openFileChooser(uploadMsg, acceptType);
            }

        });

        ImageButton logoutBtn;
        logoutBtn = (ImageButton) findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences =getSharedPreferences("auth",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                Intent i = new Intent(getApplicationContext(),Splach.class);
                startActivity(i);
            }
        });
        LinearLayout home = (LinearLayout) findViewById(R.id.home);
        LinearLayout apps = (LinearLayout) findViewById(R.id.apps);
        LinearLayout search = (LinearLayout) findViewById(R.id.search);
        LinearLayout notifications = (LinearLayout) findViewById(R.id.notification);
        LinearLayout profile = (LinearLayout) findViewById(R.id.profile);



        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Home.class);
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

        apps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Apps.class);
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
                Intent i = new Intent(getApplicationContext(),Notifications.class);
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
                Intent i = new Intent(getApplicationContext(),Search.class);
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
                Intent i = new Intent(getApplicationContext(), Profile.class);
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

//        @Override
//        public boolean onKeyDown(int keyCode, @NonNull KeyEvent event){
//            String webUrl = mWebView.getUrl();
//
//            if(event.getAction() == KeyEvent.ACTION_DOWN){
//                switch(keyCode){
//                    case KeyEvent.KEYCODE_BACK:
//                        if(mWebView.canGoBack()){
//                            if((webUrl.contains("url"))){
//                                new AlertDialog.Builder(this).setTitle("title")
//                                        .setIcon(R.mipmap.ic_launcher)
//                                        .setMessage("Are you sure you want to exit the app?")
//                                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialog, int which) {
//
//                                                Intent intent = new Intent(Intent.ACTION_MAIN);
//                                                intent.addCategory(Intent.CATEGORY_HOME);
//                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                                startActivity(intent);
//                                                finish();
//                                            }
//                                        }).setNegativeButton("no", null).show();
//                            }
//                            else
//                            if((webUrl.contains("url"))){
//                                Toast.makeText(this, "Press the X button.",
//                                        Toast.LENGTH_SHORT).show();
//                            }
//                            else
//                            if((webUrl.contains("url")||(webUrl.contains("file:///android_asset/error_page.html")||webUrl.contains("url")))) {
//                                new AlertDialog.Builder(this).setTitle("title")
//                                        .setIcon(R.mipmap.ic_launcher)
//                                        .setMessage("Are you sure you want to exit the app?")
//                                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialog, int which) {
//
//                                                Intent intent = new Intent(Intent.ACTION_MAIN);
//                                                intent.addCategory(Intent.CATEGORY_HOME);
//                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                                startActivity(intent);
//                                                finish();
//                                            }
//                                        }).setNegativeButton("no", null).show();
//                            }else {
//                                mWebView.goBack();
//                            }
//                        }else {
//                            new AlertDialog.Builder(this).setTitle("title")
//                                    .setIcon(R.mipmap.ic_launcher)
//                                    .setMessage("Are you sure you want to exit the app?")
//                                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//
//                                            Intent intent = new Intent(Intent.ACTION_MAIN);
//                                            intent.addCategory(Intent.CATEGORY_HOME);
//                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                            startActivity(intent);
//                                            finish();
//                                        }
//                                    }).setNegativeButton("no", null).show();
//                        }
//                        return true;
//                }
//            }
//            return super.onKeyDown(keyCode, event);
//        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}