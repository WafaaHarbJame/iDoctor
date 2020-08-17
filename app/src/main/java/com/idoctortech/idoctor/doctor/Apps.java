package com.idoctortech.idoctor.doctor;

import android.app.AlarmManager;
import android.app.PendingIntent;
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
import android.widget.LinearLayout;
import android.widget.ListView;
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
import com.idoctortech.idoctor.Appo;
import com.idoctortech.idoctor.Home;
import com.idoctortech.idoctor.MyBroadcastReceiver;
import com.idoctortech.idoctor.Notifications;
import com.idoctortech.idoctor.Profile;
import com.idoctortech.idoctor.R;
import com.idoctortech.idoctor.Search;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;



public class Apps extends AppCompatActivity {
    LinearLayout home , apps,search,notifications , profile;
    RelativeLayout doctors;

    ListView objectsList;
    ArrayList<Appo> objectArrayList = new ArrayList<Appo>();
    Apps.ObjectAdapter objectAdapter;
    String sub_path = "https://idoctortech.com/en/";
    String url = "",r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps);
        home = (LinearLayout) findViewById(R.id.home);
        apps = (LinearLayout) findViewById(R.id.apps);
        search = (LinearLayout) findViewById(R.id.search);
        notifications = (LinearLayout) findViewById(R.id.notification);
        profile = (LinearLayout) findViewById(R.id.profile);
