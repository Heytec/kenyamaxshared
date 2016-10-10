package com.oensa.johnmuchiri.kenyamax;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;

/**
 * Created by John Pc on 8/26/2015.
 */
public class AdapterEvent extends RecyclerView.Adapter<AdapterEvent.ViewHolderEvent>    {

    private ArrayList<EventFreeModel> listEvent=new ArrayList<>();
    private LayoutInflater layoutInflater;
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;


    public AdapterEvent(Context context) {
        layoutInflater = LayoutInflater.from(context);
        volleySingleton= VolleySingleton.getInstance();
        imageLoader=volleySingleton.getImageLoader();

    }
    public void setMovieList(ArrayList<EventFreeModel> listEvent){
        this.listEvent=listEvent;

        notifyItemRangeChanged(0, listEvent.size());
    }

    @Override
    public ViewHolderEvent onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.customkenyamovie, parent, false);
        ViewHolderEvent viewHolder = new ViewHolderEvent(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final AdapterEvent.ViewHolderEvent holder, int position) {
       EventFreeModel currentEvent=listEvent.get(position);
        holder.movieTitle.setText(currentEvent.getTitle());
        //holder.movieReleaseDate.setText(currentMovie.getYear());
        //holder.movieAudienceScore.setRating(currentMovie.getAudienceScore()/20.0F);
        AnimationUtils.animatepulse(holder);

        String urlThumnail=currentEvent.getPoster();
        if(urlThumnail!=null)
        {
            imageLoader.get(urlThumnail, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    holder.movieThumbnail.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        }


    }




    @Override
    public int getItemCount() {
        return listEvent.size();
    }



    static class ViewHolderEvent extends RecyclerView.ViewHolder {

        ImageView movieThumbnail;
        TextView movieTitle;
        TextView movieReleaseDate;
        RatingBar movieAudienceScore;

        public ViewHolderEvent(View itemView) {
            super(itemView);
            movieThumbnail = (ImageView) itemView.findViewById(R.id.movieThumbnail);
            movieTitle = (TextView) itemView.findViewById(R.id.movieTitle);
            movieReleaseDate = (TextView) itemView.findViewById(R.id.movieReleaseDate);
            // movieAudienceScore = (RatingBar) itemView.findViewById(R.id.movieAudienceScore);
        }
    }



    public EventFreeModel getEventKenya(int position){
        return (null !=listEvent ?listEvent.get(position):null);


    }


    public void setevents(ArrayList<EventFreeModel> listEvent){
        this.listEvent=listEvent;
        notifyDataSetChanged();
    }





}
