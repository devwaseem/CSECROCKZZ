package com.waseem.csecrockzz;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;
import com.google.gson.Gson;

import java.util.ArrayList;

public class BunkManager extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    int subjects;
    Gson gson;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    public Button plus,minus;
    ArrayList<bunkData> mydataset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bunk_manager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sp=getSharedPreferences("bunkpref", Context.MODE_PRIVATE);
        editor=sp.edit();
        gson=new Gson();
        mydataset=new ArrayList<bunkData>();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        plus=(Button)findViewById(R.id.bplus);
        minus=(Button)findViewById(R.id.bminus);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),addSubActivity.class));
            }
        });

        mRecyclerView=(RecyclerView)findViewById(R.id.myrv);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);



        /*String js1=gson.toJson(new bunkData("Maths",60,2));
        String js2=gson.toJson(new bunkData("Physics",45,4));
        String js3=gson.toJson(new bunkData("Chemistry",45,6));
        String js4=gson.toJson(new bunkData("English",45,1));

        editor.putString("object1",js1);
        editor.putString("object2",js2);
        editor.putString("object3",js3);
        editor.putString("object4",js4);

        editor.commit();
*/


        Toast.makeText(getApplicationContext(),sp.getInt("nos_subject",0)+"",Toast.LENGTH_SHORT).show();


        for(int i=1;i<=sp.getInt("nos_subject",0);i++){

        String json=sp.getString("object"+i,null);
            bunkData bd=gson.fromJson(json,bunkData.class);
            mydataset.add(bd);

        }



        //mydataset.add(new bunkData("Maths",60,2));
        //mydataset.add(new bunkData("Physics",45,4));
        //mydataset.add(new bunkData("Chemistry",45,6));
        mAdapter=new MyAdapter(mydataset);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();



    }
    public void putPrefs(String subj,String perc,String bc,String ba){
        SharedPreferences shp=getSharedPreferences("bunkpref", Context.MODE_PRIVATE);
        SharedPreferences.Editor seditor=sp.edit();
        seditor.putString("det_sub",subj);
        seditor.putString("det_perc",perc);
        seditor.putString("det_bc",bc);
        seditor.putString("det_ba",ba);
    }

}
