package com.idoctortech.idoctor;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.logging.Handler;

public class MyBroadcastReceiver extends BroadcastReceiver {
    MediaPlayer mp;


    @Override
    public void onReceive(Context context, Intent intent) {
        mp = MediaPlayer.create(context, R.raw.notifications);
        mp.start();

//        Toast.makeText(context, "Alarm", Toast.LENGTH_LONG).show();

        Intent i = new Intent(context,Notifications.class);
//        i.setClassName("com.idoctortech.idoctor", "com.idoctortech.idoctor.Notifications");
        i.putExtra("Apps","true");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }
}
