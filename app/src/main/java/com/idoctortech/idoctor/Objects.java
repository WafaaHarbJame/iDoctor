package com.idoctortech.idoctor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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
import java.util.Locale;



public class Objects extends AppCompatActivity {

    ListView objectsList;
    ArrayList<Object> objectArrayList = new ArrayList<Object>();
    ObjectAdapter objectAdapter;
    String sub_path = "https://idoctortech.com/en/";
    String url = "https://idoctortech.com/api/get_objects_gm.php?lat=0&long=0&type=",r;
    TextView types;
    ProgressBar progressBar;
    LinearLayout home , apps,search,notifications , profile;
    SharedPreferences sharedPreferencesLanguage;
    String locale_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objects);

        Context context = getApplicationContext();

         sharedPreferencesLanguage = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
         locale_value = sharedPreferencesLanguage.getString("locale","ar");
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



        objectsList = (ListView) findViewById(R.id.objects_list);
        types = (TextView) findViewById(R.id.types);
        home = (LinearLayout) findViewById(R.id.home);
        apps = (LinearLayout) findViewById(R.id.apps);
        search = (LinearLayout) findViewById(R.id.search);
        notifications = (LinearLayout) findViewById(R.id.notification);
        profile = (LinearLayout) findViewById(R.id.profile);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
//        doctors = (RelativeLayout) findViewById(R.id.doctors);


//        if(getIntent().getStringExtra("type")!=null|| !getIntent().getStringExtra("type").isEmpty()){
            String t = getIntent().getStringExtra("type")+"";
            if(t.equals("1")){
                types.setText(getResources().getString(R.string.doctors));
            }else if(t.equals("2")){
                types.setText(getResources().getString(R.string.medical_centers));
            }else if(t.equals("3")){
                types.setText(getResources().getString(R.string.pharmacies));
            }else if(t.equals("4")){
                types.setText(getResources().getString(R.string.nurses));
            }
