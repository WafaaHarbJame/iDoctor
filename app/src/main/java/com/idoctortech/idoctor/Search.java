package com.idoctortech.idoctor;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Search extends AppCompatActivity {
    LinearLayout home, apps, search, notifications, profile;
    RelativeLayout doctors;
    String url = "https://idoctortech.com/api/android/search_object.php", r;
    ListView objectsList;
    ArrayList<Object> objectArrayList = new ArrayList<Object>();
    Search.ObjectAdapter objectAdapter;
    String sub_path = "https://idoctortech.com/en/";
    EditText EditSearch;
    String locale_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Context context = getApplicationContext();

        SharedPreferences sharedPreferencesLanguage = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
         locale_value = sharedPreferencesLanguage.getString("locale","");

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



        objectsList = (ListView) findViewById(R.id.objects_list);
        objectsList.setVisibility(View.GONE);
        home = (LinearLayout) findViewById(R.id.home);
        apps = (LinearLayout) findViewById(R.id.apps);
        search = (LinearLayout) findViewById(R.id.search);
        notifications = (LinearLayout) findViewById(R.id.notification);
        profile = (LinearLayout) findViewById(R.id.profile);
//        doctors = (RelativeLayout) findViewById(R.id.doctors);

        EditSearch = (EditText) findViewById(R.id.EditSearch);
        EditSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Search();
                    return true;
                }
                return false;
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Search.this, Home.class);
                startActivity(i);
            }
        });

//        doctors.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Search.this,Objects.class);
//                startActivity(i);
//            }
//        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Home.class);

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
                }                startActivity(i);
                //finishAffinity()();
            }
        });

        apps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Apps.class);
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
                }                startActivity(i);
                //finishAffinity()();
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
                }                startActivity(i);
                //finishAffinity()();
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
                }                startActivity(i);
                //finishAffinity()();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Profile.class);
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
                //finishAffinity()();
            }
        });


    }

    public void Search() {
        objectArrayList.clear();
        final String q = EditSearch.getText().toString();
//        Toast.makeText(this, q+"", Toast.LENGTH_SHORT).show();
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        }
//        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//        String longitude = location.getLongitude()+"";
//        String latitude = location.getLatitude()+"";

        final String longitude = "0",latitude = "0";
        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
        final String token = sharedPref.getString("token", "");


//        url = url+getIntent().getStringExtra("token");
        RequestQueue queue = Volley.newRequestQueue(this);//
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url+"?token="+token+"&lat="+latitude+"&long="+longitude+"&query="+q, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int count = 0;
                Log.d("search_request",""+url+"?token="+token+"&lat="+latitude+"&long="+longitude+"&query="+q);
                while (count < response.length()) {
                    objectsList.setVisibility(View.VISIBLE);
                    try {
                        Object o = new Object();
                        JSONObject jsonObject = response.getJSONObject(count);
                        o.setObject_id(jsonObject.getInt("object_id"));
                        o.setObject_type(jsonObject.getString("object_type"));
                        o.setObject_ar_name(jsonObject.getString("object_ar_name"));
                        o.setObject_en_name(jsonObject.getString("object_en_name"));
                        o.setObject_ar_desc(jsonObject.getString("object_ar_desc"));
                        o.setObject_en_desc(jsonObject.getString("object_en_desc"));
                        o.setObject_city_id(jsonObject.getString("object_ar_name"));
                        o.setObject_ar_address(jsonObject.getString("object_ar_address"));
                        o.setObject_en_address(jsonObject.getString("object_en_address"));
                        o.setObject_owner_id(jsonObject.getString("object_owner_id"));
                        o.setObject_image(jsonObject.getString("object_image"));
                        o.setObject_ar_spic(jsonObject.getString("object_ar_spic"));
                        o.setObject_en_spic(jsonObject.getString("object_en_spic"));
                        o.setObject_lat(jsonObject.getString("object_lat"));
                        o.setObject_long(jsonObject.getString("object_long"));
//                        o.setDistance(jsonObject.getString("distance").substring(0,4));
                        o.setDistance("0");
                        objectArrayList.add(o);
                        count++;

                    } catch (JSONException e) {
                        Toast.makeText(Search.this, "Error: " + e, Toast.LENGTH_SHORT).show();

                    }
                    objectAdapter = new Search.ObjectAdapter(objectArrayList);
                    objectAdapter.notifyDataSetChanged();
                    objectsList.setAdapter(objectAdapter);

                }
                objectsList.setAdapter(objectAdapter);
                if (count == 0) {
//                    no.setVisibility(View.VISIBLE);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        queue.add(jsonArrayRequest);

    }

    private class ObjectAdapter extends BaseAdapter {
        ArrayList<Object> newArrayList ;

        public  ObjectAdapter(ArrayList<Object> objects ){
            this.newArrayList=objects;
        }

        @Override
        public int getCount() {
            return newArrayList.size();
        }

        @Override
        public String getItem(int position) {
            return null ;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater mInflater = LayoutInflater.from(getApplicationContext());
            View myView=  mInflater.inflate(R.layout.object_item, null);
            final Object n=newArrayList.get(position);

            TextView name = (TextView) myView.findViewById(R.id.name);
            TextView location = (TextView) myView.findViewById(R.id.location);
            TextView spicalization = (TextView) myView.findViewById(R.id.spicalization);

            if(locale_value.equals("ar")){
                name.setText(n.getObject_ar_name()+"");
                location.setText(n.getObject_ar_address()+"");
                spicalization.setText(n.getObject_ar_spic()+"");
            }else{
                name.setText(n.getObject_en_name()+"");
                location.setText(n.getObject_en_address()+"");
                spicalization.setText(n.getObject_en_spic()+"");
                Log.d("enName",n.getObject_en_name()+"");
            }
            RoundedImageView image = (RoundedImageView) myView.findViewById(R.id.image);
            Picasso.get().load(n.getObject_image()+"").into(image);

            myView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(),ViewObject.class);
                    i.putExtra("getObject_id",n.getObject_id()+"");
                    i.putExtra("getObject_ar_name",n.getObject_ar_name()+"");

                    if(locale_value.equals("ar")){
                        i.putExtra("getObject_ar_name",n.getObject_ar_name()+"");

                        i.putExtra("getObject_ar_address",n.getObject_ar_address()+"");
                        i.putExtra("getObject_ar_spic",n.getObject_ar_spic()+"");

                    }else if(locale_value.equals("en")){
                        i.putExtra("getObject_ar_name",n.getObject_en_name()+"");
                        i.putExtra("getObject_ar_address",n.getObject_en_address()+"");
                        i.putExtra("getObject_ar_spic",n.getObject_en_spic()+"");

                    }

                    i.putExtra("getObject_image",sub_path+n.getObject_image()+"");
                    i.putExtra("tt",n.getObject_type()+"");

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

            return myView;
        }

    }

}


