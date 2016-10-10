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
 * Created by John Pc on 8/11/2015.
 */

public class Adapterupcomming extends RecyclerView.Adapter<Adapterupcomming.ViewHolderUpcoming> {
    private ArrayList<Movieup> listMovies=new ArrayList<>();
    private LayoutInflater layoutInflater;
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;

    public Adapterupcomming(Context context) {
        layoutInflater = LayoutInflater.from(context);
        volleySingleton= VolleySingleton.getInstance();
        imageLoader=volleySingleton.getImageLoader();

    }
    public void setMovieList(ArrayList<Movieup> listMovies){
        this.listMovies=listMovies;
        notifyItemRangeChanged(0, listMovies.size());
    }

    @Override
    public ViewHolderUpcoming onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_movie_box_office, parent, false);
       ViewHolderUpcoming  viewHolder = new ViewHolderUpcoming(view);
        return viewHolder;
    }



    public void setMovies(ArrayList<Movieup> listMovies){
        this.listMovies=listMovies;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final ViewHolderUpcoming holder, int position) {
        Movieup currentMovie=listMovies.get(position);
        holder.movieTitle.setText(currentMovie.getTitle());
        holder.movieReleaseDate.setText(currentMovie.getReleaseDateTheater().toString());
        AnimationUtils.animate(holder);
        //holder.movieAudienceScore.setRating(currentMovie.getAudienceScore()/20.0F);

        String urlThumnail=currentMovie.getUrlThumbnail();
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
        return listMovies.size();
    }

    static class ViewHolderUpcoming extends RecyclerView.ViewHolder {

        ImageView movieThumbnail;
        TextView movieTitle;
        TextView movieReleaseDate;
        RatingBar movieAudienceScore;

        public ViewHolderUpcoming(View itemView) {
            super(itemView);
            movieThumbnail = (ImageView) itemView.findViewById(R.id.movieThumbnail);
            movieTitle = (TextView) itemView.findViewById(R.id.movieTitle);
            movieReleaseDate = (TextView) itemView.findViewById(R.id.movieReleaseDate);
            // movieAudienceScore = (RatingBar) itemView.findViewById(R.id.movieAudienceScore);
        }
    }



    public Movieup getMovie(int position){
        return (null !=listMovies ?listMovies.get(position):null);


    }
}

