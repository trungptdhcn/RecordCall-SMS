package com.example.RecordCall_SMS;

import android.app.Activity;
import android.os.Bundle;
import com.example.RecordCall_SMS.savesms.SaveSMS;

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
        saveSMS = new SaveSMS(this);
        saveSMS.saveSMSToFile();
    }
}
