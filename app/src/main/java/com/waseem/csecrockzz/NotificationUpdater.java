package com.waseem.csecrockzz;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class NotificationUpdater extends Service {

    Notification noti;
    String stat;
    PendingIntent pi;
    Calendar cl;
    int hour,min,time,day;
    boolean isNext=false;
    Intent i;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        i=new Intent(this,MainActivity.class);
         pi=PendingIntent.getActivity(this,0,i,0);
        cl = Calendar.getInstance();
         hour = cl.get(Calendar.HOUR_OF_DAY);
         min = cl.get(Calendar.MINUTE);
        day=cl.get(Calendar.DAY_OF_WEEK);
        getTime(hour,min);
        if(day!=Calendar.SUNDAY && day !=Calendar.SATURDAY)  if ((time > 900) & (time < 1640)) getnoti(getTable(day));
            return START_STICKY;
        }

        @Override
        public IBinder onBind (Intent intent){
            // TODO: Return the communication channel to the service.
            return null;
        }
    public void getnoti(String curr) {

        Log.v("newTime",stat);
        noti = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.home)
                .setContentTitle("CURRENT PERIOD:   " + curr)

                .setContentText("NEXT PERIOD:   " + getTable(day))

                .setContentIntent(pi).build();

        NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0,noti);
    }

    public String getTable(int day) {
        switch (day) {
            case Calendar.MONDAY: {
                return monday();

            }
            case Calendar.TUESDAY: {
                return tuesday();

            }
            case Calendar.WEDNESDAY: {
                return wednesday();

            }
            case Calendar.THURSDAY: {
                return thursday();

            }
            case Calendar.FRIDAY: {
               return friday();

            }
            case Calendar.SATURDAY:
            case Calendar.SUNDAY:{
                return holiday();

            }

        }
        return null;
    }
    public String monday(){
        return getperiod("ENGLISH","FREE PERIOD","PHYSICS","CHEMISTRY","ECONOMICS","MATHS","ENG LAB","ENG LAB");
    }
    public String tuesday(){
        return getperiod("PHYSICS","CHEMISTRY","ECONOMICS","MATHS","ENGG GRAPHICS","ENGG GRAPHICS","CHEM LAB","CHEM LAB");

    }
    public String wednesday(){
        return getperiod("ECONOMICS","MATHS","ENGG GRAPHICS","ENGG GRAPHICS","COMPUTER PROG.","COMPUTER PROG.","ENGLISH","FREE PERIOD");
    }
    public String thursday(){
        return getperiod("ENGG GRAPHICS","ENGG GRAPHICS","COMPUTER PROG.","COMPUTER PROG.","ENGLISH","FREE PERIOD","PHYSICS","FREE PERIOD");
    }
    public String friday(){
        return getperiod("PHY LAB","PHY LAB","FREE PERIOD","FREE PERIOD","FREE PERIOD","CHEMISTRY","FREE PERIOD","MATHS");

    }
    public String holiday(){
      return "Holiday";
    }
    public String getperiod(String p1,String p2,String p3,String p4,String p5,String p6,String p7,String p8) {
        if (!isNext) {
            isNext = true;
            if ((time > 900) & (time < 1640)) {
                if (time > 900 && time < 950) {//1 period

                    return p1;

                } else if (time >= 950 && time < 1040) {//2 period
                    return p2;
                } else if (time >= 1040 && time < 1130) {
                    return p3;
                } else if (time >= 1130 && time < 1220) {
                    return p4;
                } else if (time >= 1221 && time < 1320) {
                    return "LUNCH";
                } else if (time >= 1320 && time < 1410) {
                    return p5;
                } else if (time >= 1410 && time < 1500) {
                    return p6;
                } else if (time >= 1500 && time < 1550) {
                    return p7;
                } else if (time >= 1550 && time <= 1640) {
                    return p8;
                }
            }
                }else {
            isNext=false;
            if ((time > 900) & (time < 1640)) {

                if (time > 900 && time < 950) {//1 period

                    return p2;

                } else if (time >= 950 && time < 1040) {//2 period
                    return p3;
                } else if (time >= 1040 && time < 1130) {
                    return p4;
                } else if (time >= 1130 && time < 1220) {
                    return "LUNCH";
                } else if (time >= 1221 && time < 1320) {
                    return p5;
                } else if (time >= 1320 && time < 1410) {
                    return p6;
                } else if (time >= 1410 && time < 1500) {
                    return p7;
                } else if (time >= 1500 && time < 1550) {
                    return p8;
                } else if (time >= 1550 && time <= 1640) {
                    return "HOME";
                }

            }

        }
        stopSelf();
        return "HOME";
    }
public void getTime(int hour,int min){
    if (min < 10) {
        this.stat = hour + "" + "0" + min;
    } else {
        this.stat = hour + "" + min;
    }
    this.time = Integer.parseInt(this.stat);
    }
}




