package com.waseem.csecrockzz;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;
import android.app.NotificationManager;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView p1,p2,p3,p4,p5,p6,p7,p8,curr,prev,next,rt;
    Calendar calendar;
    int day,hour,min;
    String stat;
    Intent alarmIntent,vibrIntent;
    PendingIntent pintent,vibintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        periodinit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        startat5();
        calendar=Calendar.getInstance();

        day=calendar.get(Calendar.DAY_OF_WEEK);
        getTable(day);
        getStatus();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));


        } else if (id == R.id.forum) {
            startActivity(new Intent(getApplicationContext(),ForumActivity.class));
        } else if (id == R.id.asleep) {

        } else if (id == R.id.tools) {

        } else if (id == R.id.bmanager) {
            startActivity(new Intent(getApplicationContext(),BunkManager.class));
        }else if (id == R.id.croom) {
            startActivity(new Intent(getApplicationContext(),chatActivity.class));
        }else if (id == R.id.about) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void getTable(int day) {
        switch (day) {
            case Calendar.MONDAY: {
                monday();
                break;
            }
            case Calendar.TUESDAY: {
                tuesday();
                break;
            }
            case Calendar.WEDNESDAY: {
                wednesday();
                break;
            }
            case Calendar.THURSDAY: {
                thursday();
                break;
            }
            case Calendar.FRIDAY: {
                friday();
                break;
            }
            case Calendar.SATURDAY:
            case Calendar.SUNDAY:{
                holiday();
                break;
        }
        }
    }
    public void monday(){
        p1.setText("English");
        p2.setText("Free Period");
        p2.setTextColor(Color.parseColor("#8BC34A"));
        p3.setText("Physics");
        p4.setText("Chemistry");
        p5.setText("Economics");
        p6.setText("Maths");
        p7.setText("Engg Practice Lab");
        p8.setText("Engg Practice Lab");

    }
    public void tuesday(){
        p1.setText("Physics");
        p2.setText("Chemistry");
        p3.setText("Economics");
        p4.setText("Maths");
        p5.setText("Engg Graphics");
        p6.setText("Engg Graphics");
        p7.setText("Chemistry Lab");
        p8.setText("Chemistry Lab");

    }
    public void wednesday(){
        p1.setText("Economics");
        p2.setText("Maths");
        p3.setText("Engg Graphics");
        p4.setText("Engg Graphics");
        p5.setText("Computer Prog");
        p6.setText("Computer Prog");
        p7.setText("English");
        p8.setText("Free Period");
        p8.setTextColor(Color.parseColor("#8BC34A"));

    }
    public void thursday(){
        p1.setText("Engg Graphics");
        p2.setText("Engg Graphics");
        p3.setText("Computer Prog");
        p4.setText("Computer Prog");
        p5.setText("English");
        p6.setText("Free Period");
        p6.setTextColor(Color.parseColor("#8BC34A"));

        p7.setText("Physics");
        p8.setText("NSO");
        p8.setTextColor(Color.parseColor("#8BC34A"));

    }
    public void friday(){
        p1.setText("Physics Lab");
        p2.setText("Physics Lab");
        p3.setText("Free Period");
        p3.setTextColor(Color.parseColor("#8BC34A"));
        p4.setText("Free Period");
        p4.setTextColor(Color.parseColor("#8BC34A"));
        p5.setText("Free Period");
        p5.setTextColor(Color.parseColor("#8BC34A"));
        p6.setText("Chemistry");
        p7.setText("Free Period");
        p7.setTextColor(Color.parseColor("#8BC34A"));
        p8.setText("Maths");

    }
    public void holiday(){
        p1.setText("");
        p2.setText("");
        p3.setText("");
        p4.setText("");
        p5.setText("");
        p6.setText("");
        p7.setText("");
        p8.setText("");
    }
    public void getStatus(){
       Calendar cl=Calendar.getInstance();
        hour=cl.get(Calendar.HOUR_OF_DAY);
        min=cl.get(Calendar.MINUTE);
        if(min<10){
            stat=hour+""+"0"+min;
        }else{
            stat=hour+""+min;
        }
        int time=Integer.parseInt(stat);
        Log.v("now", stat);
        stat=""+time;
        int start=900;
        ((TextView)findViewById(R.id.prev)).setText(stat);
        ((TextView)findViewById(R.id.curr)).setText("");
        ((TextView)findViewById(R.id.next)).setText("");
        if((time>start) & (time<1640)){
            if(time>900 && time < 950){//1 period
                Log.v("now", "Period1");
                prev.setText("");
                curr.setText(p1.getText());
                next.setText(p2.getText());
                rt.setText(getRemainingMins(min,50));
            }else if(time >=950 && time <1040){//2 period
                prev.setText(p1.getText());
                curr.setText(p2.getText());
                next.setText(p3.getText());
                rt.setText(getRemainingMins(min,40));
            }
             if(time >=1040 && time <1130){
                Log.v("now", "Period2");
                prev.setText(p2.getText());
                curr.setText(p3.getText());
                next.setText(p4.getText());
                 rt.setText(getRemainingMins(min,30));
            }
            if(time >=1130 && time <1220){
                prev.setText(p3.getText());
                Log.v("now", "Period3");
                curr.setText(p4.getText());
                next.setText("LUNCH");
                rt.setText(getRemainingMins(min,20));
            }if(time >=1220 && time <1320){
                prev.setText(p4.getText());
                curr.setText("LUNCH");
                next.setText(p5.getText());
                rt.setText(getRemainingMins(min,20));
            }if(time >=1320 && time <1410){
                prev.setText("LUNCH");

                curr.setText(p5.getText());
                next.setText(p6.getText());
                rt.setText(getRemainingMins(min,10));
            }
             if(time >=1410 && time <1500) {
                prev.setText(p5.getText());
                curr.setText(p6.getText());
                next.setText(p7.getText());
                 rt.setText(getRemainingMins(min,0));
             }
             else if(time >=1500 && time <1550){
                prev.setText(p6.getText());
                curr.setText(p7.getText());
                next.setText(p8.getText());
                 rt.setText(getRemainingMins(min,50));
            }
             else if(time >=1550 && time <=1640){
                 prev.setText(p7.getText());
                 curr.setText(p8.getText());
                 next.setText("HOME");
                 rt.setText(getRemainingMins(min,40));
             }

        }else{
            prev.setText("HOME");
            curr.setText("SWEET");
            next.setText("HOME");
            rt.setText("No Time Its Home..!!!  :P ");
        }
    }
    public void periodinit(){
        p1=(TextView)findViewById(R.id.period1);
        p2=(TextView)findViewById(R.id.period2);
        p3=(TextView)findViewById(R.id.period3);
        p4=(TextView)findViewById(R.id.period4);
        p5=(TextView)findViewById(R.id.period5);
        p6=(TextView)findViewById(R.id.period6);
        p7=(TextView)findViewById(R.id.period7);
        p8=(TextView)findViewById(R.id.period8);
        curr=(TextView)findViewById(R.id.curr);
        prev=(TextView)findViewById(R.id.prev);
        next=(TextView)findViewById(R.id.next);
        rt=(TextView)findViewById(R.id.rtime);
    }

    public void show(View view){

        Intent intent=new Intent(this,TimeTableActivity.class);
        startActivity(intent);
    }

    public void startat5(){

            AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            int interval = 1000 * 60 * 50;
            alarmIntent = new Intent(MainActivity.this, NotificationService.class);

            pintent = PendingIntent.getService(this, 0, alarmIntent, 0);

            startService(alarmIntent);

            Calendar ncalendar = Calendar.getInstance();

            ncalendar.set(Calendar.HOUR_OF_DAY, 6);
            ncalendar.set(Calendar.MINUTE, 0);

            manager.setRepeating(AlarmManager.RTC_WAKEUP, ncalendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pintent);


    }
    public String getRemainingMins(int currMin,int endMin){
    String result;
        int remain =(60-currMin)+endMin;
        if(remain>60){
            remain-=60;
        }
        result=""+remain;
        return result;
    }
}

