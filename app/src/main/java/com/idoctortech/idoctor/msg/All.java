package com.idoctortech.idoctor.msg;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.idoctortech.idoctor.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class All extends AppCompatActivity {
    ListView msgList;
    ArrayList<Msg> msgArrayList = new ArrayList<Msg>();
    MsgAdapter msgAdapter;
    String URL="https://idoctortech.com/api/android/msg.php?from=";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_message);
        Context context = getApplicationContext();
        msgList = (ListView) findViewById(R.id.msgList);
        Loading();

        ImageView backBtn = (ImageView) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void Loading(){
        final Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
        final String UserId = sharedPref.getString("id","");
        RequestQueue queue = Volley.newRequestQueue(this);//
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,URL+UserId, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int count = 0;
                while (count < response.length()) {
                    try {
                        Msg msg = new Msg();
                        JSONObject jsonObject = response.getJSONObject(count);
                        msg.setFrom(Integer.parseInt(UserId));
                        msg.setTo(jsonObject.getInt("reciver"));
                        msg.setCount(jsonObject.getInt("count"));
                        msg.setName(jsonObject.getString("fname")+" "+jsonObject.getString("lname"));
                        msg.setImg(jsonObject.getString("image"));
                        msgArrayList.add(msg);
                        count++;

                    } catch (JSONException e) {
                        Toast.makeText(All.this, "Error: "+e, Toast.LENGTH_SHORT).show();

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
        ArrayList<Msg> newArrayList ;

        public  MsgAdapter(ArrayList<Msg> objects ){
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

            LayoutInflater mInflater = LayoutInflater.from(getApplicationContext());
            @SuppressLint("ViewHolder") View myView=  mInflater.inflate(R.layout.view_msg_item, null);
            final Msg n=newArrayList.get(position);

            TextView name = (TextView) myView.findViewById(R.id.name);
            TextView count = (TextView) myView.findViewById(R.id.count);
            RoundedImageView image = (RoundedImageView) myView.findViewById(R.id.image);

            name.setText(n.getName()+"");
            count.setText(n.getCount()+"");
            Picasso.get().load(n.getImg()+"").into(image);

            myView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(All.this,MsgView.class);
                    i.putExtra("Name",n.getName()+"");
                    i.putExtra("Img",n.getImg()+"");
                    i.putExtra("From",n.getFrom()+"");
                    i.putExtra("To",n.getTo()+"");
                    startActivity(i);
                }
            });

            return myView;
        }
    }

}
