package com.oensa.johnmuchiri.kenyamax;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by John Pc on 10/1/2015.
 */
public class AdapterDrawerseri extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    List<Information> data= Collections.emptyList();
    private SparseBooleanArray selectedItems;
    private static final int TYPE_HEADER=0;
    private static final int TYPE_ITEM=1;
    RecyclerView mRecyclerView;
    private int selectedItem = 0;
    private LayoutInflater inflater;
    List<Integer> pos;
    Context context;
    public AdapterDrawerseri(Context context, List<Information> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    public void delete(int position){
        data.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_HEADER){
            View view=inflater.inflate(R.layout.drawer_headerseri, parent,false);
            HeaderHolder holder=new HeaderHolder(view);
            return holder;
        }
        else{
            View view=inflater.inflate(R.layout.item_drawerseri, parent,false);
            ItemHolder holder=new ItemHolder(view);
            return holder;
        }

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);




    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return TYPE_HEADER;
        }
        else {
            return TYPE_ITEM;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof HeaderHolder ){

        }
        else{

            ItemHolder itemHolder= (ItemHolder) holder;
            Information current=data.get(position-1);
            itemHolder.title.setText(current.title);
            itemHolder.icon.setImageResource(current.iconId);
            itemHolder.itemView.setSelected(selectedItem == position);



        }

    }
    @Override
    public int getItemCount() {
        return data.size()+1;
    }

    class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView icon;


        public ItemHolder(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.listText);
            icon= (ImageView) itemView.findViewById(R.id.listIcon);


        }

        @Override
        public void onClick(View v) {

            switch (getPosition()){

                case 1:

                    break;


                case 2:

                    break;


                case 3:

                    break;

                case 4:
                    Intent start = new Intent("com.oensa.johnmuchiri.kenyamax.MAINACTIVITY");
                    context.startActivity(start);
                    break;

                case 5:
                    start = new Intent("\"android.intent.action.MAIN");
                    context.startActivity(start);
                    break;

            }




            //notifyItemChanged(selectedItem);
            //selectedItem = mRecyclerView.getChildPosition(v);
            //notifyItemChanged(selectedItem);




        }
    }
    class HeaderHolder extends RecyclerView.ViewHolder {

        public HeaderHolder(View itemView) {
            super(itemView);

        }
    }
}
