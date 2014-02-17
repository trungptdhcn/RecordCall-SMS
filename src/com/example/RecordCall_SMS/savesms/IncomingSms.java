package com.example.RecordCall_SMS.savesms;

/**
 * User: trungpt
 * Date: 2/17/14
 * Time: 5:42 PM
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class IncomingSms extends BroadcastReceiver
{

    SaveSMS saveSMS;
    // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();

    public void onReceive(Context context, Intent intent)
    {

        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();
        saveSMS = new SaveSMS(context);

        try
        {

            if (bundle != null)
            {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++)
                {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();

                    Log.i("SmsReceiver", "senderNum: " + senderNum + "; message: " + message);

                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, "senderNum: " + senderNum + ", message: " + message, duration);
                    toast.show();
                    saveSMS.saveSMSToFile();

                } // end for loop
            } // bundle is null

        }
        catch (Exception e)
        {
            Log.e("SmsReceiver", "Exception smsReceiver" + e);

        }
    }
}