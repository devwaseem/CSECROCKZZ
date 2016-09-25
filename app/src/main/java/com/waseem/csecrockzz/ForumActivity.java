package com.waseem.csecrockzz;

import android.app.DownloadManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class ForumActivity extends AppCompatActivity {
WebView forum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        forum=(WebView)findViewById(R.id.forum);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forum.reload();
                Snackbar.make(view, "Refreshing...", Snackbar.LENGTH_LONG)
                        .show();
            }
        });
        forum.loadUrl("http://bsaucse.boards.net/");
        forum.getSettings().setJavaScriptEnabled(true);
        forum.getSettings().setAllowContentAccess(true);
        forum.getSettings().setAllowFileAccess(true);
        forum.getSettings().setAllowUniversalAccessFromFileURLs(true);
        String newUA= "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.4) Gecko/20100101 Firefox/4.0";
        forum.getSettings().setUserAgentString(newUA);
        forum.setWebViewClient(new WebViewClient());
        forum.setVerticalScrollBarEnabled(true);
        forum.setHorizontalScrollBarEnabled(true);
        forum.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String s, String s1, String s2, String s3, long l) {
                DownloadManager.Request request=new DownloadManager.Request(Uri.parse(s));
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"forumdownload");
                DownloadManager dm=(DownloadManager)getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(request);
                Intent intent=new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                Toast.makeText(getApplicationContext(),"Downloading....",Toast.LENGTH_LONG).show();
            }
        });

    }
    @Override
    public void onBackPressed() {
        if (forum.canGoBack()) {
            forum.goBack();
        } else {
            super.onBackPressed();
        }
    }

}
