package com.waseem.csecrockzz;

import android.os.AsyncTask;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.sun.mail.pop3.POP3Store;

import javax.mail.*;
import java.io.*;
import java.security.NoSuchProviderException;
import java.util.Date;
import java.util.Properties;

public class MathsActivity extends AppCompatActivity {
String[] subject,content;
    int ijk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maths);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Refreshing...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Log.e("START MATHS","MATHS START");
        getmails gm=new getmails();
        gm.execute("h");
        Log.v("MATHS","STARTS");
        Toast.makeText(getApplicationContext(), "STARTED", Toast.LENGTH_SHORT).show();
        final String[] ql = {"hello1 \nhello", "hello2", "hello3", "hello4", "hello5", "hello6", "hello7", "hello8", "hello9", "hello10", "hello11", "hello12", "hello13", "hello14", "hello15",};
        ListView qlv = (ListView) findViewById(R.id.mathslistView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, ql);
        qlv.setAdapter(adapter);
        qlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), ql[i], Toast.LENGTH_SHORT).show();
            }
        });

    }

    public class getmails extends AsyncTask<String, Void, String[]> {
        @Override
        protected String[] doInBackground(String... strings) {

            String host = "mx1.1freehosting.com";// change accordingly
            Log.v("myapp","host c");
            String mailStoreType = "pop3";
            Log.v("myapp","type c");
            String username = "maths@chennaipokewalk.pixub.com";// change accordingly
            String password = "maths@123";// change accordingly
            Log.v("mathsactivity","doinback");
            
            fetch(host, mailStoreType, username, password);
            Log.v("mathsactivity","fetched finished");
            return new String[0];
        }


        public void fetch(String pop3Host, String storeType, String user,
                          String password) {
            try {

                Properties properties = new Properties();

                properties.put("mail.pop3.host", pop3Host);
                properties.put("mail.store.protocol", "pop3");

                properties.put("mail.pop3.starttls.enable", "true");
                Session emailSession = Session.getDefaultInstance(properties);

                POP3Store store = (POP3Store) emailSession.getStore(storeType);
                store.connect(pop3Host, user, password);


                Folder emailFolder = store.getFolder("INBOX");

                emailFolder.open(Folder.READ_ONLY);
                Log.v("Waseem","folder Opened");

                Message[] messages = emailFolder.getMessages();
                System.out.println("messages.length---" + messages.length);

                for (ijk = 0; ijk < messages.length; ijk++) {
                    Message message = messages[ijk];

                    writePart(message);


                }

                // close the store and folder objects
                emailFolder.close(false);
                store.close();

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        public void writePart (Part p) {
            try {
                if (p instanceof Message)
                    //Call methos writeEnvelope
                    writeEnvelope((Message) p);


                //check if the content is plain text
                if (p.isMimeType("text/plain")) {
                    System.out.println((String)p.getContent());
                    content[ijk]=((String) p.getContent());
                    Log.v("content",content[ijk]);
                    System.out.println("content: "+p.getContent().toString());
                }
                //check if the content has attachment
                else if (p.isMimeType("multipart/*")) {

                    Multipart mp = (Multipart) p.getContent();
                    int count = mp.getCount();
                    for (int i = 0; i < count; i++)
                        writePart(mp.getBodyPart(i));
                }
                //check if the content is a nested message
                else if (p.isMimeType("message/rfc822")) {

                    writePart((Part) p.getContent());
                }

                else if (p.getContentType().contains("image/")) {
                    try {

                        File f = new File("image" + new Date().getTime() + ".jpg");
                        DataOutputStream output = new DataOutputStream(
                                new BufferedOutputStream(new FileOutputStream(f)));
                        com.sun.mail.util.BASE64DecoderStream test =
                                (com.sun.mail.util.BASE64DecoderStream) p
                                        .getContent();
                        byte[] buffer = new byte[1024 * 10];
                        int bytesRead;

                        while ((bytesRead = test.read(buffer)) != -1) {
                            output.write(buffer, 0, bytesRead);
                        }
                    } catch (Exception e) {
                        Log.e("Error in File","error in retrieving image");
                    }
                }
            } catch (Exception e) {

            }
        }

        public  void writeEnvelope(Message m) throws Exception {

            Address[] a;

            // FROM
            if ((a = m.getFrom()) != null) {
                for (int j = 0; j < a.length; j++)
                    System.out.println("FROM: " + a[j].toString());
            }

            // TO
            if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
                for (int j = 0; j < a.length; j++)
                    System.out.println("TO: " + a[j].toString());
            }

            // SUBJECT
            if (m.getSubject() != null){

                System.out.println( m.getSubject().toString());
                subject[ijk]=(String) m.getSubject();
            }


        }








   /*
   * This method checks for content-type
   * based on which, it processes and
   * fetches the content of the message
   */

   /*
   * This method would print FROM,TO and SUBJECT of the message
   */




    }
}




