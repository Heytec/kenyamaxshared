package com.oensa.johnmuchiri.kenyamax;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.oensa.johnmuchiri.kenyamax.Keys.EndpointBoxOffice.KEY_AUDIENCE_SCORE;
import static com.oensa.johnmuchiri.kenyamax.Keys.EndpointBoxOffice.KEY_ID;
import static com.oensa.johnmuchiri.kenyamax.Keys.EndpointBoxOffice.KEY_MOVIES;
import static com.oensa.johnmuchiri.kenyamax.Keys.EndpointBoxOffice.KEY_POSTERS;
import static com.oensa.johnmuchiri.kenyamax.Keys.EndpointBoxOffice.KEY_RATINGS;
import static com.oensa.johnmuchiri.kenyamax.Keys.EndpointBoxOffice.KEY_RELEASE_DATES;
import static com.oensa.johnmuchiri.kenyamax.Keys.EndpointBoxOffice.KEY_SYNOPSIS;
import static com.oensa.johnmuchiri.kenyamax.Keys.EndpointBoxOffice.KEY_THEATER;
import static com.oensa.johnmuchiri.kenyamax.Keys.EndpointBoxOffice.KEY_THUMBNAIL;
import static com.oensa.johnmuchiri.kenyamax.Keys.EndpointBoxOffice.KEY_TITLE;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentUpcoming#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentUpcoming extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static final String URL_ROTTEN_TOMATOES_Upcomming="http://api.rottentomatoes.com/api/public/v1.0/lists/movies/upcoming.json";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static final String STATE_MOVIES = "state_movies";
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private RequestQueue requestQueue;
    private ArrayList<Movieup> listMovies = new ArrayList<>();
    private DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
    private Adapterupcomming adapterupcomming;
    private RecyclerView listMovieHits;
    public static final String MOVIE_TRANSFER="MOVIE_TRANSFER";
    private TextView textVolleyError;
    ProgressBar progressBar;
    Button button;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentUpcoming.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentUpcoming newInstance(String param1, String param2) {
        FragmentUpcoming fragment = new FragmentUpcoming();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentUpcoming() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
    }

   /*public static String getRequestUrl(int limit) {

        return URL_BOX_OFFICE
                + URL_CHAR_QUESTION
                + URL_PARAM_API_KEY + MyApplication.API_KEY_ROTTEN_TOMATOES
                + URL_CHAR_AMEPERSAND
                + URL_PARAM_LIMIT + limit;
    }*/


    public static String getRequestUrl(int limit){
        return URL_ROTTEN_TOMATOES_Upcomming+"?apikey="+ MyApplication.API_KEY_ROTTEN_TOMATOES+"&limit="+limit;
    }



    private void sendJsonRequest() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                getRequestUrl(30),
                (String)null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        textVolleyError.setVisibility(View.GONE);
                        button.setVisibility(View.GONE);
                       progressBar.setVisibility(View.INVISIBLE);
                        listMovies = parseJSONResponse(response);
                        adapterupcomming.setMovieList(listMovies);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handleVolleyError(error);
            }
        });
        requestQueue.add(request);
    }

    private void handleVolleyError(VolleyError error){
        textVolleyError.setVisibility(View.VISIBLE);
        //progressBar.setVisibility(View.GONE);
        progressBar.setVisibility(View.INVISIBLE);
        button.setVisibility(View.VISIBLE);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                textVolleyError.setVisibility(View.INVISIBLE);
                sendJsonRequest();
            }
        });




        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
            textVolleyError.setText(R.string.error_timeout);

        } else if (error instanceof AuthFailureError) {
            textVolleyError.setText(R.string.error_auth_failure);
            //TODO
        } else if (error instanceof ServerError) {
            textVolleyError.setText(R.string.error_auth_failure);
            //TODO
        } else if (error instanceof NetworkError) {
            textVolleyError.setText(R.string.error_network);
            //TODO
        } else if (error instanceof ParseError) {
            textVolleyError.setText(R.string.error_parser);
            //TODO
        }


    }
    private ArrayList<Movieup> parseJSONResponse(JSONObject response) {
        ArrayList<Movieup> listMovies = new ArrayList<>();
        if (response != null && response.length() > 0) {

            try {

                JSONArray arrayMovies = response.getJSONArray(KEY_MOVIES);
                for (int i = 0; i < arrayMovies.length(); i++) {
                    long id = -1;
                    String title = Constants.NA;
                    String releaseDate = Constants.NA;
                    int audienceScore = -1;
                    String synopsis = Constants.NA;
                    String urlThumbnail = Constants.NA;
                    JSONObject currentMovie = arrayMovies.getJSONObject(i);
                    //get the id of the current movie
                    if (currentMovie.has(KEY_ID)
                            && !currentMovie.isNull(KEY_ID)) {
                        id = currentMovie.getLong(KEY_ID);
                    }
                    //get the title of the current movie
                    if (currentMovie.has(KEY_TITLE)
                            && !currentMovie.isNull(KEY_TITLE)) {
                        title = currentMovie.getString(KEY_TITLE);
                    }

                    //get the date in theaters for the current movie
                    if (currentMovie.has(KEY_RELEASE_DATES)
                            && !currentMovie.isNull(KEY_RELEASE_DATES)) {
                        JSONObject objectReleaseDates = currentMovie.getJSONObject(KEY_RELEASE_DATES);

                        if (objectReleaseDates != null
                                && objectReleaseDates.has(KEY_THEATER)
                                && !objectReleaseDates.isNull(KEY_THEATER)) {
                            releaseDate = objectReleaseDates.getString(KEY_THEATER);
                        }
                    }

                    //get the audience score for the current movie
                    JSONObject objectRatings = currentMovie.getJSONObject(KEY_RATINGS);
                    if (objectRatings.has(KEY_AUDIENCE_SCORE)
                            && !currentMovie.isNull(KEY_RATINGS)) {
                        if (objectRatings != null
                                && objectRatings.has(KEY_AUDIENCE_SCORE)
                                && !objectRatings.isNull(KEY_AUDIENCE_SCORE)) {
                            audienceScore = objectRatings.getInt(KEY_AUDIENCE_SCORE);
                        }
                    }

                    // get the synopsis of the current movie
                    if (currentMovie.has(KEY_SYNOPSIS)
                            && !currentMovie.isNull(KEY_SYNOPSIS)) {
                        synopsis = currentMovie.getString(KEY_SYNOPSIS);
                    }

                    if (currentMovie.has(KEY_POSTERS)
                            && !currentMovie.isNull(KEY_POSTERS)) {
                        JSONObject objectPosters = currentMovie.getJSONObject(KEY_POSTERS);

                        if (objectPosters != null
                                && objectPosters.has(KEY_THUMBNAIL)
                                && !objectPosters.isNull(KEY_THUMBNAIL)) {
                            urlThumbnail = objectPosters.getString(KEY_THUMBNAIL);
                        }
                    }
                    Movieup movie = new Movieup();
                    movie.setId(id);
                    movie.setTitle(title);
                    Date date = null;
                    try {
                        date = dateFormat.parse(releaseDate);
                    } catch (ParseException e) {}
                    movie.setReleaseDateTheater(date);
                    movie.setAudienceScore(audienceScore);
                    movie.setSynopsis(synopsis);
                    movie.setUrlThumbnail(urlThumbnail);

                    if(id!=-1 && !title.equals(Constants.NA)) {
                        listMovies.add(movie);
                    }
                }

            } catch (JSONException e) {

            }
           //5 L.t(getActivity(), listMovies.size() + " rows fetched");
        }
        return listMovies;
    }






























    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_upcoming, container, false);
        listMovieHits = (RecyclerView) view.findViewById(R.id.listMovieHits);
        listMovieHits.setLayoutManager(new LinearLayoutManager(getActivity()));
        button = (Button)view.findViewById(R.id.button);
        button.setVisibility(view.INVISIBLE);
        textVolleyError= (TextView) view.findViewById(R.id.textVolleyError);
        progressBar=(ProgressBar)view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
       adapterupcomming = new Adapterupcomming(getActivity());
        listMovieHits.setAdapter(adapterupcomming);
        listMovieHits.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), listMovieHits, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent intent = new Intent(getActivity(),Moviedetail.class);
                intent.putExtra(MOVIE_TRANSFER,adapterupcomming.getMovie(position));
                startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        if(savedInstanceState!=null)
        {

            progressBar.setVisibility(View.INVISIBLE);
            button.setVisibility(View.GONE);
            listMovies=savedInstanceState.getParcelableArrayList(STATE_MOVIES);
            adapterupcomming.setMovies(listMovies);
        }
        else{
            sendJsonRequest();
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //save the movie list to a parcelable prior to rotation or configuration change
        outState.putParcelableArrayList(STATE_MOVIES, listMovies);
    }

    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }
    }


}
