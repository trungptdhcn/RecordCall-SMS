package com.example.RecordCall_SMS.savesms;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Trung
 * Date: 2/16/14
 * Time: 10:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class SaveSMS
{
    public Context context;

    public SaveSMS(Context context)
    {
        this.context = context;
    }

    public void saveSMSToFile()
    {
        //save SMS sent
        Uri sentURI = Uri.parse("content://sms/sent");
        String[] reqCols = new String[]{"_id", "address", "body", "read", "date"};
        ContentResolver cr = context.getContentResolver();
        Cursor cursorSent = cr.query(sentURI, reqCols, null, null, null);
        //save SMS receive
        Uri inboxURI = Uri.parse("content://sms/inbox");
        Cursor cursorInbox = cr.query(inboxURI, reqCols, null, null, null);
        try
        {
            String filepath = Environment.getExternalStorageDirectory().getPath();
            File file = new File(filepath, "RECORDCALL&SMS");
            if (!file.exists())
            {
                file.mkdirs();
            }
            FileWriter writerSent = new FileWriter(new File(file, "SMSSent.txt"));
            if (cursorSent.moveToFirst())
            {
                while (cursorSent.moveToNext())
                {
                    writerSent.append("id: " + cursorSent.getString(cursorSent.getColumnIndex("_id")) + "\n");
                    writerSent.append("address: " + cursorSent.getString(cursorSent.getColumnIndex("address")) + "\n");
                    writerSent.append("body: " + cursorSent.getString(cursorSent.getColumnIndex("body")) + "\n");
                    writerSent.append("read: "+cursorSent.getString(cursorSent.getColumnIndex("read")) + "\n");
                    Long milliseconds = cursorSent.getLong(cursorSent.getColumnIndex("date"));
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS", Locale.US);
                    GregorianCalendar calendar = new GregorianCalendar(TimeZone.getTimeZone("US/Central"));
                    calendar.setTimeInMillis(milliseconds);
                    writerSent.append("date:" + sdf.format(calendar.getTime())+ "\n");
                    writerSent.append("====================================================="+"\n");
                }
                writerSent.flush();
                writerSent.close();
            }
            cursorSent.close();

            FileWriter writerReceive = new FileWriter(new File(file, "SMSReceive.txt"));
            if (cursorInbox.moveToFirst())
            {
                while (cursorInbox.moveToNext())
                {
                    writerReceive.append("id: " + cursorInbox.getString(cursorInbox.getColumnIndex("_id")) + "\n");
                    writerReceive.append("address: " + cursorInbox.getString(cursorInbox.getColumnIndex("address")) + "\n");
                    writerReceive.append("body: " + cursorInbox.getString(cursorInbox.getColumnIndex("body")) + "\n");
                    writerReceive.append("read: "+cursorInbox.getString(cursorInbox.getColumnIndex("read")) + "\n");
                    Long milliseconds = cursorInbox.getLong(cursorInbox.getColumnIndex("date"));
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS", Locale.US);
                    GregorianCalendar calendar = new GregorianCalendar(TimeZone.getTimeZone("US/Central"));
                    calendar.setTimeInMillis(milliseconds);
                    writerReceive.append("date:" + sdf.format(calendar.getTime())+ "\n");
                    writerReceive.append("====================================================="+"\n");
                }
                writerReceive.flush();
                writerReceive.close();
            }
            writerReceive.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
