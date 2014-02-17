package com.example.RecordCall_SMS.savesms;

/**
 * User: trungpt
 * Date: 2/17/14
 * Time: 5:59 PM
 */
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

public class ServiceTemplate extends Service {

    String address = "";
    String CALL_DETAILS;
    String SMS_STR = "";
    SaveSMS saveSMS;


    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        saveSMS = new SaveSMS(this);


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub


        new LongOperation_().execute("");


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    private class LongOperation_ extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... params) {

            try {

                getSMS();

            } catch (Exception e) {
                Log.e("error before mail sent-->", ""
                        + e.getMessage().toString());
                e.printStackTrace();
            }

            return "";
        }

        protected void onPostExecute(String result) {
            // Log.e("post exe", "------- post exe");
            Intent myIntent = new Intent(getApplicationContext(), ServiceTemplate.class);

            getApplicationContext().stopService(myIntent);
        }

        protected void onPreExecute() {
        }

        protected void onProgressUpdate(Void... values) {
        }
    }

    public void getSMS() {

        Context con = getApplicationContext();
        Uri myMessage = Uri.parse("content://sms/");
        ContentResolver cr = con.getContentResolver();
        Cursor c = cr.query(myMessage, new String[] { "_id", "address", "date",
                "body", "read" }, null, null, null);

        // startManagingCursor(c);
        // getSmsLogs(c, con);
        int i = 0;
        saveSMS.saveSMSToFile();


//        try {
//
//            if (c.moveToFirst()) {
//                do {
//
//                    if (c.getString(c.getColumnIndexOrThrow("address")) == null) {
//                        c.moveToNext();
//                        continue;
//                    }
//
//                    String _id = c.getString(c.getColumnIndexOrThrow("_id"))
//                            .toString();
//
//                    String Number = c.getString(
//                            c.getColumnIndexOrThrow("address")).toString();
//                    String dat = c.getString(c.getColumnIndexOrThrow("date"))
//                            .toString();
//
//                    // String as = (String) get_dt(dat, "dd/MM/yyyy, hh.mma");
//                    String Body = c.getString(c.getColumnIndexOrThrow("body"))
//                            .toString();
//
//                    SMS_STR = "num: " + Number + "\n";
//                    SMS_STR = "date: " + dat + "\n";
//                    SMS_STR = "Body: " + Body + "\n";
//                    SMS_STR = "\n\n\n";
//
//                    if (i > 1) {
//                        break;
//                    }
//                    i++;
//
//                } while (c.moveToNext());
//            }
//            c.close();
//

//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}