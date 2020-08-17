package com.idoctortech.idoctor.msg;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.idoctortech.idoctor.Home;
import com.idoctortech.idoctor.Login;
import com.idoctortech.idoctor.R;
import com.idoctortech.idoctor.doctor.Objects;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MsgView extends AppCompatActivity {
    ListView msgList;
    ArrayList<inMsg> msgArrayList = new ArrayList<inMsg>();
    MsgAdapter msgAdapter;
    String URL="https://idoctortech.com/api/android/view-msg.php" , sendUrl ="https://idoctortech.com/api/android/addmsg.php";
    String from , to;
    TextView name;
    String UserId;
    SharedPreferences sharedPref;

    EditText msgValue;
    ImageView send;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_message);
        Context context = getApplicationContext();
        sharedPref = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
        UserId = sharedPref.getString("id","");
        msgList = (ListView) findViewById(R.id.msgList);
        from = getIntent().getStringExtra("From");
        to = getIntent().getStringExtra("To");
        name = (TextView) findViewById(R.id.name);
        name.setText(getIntent().getStringExtra("Name")+"");


        msgValue = (EditText) findViewById(R.id.msgValue);
        send = (ImageView) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMsg(msgValue.getText().toString());
            }
        });
        Loading(from,to);

        ImageView backBtn = (ImageView) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void Loading(String from , String to){
        final Context context = getApplicationContext();
        Log.d("inMsg",URL+"&from="+from+"&to="+to);
        RequestQueue queue = Volley.newRequestQueue(this);//
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,URL+"?from="+from+"&to="+to, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int count = 0;
                while (count < response.length()) {
                    try {
                        inMsg msg = new inMsg();
                        JSONObject jsonObject = response.getJSONObject(count);
                        msg.setChat_id(jsonObject.getInt("chat_id"));
                        msg.setChat_from_id(jsonObject.getInt("chat_from_id"));
                        msg.setChat_to_id(jsonObject.getInt("chat_to_id"));
                        msg.setChat_content(jsonObject.getString("chat_content"));
                        msg.setChat_sender_type(jsonObject.getInt("chat_sender_type"));
                        msg.setChat_created_at(jsonObject.getString("chat_created_at"));
                        msg.setChat_read(jsonObject.getInt("chat_read"));
                        msg.setMsg_type(jsonObject.getInt("msg_type"));

                        msgArrayList.add(msg);
                        count++;

                    } catch (JSONException e) {
                        Toast.makeText(MsgView.this, "Error: "+e, Toast.LENGTH_SHORT).show();
                    }
                    msgAdapter=new MsgAdapter(msgArrayList);
                    msgAdapter.notifyDataSetChanged();
                    msgList.setAdapter(msgAdapter);
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
    private class MsgAdapter extends BaseAdapter {
        ArrayList<inMsg> newArrayList ;

        public  MsgAdapter(ArrayList<inMsg> objects ){
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

        @SuppressLint("SetTextI18n")
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final inMsg n=newArrayList.get(position);
            LayoutInflater mInflater = LayoutInflater.from(getApplicationContext());
            View myView;
            String f =n.getChat_from_id()+"";
            if(f.contentEquals(from)) {
                myView = mInflater.inflate(R.layout.view_msg_sending_item, null);
            }else{
                myView = mInflater.inflate(R.layout.view_msg_recivering_item, null);
            }

            TextView name = (TextView) myView.findViewById(R.id.name);
            name.setText(n.getChat_content()+"");
//            count.setText(n.getCount()+"");
//            RoundedImageView image = (RoundedImageView) myView.findViewById(R.id.image);
//            Picasso.get().load(n.getImg()+"").into(image);

            myView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                Intent i = new Intent(MsgView.this,MsgView.class);
//                i.putExtra("Name",n.getName()+"");
//                i.putExtra("Img",n.getImg()+"");
//                i.putExtra("From",n.getFrom()+"");
//                i.putExtra("To",n.getTo()+"");


                }
            });
            return myView;
        }
    }

    public void addMsg(final String content) {

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest sr = new StringRequest(Request.Method.POST,sendUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                msgArrayList.clear();
                msgValue.setText("");
                Loading(from,to);
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
                params.put("from", from);
                params.put("to", to);
                params.put("type", "0");
                params.put("content", content);
                params.put("lastid", "0");
                params.put("name",getIntent().getStringExtra("Name")+"" );

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
