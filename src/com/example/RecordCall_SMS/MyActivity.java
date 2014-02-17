package com.example.RecordCall_SMS;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.example.RecordCall_SMS.savesms.SaveSMS;
import com.example.RecordCall_SMS.savesms.SaveSMSService;
import com.example.RecordCall_SMS.savesms.ServiceTemplate;

import java.util.Date;

public class MyActivity extends Activity
{
    /**
     * Called when the activity is first created.
     */
    SaveSMS saveSMS;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Intent i = new Intent("com.example.RecordCall_SMS.savesms.SaveSMSService");
        startService(new Intent(this, ServiceTemplate.class));
    }
}
