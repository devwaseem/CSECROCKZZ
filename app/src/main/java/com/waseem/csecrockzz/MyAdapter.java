package com.waseem.csecrockzz;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Waseem on 9/11/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
private ArrayList<bunkData> mDataset;
    public TextView subject,tc,bc,sb,marks,perc;
    public Button plus,minus;

    public class ViewHolder extends RecyclerView.ViewHolder{

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
        perc.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                plus.setVisibility(View.VISIBLE);
                minus.setVisibility(View.VISIBLE);


                notifyDataSetChanged();
                return true;
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {
    subject.setText(mDataset.get(position).getSubject().toString());
        tc.setText(mDataset.get(position).getTotClasses().toString());
        bc.setText(mDataset.get(position).getBunked().toString());
        sb.setText(mDataset.get(position).getSafeBunks().toString());
        marks.setText(mDataset.get(position).getMarks().toString());
        perc.setText(mDataset.get(position).getPerc().toString());
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                (mDataset.get(position).bunked)++;

            }
        });


    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
