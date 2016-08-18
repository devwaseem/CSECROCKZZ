package com.waseem.csecrockzz;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

public class NotificationService extends Service {
    public NotificationService() {
    }
    Intent alarmIntent;
    Calendar cl;
    int hour,min,time,day;
    String stat;
    AlarmManager manager;
    PendingIntent pintent;
    @Override
    public void onCreate() {
        manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmIntent = new Intent(getBaseContext(), NotificationUpdater.class);
         pintent= PendingIntent.getService(this, 0, alarmIntent, 0);
        startService(alarmIntent);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        cl = Calendar.getInstance();
        hour = cl.get(Calendar.HOUR_OF_DAY);
        min = cl.get(Calendar.MINUTE);
        day=cl.get(Calendar.DAY_OF_WEEK);
        Calendar c=Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,9);
        c.set(Calendar.MINUTE,0);

        if (min < 10) {
            this.stat = hour + "" + "0" + min;
        } else {
            this.stat = hour + "" + min;
        }
        this.time = Integer.parseInt(this.stat);

        if(day!=Calendar.SUNDAY && day !=Calendar.SATURDAY) {
            if ((time > 900) & (time < 1640)) {
                Toast.makeText(this, "notification update", Toast.LENGTH_LONG).show();
                Log.v("service", "invoked");
                ;
                manager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),
                        AlarmManager.INTERVAL_HALF_HOUR, pintent);

            }
        }

        stopService(alarmIntent);
        stopSelf();
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       return null;
    }
}
