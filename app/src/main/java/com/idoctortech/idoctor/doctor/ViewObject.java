package com.idoctortech.idoctor.doctor;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.idoctortech.idoctor.Apps;
import com.idoctortech.idoctor.Home;
import com.idoctortech.idoctor.Notifications;
import com.idoctortech.idoctor.Profile;
import com.idoctortech.idoctor.R;
import com.idoctortech.idoctor.Search;
import com.idoctortech.idoctor.insideMsg;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ViewObject extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    LinearLayout home , apps,search,notifications , profile;
    RelativeLayout doctors,centers,pharmces,nurses,booking,ResCard;
    SharedPreferences sharedPref;
    WebView web;
    Button go_to_appo,go_to_chat,book,ResBtn;
    ImageView close_appo;
    ImageButton msg;
    SeekBar seekBar;
    int seekBarValue=0;
    DatePicker datePicker;
    String datePickerValue="";
    String time="";
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    ImageView backBtn;
TextView startFrom;
    RequestQueue queue;
    String APPOURL = "https://idoctortech.com/api/android/add-appo-o.php";

    String v15="0" , v30="0" , v45="0",vv="0";
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_object);
        Context context = getApplicationContext();

        final AlertDialog.Builder builder1 = new AlertDialog.Builder(context);

        backBtn = (ImageView) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        SharedPreferences sharedPreferencesLanguage = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
        String locale_value = sharedPreferencesLanguage.getString("locale","");
        go_to_appo = (Button) findViewById(R.id.go_to_appo);
        go_to_chat = (Button) findViewById(R.id.go_to_chat);
        if(locale_value.equals("ar")){
            Locale locale = new Locale("ar");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            go_to_chat.setText("مراسلة");
            go_to_appo.setText("حجز موعد");
        }else if(locale_value.equals("en")){
            Locale locale = new Locale("en");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            go_to_chat.setText("Chat");
            go_to_appo.setText("Add Appointment");
        }

        Costs();
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
        booking = (RelativeLayout) findViewById(R.id.booking);
        msg = (ImageButton) findViewById(R.id.msg);

        close_appo = (ImageView) findViewById(R.id.close_appo);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        datePicker =(DatePicker) findViewById(R.id.datePicker1);
        book =(Button) findViewById(R.id.book);
        ResCard = (RelativeLayout) findViewById(R.id.ResCard);
        ResBtn = (Button) findViewById(R.id.ResBtn);
        startFrom = (TextView) findViewById(R.id.startFrom);

        ResBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResCard.setVisibility(View.GONE);
            }
        });

        seekBar.setProgress(0);
        seekBar.incrementProgressBy(50);
        seekBar.setMax(100);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub

                seekBarValue=progress;
                if(seekBarValue<=10) {
                    startFrom.setText(v15 + "");
                    vv = v15 + "";
                }else if(seekBarValue>=11&&seekBarValue<=60){
                    startFrom.setText(v30 + "");
                    vv = v30 + "";
                }else if(seekBarValue>=61){
                    startFrom.setText(v45 + "");
                    vv = v45 + "";
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("08:00");
        categories.add("08:30");
        categories.add("09:00");
        categories.add("09:30");
        categories.add("10:00");
        categories.add("10:30");
        categories.add("11:00");
        categories.add("11:30");
        categories.add("12:00");
        categories.add("12:30");
        categories.add("13:00");
        categories.add("13:30");
        categories.add("14:00");
        categories.add("14:30");
        categories.add("15:00");
        categories.add("15:30");
        categories.add("16:00");
        categories.add("16:30");
        categories.add("17:00");
        categories.add("17:30");
        categories.add("18:00");
        categories.add("18:30");
        categories.add("19:00");
        categories.add("19:30");
        categories.add("20:00");
        categories.add("20:30");
        categories.add("21:00");
        categories.add("21:30");
        categories.add("22:00");
        categories.add("22:30");
        categories.add("23:00");
        categories.add("23:30");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Context context = getApplicationContext();
                sharedPref = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
                String id = sharedPref.getString("id","");
                datePickerValue = datePicker.getYear()+"-"+ (datePicker.getMonth() + 1)+"-"+datePicker.getDayOfMonth();
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                String app_type = "";
                if(radioButton.getText().toString()=="لقاء داخلي"||radioButton.getText().toString()=="Meeting"){
                    app_type = "0";
                }else  if(radioButton.getText().toString().equals("مكالمة فيديو")||radioButton.getText().toString().equals("Video call")){
                    app_type = "1";
                }
//                Toast.makeText(getApplicationContext(), "selected = "+radioButton.getText()+" app_type= "+app_type+" datePickerValue = "+datePickerValue+" seekBarValue = "+seekBarValue, Toast.LENGTH_SHORT).show();
                queue = Volley.newRequestQueue(getApplicationContext());
                APPOURL = APPOURL+"?p="+id+"&id="
                        +getIntent().getStringExtra("getObject_id")+"&date="+datePickerValue+"&time="+time+"&type="+app_type+"&period="+seekBarValue+"&cost="+vv;
//                Toast.makeText(context, APPOURL+"", Toast.LENGTH_SHORT).show();
                StringRequest request = new StringRequest(Request.Method.GET,APPOURL , new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        ResCard.setVisibility(View.VISIBLE);
                        Log.d("APPOURL",APPOURL);

                        TextView ResMsg = (TextView) findViewById(R.id.ResMsg);

//                        Toast.makeText(getApplicationContext(), response+"", Toast.LENGTH_LONG).show();
                        if(response.contains("بنجاح")){

                            ResMsg.setText(R.string.appo_added);

                            booking.setVisibility(View.GONE);

//                            Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                        }else{
                            ResMsg.setText(R.string.appo_not_added);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error",error.toString());
                    }
                });
                queue.add(request);
            }

        });


        web = (WebView) findViewById(R.id.web);
        web.loadUrl(getResources().getString(R.string.object_agenda)+getIntent().getStringExtra("getObject_id"));
        Log.d("ViewObject",(getResources().getString(R.string.object_agenda)+getIntent().getStringExtra("getObject_id")));
