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
import android.widget.EditText;
import android.widget.Toast;
import com.google.gson.Gson;

public class addSubActivity extends AppCompatActivity {

    EditText subject,toc,bc;
    Button save;
    SharedPreferences sp;
    Gson gson;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        subject=(EditText)findViewById(R.id.subedit);
        toc=(EditText)findViewById(R.id.tcedit);
        bc=(EditText)findViewById(R.id.cbedit);
        save=(Button)findViewById(R.id.b_save);
        //BunkManager BM=new BunkManager();
        sp=getSharedPreferences("bunkpref", Context.MODE_PRIVATE);
        editor=sp.edit();
        gson=new Gson();


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplication(),"saved Successfully",Toast.LENGTH_SHORT).show();
                if(subject.getText().toString()!= null && toc.getText().toString()!=null && bc.getText().toString()!=null){
                    int count=sp.getInt("nos_subject",0);
                    count++;
                    String js=gson.toJson(new bunkData(
                            subject.getText().toString()
                            ,Integer.parseInt(toc.getText().toString()),
                            Integer.parseInt(bc.getText().toString())
                    ));
                    editor.putString("object"+count,js);
                    editor.putInt("nos_subject",count);
                    editor.commit();


                    startActivity(new Intent(getApplicationContext(),BunkManager.class));
                }else{
                    Toast.makeText(getApplicationContext(),"please fill all the fields",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

}
