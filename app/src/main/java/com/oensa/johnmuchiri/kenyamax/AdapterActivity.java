package com.oensa.johnmuchiri.kenyamax;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by John Pc on 9/16/2015.
 */
public class AdapterActivity  extends RecyclerView.Adapter<AdapterActivity.MyViewHolder>{
    List<Information> data= Collections.emptyList();
    private LayoutInflater inflater;
    Context context;
    public AdapterActivity(Context context, List<Information> data){
        inflater=LayoutInflater.from(context);
        this.data=data;
        this.context=context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.custom_row, parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AdapterActivity.MyViewHolder holder, int position) {
        Information current=data.get(position);
        //holder.title.setText(current.title);
        holder.icon.setImageResource(current.iconId);
        AnimationUtils.animatestand(holder);


    }



    @Override
    public int getItemCount() {
        return data.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        //TextView title;
        ImageView icon;
        public MyViewHolder(View itemView) {
            super(itemView);
            //title= (TextView) itemView.findViewById(R.id.listText);
            icon= (ImageView) itemView.findViewById(R.id.listIcon);
            icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    switch (getPosition()+1) {

                        case 1:
                            Intent event = new Intent("android.intent.action.EVENTS");
                            context.startActivity(event);
                            break;


                        case 2:
                            Intent perfomanceart = new Intent("android.intent.action.PERFOMANCEARTS");
                            context.startActivity(perfomanceart);
                            break;


                        case 3:
                            Intent movies = new Intent("android.intent.action.MOVIES");
                            context.startActivity(movies);
                            break;
                    }


                }
            });
        }
    }













}
