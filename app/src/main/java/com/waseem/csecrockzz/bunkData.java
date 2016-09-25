package com.waseem.csecrockzz;

import android.util.Log;

/**
 * Created by Waseem on 9/11/2016.
 */

public class bunkData {
    private String subject;
    int TotClasses,bunked,SafeBunks,Marks;
    float perc;
    float att;
    bunkData(String subject,int Totclass,int bunked){
        this.subject=subject;
        this.TotClasses=Totclass;
        this.bunked=bunked;

        att=(TotClasses*60)/50;
        perc=((att-bunked)/att)*100;
        Marks=5;
    }

    public String getSubject() {
        return subject;
    }

    public String getTotClasses() {
        String sTotClasses="Total Classes: "+TotClasses;

        return sTotClasses;
    }

    public String getBunked() {
        String sbunked="Number Of Classes Bunked: "+bunked;
        return sbunked;
    }

    public String getSafeBunks() {
        double s =(0.75)*att;

        SafeBunks=(int)(att-s);
       String sSafeBunks="Safe Bunks Available: "+(SafeBunks-bunked);
        return sSafeBunks;
    }

    public String getMarks() {
        String sMarks="Marks Obtained: "+Marks;
        return sMarks;
    }
    public String getPerc(){

        String sperc=String.format("%2.1f",perc) + "%";
        return sperc;
    }
}
