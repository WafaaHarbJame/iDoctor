package com.idoctortech.idoctor.doctor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;

import com.idoctortech.idoctor.R;

public class MyBroadcastReceiver extends BroadcastReceiver {
    MediaPlayer mp;


    @Override
    public void onReceive(Context context, Intent intent) {
        mp = MediaPlayer.create(context, R.raw.notifications);
        mp.start();

//        Toast.makeText(context, "Alarm", Toast.LENGTH_LONG).show();

        Intent i = new Intent(context,com.idoctortech.idoctor.doctor.Notifications.class);
//        i.setClassName("com.idoctortech.idoctor.doctor", "Notifications");
        i.putExtra("Apps","true");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
