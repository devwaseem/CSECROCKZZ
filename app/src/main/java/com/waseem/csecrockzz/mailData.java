package com.waseem.csecrockzz;

/**
 * Created by Waseem on 8/14/2016.
 */
public class mailData {
    String subject,content;
   public mailData(String subject,String content){
        this.subject=subject;
       this.content=content;
    }
    public String getSubject(){
       return subject;
    }
    public String getContent(){
        return content;
    }
}

