package com.waseem.csecrockzz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Waseem on 9/11/2016.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
private ArrayList<bunkData> mDataset;
    public TextView subject,tc,bc,sb,marks,perc;
    public Button plus,minus;
    SharedPreferences sp;
    SharedPreferences.Editor edit;
    Context context;
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ViewHolder(View v){
        super(v);
            subject=(TextView)v.findViewById(R.id.subject);
            tc=(TextView)v.findViewById(R.id.tc);
            bc=(TextView)v.findViewById(R.id.Nb);
            sb=(TextView)v.findViewById(R.id.sb);
            marks=(TextView)v.findViewById(R.id.mo);
            perc=(TextView)v.findViewById(R.id.intperc);
            plus=(Button)v.findViewById(R.id.bplus);
            minus=(Button)v.findViewById(R.id.bminus);
            context=v.getContext();


        }

        @Override
        public void onClick(View view) {
            Log.d("holder","onclick"+getPosition());
        }
    }
    public void add(int position,String item){
       // mDataset.add(position,item);
        notifyItemInserted(position);
    }
    public void remove(String item){
        int position=mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    public MyAdapter(ArrayList<bunkData> myDataset){
        mDataset=myDataset;

    }


    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        ViewHolder vh= new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {



    subject.setText(mDataset.get(position).getSubject());
        tc.setText(mDataset.get(position).getTotClasses());
        bc.setText(mDataset.get(position).getBunked());
        sb.setText(mDataset.get(position).getSafeBunks());
        marks.setText(mDataset.get(position).getMarks())
        ;
        perc.setText(mDataset.get(position).getPerc());


        perc.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                BunkManager bm=new BunkManager();
                bm.putPrefs(
                        mDataset.get(position).getSubject(),
                        mDataset.get(position).getPerc().toString(),
                        mDataset.get(position).getBunked(),
                        mDataset.get(position).getSafeBunks()
                );
                Toast.makeText(context,"its"+mDataset.get(position).getSubject()+"",Toast.LENGTH_SHORT);

                Log.d("BM", "onLongClick: "+"its"+mDataset.get(position).getSubject()+"");
                context.startActivity(new Intent(context,BmDetailedView.class));

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }



}
