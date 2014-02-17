package com.example.RecordCall_SMS.savesms;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.Date;

/**
 * User: trungpt
 * Date: 2/17/14
 * Time: 1:28 PM
 */
public class SaveSMSService extends Service
{
    SaveSMS saveSMS;
    Intent intent;

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onStart(Intent intent, int startId)
    {
        // TODO Auto-generated method stub
        super.onStart(intent, startId);
        this.intent = intent;
        saveSMS = new SaveSMS(this);
        //new PostFile().execute();
        while (true)
        {
            // Do your task
            try
            {
                Thread.sleep(1000);
                saveSMS.saveSMSToFile();
            }
            catch (Exception e)
            {

            }

        }
    }
}
