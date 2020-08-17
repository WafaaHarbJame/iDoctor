package com.idoctortech.idoctor.uProfile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.idoctortech.idoctor.R;
import com.idoctortech.idoctor.Splach;
import com.idoctortech.idoctor.msg.inMsg;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Profile extends AppCompatActivity {
    ListView msgList;
    ArrayList<inMsg> msgArrayList = new ArrayList<inMsg>();
    String URL="https://idoctortech.com/api/v-profile.php?id=";
    String UPDATE_URL="https://idoctortech.com/api/android/o-profile.php";
    String from , to;
    TextView name;
    String UserId;
    SharedPreferences sharedPref;
    ImageButton logoutBtn;
    EditText msgValue;
    ImageView send;
    Button update;
    EditText fname,sname,lname,dob,gender,country,city,insurance,phone,height,wight,blood,smoking,drink;
    String user_email ="";
    Spinner spinner;
    List<String> countryList;
    ArrayAdapter<String> dataAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.u_profile);
        Context context = getApplicationContext();
        sharedPref = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
        UserId = sharedPref.getString("id","");

        ImageView backBtn = (ImageView) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        logoutBtn = (ImageButton) findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences =getSharedPreferences("auth",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                Intent i = new Intent(getApplicationContext(), Splach.class);
                startActivity(i);
            }
        });

        update = (Button) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateInfo();
            }
        });

        fname = (EditText) findViewById(R.id.fname);
        sname = (EditText) findViewById(R.id.sname);
        lname = (EditText) findViewById(R.id.lname);
        dob = (EditText) findViewById(R.id.dob);
        gender = (EditText) findViewById(R.id.gender);
        country = (EditText) findViewById(R.id.country);
        city = (EditText) findViewById(R.id.city);
        insurance = (EditText) findViewById(R.id.insurance);
        phone = (EditText) findViewById(R.id.phone);
        height = (EditText) findViewById(R.id.height);
        wight = (EditText) findViewById(R.id.wight);
        blood = (EditText) findViewById(R.id.blood);
        smoking = (EditText) findViewById(R.id.smoking);
        drink = (EditText) findViewById(R.id.drink);


        Loading();
        spinner = (Spinner) findViewById(R.id.spinner);
        countryList = new ArrayList<String>();
        countryList.add(getResources().getString(R.string.palestine));
        countryList.add(getResources().getString(R.string.jordan));


    }

    public void Loading(){
        final Context context = getApplicationContext();
        RequestQueue queue = Volley.newRequestQueue(this);//
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,URL+UserId, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int count = 0;
                while (count < 1) {
                    try {
                        Log.d("URLID",response+"");
                        JSONObject jsonObject = response.getJSONObject(count);
//                        Toast.makeText(context, response+"", Toast.LENGTH_LONG).show();
                        fname.setText(jsonObject.getString("first_name")+"");
                        sname.setText(jsonObject.getString("second_name")+"");
                        lname.setText(jsonObject.getString("last_name")+"");
                        user_email=jsonObject.getString("user_email")+"";
                        dob.setText(jsonObject.getString("user_dob")+"");
                        gender.setText(jsonObject.getString("user_gender")+"");
                        insurance.setText(jsonObject.getString("user_insurance")+"");

                        country.setText(jsonObject.getString("user_country_id")+"");
//                        if(jsonObject.getString("user_country_id")=="1"){
//                            spinner.setSelection(0);
//                        }else{
//                            spinner.setSelection(1);
//                        }

                        city.setText(jsonObject.getString("user_city_id")+"");
                        phone.setText(jsonObject.getString("user_phone")+"");
                        blood.setText(jsonObject.getString("user_bload")+"");
                        wight.setText(jsonObject.getString("user_wight")+"");
                        height.setText(jsonObject.getString("user_height")+"");
                        smoking.setText(jsonObject.getString("user_smoking")+"");
                        drink.setText(jsonObject.getString("user_alcohol")+"");
                        count++;

                    } catch (JSONException e) {
                        Toast.makeText(Profile.this, "Error: "+e, Toast.LENGTH_SHORT).show();
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

        queue.add(jsonArrayRequest);

    }

    public void updateInfo() {

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest sr = new StringRequest(Request.Method.POST,UPDATE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                msgArrayList.clear();
//                msgValue.setText("");
//                Toast.makeText(MsgView.this, response+"", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("id", UserId);
                params.put("first_name", fname.getText()+"");
                params.put("second_name", sname.getText()+"");
                params.put("last_name", lname.getText()+"");
                params.put("email", user_email);
                params.put("date", dob.getText()+"");
                params.put("gender", gender.getText()+"");
                params.put("country", country.getText()+"");
                params.put("city",city.getText()+"" );
                params.put("insurance",insurance.getText()+"");
                params.put("phone",phone.getText()+"" );
                params.put("user_height", height.getText()+"" );
                params.put("user_wight",wight.getText()+"" );
                params.put("user_bload",blood.getText()+"");
                params.put("user_smoking",smoking.getText()+"" );
                params.put("user_alcohol",drink.getText()+"");

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);

    }



}