//        }
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
            }
        });


        Loading();
    }

    public void Loading(){
        Context context = getApplicationContext();
//        SharedPreferences sharedPref = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
//        String driver_id = sharedPref.getString("id","");
        url = url+getIntent().getStringExtra("type");
        RequestQueue queue = Volley.newRequestQueue(this);//
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int count = 0;
                while (count < response.length()) {
                    progressBar.setVisibility(View.GONE);
                    try {
                        Object o = new Object();
                        JSONObject jsonObject = response.getJSONObject(count);
                        o.setObject_id(jsonObject.getInt("object_id"));
                        o.setObject_type(jsonObject.getString("object_type"));
                        o.setObject_ar_name(jsonObject.getString("object_ar_name"));
                        o.setObject_en_name(jsonObject.getString("object_en_name"));
                        o.setObject_ar_desc(jsonObject.getString("object_ar_desc"));
                        o.setObject_en_desc(jsonObject.getString("object_en_desc"));
                        o.setObject_city_id(jsonObject.getString("object_ar_address"));
                        o.setObject_en_address(jsonObject.getString("object_en_address"));
                        if(jsonObject.getString("object_city_id")=="1"){
                            o.setObject_ar_address(getResources().getString(R.string.hebron)+", "+jsonObject.getString("object_ar_address")+"");
                        }else if(jsonObject.getString("object_city_id")=="2"){
                            o.setObject_ar_address(getResources().getString(R.string.bethlehem)+", "+jsonObject.getString("object_ar_address")+"");
                        }else if(jsonObject.getString("object_city_id")=="3"){
                            o.setObject_ar_address(getResources().getString(R.string.jerusalem)+", "+jsonObject.getString("object_ar_address")+"");
                        }else if(jsonObject.getString("object_city_id")=="4"){
                            o.setObject_ar_address(getResources().getString(R.string.jericho)+", "+jsonObject.getString("object_ar_address")+"");
                        }else if(jsonObject.getString("object_city_id")=="5"){
                            o.setObject_ar_address(getResources().getString(R.string.ramallah)+", "+jsonObject.getString("object_ar_address")+"");
                        }else if(jsonObject.getString("object_city_id")=="6"){
                            o.setObject_ar_address(getString(R.string.salfit)+", "+jsonObject.getString("object_ar_address")+"");
                        }else if(jsonObject.getString("object_city_id")=="7"){
                            o.setObject_ar_address(getString(R.string.qalqilya)+", "+jsonObject.getString("object_ar_address")+"");
                        }else if(jsonObject.getString("object_city_id")=="8"){
                            o.setObject_ar_address(getString(R.string.nablus)+", "+jsonObject.getString("object_ar_address")+"");
                        }else if(jsonObject.getString("object_city_id")=="9"){
                            o.setObject_ar_address(getString(R.string.tulkarm)+", "+jsonObject.getString("object_ar_address")+"");
                        }else if(jsonObject.getString("object_city_id")=="10"){
                            o.setObject_ar_address(getString(R.string.tubas)+", "+jsonObject.getString("object_ar_address")+"");
                        }else if(jsonObject.getString("object_city_id")=="11"){
                            o.setObject_ar_address(getString(R.string.jenin)+", "+jsonObject.getString("object_ar_address")+"");
                        }else if(jsonObject.getString("object_city_id")=="12"){
                            o.setObject_ar_address(getString(R.string.north_gaza)+", "+jsonObject.getString("object_ar_address")+"");
                        }else if(jsonObject.getString("object_city_id")=="13"){
                            o.setObject_ar_address(getString(R.string.gaza)+", "+jsonObject.getString("object_ar_address")+"");
                        }else if(jsonObject.getString("object_city_id")=="14"){
                            o.setObject_ar_address(getString(R.string.dair)+", "+jsonObject.getString("object_ar_address")+"");
                        }else if(jsonObject.getString("object_city_id")=="15"){
                            o.setObject_ar_address(getString(R.string.khan)+", "+jsonObject.getString("object_ar_address")+"");
                        }else if(jsonObject.getString("object_city_id")=="16"){
                            o.setObject_ar_address(getString(R.string.rafah)+", "+jsonObject.getString("object_ar_address")+"");

                        }
                        o.setObject_ar_address(jsonObject.getString("object_ar_address")+"");
//                        o.setObject_ar_address(jsonObject.getString("object_ar_address"));
                        o.setObject_city_id(jsonObject.getString("object_ar_address"));
                        o.setObject_en_address(jsonObject.getString("object_en_address"));
                        o.setObject_owner_id(jsonObject.getString("object_owner_id"));
                        o.setObject_image(jsonObject.getString("object_image"));
                        o.setObject_ar_spic(jsonObject.getString("object_ar_spic"));
                        o.setObject_en_spic(jsonObject.getString("object_en_spic"));
                        o.setObject_lat(jsonObject.getString("object_lat"));
                        o.setObject_long(jsonObject.getString("object_long"));
//                        o.setDistance(jsonObject.getString("distance"));
                        o.setDistance("0");

                        objectArrayList.add(o);

                        count++;

                    } catch (JSONException e) {
                        Toast.makeText(Objects.this, "Error: "+e, Toast.LENGTH_SHORT).show();

                    }
                    objectAdapter=new ObjectAdapter(objectArrayList);
                    objectAdapter.notifyDataSetChanged();
                    objectsList.setAdapter(objectAdapter);

                }
                if(count == 0){
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
            View myView =  mInflater.inflate(R.layout.object_item, null);
            final Object n=newArrayList.get(position);

            TextView name = (TextView) myView.findViewById(R.id.name);
            TextView location = (TextView) myView.findViewById(R.id.location);
            TextView spicalization = (TextView) myView.findViewById(R.id.spicalization);

            if(locale_value.equals("ar")){
            name.setText(n.getObject_ar_name()+"");
                location.setText(n.getObject_ar_address()+"");
                spicalization.setText(n.getObject_ar_spic()+"");
            }else if(locale_value.equals("en")){
                name.setText(n.getObject_en_name()+"");
                location.setText(n.getObject_en_address()+"");
                spicalization.setText(n.getObject_en_spic()+"");
            }

            RoundedImageView image = (RoundedImageView) myView.findViewById(R.id.image);
            Picasso.get().load(n.getObject_image()+"").into(image);
            Log.d("imagO",n.getObject_image()+"");



            myView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(getApplicationContext(),ViewObject.class);
                    i.putExtra("getObject_id",n.getObject_id()+"");
                    i.putExtra("getObject_ar_name",n.getObject_ar_name()+"");

                    if(locale_value.equals("ar")){
                        i.putExtra("getObject_ar_name",n.getObject_ar_name()+"");

                        i.putExtra("getObject_ar_address",n.getObject_en_address()+"");


                    }else if(locale_value.equals("en")){
                        i.putExtra("getObject_ar_name",n.getObject_en_name()+"");
                        i.putExtra("getObject_ar_address",n.getObject_en_address()+"");

                    }
                    i.putExtra("getObject_ar_spic",n.getObject_ar_spic()+"");
                    i.putExtra("getObject_image",n.getObject_image()+"");
                    i.putExtra("tt",n.getObject_type()+"");
                    startActivity(i);
                }
            });



            return myView;
        }

    }
}
