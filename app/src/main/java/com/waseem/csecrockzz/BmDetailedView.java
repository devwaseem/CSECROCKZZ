package com.waseem.csecrockzz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BmDetailedView extends AppCompatActivity {
SharedPreferences sp;
    SharedPreferences.Editor edit;
    TextView subs,perc,bc,ba;
        Button plus,minus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bm_detailed_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Updating...", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                startActivity(new Intent(getApplicationContext(),BunkManager.class));
            }
        });
        subs=(TextView)findViewById(R.id.dtsub);
        perc=(TextView)findViewById(R.id.dtperc);
        bc=(TextView)findViewById(R.id.dtbc);
        ba=(TextView)findViewById(R.id.dtba);

        SharedPreferences sp=getSharedPreferences("bunkpref", Context.MODE_PRIVATE);

        plus=(Button)findViewById(R.id.dtplus);
        minus=(Button)findViewById(R.id.dtminus);

        subs.setText(sp.getString("det_subject","ERROR"));
        perc.setText(sp.getString("det_perc","ERROR"));
        bc.setText(sp.getString("det_bc","ERROR"));
        ba.setText(sp.getString("det_ba","ERROR"));
    }

}
