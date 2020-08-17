package com.idoctortech.idoctor.doctor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
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
import com.idoctortech.idoctor.AddAppo;
import com.idoctortech.idoctor.Apps;
import com.idoctortech.idoctor.Home;
import com.idoctortech.idoctor.Msg;
import com.idoctortech.idoctor.Notifications;
import com.idoctortech.idoctor.Object;
import com.idoctortech.idoctor.Profile;
import com.idoctortech.idoctor.R;
import com.idoctortech.idoctor.Search;
import com.idoctortech.idoctor.ViewObject;
import com.idoctortech.idoctor.insideMsg;
import com.idoctortech.idoctor.msg.All;
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
    String url = "https://idoctortech.com/api/android/get_patients.php?id=",r;
    ImageButton msg;
    LinearLayout home , apps,search,notifications , profile;
    ProgressBar progressBar;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients );

        msg = (ImageButton) findViewById(R.id.msg);


        Context context = getApplicationContext();
        SharedPreferences sharedPreferencesLanguage = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
        String locale_value = sharedPreferencesLanguage.getString("locale","ar");
        assert locale_value != null;

            Locale locale = new Locale(locale_value);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());



        id = sharedPreferencesLanguage.getString("id","");
        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), com.idoctortech.idoctor.doctor.AllMsg.class);
//                i.putExtra("Name",n.getObject_ar_name()+"");
                i.putExtra("tt",getIntent().getStringExtra("type")+"");
                i.putExtra("From",id+"");
                i.putExtra("To",getIntent().getStringExtra("getObject_id")+"");
                startActivity(i);

            }
        });

        objectsList = (ListView) findViewById(R.id.objects_list);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        home = (LinearLayout) findViewById(R.id.home);
        apps = (LinearLayout) findViewById(R.id.apps);
        search = (LinearLayout) findViewById(R.id.search);
        notifications = (LinearLayout) findViewById(R.id.notification);
        profile = (LinearLayout) findViewById(R.id.profile);
//        doctors = (RelativeLayout) findViewById(R.id.doctors);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), com.idoctortech.idoctor.doctor.Objects.class);
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


        apps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),com.idoctortech.idoctor.doctor.Apps.class);
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

        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),com.idoctortech.idoctor.doctor.Notifications.class);
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


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),com.idoctortech.idoctor.doctor.Search.class);
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
                //finishAffinity()();

            }
        });

        Loading();
    }

    public void Loading(){
        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
        String id = sharedPref.getString("id","");
        url = url+id;
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
                        o.setObject_id(jsonObject.getInt("user_id"));
                        o.setObject_ar_name(jsonObject.getString("first_name")+" "+jsonObject.getString("last_name"));
                        o.setObject_ar_desc(jsonObject.getString("user_phone"));
                        o.setObject_ar_address(jsonObject.getString("user_gender"));
                        o.setObject_image(jsonObject.getString("user_image"));


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
            View myView=  mInflater.inflate(R.layout.p_item, null);
            final Object n=newArrayList.get(position);



            TextView name = (TextView) myView.findViewById(R.id.name);
            name.setText(n.getObject_ar_name()+"");

            TextView spicalization = (TextView) myView.findViewById(R.id.spicalization);
            spicalization.setText(n.getObject_ar_desc()+"");

            TextView location = (TextView) myView.findViewById(R.id.location);
            String gender = "";
            SharedPreferences sharedPreferencesLanguage = getApplicationContext().getSharedPreferences("auth", Context.MODE_PRIVATE);
            String locale_value = sharedPreferencesLanguage.getString("locale","");
            if(n.getObject_ar_address().equalsIgnoreCase("0")){
                if(locale_value.equals("ar")){
                    Locale locale = new Locale("ar");
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                    gender = "انثى";
                }else if(locale_value.equals("en")){
                    Locale locale = new Locale("en");
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                    gender = "Female";
                }
//                gender = getResources().getString(R.string.female);
            }else if(n.getObject_ar_address().equalsIgnoreCase("1")){
                if(locale_value.equals("ar")){
                    Locale locale = new Locale("ar");
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                    gender = "ذكر";
                }else if(locale_value.equals("en")){
                    Locale locale = new Locale("en");
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                    gender = "Male";
                }
//                gender = getResources().getString(R.string.male);
            }
            location.setText(gender+"");
//            Toast.makeText(Objects.this, n.getObject_city_id()+"", Toast.LENGTH_SHORT).show();


            RoundedImageView image = (RoundedImageView) myView.findViewById(R.id.image);
            Picasso.get().load(n.getObject_image()+"").into(image);

            ImageView chatBtn = (ImageView) myView.findViewById(R.id.chatBtn);
            chatBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), com.idoctortech.idoctor.doctor.insideMsg.class);
                    i.putExtra("Name",n.getObject_ar_name()+"");
                    i.putExtra("tt",getIntent().getStringExtra("type")+"");
                    i.putExtra("From",id+"");
                    i.putExtra("To",getIntent().getStringExtra("getObject_id")+"");
                    startActivity(i);
                }
            });

            TextView viewMore = (TextView) myView.findViewById(R.id.viewMore);

//            TextView spicalization = (TextView) myView.findViewById(R.id.spicalization);
//            spicalization.setText(n.getObject_ar_desc()+"");
//
//            TextView location = (TextView) myView.findViewById(R.id.location);
//            if(n.getObject_ar_address()=="0"){
//                location.setText(getResources().getString(R.string.female)+"");
//            }else{
//                location.setText(getResources().getString(R.string.male)+"");
//            }
//
//
//            ImageView image = (ImageView) myView.findViewById(R.id.image);
//            Picasso.get().load(n.getObject_image()+"").into(image);

            viewMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(),P.class);
                    i.putExtra("user_id",n.getObject_id()+"");
                    i.putExtra("name",n.getObject_ar_name()+"");
                    i.putExtra("email",n.getObject_ar_desc()+"");
                    i.putExtra("image",n.getObject_image()+"");
                    if(n.getObject_ar_address()=="0"){

                        i.putExtra("gender",getResources().getString(R.string.female));
                    }else{
                        i.putExtra("gender",getResources().getString(R.string.male));
                    }
                    startActivity(i);
                }
            });
//



            return myView;
        }

    }

    @Override
    public void onRestart(){
        super.onRestart();
        Intent i = new Intent(getApplicationContext(),com.idoctortech.idoctor.doctor.Objects.class);
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
        finish();
        startActivity(i);

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(),com.idoctortech.idoctor.doctor.Objects.class);
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

}
