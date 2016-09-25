package com.waseem.csecrockzz;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;


import java.util.ArrayList;

public class BunkManager extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    int subjects;
  
    SharedPreferences sp;
    public Button plus,minus;
    ArrayList<bunkData> mydataset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bunk_manager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sp=getSharedPreferences("bunkpref", Context.MODE_PRIVATE);
        mydataset=new ArrayList<bunkData>();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        plus=(Button)findViewById(R.id.bplus);
        minus=(Button)findViewById(R.id.bminus);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mRecyclerView=(RecyclerView)findViewById(R.id.myrv);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        subjects=Integer.parseInt(sp.getString("nos_subject",""));
        for(int i=0;i<=subjects;i++){

        }


        mydataset.add(new bunkData("Maths",60,2));
        mydataset.add(new bunkData("Physics",45,4));
        mydataset.add(new bunkData("Chemistry",45,6));
        mAdapter=new MyAdapter(mydataset);
        mRecyclerView.setAdapter(mAdapter);

    }

}