//        doctors = (RelativeLayout) findViewById(R.id.doctors);
        objectsList = (ListView) findViewById(R.id.appoList);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                //finishAffinity()();
            }
        });

        apps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), com.idoctortech.idoctor.doctor.Apps.class);
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

        Context context = getApplicationContext();
        SharedPreferences sharedPref;

        sharedPref = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
        String id = sharedPref.getString("id","");
        String token = sharedPref.getString("token","");
        Loading(id,token);
    }




    public void Loading(String id , String token){
        final Context context = getApplicationContext();
//        SharedPreferences sharedPref = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
//        String driver_id = sharedPref.getString("id","");
//        Log.d("Appo_url",getResources().getString(R.string.url_get_appo)+"?token="+token+"&id="+id);
        RequestQueue queue = Volley.newRequestQueue(this);//
        url ="https://idoctortech.com/api/android/en/o_get_appos.php";
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,url+"?token="+token+"&id="+id, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int count = 0;
                while (count < response.length()) {
                    try {
                        Appo o = new Appo();
                        JSONObject jsonObject = response.getJSONObject(count);
                        o.setObject_id(jsonObject.getInt("object_id"));
                        o.setObject_ar_name(jsonObject.getString("object_ar_name"));
//                        o.setObject_ar_desc(jsonObject.getString("object_ar_desc"));
//                        o.setObject_en_desc(jsonObject.getString("object_en_desc"));
                        o.setObject_city_id(jsonObject.getString("object_ar_name"));
                        o.setObject_ar_address(jsonObject.getString("object_ar_address"));
//                        o.setObject_en_address(jsonObject.getString("object_en_address"));
//                        o.setObject_owner_id(jsonObject.getString("object_owner_id"));
                        o.setObject_image(jsonObject.getString("object_image"));
                        o.setObject_ar_spic(jsonObject.getString("object_ar_spic"));
//                        o.setObject_en_spic(jsonObject.getString("object_en_spic"));
//                        o.setObject_lat(jsonObject.getString("object_lat"));
//                        o.setObject_long(jsonObject.getString("object_long"));
//                        o.setDistance(jsonObject.getString("distance"));
                        o.setDate(jsonObject.getString("appo_date"));
                        o.setTime(jsonObject.getString("appo_time"));
                        o.setType(jsonObject.getString("appo_type"));


                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

                        long diff = 0;
                        try {
                            diff = format.parse(jsonObject.getString("appo_date")).getTime() - Calendar.getInstance().getTime().getTime();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        long seconds = diff / 1000;
                        long minutes = seconds / 60;
                        long hours = minutes / 60;
                        long days = hours / 24;
                        long total = (days*24*60*60)+(hours*60*60)+(minutes*60)+seconds;
//                        long dayhalf = Time.parse(jsonObject.getString("appo_time"));

                        String time = jsonObject.getString("appo_time"); //mm:ss
                        String[] units = time.split(":"); //will break the string up into an array
                        int iiiiminutes = Integer.parseInt(units[0]); //first element
                        int iiiiseconds = Integer.parseInt(units[1]); //second element
                        long duration = 60*60 * iiiiminutes + (60*iiiiseconds);
                        Log.d("sss",seconds+"");
                        SetAlarm(( duration + seconds));

                        objectArrayList.add(o);

                        count++;

                    } catch (JSONException e) {
                        Toast.makeText(Apps.this, "Error: "+e, Toast.LENGTH_SHORT).show();

                    }
                    objectAdapter=new Apps.ObjectAdapter(objectArrayList);
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
        ArrayList<Appo> newArrayList ;

        public  ObjectAdapter(ArrayList<Appo> objects ){
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
            View myView=  mInflater.inflate(R.layout.view_appo_item_2, null);
            final Appo n=newArrayList.get(position);

            TextView name = (TextView) myView.findViewById(R.id.name);
            name.setText(n.getObject_ar_name()+"");

            TextView day = (TextView) myView.findViewById(R.id.day);
            if(n.getType().equalsIgnoreCase("0")){
                day.setText(getResources().getString(R.string.meeting));
            }else{
                day.setText(getResources().getString(R.string.video));
            }


            TextView time = (TextView) myView.findViewById(R.id.time);
            time.setText(n.getTime()+"");

            TextView date = (TextView) myView.findViewById(R.id.date);
            date.setText(n.getDate()+"");

            TextView spicalization = (TextView) myView.findViewById(R.id.spicalization);
            spicalization.setText(n.getObject_ar_spic()+"");
            String gender = "";
            TextView location = (TextView) myView.findViewById(R.id.location);
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
//            location.setText(n.getObject_ar_address()+"");

            RoundedImageView image = (RoundedImageView) myView.findViewById(R.id.image);
            Picasso.get().load(n.getObject_image()+"").into(image);

//            ImageView image = (ImageView) myView.findViewById(R.id.image);
//            Picasso.get().load(sub_path+n.getObject_image()+"").into(image);

            myView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

                    long diff = 0;
                    try {
                        diff = format.parse(n.getDate()).getTime() - Calendar.getInstance().getTime().getTime();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    long seconds = diff / 1000;
                    long minutes = seconds / 60;
                    long hours = minutes / 60;
                    long days = hours / 24;
                    long total = (days*24*60*60)+(hours*60*60)+(minutes*60)+seconds;
//                    long dayhalf = Time.parse(n.getTime());
                    String time = n.getTime(); //mm:ss
                    String[] units = time.split(":"); //will break the string up into an array
                    int iiiiminutes = Integer.parseInt(units[0]); //first element
                    int iiiiseconds = Integer.parseInt(units[1]); //second element
                    long duration = 60*60 * iiiiminutes + (60*iiiiseconds);

                    if(seconds>0){


                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMMM-yyyy HH:mm");
                        String dateString = formatter.format(new Date((seconds+duration) * 1000L));
//                        Toast.makeText(getApplicationContext()
//                                , "متبقى للموعد " + (duration + seconds)/3600+" ساعة", Toast.LENGTH_LONG).show();}else{

//                        SetAlarm(seconds-(dayhalf/60));
                    }
                }
            });
            return myView;
        }
    }

    public void SetAlarm(long sec) {
        int timeInSec = (int) sec;

        Intent intent = new Intent(getApplicationContext(),com.idoctortech.idoctor.doctor.MyBroadcastReceiver.class);
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
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 234, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (timeInSec * 1000), pendingIntent);
//        Toast.makeText(this, "Alarm set to after " + 2 + " seconds",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(),com.idoctortech.idoctor.doctor.Objects.class);
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

}