//
        close_appo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                booking.setVisibility(View.GONE);
            }
        });

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
        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), com.idoctortech.idoctor.doctor.insideMsg.class);
                i.putExtra("Name",getIntent().getStringExtra("getObject_ar_name")+"");
                i.putExtra("tt",getIntent().getStringExtra("type")+"");
                String id = sharedPref.getString("id","");
                i.putExtra("From",id+"");
                i.putExtra("To",getIntent().getStringExtra("getObject_id")+"");
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

        TextView name = (TextView) findViewById(R.id.name);
        name.setText(getIntent().getStringExtra("getObject_ar_name")+"");
//        Toast.makeText(context, getIntent().getStringExtra("getObject_ar_name")+"", Toast.LENGTH_SHORT).show();
        TextView navname = (TextView) findViewById(R.id.navname);
        navname.setText(getIntent().getStringExtra("getObject_ar_name")+"");

        TextView spicalization = (TextView) findViewById(R.id.spicalization);
        spicalization.setText(getIntent().getStringExtra("getObject_ar_spic"));

        TextView specialty = (TextView) findViewById(R.id.specialty);
        specialty.setText(getIntent().getStringExtra("getObject_ar_spic"));

        TextView location = (TextView) findViewById(R.id.location);
        location.setText(getIntent().getStringExtra("getObject_ar_address"));

        RoundedImageView image = (RoundedImageView) findViewById(R.id.image);
        Picasso.get().load(getIntent().getStringExtra("getObject_image")).into(image);
        String areDoctor = getIntent().getStringExtra("tt")+"";
//        Toast.makeText(context, areDoctor+"", Toast.LEN/GTH_SHORT).show();
        if(areDoctor.equals("1")||areDoctor.equals("4")){
            go_to_appo.setVisibility(View.VISIBLE);
        }else{
            go_to_appo.setVisibility(View.GONE);
        }
        go_to_appo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                booking.setVisibility(View.VISIBLE);
//                Intent i = new Intent(getApplicationContext(),AddAppo.class);
//                i.putExtra("getObject_id",getIntent().getStringExtra("getObject_id")+"");
//                i.putExtra("getObject_ar_name",getIntent().getStringExtra("getObject_ar_name")+"");
//                i.putExtra("getObject_ar_spic",getIntent().getStringExtra("getObject_ar_spic")+"");
//                i.putExtra("getObject_ar_address",getIntent().getStringExtra("getObject_ar_address")+"");
//                i.putExtra("getObject_image",getIntent().getStringExtra("getObject_image")+"");
//                startActivity(i);
            }
        });
        go_to_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), com.idoctortech.idoctor.doctor.insideMsg.class);
                i.putExtra("Name",getIntent().getStringExtra("getObject_ar_name")+"");
                i.putExtra("tt",getIntent().getStringExtra("type")+"");
                String id = sharedPref.getString("id","");
                i.putExtra("From",id+"");
                i.putExtra("To",getIntent().getStringExtra("getObject_id")+"");
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
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        time = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item

    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void Costs() {
//        Toast.makeText(this, url+"?email="+email+"&password="+pass, Toast.LENGTH_SHORT).show();
        RequestQueue queue = Volley.newRequestQueue(this);//
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,"https://idoctortech.com/api/android/get_appo_cost.php?id="+getIntent().getStringExtra("getObject_id")+"", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
//                Toast.makeText(getApplicationContext(), "response="+response, Toast.LENGTH_LONG).show();
                int count = 0;
                while (count < response.length()) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(count);
                        v15 = jsonObject.getString("object_cost_15");
                        v30 = jsonObject.getString("object_cost_30");
                        v45 = jsonObject.getString("object_cost_45");

                        count++;

                    } catch (JSONException e) {
                        Toast.makeText(ViewObject.this, "Error: "+e, Toast.LENGTH_SHORT).show();

                    }

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
//        {
//            @Override
//            protected Map<String, String> getParams() {
//                // Posting parameters to login url
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("email", email);
//                params.put("password", pass);
//                return params;
//            }
//
//            @Override
//            public int getMethod() {
//                return Method.POST;
//            }
//        };

        queue.add(jsonArrayRequest);

    }

}


