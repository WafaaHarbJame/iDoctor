package com.idoctortech.idoctor.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;

import com.idoctortech.idoctor.Classes.Constants;
import com.idoctortech.idoctor.R;
import com.idoctortech.idoctor.RootApplication;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by wokman on 11/11/2016.
 */

public class ActivityHandler {

    public static boolean isPackageExist(Context context, String packageName) {

        List<ApplicationInfo> packages;
        PackageManager pm;

        pm = context.getPackageManager();
        packages = pm.getInstalledApplications(0);
        for (ApplicationInfo packageInfo : packages) {
            if (packageInfo.packageName.equals(packageName))
                return true;
        }
        return false;

    }

    public static void OpenGooglePlay(Context context) {

        final String appPackageName = context.getPackageName(); // getPackageName() from Context or Activity object
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }

    }

    public static void OpenBrowser(Context context, String url) {

        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(browserIntent);

    }

    public static void OpenSendEmail(Context context, String subject, String body, String receiver) {

        Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject + "");
        intent.putExtra(Intent.EXTRA_TEXT, body + "");
        intent.setData(Uri.parse("mailto:" + receiver)); // or just "mailto:" for blank
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
        context.startActivity(intent);

    }

    @SuppressLint("MissingPermission")
    public static void makeCall(Context context, String phoneNumber) {

        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
        context.startActivity(intent);

    }

    public static void OpenDialerIntent(Context context, String phoneNumber) {

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        context.startActivity(intent);

    }

    public static void shareTextUrl(Context context, String title, String url, String shareChannel) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        if (shareChannel != null && !shareChannel.equals(""))
            share.setPackage(shareChannel);
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        String appStr = RootApplication.getInstance().getString(R.string.by_app) + "\n" + Constants.Paly_Link;
        // Add data to the intent, the receiving app will decide
        // what to do with it.
//        share.putExtra(Intent.EXTRA_SUBJECT, title + "");
        String body = "";
        if (title != null && !title.isEmpty()) {
            body += title;
        }
        if (url != null && !url.isEmpty()) {
            body += "\n" + url;
        }
        share.putExtra(Intent.EXTRA_TEXT, body /*+ "\n\n" + appStr*/);

        context.startActivity(Intent.createChooser(share, context.getResources().getString(R.string.share_with)));
    }

    public static void shareTwitter(Context context, String message) {
        Intent tweetIntent = new Intent(Intent.ACTION_SEND);
        tweetIntent.putExtra(Intent.EXTRA_TEXT, message);
        tweetIntent.setType("text/plain");

        PackageManager packManager = context.getPackageManager();
        List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(tweetIntent, PackageManager.MATCH_DEFAULT_ONLY);

        boolean resolved = false;
        for (ResolveInfo resolveInfo : resolvedInfoList) {
            if (resolveInfo.activityInfo.packageName.startsWith("com.twitter.android")) {
                tweetIntent.setClassName(
                        resolveInfo.activityInfo.packageName,
                        resolveInfo.activityInfo.name);
                resolved = true;
                break;
            }
        }
        if (resolved) {
            context.startActivity(tweetIntent);
        } else {
            Intent i = new Intent();
            i.putExtra(Intent.EXTRA_TEXT, message);
            i.setAction(Intent.ACTION_VIEW);
            i.setData(Uri.parse("https://twitter.com/intent/tweet?text=" + urlEncode(message)));
            context.startActivity(i);
//            GlobalData.Toast("Twitter app isn't found");
//            Toast.makeText(this, "Twitter app isn't found", Toast.LENGTH_LONG).show();
        }
    }

    private static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.wtf("TAG", "UTF-8 should always be supported", e);
            return "";
        }
    }


}
