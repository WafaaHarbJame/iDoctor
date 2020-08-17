package com.idoctortech.idoctor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.idoctortech.idoctor.doctor.Objects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Login extends AppCompatActivity {

    EditText Email , Pass;
    Button loginBtn;
    SharedPreferences sharedPref;
    String url="https://idoctortech.com/api/android/glogin.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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

        sharedPref = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
        String driver = sharedPref.getString("id","");
//        if(driver!=null&&driver.length()!=0&&driver!=""){
//            Intent i = new Intent(Login.this,Home.class);
//            i.putExtra("orders","all");
//            startActivity(i);
//        }


        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Email = (EditText) findViewById(R.id.email);
                Pass = (EditText) findViewById(R.id.password);
                if((Email.getText().length()!=0)&&(Pass.getText().length()!=0)){
//                    Toast.makeText(getApplicationContext(), ""+Email.getText()+" "+Pass.getText(), Toast.LENGTH_SHORT).show();

                    loginToApp(Email.getText()+"",Pass.getText()+"");
                }
//                Intent i = new Intent(Login.this,Home.class);
//                startActivity(i);
            }
        });


    }

    public void loginToApp( String email , String pass) {
//        Toast.makeText(this, url+"?email="+email+"&password="+pass, Toast.LENGTH_SHORT).show();
        RequestQueue queue = Volley.newRequestQueue(this);//
        Log.d("URL_LOGIN",url+"?email="+email+"&password="+pass);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,url+"?email="+email+"&password="+pass, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
//                Toast.makeText(getApplicationContext(), "response="+response, Toast.LENGTH_LONG).show();
                int count = 0;
                while (count < response.length()) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(count);
                        if(!jsonObject.getString("token").equalsIgnoreCase("null")) {
                            if (jsonObject.getString("type").equalsIgnoreCase("0")) {
                                Intent i = new Intent(Login.this, Home.class);
                                i.putExtra("id", response + "");
                                i.putExtra("orders", "all");
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putString("token", jsonObject.getString("token"));
                                editor.putString("id", jsonObject.getString("id"));
                                editor.putString("type", jsonObject.getString("type"));
                                editor.commit();
                                editor.apply();
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
                            } else if (!jsonObject.getString("type").equalsIgnoreCase("0")) {
                                Intent i = new Intent(Login.this, Objects.class);
                                i.putExtra("id", response + "");
                                i.putExtra("orders", "all");
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putString("token", jsonObject.getString("token"));
                                editor.putString("id", jsonObject.getString("id"));
                                editor.putString("type", jsonObject.getString("type"));
                                editor.commit();
                                editor.apply();
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
                        }else{
                            Toast.makeText(Login.this, ""+getResources().getString(R.string.error_login), Toast.LENGTH_LONG).show();
                        }

                        count++;

                    } catch (JSONException e) {
                        Toast.makeText(Login.this, "Error: "+e, Toast.LENGTH_SHORT).show();

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

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Login.this,AuthChoise.class);
        startActivity(i);
    }
}

