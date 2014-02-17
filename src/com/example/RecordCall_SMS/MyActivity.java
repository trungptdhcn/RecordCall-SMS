package com.example.RecordCall_SMS;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.example.RecordCall_SMS.savesms.SaveSMS;
import com.example.RecordCall_SMS.savesms.ServiceTemplate;

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
        startService(new Intent(this, ServiceTemplate.class));
        finish();
    }


}
